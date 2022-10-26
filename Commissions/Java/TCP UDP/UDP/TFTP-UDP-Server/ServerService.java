import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class ServerService extends Thread{
    private static InetAddress clientAddress;
    private int clientPort;
    private DatagramSocket dataSocket;
    private DatagramPacket dataPacket;

    public ServerService(InetAddress address, int port) throws SocketException {
        super();
        clientAddress = address;
        clientPort = port;
        dataSocket = new DatagramSocket();
    }

    public void run(){
        try {
            startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startService() throws IOException {
        //First packet from new Thread (Needed to let the client get the new port)
        sendAck();
        do {
            consoleWrite("*** Thread ClientPort = " +clientPort + " ***");

            byte[] buf = new byte[512];

            consoleWrite("Waiting Client...");
            receiveDataPacket(buf,buf.length);

            String stringOption = new String(buf, 0 ,dataPacket.getLength());
            int option = Integer.parseInt(stringOption);

            switch (option) {
                case 1:
                    //Confirmation, server is ready to execute option 1
                    sendAck();

                    consoleWrite("Waiting Client File");

                    String name = getFileName();

                    consoleWrite("Store, File name: " + name);

                    storeFile(name);
                    break;
                case 2:
                    //Confirmation, server is ready to execute option 2
                    sendAck();

                    consoleWrite("Waiting Client File Name");

                    String fileName = getFileName();

                    consoleWrite("Retrieve, File Name: " +fileName);

                    if(checkIfFileExists(fileName)) {
                        sendPacketOfFileExistence(true);
                        sendFile(fileName);
                    } else {
                        sendPacketOfFileExistence(false);
                        consoleWrite("File not found");
                    }

                    break;
                case 3:
                    //Confirmation, server is ready to execute option 3
                    sendAck();
                    consoleWrite("*** Closing connection with Client (Port = " + clientPort + ") ***");
                    return;
            }
        } while(true);
    }

    private String getFileName() throws IOException {
        String fileName;

        byte[] buf = new byte[512];

        receiveDataPacket(buf,buf.length);

        //Confirmation, fileName received
        sendAck();

        fileName = (new String(dataPacket.getData()).trim());
        return fileName;
    }

    private boolean checkIfFileExists(String fileName){
        Path path = Paths.get(fileName);
        return Files.exists(path);
    }

    private void sendPacketOfFileExistence(boolean exists) throws IOException {
        byte[] buffer = Boolean.toString(exists).getBytes();
        sendNewDataPacket(buffer,buffer.length);
    }

    private void sendFile(String fileName) throws IOException {

        File file = new File(fileName);

        consoleWrite("*** Start sending file ***\n");

        byte[] buf;

        buf = Files.readAllBytes((file.toPath()));
        sendNewDataPacket(buf,buf.length);

        //Waiting confirmation of file received
        getAck();

        consoleWrite("*** File sent successfully ***\n");

    }

    private void storeFile(String fileName) throws IOException {

        FileOutputStream fos = new FileOutputStream(fileName);

        byte[] buf = new byte[512];
        receiveDataPacket(buf,buf.length);

        fos.write(dataPacket.getData(),dataPacket.getOffset(),dataPacket.getLength());

        //Sending confirmation of file received
        sendAck();

        consoleWrite("*** File successfully received ***");
    }

    private void receiveDataPacket(byte[] buffer, int bufferLength) throws IOException {
        dataPacket = new DatagramPacket(buffer,bufferLength);
        dataSocket.receive(dataPacket);
    }

    private void sendNewDataPacket(byte[] buffer, int bufferLength) throws IOException {
        dataPacket = new DatagramPacket(buffer, bufferLength, clientAddress,clientPort);
        dataSocket.send(dataPacket);
    }

    private void getAck() throws IOException {
        DatagramPacket response = new DatagramPacket(new byte[1], 1);
        dataSocket.receive(response);
    }

    private void sendAck() throws IOException {
        DatagramPacket sendResponse = new DatagramPacket(new byte[1],1,clientAddress, clientPort);
        dataSocket.send(sendResponse);
    }

    private void consoleWrite(String string){
        System.out.println(string);
    }
}
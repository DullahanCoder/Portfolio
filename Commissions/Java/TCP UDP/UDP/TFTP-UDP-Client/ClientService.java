import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.util.Scanner;

public class ClientService {
    private InetAddress address;
    private int serverPort;
    private DatagramSocket dataSocket;
    private DatagramPacket dataPacket;
    private Scanner scanner;

    public ClientService(InetAddress address, int serverPort){
        this.address = address;
        this.serverPort = serverPort;
    }

    public void startConnection() {
        try {
            setUpDataSocket();

            connectToServer();

            consoleWrite("*** Connected ***\n");
            startService();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpDataSocket() throws SocketException {
        dataSocket = new DatagramSocket();
        dataSocket.setSoTimeout(3000);
    }

    private void connectToServer() throws IOException {
        consoleWrite("Connection attempt...");

        //Packet to send a comunication request to server
        DatagramPacket request = new DatagramPacket(new byte[1], 1, address, serverPort);
        dataSocket.send(request);

        //Response from server
        DatagramPacket response = new DatagramPacket(new byte[1], 1);
        dataSocket.receive(response);

        //Change communication port
        serverPort = response.getPort();
    }

    private void startService() throws IOException {
        int option;
        boolean run = true;

        do {
            consoleWrite("Select Option:\n1- Store File\n2- Retrieve File\n3- Exit\n\nWaiting Input...");
            String input = getConsoleInput();

            if (validateInput(input)) {
                option = Integer.parseInt(input);
                run = selectService(option);

            } else {
                consoleWrite("\nOption invalid\n");
            }
        } while (run);
    }

    private boolean validateInput(String input){
        try{
            int inputInt = Integer.parseInt(input);

            if(inputInt > 3 || inputInt < 1){
                return false;
            }

            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    private boolean selectService(int option) throws IOException {

        String sending = ""+option;
        sendNewDataPacket(sending.getBytes(), sending.getBytes().length);

        //Waiting confirmation of "option" successfully received
        getAck();

        switch (option) {
            case 1:
                consoleWrite("\nWrite the name of the file to store (if necessary with the path):");

                String filePath = getConsoleInput();
                File file = getFile(filePath);

                sendFile(file);
                break;
            case 2:
                consoleWrite("\nWrite the name of the file to retrieve:");
                String fileName = getConsoleInput();

                requestFile(fileName);
                break;
            case 3:
                consoleWrite("*** Closing Client and Connection... ***");
                return false;
        }
        return true;
    }

    private File getFile(String filePath){
        File file = new File(filePath);

        consoleWrite("\nFile dimension: " + file.length() + " byte(s)");
        consoleWrite("Path: " +file.getAbsolutePath()+"\n");

        return file;
    }

    private void sendFileName(String fileName) throws IOException {
        byte[] buffer = fileName.getBytes();
        sendNewDataPacket(buffer,buffer.length);

        //Waiting confirm of file name received
        getAck();
    }

    private void sendFile(File file) throws IOException {

        sendFileName(file.getName());

        consoleWrite("*** Start sending file ***\n");

        byte[] buf;

        buf = Files.readAllBytes((file.toPath()));
        sendNewDataPacket(buf,buf.length);

        //Waiting confirm of file received
        getAck();

        System.out.println("*** File sent successfully ***\n");

    }

    private void requestFile(String fileName) throws IOException {
        sendFileName(fileName);

        if(fileExistsInServer()) {
            receiveFile(fileName);
        } else {
            consoleWrite("File not found inside the server\n");
        }
    }

    private boolean fileExistsInServer() throws IOException {
        byte[] buf = new byte[512];
        receiveDataPacket(buf,buf.length);

        String booleanString = new String(buf, 0 ,dataPacket.getLength());
        return Boolean.parseBoolean(booleanString);
    }

    private void receiveFile(String fileName) throws IOException {

        byte[] buf = new byte[512];

        receiveDataPacket(buf,buf.length);

        FileOutputStream fos = new FileOutputStream(fileName);

        fos.write(dataPacket.getData(), dataPacket.getOffset(),dataPacket.getLength());

        //Send Confirm of file received
        sendAck();

        consoleWrite("File Received\n");
    }

    private String getConsoleInput(){
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void consoleWrite(String string){
        System.out.println(string);
    }

    private void receiveDataPacket(byte[] buffer, int bufferLength) throws IOException {
        dataPacket = new DatagramPacket(buffer,bufferLength);
        dataSocket.receive(dataPacket);
    }

    private void sendNewDataPacket(byte[] buffer, int bufferLength) throws IOException {
        dataPacket = new DatagramPacket(buffer, bufferLength, address,serverPort);
        dataSocket.send(dataPacket);
    }

    private void getAck() throws IOException {
        DatagramPacket response = new DatagramPacket(new byte[1], 1);
        dataSocket.receive(response);
    }

    private void sendAck() throws IOException {
        DatagramPacket sendResponse = new DatagramPacket(new byte[1],1,address, serverPort);
        dataSocket.send(sendResponse);
    }
}

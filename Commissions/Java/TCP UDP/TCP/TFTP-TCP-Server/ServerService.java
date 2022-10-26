package tftp_server;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerService extends Thread{

    private Socket clientSocket;

    private DataOutputStream dataOutput;
    private DataInputStream dataInput;


    public ServerService(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;

        consoleWrite("*** Client connected (Port = " + clientSocket.getPort() + ") ***");

        dataInput = new DataInputStream(clientSocket.getInputStream());
        dataOutput = new DataOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            startService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startService() throws IOException {
        do {
            int option = dataInput.readInt();

            switch (option) {
                case 1:
                    consoleWrite("Waiting Client File");

                    receiveFile();

                    break;
                case 2:

                    consoleWrite("Waiting Client File Name");

                    String fileName = dataInput.readUTF();

                    consoleWrite("Retrieve, File Name: " + fileName);

                    if(checkIfFileExists(fileName)) {
                        sendPacketOfFileExistence(true);
                        sendFile(fileName);
                    } else {
                        sendPacketOfFileExistence(false);
                        consoleWrite("File not found");
                    }

                    break;
                case 3:
                    stopConnection();
                    consoleWrite("*** Connection with Client closed (Port = " + clientSocket.getPort() + ") ***");
                    return;
            }
        } while(true);
    }

    private boolean checkIfFileExists(String fileName){
        Path path = Paths.get(fileName);
        return Files.exists(path);
    }

    private void sendPacketOfFileExistence(boolean exists) throws  IOException{
        dataOutput.writeBoolean(exists);
    }

    private void sendFile(String fileName) throws IOException {
        File file = new File(fileName);
        sendFileSize(file);
        FileInputStream fileInputStream = new FileInputStream(file);

        int bytes;
        //break file into chunks of 512 bytes
        byte[] buffer = new byte[512]; //TO CHECK - IF NOT LIMITED 1024/2048/4096
        //read up to 512 bytes of the file input stream
        while((bytes=fileInputStream.read(buffer))!=-1) {
            dataOutput.write(buffer,0,bytes);
            dataOutput.flush();
        }
        fileInputStream.close();

    }

    private void sendFileName(String fileName) throws  IOException{
        dataOutput.writeUTF(fileName);

    }

    private void sendFileSize(File file) throws  IOException{
        dataOutput.writeLong(file.length());
    }

    private void receiveFile() throws IOException{
        int bytes;
        String fileName = dataInput.readUTF();//read file name
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInput.readLong(); //read file size
        byte[] buffer = new byte[512];
        //if the file size is less than 512 bytes then use as length to read its size and not the buffer one (512)
        while(size > 0 && (bytes = dataInput.read(buffer,0,(int)Math.min(buffer.length,size))) != -1){
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
    }

    private void consoleWrite(String string){
        System.out.println(string);
    }

    public void stopConnection() throws IOException {
        dataOutput.close();
        dataInput.close();
        clientSocket.close();
    }

}

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientService {

    private Socket clientSocket;

    private DataInputStream dataInput;
    private DataOutputStream dataOutput;

    private String ip;
    private int port;

    public ClientService(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void startConnection() throws IOException {
        consoleWrite("Connection attempt...");

        clientSocket = new Socket(ip, port);

        consoleWrite("*** Connected ***");

        //Assign to dataInput and dataOutput the client input and output streams
        dataInput = new DataInputStream(clientSocket.getInputStream());
        dataOutput = new DataOutputStream(clientSocket.getOutputStream());

        startService();
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
            return inputInt <= 3 && inputInt >= 1;
        } catch (NumberFormatException e){
            return false;
        }
    }

    private boolean selectService(int option) throws IOException {

        dataOutput.writeInt(option);

        switch (option) {
            case 1:
                consoleWrite("\nWrite the name of the file to store (if necessary with the path):");
                String filePath = getConsoleInput();

                File file = getFile(filePath);

                sendFileName(filePath);
                sendFileSize(file);
                sendFile(file);
                break;
            case 2:
                consoleWrite("\nWrite the name of the file to retrieve:");
                String fileName = getConsoleInput();

                requestFile(fileName);

                break;
            case 3:
                consoleWrite("*** Closing Client and Connection... ***");
                stopConnection();
                return false;
        }
        return true;
    }

    private File getFile(String filePath){
        File file = new File(filePath);

        consoleWrite("File dimension: " + file.length() + " byte(s)");
        consoleWrite("Path: " +file.getAbsolutePath());

        return file;
    }

    private void sendFileName(String fileName) throws  IOException{
        dataOutput.writeUTF(fileName);
    }

    private void sendFileSize(File file) throws  IOException{
        dataOutput.writeLong(file.length());
    }

    private void sendFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);

        int bytes;
        byte[] buffer = new byte[512];
        //read up to 512 bytes of the file input stream, in this way we break the file in chunks of 512 bytes
        while((bytes = fileInputStream.read(buffer)) != - 1) {
            dataOutput.write(buffer,0,bytes);
            dataOutput.flush();
        }
        fileInputStream.close();
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
        return dataInput.readBoolean();
    }


    private void receiveFile(String fileName) throws IOException{
        int bytes;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        //read file size
        long size = dataInput.readLong();
        byte[] buffer = new byte[512];
        //if the file size is less than 512 bytes, then, use as length to read the input the file size and not the buffer size (512 bytes)
        while(size > 0 && (bytes = dataInput.read(buffer,0,(int)Math.min(buffer.length,size))) != -1){
            fileOutputStream.write(buffer,0,bytes);
            //decreasing the size of the file with the bytes length read, this allow the while to stop once the file is fully ridden
            size -= bytes;
        }
        fileOutputStream.close();
    }

    private void stopConnection() throws IOException {
        dataInput.close();
        dataOutput.close();
        clientSocket.close();
    }

    private String getConsoleInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void consoleWrite(String string){
        System.out.println(string);
    }
}

package tftp_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainTCPServer {

    private static ServerSocket serverSocket = null;

    public static void main(String[] args) throws IOException
    {
        int port = 9876;
        startServer(port);
        do {
            System.out.println("Waiting new Client");
            Socket clientSocket = serverSocket.accept();
            ServerService service = new ServerService(clientSocket);
            service.start();
        } while(true);

        //closeServer();
    }

    private static void startServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    //private static void closeServer() throws IOException {
    //    serverSocket.close();
    //}

}

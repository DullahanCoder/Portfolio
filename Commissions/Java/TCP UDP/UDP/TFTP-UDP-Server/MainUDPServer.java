import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MainUDPServer {

    public static void main(String[] args) throws IOException
    {
        startServer();
    }

    private static void startServer() throws SocketException {
        InetAddress clientAddress;
        int port = 9876;
        int clientPort;
        DatagramSocket dataSocket = new DatagramSocket(port);

        //Not efficient, ports are limited
        //Everytime a client connect to server the server use a new port to continue the communication
        do {
            try {
                System.out.println("Server waiting new client on port: " + port);

                //Connection request from client
                DatagramPacket request = new DatagramPacket(new byte[1], 1);
                dataSocket.receive(request);

                clientAddress = request.getAddress();
                clientPort = request.getPort();

                //Start a parallel Thread with the client information
                new ServerService( clientAddress, clientPort).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(true);
    }
}


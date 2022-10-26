import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class MainUDPClient {

    public static void main(String[] args) throws IOException{
        int port = 9876;

        System.out.println("Insert Client Address or type $$$ to use default (127.0.0.1)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String addressString = input.equals("$$$") ? "127.0.0.1" : input;
        InetAddress address = InetAddress.getByName(addressString);

        ClientService service = new ClientService(address, port);
        service.startConnection();
    }
}

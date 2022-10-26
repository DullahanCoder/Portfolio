import java.io.IOException;
import java.util.Scanner;

public class MainTCPClient {

    public static void main(String[] args) throws IOException
    {
        int port = 9876;

        System.out.println("Insert Client Address or type $$$ to use default (127.0.0.1)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String address = input.equals("$$$") ? "127.0.0.1" : input;

        ClientService service = new ClientService(address, port);
        service.startConnection();
    }
}
import java.net.*;
import java.io.*;

public class DNSServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(3000);
        System.out.println("Server is running on port 3000...");

        Socket client = server.accept();  // Accept one client

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        // Read message from client
        String message = in.readLine();

        // Reverse the message
        String reversed = new StringBuilder(message).reverse().toString();

        // Send reversed message back
        out.println(reversed);

        // Close connections
        in.close();
        out.close();
        client.close();
        server.close();
    }
  }

import java.net.*;
import java.io.*;
import java.util.*;

public class DNSClient {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        Socket s = new Socket(InetAddress.getLocalHost(), 3000);

        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // Send text to server
        out.println(text);

        // Receive reversed text
        String reversed = in.readLine();
        System.out.println("Reversed Text: " + reversed);

        // Close connections
        in.close();
        out.close();
        s.close();
        sc.close();
    }
}

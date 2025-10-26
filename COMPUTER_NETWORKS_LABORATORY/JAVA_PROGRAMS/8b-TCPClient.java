import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 9876);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter a message: ");
            String msg = user.readLine();

            out.println(msg);
            String reply = in.readLine();

            System.out.println("From Server: " + reply);
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

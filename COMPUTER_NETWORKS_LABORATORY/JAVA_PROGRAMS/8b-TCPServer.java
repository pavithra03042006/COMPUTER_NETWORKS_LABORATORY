import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9876);
            System.out.println("Server started... waiting for client...");

            Socket s = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            String msg = in.readLine();
            String rev = new StringBuilder(msg).reverse().toString();

            System.out.println("Reversed Message: " + rev);
            out.println(rev);

            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

import java.net.*;
import java.io.*;
import java.util.*;

public class CHATServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(3333);
            Socket s = ss.accept();

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);

            System.out.println("Connection established.");
            System.out.println("Type 'stop' to end the chat.");

            String clientMsg = "", serverMsg = "";

            do {
                clientMsg = din.readUTF();
                System.out.println("Client: " + clientMsg);

                System.out.print("Server: ");
                serverMsg = sc.nextLine();
                dout.writeUTF(serverMsg);
                dout.flush();

            } while (!serverMsg.equalsIgnoreCase("stop"));

            din.close();
            dout.close();
            s.close();
            ss.close();

            System.out.println("Connection closed.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

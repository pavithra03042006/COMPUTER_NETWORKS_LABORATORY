import java.net.*;
import java.io.*;
import java.util.*;

public class CHATClient{
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 3333);

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);

            System.out.println("Type 'stop' to end the chat.");

            String clientMsg = "", serverMsg = "";

            do {
                System.out.print("Client: ");
                clientMsg = sc.nextLine();
                dout.writeUTF(clientMsg);
                dout.flush();

                serverMsg = din.readUTF();
                System.out.println("Server: " + serverMsg + "\n");

            } while (!clientMsg.equalsIgnoreCase("stop"));

            din.close();
            dout.close();
            s.close();

            System.out.println("Connection closed.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

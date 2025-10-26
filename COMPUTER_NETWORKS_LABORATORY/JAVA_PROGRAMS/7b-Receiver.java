import java.io.*;
import java.net.*;

public class Receiver {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(2004);
            System.out.println("Waiting for connection...");
            Socket socket = server.accept();
            System.out.println("Connected!");

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject("connected.");

            String packet, data = "";
            int seq = 0, i = 0;

            while (true) {
                packet = (String) in.readObject();
                if (packet.equals("end")) break;

                if (Integer.parseInt(packet.substring(0, 1)) == seq) {
                    data += packet.substring(1);
                    System.out.println("Received: " + packet);
                    seq = (seq + 1) % 2;
                } else {
                    System.out.println("Duplicate: " + packet);
                }

                if (i < 3) {
                    out.writeObject(String.valueOf(seq));
                    i++;
                } else {
                    out.writeObject(String.valueOf((seq + 1) % 2));
                    i = 0;
                }
            }

            System.out.println("Data received: " + data);
            out.writeObject("connection ended.");

            in.close();
            out.close();
            socket.close();
            server.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

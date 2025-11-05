import java.io.*;
import java.net.*;

public class Receiver {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(2004);
            System.out.println("Waiting for connection...");
            Socket s = ss.accept();
            System.out.println("Connected!");

            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            out.writeObject("connected.");

            StringBuilder data = new StringBuilder();
            int exp = 0;

            while (true) {
                String msg = (String) in.readObject();
                if (msg.equals("end")) break;

                char seq = msg.charAt(0), ch = msg.charAt(1);
                if (seq - '0' == exp) {
                    System.out.println("Received: " + msg);
                    data.append(ch);
                    exp = (exp + 1) % 2;
                } else
                    System.out.println("Duplicate: " + msg);

                out.writeObject(String.valueOf(exp));
            }

            System.out.println("Data received: " + data);
            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

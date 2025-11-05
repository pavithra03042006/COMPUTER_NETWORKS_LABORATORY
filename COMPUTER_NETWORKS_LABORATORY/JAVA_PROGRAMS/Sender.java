import java.io.*;
import java.net.*;

public class Sender {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 2004);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Receiver > " + in.readObject());
            System.out.print("Enter data to send: ");
            String data = br.readLine();

            int seq = 0, i = 0;
            while (i <= data.length()) {
                if (i == data.length()) {
                    out.writeObject("end");
                    break;
                }
                String msg = seq + data.substring(i, i + 1);
                out.writeObject(msg);
                out.flush();
                System.out.println("Sent: " + msg);
                System.out.println("Waiting for ack...");

                String ack = (String) in.readObject();
                if (ack.equals(String.valueOf((seq + 1) % 2))) {
                    System.out.println("Ack received, next packet\n");
                    seq = (seq + 1) % 2;
                    i++;
                } else
                    System.out.println("Timeout! Resending...\n");
            }

            System.out.println("All data sent. Exiting...");
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

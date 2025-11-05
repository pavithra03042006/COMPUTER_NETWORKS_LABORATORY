import java.net.*;
import java.util.*;

public class DNSClient {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();

        // Send text to server
        ds.send(new DatagramPacket(text.getBytes(), text.length(), ip, 3000));

        // Receive reversed message
        byte[] recv = new byte[100];
        DatagramPacket rp = new DatagramPacket(recv, recv.length);
        ds.receive(rp);

        String reversed = new String(rp.getData(), 0, rp.getLength());
        System.out.println("Reversed Text: " + reversed);

        ds.close();
        sc.close();
    }
}

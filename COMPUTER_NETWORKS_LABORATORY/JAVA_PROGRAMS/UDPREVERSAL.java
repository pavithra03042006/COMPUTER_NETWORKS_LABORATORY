import java.net.*;

public class UDPREVERSAL {

    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(3000);
        byte[] buf = new byte[100];

        // Receive packet
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        ds.receive(p);

        String received = new String(p.getData(), 0, p.getLength()).trim();

        // Reverse the message
        String res = new StringBuilder(received).reverse().toString();

        // Send back the reversed message
        ds.send(new DatagramPacket(res.getBytes(), res.length(), p.getAddress(), p.getPort()));

        ds.close();
    }
}

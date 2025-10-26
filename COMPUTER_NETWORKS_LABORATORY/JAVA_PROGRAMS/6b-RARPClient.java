import java.net.*;
import java.util.*;
public class RARPClient {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter MAC Address: ");
        String mac=sc.nextLine();

        DatagramSocket s=new DatagramSocket();
        InetAddress ip=InetAddress.getLocalHost();
        s.send(new DatagramPacket(mac.getBytes(),mac.length(),ip,1309));

        byte[] b=new byte[100];
        DatagramPacket r=new DatagramPacket(b,b.length);
        s.receive(r);
        System.out.println("IP Address: "+new String(r.getData()).trim());
        s.close();
    }
}
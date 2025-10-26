import java.net.*;
import java.util.*;
public class ARPClient {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter IP Address: ");
        String ip=sc.nextLine();

        DatagramSocket s=new DatagramSocket();
        InetAddress addr=InetAddress.getLocalHost();
        s.send(new DatagramPacket(ip.getBytes(),ip.length(),addr,1409));

        byte[] b=new byte[100];
        DatagramPacket r=new DatagramPacket(b,b.length);
        s.receive(r);
        System.out.println("MAC Address: "+new String(r.getData()).trim());
        s.close();
    }
}
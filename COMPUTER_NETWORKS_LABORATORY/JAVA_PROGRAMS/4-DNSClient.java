import java.net.*;
import java.util.*;

public class DNSClient {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter host name: ");
        String name=sc.nextLine();

        DatagramSocket ds=new DatagramSocket();
        InetAddress ip=InetAddress.getLocalHost();
        ds.send(new DatagramPacket(name.getBytes(),name.length(),ip,3000));

        byte[] recv=new byte[100];
        DatagramPacket rp=new DatagramPacket(recv,recv.length);
        ds.receive(rp);
        System.out.println("IP Address: "+new String(rp.getData()).trim());
        ds.close();
    }
} 
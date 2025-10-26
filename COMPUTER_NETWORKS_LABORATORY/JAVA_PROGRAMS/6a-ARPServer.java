import java.net.*;
public class ARPServer {
    public static void main(String[] args) throws Exception {
        String[] ips={"165.165.80.80","165.165.79.1"};
        String[] macs={"6A:08:AA:C2","8A:BC:E3:FA"};
        DatagramSocket s=new DatagramSocket(1409);
        byte[] b=new byte[100];
        DatagramPacket p=new DatagramPacket(b,b.length);
        s.receive(p);

        String res="Not Found";
        for(int i=0;i<ips.length;i++) if(new
                String(p.getData()).trim().equalsIgnoreCase(ips[i])) res=macs[i];

        s.send(new
                DatagramPacket(res.getBytes(),res.length(),p.getAddress(),p.getPort()));
        s.close();
    }
}
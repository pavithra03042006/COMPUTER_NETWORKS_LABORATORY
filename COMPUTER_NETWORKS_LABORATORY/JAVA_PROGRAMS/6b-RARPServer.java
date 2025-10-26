import java.net.*;
public class RARPServer {
    public static void main(String[] args) throws Exception {
        String[] macs={"6A:08:AA:C2","8A:BC:E3:FA"};
        String[] ips={"165.165.80.80","165.165.79.1"};
        DatagramSocket s=new DatagramSocket(1309);
        byte[] b=new byte[100];
        DatagramPacket p=new DatagramPacket(b,b.length);
        s.receive(p);

        String res="Not Found";
        for(int i=0;i<macs.length;i++) if(new
                String(p.getData()).trim().equalsIgnoreCase(macs[i])) res=ips[i];

        s.send(new
                DatagramPacket(res.getBytes(),res.length(),p.getAddress(),p.getPort()));
        s.close();
    }
} 
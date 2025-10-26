import java.net.*;

public class DNSServer {
    public static void main(String[] args) throws Exception {
        String[] hosts = {"google.com","facebook.com","youtube.com"};
        String[] ips = {"192.168.1.1","192.168.1.2","192.168.1.3"};
        DatagramSocket ds = new DatagramSocket(3000);
        byte[] buf = new byte[100];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        ds.receive(p);

        String res = "Host Not Found";
        for(int i=0;i<hosts.length;i++) if(new
                String(p.getData()).trim().equalsIgnoreCase(hosts[i])) res=ips[i];

        ds.send(new DatagramPacket(res.getBytes(), res.length(),
                p.getAddress(), p.getPort()));
        ds.close();
    }
} 
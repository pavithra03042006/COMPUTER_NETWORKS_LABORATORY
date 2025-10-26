import java.io.*; 
import java.net.*; 
 
public class EchoServer { 
    public static void main(String[] args) throws Exception { 
        ServerSocket ss = new ServerSocket(6666); 
        Socket s = ss.accept(); 
        DataInputStream in = new DataInputStream(s.getInputStream()); 
        System.out.println("Message = " + in.readUTF()); 
        s.close(); 
        ss.close(); 
    } 
}
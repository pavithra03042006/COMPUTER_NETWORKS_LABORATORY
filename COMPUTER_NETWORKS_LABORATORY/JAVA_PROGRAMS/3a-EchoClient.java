import java.io.*; 
import java.net.*; 
 
public class EchoClient { 
    public static void main(String[] args) throws Exception { 
        Socket s = new Socket("localhost", 6666); 
        new DataOutputStream(s.getOutputStream()).writeUTF("Hello Server"); 
        s.close(); 
    } 
} 
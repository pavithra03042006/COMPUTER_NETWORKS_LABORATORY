import java.io.*; 
import java.net.*; 
import java.util.*; 
 
public class SimpleWebClient { 
    public static void main(String[] args) { 
        try { 
            Scanner sc = new Scanner(System.in); 
            System.out.print("Enter website name: "); 
            String host = sc.nextLine(); 
 
            Socket s = new Socket(host, 80); 
            PrintWriter out = new PrintWriter(s.getOutputStream(), true); 
            out.println("GET / HTTP/1.1"); 
            out.println("Host: " + host); 
            out.println("Connection: close"); 
            out.println(); 
 
            BufferedReader in = new BufferedReader(new 
InputStreamReader(s.getInputStream())); 
            BufferedWriter file = new BufferedWriter(new 
FileWriter("DownloadedPage.html")); 
 
            String line; 
            while ((line = in.readLine()) != null) { 
                file.write(line + "\n"); 
            } 
 
            file.close(); 
            in.close(); 
            s.close(); 
            System.out.println("Web page downloaded as DownloadedPage.html");
        } catch (Exception e) { 
            System.out.println(e); 
        } 
    } 
} 
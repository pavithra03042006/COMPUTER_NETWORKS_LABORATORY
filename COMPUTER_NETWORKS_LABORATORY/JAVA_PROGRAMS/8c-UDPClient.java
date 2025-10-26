import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            byte[] sendData;
            byte[] receiveData = new byte[1024];

            while (true) {
                System.out.print("Enter a message (type 'exit' to quit): ");
                String message = userInput.readLine();
                if (message.equalsIgnoreCase("exit")) break;

                sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9877);
                clientSocket.send(sendPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String reply = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("From Server: " + reply);
            }

            clientSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

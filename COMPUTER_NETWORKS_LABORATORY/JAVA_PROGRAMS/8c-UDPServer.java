import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9877);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            System.out.println("UDP Server is running...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received: " + message);

                // Reverse the message
                String reversed = new StringBuilder(message).reverse().toString();
                sendData = reversed.getBytes();

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Sent Reversed: " + reversed);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

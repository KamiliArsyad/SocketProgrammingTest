import java.io.IOException;
import java.net.*;

public class UPDServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(5000);
        byte[] recvBuffer = new byte[1024];
        byte[] sendData;

        while (true) {
            DatagramPacket recvPacket = new DatagramPacket(recvBuffer, recvBuffer.length);
            socket.receive(recvPacket);
            String sentence = new String(recvPacket.getData());
            System.out.println("RECEIVED: " + sentence);
            InetAddress IPAddress = recvPacket.getAddress();
            int port = recvPacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            socket.send(sendPacket);
            // Flush the buffer
            recvBuffer = new byte[1024];
        }
    }
}

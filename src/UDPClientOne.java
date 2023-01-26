import java.net.*;
import java.io.*;

public class UDPClientOne {
    public static void main(String[] args) throws IOException {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");

            // Keep reading input from the user until the word "exit" is entered
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            String sentence = inFromUser.readLine();
            while (!sentence.equals("exit")) {
                byte[] sendData;
                byte[] recvBuffer = new byte[sentence.length()];
                sendData = sentence.getBytes();

                // Find the input length of the sentence
                int length = sentence.length();
                DatagramPacket sendPacket = new DatagramPacket(sendData, length, IPAddress, 5000);
                socket.send(sendPacket);
                DatagramPacket recvPacket = new DatagramPacket(recvBuffer, length);
                socket.receive(recvPacket);
                String modifiedSentence = new String(recvPacket.getData());
                System.out.println("FROM SERVER: " + modifiedSentence);
                sentence = inFromUser.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

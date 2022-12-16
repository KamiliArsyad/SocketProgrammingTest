import java.io.*;
import java.net.*;

public class TCPClientOne {
    /**
     * @param args the command line arguments (e.g. localhost 3230)
     */
    public static void main(String[] args) {
        Socket socket;
        InputStream is;
        OutputStream os;
        BufferedReader br;
        if (args.length != 2) {
            System.out.println("Usage: java TCPClientOne <host> <port>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        // Try to initiate a connection to the server
        try {
            socket = new Socket(host, port);
            System.out.println("Connected to " + host + " on port " + port);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + host);
            return;
        } catch (IOException e) {
            System.out.println("No I/O");
            return;
        }

        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
            br = new BufferedReader(new InputStreamReader(is));
            DataOutputStream dos = new DataOutputStream(os);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            // Read from keyboard and send to server
            while (true) {
                String userInput = stdIn.readLine();
                if (userInput == null) break;
                dos.writeBytes(userInput + "\r\n");
            }

            dos.flush();

            // Read from server and print to screen
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("+" + line);
            }

            // Close the socket
            socket.close();
        } catch (IOException e) {
                System.out.println("Unable to read/write from/to socket: " + e.getMessage());
        }
    }
}

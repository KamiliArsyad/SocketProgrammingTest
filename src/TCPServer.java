import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        // Create a server socket and listen to port 3230
        try {
            serverSocket = new ServerSocket(3230);
            System.out.println("Server is listening on port 3230");
        } catch (IOException e) {
            System.out.println("Could not listen on port 3230");
            return;
        }

        // Listen forever for incoming connection. Only handle one connection at a time
        while (true) {
            Socket clientSocket;
            InputStream is;
            OutputStream os;
            BufferedReader br;
            DataOutputStream dos;

            // Wait for a connection
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());
            } catch (IOException e) {
                System.out.println("Accept failed: 3230");
                continue;
            }

            // Get input and output streams
            try {
                is = clientSocket.getInputStream();
                os = clientSocket.getOutputStream();
                br = new BufferedReader(new InputStreamReader(is));
                dos = new DataOutputStream(os);

                // Read from the client
                String line = br.readLine();

                if (line == null) continue;

                String response = "You said: " + line;
                System.out.println(response);
            } catch (IOException e) {
                System.out.println("Could not create I/O streams");
            }
        }
    }
}

import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1234);
            Socket client = server.accept();
            System.out.println("Client connected");
            while(true) {
                InputStreamReader in = new InputStreamReader(client.getInputStream());
                char[] buffer = new char[1024];
                int read = in.read(buffer);
                System.out.println("Received: " + new String(buffer, 0, read));
            }
            client.close();
            server.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

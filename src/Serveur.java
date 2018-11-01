import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

    static final int PORT = 1978;
    
    public static void main(String args[]) throws Exception {
        ServerSocket serverSocket = null;
        Socket socket = null;
        NbClients clients = new NbClients();
        Gestionnaire g = new Gestionnaire();
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message;
        while (true) {
            try {
                socket = serverSocket.accept();
                clients.nb++;
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
        	new Thread(new NumberThread(socket, clients, g)).start();
        }
    }
}

class NbClients {
	int nb;
	
	public NbClients() {
		nb = 0;
	}
}

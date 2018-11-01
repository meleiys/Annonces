import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

    static final int PORT = 1978;
    
    public static boolean traitementRequete(String message) {
    	try {
    		// on teste si le message finie bien par +++
    		if(!(message.substring(message.length() -3, message.length()).equals("+++"))){
    			return false;
    		}
    		message = message.substring(0, message.length()-3);
    		String[] tab = message.split(" ");
    		String typeRequete = tab[0];
    		switch(typeRequete) {
    		case "INS": 
    			// Inscription
    			return true;
    		case "CON":
    			// Connexion
    			return true;
    		case "DIS": 
    			// Déconnexion
    			return true;
    		case "SHOW":
    			// Liste des annonces
    			return true;
    		case "DEP": 
    			// Dépôt d'annonce
    			return true;
    		case "RET":
    			// Retirer une annonce
    			return true;
    		}
    		return false;
    	}
    	catch(Exception e) {
    		return false;
    	}
    }
    
    public static void main(String args[]) throws Exception {
        ServerSocket serverSocket = null;
        Socket socket = null;
        NbClients clients = new NbClients();
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
//              new Thread(new NumberThread(socket, clients)).start();
                while(true) {
                    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    message = inFromServer.readLine();
                    System.out.println(message);
                    System.out.println(traitementRequete(message));
                }
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
        }
    }
}

class NbClients {
	int nb;
	
	public NbClients() {
		nb = 0;
	}
}

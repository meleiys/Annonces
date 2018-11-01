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
    			if(tab.length == 3) {
        			return requeteInscription(tab[1], tab[2]);
    			}
    			else {
    				return false;
    			}
    		case "CON":
    			// Connexion
    			if(tab.length == 3) {
        			return requeteConnexion(tab[1], tab[2]);
    			}
    			else {
    				return false;
    			}
    		case "DIS": 
    			// Déconnexion
        		return requeteDeconnexion();

    		case "SHOW":
    			// Liste des annonces
        			return requeteListeAnnonces();
    		case "DEP": 
    			// Dépôt d'annonce
    			if(tab.length == 5) {
        			return requeteDepotDAnnonce(tab[1], tab[2], Integer.parseInt(tab[3]), Integer.parseInt(tab[4]));
    			}
    			else {
    				return false;
    			}
    		case "RET":
    			// Retirer une annonce
    			if(tab.length == 3) {
        			return requeteRetirerAnnonce(Integer.parseInt(tab[1]), Integer.parseInt(tab[2]));
    			}
    			else {
    				return false;
    			}
    		}
    		return false;
    	}
    	catch(Exception e) {
    		return false;
    	}
    }
    
    public static boolean requeteInscription(String pseudo, String mdp) {
    	return true;
    }
    
    public static boolean requeteConnexion(String pseudo, String mdp) {
    	return true;
    }
    
    public static boolean requeteDeconnexion() {
    	return true;
    }
    
    public static boolean requeteListeAnnonces() {
    	return true;
    }
    
    public static boolean requeteDepotDAnnonce(String type, String message, int prix, int port) {
    	return true;
    }
    
    public static boolean requeteRetirerAnnonce(int id_annonce, int id) {
    	return true;
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

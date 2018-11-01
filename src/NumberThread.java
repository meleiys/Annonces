import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class NumberThread implements Runnable{
	Socket socket;
	NbClients clients;
	static Gestionnaire g;
	static int id;
	BufferedReader in = null;
    static DataOutputStream out = null;

    public NumberThread(Socket clientSocket, NbClients clients, Gestionnaire g) {
        this.socket = clientSocket;
        this.clients = clients;
        this.g = g;
        this.id = -1;
    }
    
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
    			return false;
    			
    		case "CON":
    			// Connexion
    			if(tab.length == 3) {
        			id = requeteConnexion(tab[1], tab[2]);
        			if(id!=-1) {
        				return true;
        			}
    			}
    			return false;
    			
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
    	id = g.ajouterUtilisateur(new Utilisateur(pseudo, mdp));
    	sendOK();
    	return true;
    }
    
    public static int requeteConnexion(String pseudo, String mdp) {
    	id = g.existe(pseudo, mdp);
    	if(id == -1) {
        	sendError();
        	return -1;
    	}
		sendOK();
		return id;
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
    
    public static void sendOK() {
    	try {
            out.writeBytes("OK+++");
            out.flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }	
    }
    
    public static void sendError() {
    	try {
            out.writeBytes("ERR+++");
            out.flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }	
    }

    public void run() {
        String message;
        try {
        	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        	out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        while (true) {
            try {
                Thread.sleep(1000);
                message = in.readLine();
                System.out.println(message);
                traitementRequete(message);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
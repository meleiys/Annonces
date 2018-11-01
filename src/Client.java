import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
    public static boolean traitementRequete(String message) {
    	try {
    		// on teste si le message finie bien par +++
    		if(!(message.substring(message.length()-3, message.length()).equals("+++"))){
    			return false;
    		}
    		message = message.substring(0, message.length()-3);
    		String[] tab = message.split(" ");
    		String typeRequete = tab[0];
    		switch(typeRequete) {
    		
    		case "OK": 
    			// OK
    			System.out.println("OK");
    			return true;
    			
    		case "ERR":
    			// Error
    			System.out.println("Error");
    			return true;
    		}
    		return false;
    	}
    	catch(Exception e) {
    		return false;
    	}
    }

	public static void main(String[] args) throws Exception {
		try {
			Socket s = new Socket("localhost", 1978);
			Scanner sc = new Scanner(System.in);
			String message;
			System.out.println("Commandes : \n\tInscription : INS pseudo mdp+++\n\tConnexion : CON pseudo mdp+++\n\tDéconnexion : DIS+++\n\tListe des annonces : SHOW+++\n\tDépôt d'annonce : DEP type message prix port+++\n\tRetirer une annonce : RET id_annonce+++");
			while(true) {
				message = sc.nextLine();
				System.out.println(message);
				PrintStream out = new PrintStream(s.getOutputStream());
				out.println(message);
//	        	BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//	        	message = in.readLine();
//	        	traitementRequete(message);
			}
		} catch(ConnectException e) {
			System.err.println("Impossible de se connecter au serveur, vérifiez que celui-ci est bien lancé.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

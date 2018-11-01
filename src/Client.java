import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws Exception {
		try {
			Socket s = new Socket("localhost", 1978);
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
			Scanner sc = new Scanner(System.in);
			String message;
			System.out.println("Commandes : \n\tInscription : INS pseudo mdp+++\n\tConnexion : CON pseudo mdp+++\n\tDéconnexion : DIS+++\n\tListe des annonces : SHOW+++\n\tDépôt d'annonce : DEP type message prix port+++\n\tRetirer une annonce : RET id_annonce+++");
			while(true) {
				message = sc.nextLine();
				System.out.println(message);
				PrintStream out = new PrintStream(s.getOutputStream());
				out.println(message);
			//	String res = inFromServer.readLine();
			//	System.out.println(res);
			}
		} catch(ConnectException e) {
			System.err.println("Impossible de se connecter au serveur, vérifiez que celui-ci est bien lancé.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

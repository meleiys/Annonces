import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class Gestionnaire {

	ArrayList<Annonce> listeAnnonces;
	
	public Gestionnaire() {
		listeAnnonces = new ArrayList<Annonce>();
	}
	
	public void ajouterAnnonce(Annonce a) {
		this.listeAnnonces.add(a);
	}
	
	public void affiche() {
		for(Annonce a : this.listeAnnonces) {
			a.affiche();
			System.out.println("");
		}
	}

	public static void main(String[] args) throws Exception {
		Utilisateur u1 = new Utilisateur("Marc", "a");
		Utilisateur u2 = new Utilisateur("Julie","b");
		Annonce a = new Annonce("stylo","description1",7,1,u1);
		Annonce b = new Annonce("livre","description2",15,1,u2);
		Gestionnaire g = new Gestionnaire();
		g.ajouterAnnonce(a);
		g.ajouterAnnonce(b);
		g.affiche();
	}
}


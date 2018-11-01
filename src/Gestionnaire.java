import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class Gestionnaire {

	ArrayList<Utilisateur>listeUtilisateurs;
	ArrayList<Annonce> listeAnnonces;
	
	public Gestionnaire() {
		listeUtilisateurs = new ArrayList<Utilisateur>();
		listeAnnonces = new ArrayList<Annonce>();
	}
	
	public int ajouterUtilisateur(Utilisateur u) {
		this.listeUtilisateurs.add(u);
		return u.id;
	}
	
	public void retirerUtilisateur(Utilisateur u) {
		this.listeUtilisateurs.remove(u);
	}
	
	public int existe(String pseudo, String mdp) {
		for(Utilisateur u : listeUtilisateurs) {
			if(u.pseudo.equals(pseudo) && u.mdp.equals(mdp)) {
				return u.id;
			}
		}
		return -1;
	}
	
	public void ajouterAnnonce(Annonce a) {
		this.listeAnnonces.add(a);
	}
	
	public void retirerAnnonce(Annonce a) {
		this.listeAnnonces.remove(a);
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


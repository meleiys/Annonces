public class Utilisateur {

	int id;
	String pseudo;
	String mdp;
	static int count_id=0;
	
	public Utilisateur(String pseudo, String mdp) {
		this.id = count_id;
		this.pseudo = pseudo;
		this.mdp = mdp;
		count_id++;
	}
}

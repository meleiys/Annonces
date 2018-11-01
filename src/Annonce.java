public class Annonce {

	int id;
	String type;
	String message;
	int prix;
	int port;
	Utilisateur utilis;
	static int count_id=0;
	
	public Annonce(String type, String message, int prix, int port, Utilisateur utilis) {
		this.id=count_id;
		this.type = type;
		this.message = message;
		this.prix = prix;
		this.port = port;
		this.utilis = utilis;
		count_id++;
	}
	
	public void affiche() {
		System.out.println("Id : "+this.id+"\nType : "+this.type+"\nMessage : "+this.message+"\nPrix : "+this.prix+"\nUtilisateur : "+this.utilis.pseudo);
	}
}


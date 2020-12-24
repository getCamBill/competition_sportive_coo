package core_match_classique;

/**
 * This class is a Competitor object which represent a player, 
 * an athlete in a competition caracterized by a name and a number of victories and defeats.
 * The Competitor also know his status, if he is eliminated or not.
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class Competitor {

	private String nomJoueur;
	private int nbVictoires = 0;
	private Boolean elimine; 
	private int nbMatchJoue;
	
	/**
	 * Constructor which create an instance of Competitor and initialize his attributes and give him a name.
	 * @param nomJoueur		Contain the name of the new instance of Competitor to assign to his attributes
	 */
	public Competitor (String nomJoueur) {
		this.nomJoueur = nomJoueur;
		this.elimine = false;
	}
	
	/**
	 * Accessor which return the attribute name of the instance of Competitor.
	 * @return Return a String which contain the name of the instance of Competitor
	 */
    public String getNomJoueur () { return nomJoueur; }
    
    /**
     * This accessor return the number of victories of the instance of Competitor.
     * @return Return an integer which contain the number of victory of the Competitor instance
     */
    public int getNbVictory () { return nbVictoires; }
	
    /**
     * Setter personalized accessor which increment the attribute of the instance which is containing the number of victory of the Competitor instance.
     */
	public void addVictory () { this.nbVictoires++; }
	
	/**
	 * Getter accessor with a personalize name which return if the instance of Competitor is eliminated or not.
	 * @return Return a true Boolean if the instance of Competitor is elimated or return false if he is still available to play
	 */
	public Boolean isElimine () { return this.elimine; }
	
	/**
	 * Personalized setter accessor which turn the elimination attribute in true. The goal is to eliminate the instance of Competitor.
	 */
	public void eliminate () { this.elimine = true; }
    
	/**
	 * This accessor return the number of played matches by the instance of Competitor.
	 * @return Return an integer which contains the number of played matchs by the instance of Competitor
	 */
	public int getNbMatchJoue() { return nbMatchJoue; } 

	/**
	 * This personalized setter accessor increment the number of played matches by the instance of Competitor.
	 */
	public void addMatchJoue() { ++this.nbMatchJoue; }

	public void setNbVictory(int i) {this.nbVictoires = i;}
}

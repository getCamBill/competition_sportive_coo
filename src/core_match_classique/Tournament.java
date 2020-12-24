package core_match_classique;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is a type of Competition and make a tournament with different rounds.
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class Tournament extends Competition {
	
	private int nbTours;
	private ArrayList[] listePool; // future n-player list
	private List <Competitor> resteJoueur; // at each round the incremental list of winners

	/**
	 * The constructor take a list of competitor and check the parameters.
	 * Use the super constructor (look up) to use attributes.
	 * @param competitors	It's the list of competitors subscribed to the competition
	 */
	public Tournament (List<Competitor> competitors) throws CompetitionException {
		super(competitors);
		if (!isPowerOfTwo(competitors.size())) { throw new CompetitionException(competitors.size() + " pas une puissance de 2"); }
	}
	
	/**
	 * This method verify if the number of players in this Tournament is a power of two.
	 * author https://www.geeksforgeeks.org/java-program-to-find-whether-a-no-is-power-of-two/
	 * @param nbPlayer		It's the number of Competitor subscribed in the Tournament
	 * @return	If true, the number of Competitor is a power of two, if false it's not
	 */
	protected static boolean isPowerOfTwo(int nbPlayer) {
		return (int)(Math.ceil((Math.log(nbPlayer) / Math.log(2)))) == (int)(Math.floor(((Math.log(nbPlayer) / Math.log(2)))));
	}
	
	private void playMatchs(ArrayList[] listePool) {
		
		// create the list of players who can participate in the matches
		List <Competitor> joueursTourSuivant = new ArrayList<>();
		// for each pool we play the match
		for (List<Competitor> m : listePool) {
			// set players 
			super.getMatch().setPlayer1(m.get(0));
			super.getMatch().setPlayer2(m.get(1));
			//  start the game 
			try { super.getMatch().play();
			} catch (Exception e) {/*e.printStackTrace(); */}
			super.setRanking(m.get(0));
			super.setRanking(m.get(1));
			
			// we test the loser then we add the winner to the list of winners
			Boolean b = super.getMatch().getPlayer1().isElimine();
			if (Boolean.TRUE.equals(b)) { // si NULL
				joueursTourSuivant.add(m.get(1));
			}
			 else {
				joueursTourSuivant.add(m.get(0));
			}
		}
		// we overwrite the old list with the new one 
		resteJoueur = joueursTourSuivant;
	}
	
	/**
	 * Play the Tournament until the last round
	 */
	@Override
	public void play () {
		int nbCompetitors = super.getCompetitors().size();
		nbTours = (nbCompetitors / 4);
		resteJoueur = super.getCompetitors(); //For the first round, the list take all every player
		
		while (nbTours >= 0) {
			this.listePool = createPool(resteJoueur, 2);
			showListPool();
			playMatchs(this.listePool);
			
			--nbTours;
		}	
	}
	
	/**		CHANGER COMMENTAIRE DEPUIS MODIF !!
	 * 
	 * This method use the attribute listPool to make the groups for the next round.
	 * This method is used for every round.
	 * @param competitors : A list of competitiors
	 * @param nbPlayersPoule : Number of players for each pool
	 * @return A list of pools
	 */
	protected static ArrayList[] createPool(List<Competitor> competitors, int nbPlayersPoule) {
		
		int taille = competitors.size(); //nb players
		int nbPool = (taille/nbPlayersPoule); //nb pool for each round
		
		//initialize the list of pool
		ArrayList[] listePool = new ArrayList[nbPool];
		
		//initialize a list for each pool
		for (int i =0; i < nbPool; i++) { listePool[i] = new ArrayList(); }
		
		//add 1x / 2 players in each list
		for (int j =0; j< taille;j++) { listePool[j/nbPlayersPoule].add(competitors.get(j)); }
	
		return listePool;
	}
	
	/**
	 * This method just print the list of pool
	 */
	public void showListPool () {
		System.out.println('\n');
		for (List l : this.listePool) 
			System.out.println(((Competitor) l.get(0)).getNomJoueur() + " " + ((Competitor) l.get(1)).getNomJoueur());
	}
	
	/**
	 * An accessor to get the rest of players attribute after a round.
	 * @return The list of available players after a round
	 */
	public List<Competitor> getResteJoueur () { return this.resteJoueur; }

}

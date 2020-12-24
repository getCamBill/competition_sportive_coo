package core_match_classique;

/**
 This class is a type of ...
* @author Cedric Bevilacqua, Camille Billouard
* @version 2.0
*/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.MapUtil;

public abstract class Competition {

	private final Match match = new Match();
	private final List<Competitor> competitors;
	private Map<Competitor, Integer> map = new HashMap<>();
	/**
	 * The constructor of Competition is made to put the list of competitors 
	 * given in argument in the attribute to subscribe them to the competition.
	 * @param competitors	The list of competitors to subscribe in the Competition.
	 */
	public Competition (List<Competitor> competitors) { this.competitors = competitors; }
	
	/**
	 * This method will be used to start a Competition until his end.
	 */
	abstract public void play();
	
	/**
	 * Take competitors and fight them
	 * @param c1 Instance of Competitor to play in the match
	 * @param c2 : Instance of Competitor to play in the match
	 */
	protected void playMatch (Competitor c1, Competitor c2) {
		match.setPlayer1(c1);
		match.setPlayer2(c2);
		match.play();
	}
	
	/**
	 * This method is given in the Util package.
	 * It's used to make a final ranking.
	 * @return Return a list of each Competitor with their rank for each of them
	 * @see util.MapUtil#sortByDescendingValue(Map)
	 */
	public Map<Competitor, Integer> ranking () {
		// Methode fournit dans le package util, 
		// permettant de realiser le classement finale 
		return MapUtil.sortByDescendingValue(getMap());
	}
	
	/**
	 * This accessor return the Match played in the Competition.
	 * @return	The match of the Competition used for every Match in this Competition
	 */
	public Match getMatch() { return match; }
	
	/**
	 * This accessor return all the Competitor subscribed in this Competition.
	 * @return The list of Competitor subscribed in this instance of Competition
	 */
	public List<Competitor> getCompetitors() { return this.competitors; }
	
	/**
	 * This accessor return a Map object used for the ranking.
	 * @return		The Map used for the ranking
	 */
	public Map<Competitor, Integer> getMap() { return map; }

	/**
	 * This is a personalized accessor which put a new Competitior 
	 * in the Map stored in the attribute of this instance of Competition.
	 * @param c : Put a Competitor in the rank
	 */
	protected void setRanking(Competitor c) { map.put(c, c.getNbVictory()); }

	protected void setMap(Map<Competitor, Integer> rank) {
		this.map = rank;
	}
	
	public int getNbPlayers() {return this.getCompetitors().size();}
}



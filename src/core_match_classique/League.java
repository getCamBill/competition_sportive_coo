package core_match_classique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a type of Competition and make a League with return match.
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class League extends Competition {
	
	private Map<Competitor, List<Competitor>> historiqueMatchJoues;
	
	/**
	 * The constructor take a list of competitor.
	 * Use the super constructor (look up) to use attributes.
	 * @param competitors	It's the list of competitors subscribed to the competition
	 */
	public League (List<Competitor> competitors) {
		
		super(competitors);
	}

	/**
	 * Play the League until that all the players have met
	 */
	@Override
	public void play () { 
		
		// create the list of players
		List<Competitor> compet = super.getCompetitors();
		historiqueMatchJoues = new HashMap<>();
		
		
		for (Competitor c1: compet) { 
			super.getMatch().setPlayer1(c1);
			
			// for each player c1 we create an empty list for the opponents 
			List<Competitor> adv = new ArrayList<Competitor>();
			historiqueMatchJoues.put(c1, adv);
			
			for (Competitor c2 : compet) {
				super.getMatch().setPlayer2(c2);
				
				try {
					super.getMatch().play();
					adv.add(c2); 
					historiqueMatchJoues.remove(c1, adv);
				} catch (Exception e) {/*e.printStackTrace();*/}
				
				// update the map for the final ranking 
				super.setRanking(c2);
			}
			super.setRanking(c1);
		}
		System.out.println("-------------------------------------");
	}
	
	public Map<Competitor, List<Competitor>> getHistoriqueMatchJoues() {
		return this.historiqueMatchJoues;
	}
	
}

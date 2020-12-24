package core_match_classique;

import java.util.HashMap;
import java.util.Map;

/**
 * The Bookmakers update the cotes of each Competitor in the Matchs
 * The bookmakers, they make the odds of the competitors 
 * evolve according to the results of the matches of the competition. 
 * They maintain a list of the competitors' odds that they post and 
 * make them evolve according to victories (decrease in odds) and defeats (increase in odds).
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class Bookmakers implements MatchListener{
    

    private Map<Competitor, Integer> cotes_liste = new HashMap<Competitor, Integer>();
    
    /**
	 * Get all the necessary informations of a Match and engage the update of cotes
	 * @param e : The Match observable 
	 */
    public void matchResult (MatchEvent e) {
        Match m = (Match) e.getSource();

        updateCotes(m.getWinner(), m.getLooser());
        showCotes(m.getWinner(), m.getLooser());
    }

    /**
	 * Update the popularity of Competitors after a Match
	 * @param winner : 
     * @param looser : 
	 */
    private void updateCotes(Competitor winner, Competitor looser) {
        setCote(looser, 1);
        setCote(winner, -1);
    }

    /**
	 * Define the cote for a Competitor instance
	 * @param c : The Competitor cote to change
     * @param cote : The value of his new cote
	 */
    public void setCote(Competitor c, Integer cote) {
        if (!cotes_liste.containsKey(c)) { // première insertion 
            cotes_liste.put(c, cote);
        }
        else {
            Integer old = this.getCote(c);
            cotes_liste.put(c, old+cote);
        }
    }
        
    /**
	 * Get the cote for a Competitor instance
	 * @param c : The instance of Competitor which you want to see the cote
     * @return : The cote of the selected Competitor
	 */
    public Integer getCote(Competitor c) {return this.cotes_liste.get(c).intValue(); }

    /**
	 * Get the list of all the cotes for every registered Competitor
     * @return : The cote for each registered Competitor
	 */
    public Map<Competitor, Integer> getCotesListe() {return this.cotes_liste;}
    
    /**
	 * Show the cote between 2 Competitor to compare them
	 * @param c1 : The first instance of Competitor which you want to see the cote
     * @param c2 : The second instance of Competitor which you want to see the cote
	 */
    public void showCotes(Competitor c1, Competitor c2){
        System.out.println(c1.getNomJoueur() + " cote = "+cotes_liste.get(c1) );
        System.out.println(c2.getNomJoueur() + " cote = "+cotes_liste.get(c2) + '\n');
    }
}

package core_match_classique;

/**
 * Journalists attend the competitions and broadcast the results of the matches.
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class Journalistes implements MatchListener {

    /**
	 * Get all the necessary informations of a Match and introduce the match
	 * @param e : The Match observable 
	 */
    public void matchResult(MatchEvent e) {
        Match m = (Match) e.getSource();
        System.out.println(m.getPlayer1().getNomJoueur() + " VS " + 
		m.getPlayer2().getNomJoueur() + " --> " + m.getWinner().getNomJoueur() ); 
    }
}

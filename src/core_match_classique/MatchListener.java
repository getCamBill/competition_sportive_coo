package core_match_classique;

/* The listener is the interface to connect a MatchEvent and get the informations from it
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public interface MatchListener extends java.util.EventListener {
    
    /**
     * The math event where we can get the informations
     * @param e : The event containing the Match informations
     */
    public void matchResult(MatchEvent e);

}

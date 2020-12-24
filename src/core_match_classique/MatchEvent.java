package core_match_classique;

/* This event take all the necessary informations that need to be transfered to the observer
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class MatchEvent extends java.util.EventObject{

    private static final long serialVersionUID = 1L;

    /**
	 * Constructor using the look up
	 * @param source : The Match observable 
	 */
    public MatchEvent(Match source) {
        super(source);
    }
    
}

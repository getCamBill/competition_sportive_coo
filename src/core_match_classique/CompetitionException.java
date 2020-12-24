package core_match_classique;
/**
 * This class refer to a personalize exception when 
 * the number of players in the Tournament is not a power of two.
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class CompetitionException extends RuntimeException {

	public CompetitionException( String msg) {
		super(msg);
	}
}


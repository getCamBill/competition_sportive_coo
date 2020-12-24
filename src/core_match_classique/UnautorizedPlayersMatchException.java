package core_match_classique;

/**
 * This class refer to a personalize exception when the two players in the match are the same and we try to play the match.
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class UnautorizedPlayersMatchException extends RuntimeException{

	public UnautorizedPlayersMatchException(String msg) {
		super(msg);
	}
	
}

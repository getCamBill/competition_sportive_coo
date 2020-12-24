package core_match_classique;
/**
 * This class refer to a personalize exception when 
 * the number of players in the Master's parameters 
 * does not provide, for the final part, a power of two.
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class MasterException extends RuntimeException {

	public MasterException (String msg) {
		super(msg);
	}
}

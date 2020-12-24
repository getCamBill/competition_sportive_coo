

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import core_match_classique.Competitor;
import core_match_classique.Match;
import core_match_classique.UnautorizedPlayersMatchException;

public class MatchTest {
	
	@Test
	public void testGetSetC1C2() {
		Match match = new Match();
		Competitor competitor1 = new Competitor("Abitbol");
		Competitor competitor2 = new Competitor("Oss117");
		match.setPlayer1(competitor1);
		match.setPlayer2(competitor2);
		assertSame(competitor1, match.getPlayer1());
		assertSame(competitor2, match.getPlayer2());
	}
	
	@Test
	public void testPlay() {
		Match match = new Match();
		Competitor competitor1 = new Competitor("Abitbol");
		Competitor competitor2 = new Competitor("Oss117");
		match.setPlayer1(competitor1);
		match.setPlayer2(competitor2);
		
		assertSame(0, match.getPlayer1().getNbMatchJoue());
		assertSame(0, match.getPlayer2().getNbMatchJoue());
		
		match.play();
		// verifie que si le joueur 1 a une victoire le J2 n'an a pas 
		// et inversement
		assertTrue(((match.getPlayer1().getNbVictory() == 1 && match.getPlayer2().getNbVictory() == 0) 
				|| (match.getPlayer1().getNbVictory() == 0 && match.getPlayer2().getNbVictory() == 1)));
		assertSame(1, match.getPlayer1().getNbMatchJoue());
		assertSame(1, match.getPlayer2().getNbMatchJoue());
	}
	
	@Test(expected=UnautorizedPlayersMatchException.class)
	public void testPlayException() {
		/*
		 * Verifie qu'un match ne peut avoir lieu avec le meme joueur
		 * */
		Match match = new Match();
		Competitor competitor = new Competitor("Abitbol");
		match.setPlayer1(competitor);
		match.setPlayer2(competitor);
		
		match.play();
	}
	
	public static junit.framework.Test suite(){
        return new junit.framework.JUnit4TestAdapter(MatchTest.class);
    }
}

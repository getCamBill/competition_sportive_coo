

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import core_match_classique.Competitor;
import core_match_classique.Tournament;
import core_match_classique.CompetitionException;

/**
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class TournamentsTest {

	@Test
	public void testJoueurReglo () {
		// verify that the player has won his previous match.
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Luigi"), new Competitor("Mario"));

		Tournament t = new Tournament(competitors);
		
		for (Competitor c : t.getCompetitors()) {
			assertFalse(c.isElimine());
		}
		t.play();
		assertFalse(t.getResteJoueur().get(0).isElimine());
	}
	
	@Test(expected=CompetitionException.class)
	public void testPow2 () {
		// check that if the number of players 
		// is not power of 2 ==> raises an exception
		
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Luigi"));

		Tournament t = new Tournament(competitors);
		
	}
	
	@Test
	public void testNbToursEqNbVict() {
		// verifies that the turn of winner == nbVict of winner
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Luigi"), new Competitor("Mario"));

		Tournament t = new Tournament(competitors);
		t.play();
		// retrieves the ranking of the players -> list 
		ArrayList<Competitor> playersList = new ArrayList<Competitor>
		(t.ranking().keySet());
		
		assertSame(playersList.get(0).getNbMatchJoue(),playersList.get(0).getNbVictory());
	}
	
	@Test
	public void testLoosersAreLooser () {
		// check that all other players are eliminated
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Luigi"), new Competitor("Mario"));

		Tournament t = new Tournament(competitors);
		
		t.play();
		
		// retrieves the ranking of the players -> list 
		ArrayList<Competitor> playersList = new ArrayList<Competitor>
		(t.ranking().keySet());
		
		int taille = playersList.size()-1;
		
		for (int i = 1; i <= taille; i++) { // 1 car 0 => winner
			assertTrue(playersList.get(i).isElimine());
		}
	}
	
	public static junit.framework.Test suite(){
        return new junit.framework.JUnit4TestAdapter(TournamentsTest.class);
    }
}

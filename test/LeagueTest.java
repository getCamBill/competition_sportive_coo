
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import core_match_classique.Competitor;
import core_match_classique.League;
 

/**
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class LeagueTest { 
	
	@Test
	public void test2MatchParJoueur () {
		// check that all the players have played 2 games. 
		List<Competitor> competitors = Arrays.asList(
						new Competitor("Abitbol"),
						new Competitor("Oss117"),
						new Competitor("Luigi"));

		League ldc = new League(competitors);

		assertEquals(0, competitors.get(0).getNbMatchJoue());
		assertEquals(0, competitors.get(1).getNbMatchJoue());
		assertEquals(0, competitors.get(2).getNbMatchJoue());

		ldc.play();

		assertEquals(4, competitors.get(0).getNbMatchJoue());
		assertEquals(4, competitors.get(1).getNbMatchJoue());
		assertEquals(4, competitors.get(2).getNbMatchJoue());
	}

	@Test
	public void testAllPlayersPlayed () {
		// check that each player meets the others
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"),
				new Competitor("Oss117"),
				new Competitor("Luigi"));

		League ldc = new League(competitors);

		assertNull(ldc.getHistoriqueMatchJoues()); // car non initialisee vant play()
		ldc.play();

		for (Competitor c : ldc.getHistoriqueMatchJoues().keySet()) {
			assertEquals(2, ldc.getHistoriqueMatchJoues().values().size());
		}

	}
	
	public static junit.framework.Test suite(){
        return new junit.framework.JUnit4TestAdapter(LeagueTest.class);
    }

}

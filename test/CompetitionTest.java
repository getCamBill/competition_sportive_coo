import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import core_match_classique.Competitor;



/**
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */

public class CompetitionTest {
	
	@Test
	public void testPointsJoueur() {
		/*
		 * This test allows you to check 2 points: 
		 * - at the beginning of the game all the players have 0 points
		 * - at the end of the game, the players have the number of points given.
		 * */
		
		MockCompetition competition = new MockCompetition();
		
		competition.setRanking(competition.getCompetitors().get(0));
		competition.setRanking(competition.getCompetitors().get(1));
		competition.setRanking(competition.getCompetitors().get(2));
		
		assertSame("Abitbol", competition.getCompetitors().get(0).getNomJoueur());
		assertSame(0, competition.ranking().get(competition.getCompetitors().get(0)));
		
		assertSame("Oss117", competition.getCompetitors().get(1).getNomJoueur());
		assertSame(0, competition.ranking().get(competition.getCompetitors().get(1)));
		
		assertSame("Luigi", competition.getCompetitors().get(2).getNomJoueur());
		assertSame(0, competition.ranking().get(competition.getCompetitors().get(2)));
		
		// Abitbol +2 pt
		competition.getCompetitors().get(0).addVictory(); 
		competition.getCompetitors().get(0).addVictory(); 
		competition.setRanking(competition.getCompetitors().get(0));
		// Luigi +1 pt
		competition.getCompetitors().get(2).addVictory(); 
		
		competition.setRanking(competition.getCompetitors().get(0));
		competition.setRanking(competition.getCompetitors().get(1));
		competition.setRanking(competition.getCompetitors().get(2));
		
		assertSame("Abitbol", competition.getCompetitors().get(0).getNomJoueur());
		assertSame(2, competition.ranking().get(competition.getCompetitors().get(0)));
		
		assertSame("Oss117", competition.getCompetitors().get(1).getNomJoueur());
		assertSame(0, competition.ranking().get(competition.getCompetitors().get(1)));
		
		assertSame("Luigi", competition.getCompetitors().get(2).getNomJoueur());
		assertSame(1, competition.ranking().get(competition.getCompetitors().get(2)));
	}
	
	
	@Test
	public void testRanking() {
		/*
		 * Allows you to check the ranking of the first and last player. 
		 * according to a given number of points
		 * */
		
		MockCompetition competition = new MockCompetition();

		// Abitbol +2 pt
		competition.getCompetitors().get(0).addVictory(); 
		competition.getCompetitors().get(0).addVictory(); 
		competition.setRanking(competition.getCompetitors().get(0));
		// Oss117
		competition.setRanking(competition.getCompetitors().get(1));
		// Luigi +1 pt
		competition.getCompetitors().get(2).addVictory();
		competition.setRanking(competition.getCompetitors().get(2));
		
		// recup le classement des joueurs -> liste 
		ArrayList<Competitor> playersList = new ArrayList<Competitor>(competition.ranking().keySet());

		assertSame("Abitbol",playersList.get(0).getNomJoueur()); // first 
		assertSame("Oss117",playersList.get(playersList.size() - 1).getNomJoueur()); // last
	}
		
	@Test
	public void testPlayMatch() {
		/*
		 * Allows to check that the playMatch method of competition
		 * */
		MockCompetition competition = new MockCompetition();
		Competitor c1 = competition.getCompetitors().get(0);
		Competitor c2 = competition.getCompetitors().get(1);
		
		assertSame(0, c1.getNbMatchJoue());
		assertSame(0, c2.getNbMatchJoue());
		
		competition.playMatch(c1,c2);
		
		// check that if player 1 has a victory the J2 has not yet won 
		// and vice versa
		assertTrue(((c1.getNbVictory() == 1 && c2.getNbVictory() == 0) || (c1.getNbVictory() == 0 && c2.getNbVictory() == 1)));
		
		assertSame(1, c1.getNbMatchJoue());
		assertSame(1, c2.getNbMatchJoue());
	}
	
	@Test
	public void testNbPlayers() {
		MockCompetition competition = new MockCompetition();
		assertEquals(3, competition.getNbPlayers());
	}
	
	public static junit.framework.Test suite(){
        return new junit.framework.JUnit4TestAdapter(CompetitionTest.class);
    }

}




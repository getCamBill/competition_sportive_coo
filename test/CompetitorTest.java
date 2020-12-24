import static org.junit.Assert.assertSame;

import org.junit.Test;

import core_match_classique.Competitor;


/**
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */ 
public class CompetitorTest {

	@Test 
	public void testGetNomJoueur() {
		Competitor competitor = new Competitor("Abitbol");
		assertSame("Abitbol", competitor.getNomJoueur());
	}
	
	@Test
	public void testEliminate() {
		Competitor competitor = new Competitor("Abitbol");
		assertSame(false,competitor.isElimine());
		competitor.eliminate();
		assertSame(true,competitor.isElimine());
	}
	
	@Test
	public void testAddVictory() {
		Competitor competitor = new Competitor("Abitbol");
		assertSame(0,competitor.getNbVictory());
		competitor.addVictory();
		assertSame(1,competitor.getNbVictory());
		competitor.addVictory();
		competitor.addVictory();
		competitor.addVictory();
		competitor.addVictory();
		assertSame(5,competitor.getNbVictory());
	}
	
	@Test
	public void testAddNbMatchsJoue() {
		Competitor competitor = new Competitor("Abitbol");
		assertSame(0, competitor.getNbMatchJoue());
		competitor.addMatchJoue();
		assertSame(1, competitor.getNbMatchJoue());
		competitor.addMatchJoue();
		competitor.addMatchJoue();
		competitor.addMatchJoue();
		competitor.addMatchJoue();
		assertSame(5, competitor.getNbMatchJoue());
	}
	
	public static junit.framework.Test suite(){
        return new junit.framework.JUnit4TestAdapter(CompetitorTest.class);
    }
}


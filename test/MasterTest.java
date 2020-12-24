import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import core_match_classique.Competitor;
import core_match_classique.Master;
import core_match_classique.MasterBuilder;
import core_match_classique.MasterException;

public class MasterTest {
	
	@Test
	public void testMaster() {
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Hulk"),	new Competitor("AntMan"),
				new Competitor("BlackWidow"), new Competitor("SpiderMan"));

		int nbPlayerParPool = 3;

		Master m1 = new MasterBuilder()
			.setCompetitors(competitors)
			.setNbPlayerParPool(nbPlayerParPool)
			.setSelection(1, true, 2, true) // les 2 premiers
			.build();
		
		assertEquals(3,m1.getNbPlayersPoule());
	}
	
	@Test(expected=MasterException.class)
	public void testSetSelection() {
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Hulk"),	new Competitor("AntMan"),
				new Competitor("BlackWidow"), new Competitor("SpiderMan"),
				new Competitor("Locky"), new Competitor("McFly"),
				new Competitor("Carlito"), new Competitor("IronMan"),
				new Competitor("Tod"), new Competitor("Luigi"));

		int nbPlayerParPool = 3;
		
		Master m1 = new MasterBuilder()
			.setCompetitors(competitors)
			.setNbPlayerParPool(nbPlayerParPool)
			.setSelection(1, true, 3, true) // les 3 premiers
			.build();
	}
	
	@Test(expected=MasterException.class)
	public void testNbJoueurSetSelection() {
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Hulk"),	new Competitor("AntMan"),
				new Competitor("BlackWidow"), new Competitor("SpiderMan"),
				new Competitor("Locky"), new Competitor("McFly"),
				new Competitor("Carlito"), new Competitor("IronMan"),
				new Competitor("Tod"), new Competitor("Luigi"));

		int nbPlayerParPool = 3;

		Master m1 = new MasterBuilder()
			.setCompetitors(competitors)
			.setNbPlayerParPool(nbPlayerParPool)
			.setSelection(1, true, 3, true) // les 3 premiers
			.setSelection(1, true, 2, true) // les 2 premiers
			.build();
	}
	
	@Test(expected=MasterException.class)
	public void testOutOfRangeSetSelection() {
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Hulk"),	new Competitor("AntMan"),
				new Competitor("BlackWidow"), new Competitor("SpiderMan"),
				new Competitor("Locky"), new Competitor("McFly"),
				new Competitor("Carlito"), new Competitor("IronMan"),
				new Competitor("Tod"), new Competitor("Luigi"));

		int nbPlayerParPool = 3;

		Master m1 = new MasterBuilder()
			.setCompetitors(competitors)
			.setNbPlayerParPool(nbPlayerParPool)
			.setSelection(3, true, 2, true) 
			.build();
	}
	
	@Test(expected=MasterException.class)
	public void testOutOfRange2SetSelection() {
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Hulk"),	new Competitor("AntMan"),
				new Competitor("BlackWidow"), new Competitor("SpiderMan"),
				new Competitor("Locky"), new Competitor("McFly"),
				new Competitor("Carlito"), new Competitor("IronMan"),
				new Competitor("Tod"), new Competitor("Luigi"));

		int nbPlayerParPool = 3;

		Master m1 = new MasterBuilder()
			.setCompetitors(competitors)
			.setNbPlayerParPool(nbPlayerParPool)
			.setSelection(-4, true, 2, true) 
			.build();
	}
	
	@Test
	public void testGetNbPlayersPoule() {
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Hulk"),	new Competitor("AntMan"),
				new Competitor("BlackWidow"), new Competitor("SpiderMan"));

		int nbPlayerParPool = 2;
		Master m1 = new MasterBuilder()
			.setCompetitors(competitors)
			.setNbPlayerParPool(nbPlayerParPool)
			.setSelection(0, true, 2, true) 
			.build();
		
		assertEquals(2,m1.getNbPlayersPoule());
	}

	@Test(expected=MasterException.class)
	public void testSetNbPlayersPoule() {
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Hulk"),	new Competitor("AntMan"),
				new Competitor("BlackWidow"), new Competitor("SpiderMan"));

		int nbPlayerParPool = 7;

		Master m1 = new MasterBuilder()
			.setCompetitors(competitors)
			.setNbPlayerParPool(nbPlayerParPool)
			.setSelection(0, true, 2, true) 
			.build();
	}
	
	@Test
	public void testPlay1() {
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Hulk"),	new Competitor("AntMan"),
				new Competitor("BlackWidow"), new Competitor("SpiderMan"),
				new Competitor("Locky"), new Competitor("McFly"),
				new Competitor("Carlito"), new Competitor("IronMan"),
				new Competitor("Tod"), new Competitor("Luigi"));

		int nbPlayerParPool = 3;
		int nbPlayerFinalPhase = 4;

		Master m1 = new MasterBuilder()
			.setCompetitors(competitors)
			.setNbPlayerParPool(nbPlayerParPool)
			.setSelection(0, true, nbPlayerFinalPhase, false) 
			.build();
		m1.play();
		
		assertTrue(nbPlayerFinalPhase == m1.ranking().keySet().size());
	}

	@Test
	public void testPlay2() {
		List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Hulk"),	new Competitor("AntMan"),
				new Competitor("BlackWidow"), new Competitor("SpiderMan"),
				new Competitor("Locky"), new Competitor("McFly"),
				new Competitor("Carlito"), new Competitor("IronMan"),
				new Competitor("Tod"), new Competitor("Luigi"));

		int nbPlayerParPool = 3;
		int nbPlayerFinalPhase = 4;

		Master m1 = new MasterBuilder()
			.setCompetitors(competitors)
			.setNbPlayerParPool(nbPlayerParPool)
			.setSelection(0, true, nbPlayerFinalPhase, false) 
			.build();
		m1.play();
		
		assertTrue(nbPlayerFinalPhase == m1.ranking().keySet().size());
		
	}

	public static junit.framework.Test suite(){
        return new junit.framework.JUnit4TestAdapter(MasterTest.class);
    }
	
}

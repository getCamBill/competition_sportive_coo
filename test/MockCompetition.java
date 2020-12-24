
import java.util.Arrays;
import java.util.List;

import core_match_classique.Competition;
import core_match_classique.Competitor;

/**
 * @author Cedric Bevilacqua, Camille Billouard
 *
 */
public class MockCompetition extends Competition {
	
	@Override
	protected void playMatch (Competitor c1, Competitor c2) {
		super.playMatch(c1, c2);
	}
	
	public MockCompetition() {
		super(Arrays.asList(new Competitor("Abitbol"),
				new Competitor("Oss117"),
				new Competitor("Luigi")));
	}
	
	public void setRanking(Competitor c) { super.setRanking(c); }

	@Override
	public void play() {
		
	}


}

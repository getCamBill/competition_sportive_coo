import static org.junit.Assert.assertTrue;

import org.junit.Test;

import core_match_classique.Competitor;
import core_match_classique.Match;

public class ObserverTest {

    @Test
    public void testNbListeners() {
        Match match = new Match();
        Competitor competitor1 = new Competitor("Abitbol");
        Competitor competitor2 = new Competitor("Oss117");
        match.setPlayer1(competitor1);
        match.setPlayer2(competitor2);
        assertTrue(0 == match.getMatchListeners().size());
        match.play();
        assertTrue(2 == match.getMatchListeners().size());
    }

    @Test
    public void testAddRemoveListeners() {
        Match match = new Match();
        Competitor competitor1 = new Competitor("Abitbol");
        Competitor competitor2 = new Competitor("Oss117");
        match.setPlayer1(competitor1);
        match.setPlayer2(competitor2);
        assertTrue(0 == match.getMatchListeners().size());
        MockBookmakers bb = new MockBookmakers();
        MockJournalist jj = new MockJournalist();
        match.addMatchListener(bb);
        match.addMatchListener(jj);
        match.play();
        assertTrue(4 == match.getMatchListeners().size());
        match.removeMatchListener(bb);
        assertTrue(3 == match.getMatchListeners().size());
        match.removeMatchListener(jj);
        assertTrue(2 == match.getMatchListeners().size());
        
    }

    @Test
    public void testCall() {
        Match match = new Match();
        Competitor competitor1 = new Competitor("Abitbol");
        Competitor competitor2 = new Competitor("Oss117");
        match.setPlayer1(competitor1);
        match.setPlayer2(competitor2);
        MockBookmakers bb = new MockBookmakers();
        MockJournalist jj = new MockJournalist();
        match.addMatchListener(bb);
        match.addMatchListener(jj);
        match.play();
        assertTrue(bb.verif);
        assertTrue(jj.verif);
    }

    @Test
    public void testPLayersExistBookMakersCoteList() {
        Match match = new Match();
        Competitor competitor1 = new Competitor("Abitbol");
        Competitor competitor2 = new Competitor("Oss117");
        match.setPlayer1(competitor1);
        match.setPlayer2(competitor2);
        MockBookmakers bb = new MockBookmakers();
        match.addMatchListener(bb);
        match.play();
        assertTrue(bb.getCompetCoteList(competitor1));
        assertTrue(bb.getCompetCoteList(competitor2));
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(ObserverTest.class);
    }
}

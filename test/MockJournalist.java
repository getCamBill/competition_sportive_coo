import core_match_classique.Journalistes;
import core_match_classique.MatchEvent;

public class MockJournalist extends Journalistes {
    

    public boolean verif = false;

    @Override
    public void matchResult(MatchEvent e) {
        super.matchResult(e);
        this.verif = true;
    }
}

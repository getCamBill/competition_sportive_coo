import core_match_classique.Bookmakers;
import core_match_classique.Competitor;
import core_match_classique.MatchEvent;

public class MockBookmakers extends  Bookmakers {
    

    public boolean verif = false;

    @Override
    public void matchResult(MatchEvent e) {
        
        super.matchResult(e);
        verif = true;
    }

    public int getSizeCoteList() {return super.getCotesListe().size();}

    public Boolean getCompetCoteList(Competitor c) { return super.getCotesListe().containsKey(c);}
}

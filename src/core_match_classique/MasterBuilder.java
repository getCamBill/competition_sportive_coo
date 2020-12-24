package core_match_classique;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is the builder of the Master class
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */ 
	
public class MasterBuilder {

    private List<Competitor> competitors;
    private List<List<Integer>> selection = new ArrayList<>();
    private int nbPlayerParPool;

    /**
	* Setter for the competitors of the future Master
	* @param competitors : Get a list of competitors to add in the master competition
	* @return Return the new version of the MasterBuilder itself
	*/
    public MasterBuilder setCompetitors(List<Competitor> competitors) {
        
        this.competitors = competitors;
        return this;
    }

    /**
	* Setter for the number of players in each pool of the future Master
	* @param nbPlayerParPool : Get the number of competitors of each pool of the Master
	* @return Return the new version of the MasterBuilder itself
	*/
    public MasterBuilder setNbPlayerParPool(int nbPlayerParPool) {
        this.nbPlayerParPool = nbPlayerParPool;
        return this;
    }

    /**
	* Adder for a new parameter of selection
	* @param posDepart : Rank from where we are starting (from the firsts or the lasts will be determined by startFrom)
	* @param startFrom : True if we start counting from the firsts and False if we start counting from the lasts
	* @param nbPlayers : Number of competitors we want in the result
	* @param getBests : True if we want the best of the category and False if we want the last of the category
	* @return Return the new version of the MasterBuilder itself
	*/
    public MasterBuilder setSelection(int posDepart, boolean startFrom, int nbPlayers, boolean getBests) {
        int startFromInt = (startFrom) ? 1 : -1;
        List<Integer> param = Arrays.asList(posDepart*startFromInt,nbPlayers,((getBests) ? 1 : 0)); 
        selection.add(param);

        return this;
    }

    /**
	* Build the Master instanciation with elements indicated
	* @return Return the new instance of Master, or null if the indications to build Master are false
	*/
    public Master build() {
        if((!this.selection.isEmpty()) && this.selection.size() != 0) { 
            return new Master(this.competitors, this.nbPlayerParPool, this.selection);
        }
        
        return null;
    }

    /**
	* Remove a param added in this builder
	* @param index : Index of the param to remove
	*/
    public void removeParam(int index) {
        selection.remove(index);
    }

    /**
	* Get a param added in this builder
	* @param index : Index of the param to get
	* @return Return the list of params asked for
	*/
    public List<Integer> getParam(int index) {
        return selection.get(index);
    }

}

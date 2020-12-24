package core_match_classique;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.MapUtil;

/**
 * This class is a type of Competition and make a Master 
 * which is composed of a first phase of pool corresponding 
 * to a League each and then according to the parameters of 
 * selections given the final phase corresponding to a Tournament.  
 * compose of e first part with .
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */ 
public class Master extends Competition {

	private League ldc;
	private Tournament garros;
	private int nbPlayersPoule;
	private List<List<Integer>> selection = new ArrayList<>();
	private ArrayList<Map<Competitor, Integer>> joueursSelection = new ArrayList<>();
	private List <Competitor> joueursPhaseFinale = new ArrayList<>();
	
	/**
	 * The constructor take a list of competitor.
	 * Use the super constructor (look up) to use attributes and insert new attributes.
	 * Then, make pools.
	 * @param competitors	It's the list of competitors subscribed to the competition
	 * @param nbPlayersPoule	Contain the number of players in each pool
	 * @param selection		Contain the settings of this Master competition
	 * 						This representation is the one given by the Master builder 
	 *  					[0,1,1]		  : the first
	 *  					[-1,1,0]	  : the last
	 * 						[1,3,1(Boolean)] : [the second one , we want 3 of them , 1 = the best one]
	 *  					[2,2,0(Boolean)] : [the third one  , we want 2 of them , 0 = the loosers]
	 */
	public Master(List<Competitor> competitors, int nbPlayersPoule, List<List<Integer>> selection) throws MasterException {
		super(competitors);
		this.setNbPlayersPoule(nbPlayersPoule);
		makeSelection(selection);
	}
	
	/**
	 * Play the League until that all the players have met
	 * @param selection		Contain the settings of this Master competition
	 */
	private void makeSelection(List<List<Integer>> selection) throws MasterException {
		
		int addition = 0;
		for(List<Integer> list : selection) {
			addition += list.get(1);
			if(list.get(0) >= this.nbPlayersPoule || list.get(0) < -this.nbPlayersPoule) {
				throw new MasterException("Nombre de joueurs inférieur au nombre  de joueurs du pool");
			}
			else if(list.get(2) != 1 && list.get(2) != 0) {
				throw new MasterException("La valeur est différented e 0 ou 1");
			}
		}
		//this.selection = selection; 
		if(!Tournament.isPowerOfTwo(addition)) {
			throw new MasterException("Le nombre de joueurs n'est pas une puissance de 2 : "  + addition);
		}
		this.selection = selection;
	}
	
	/**
	 * Play the Master
	 */
	@Override
	public void play() {
		
		//call crete poule
		ArrayList[] listePool = Tournament.createPool(super.getCompetitors(), getNbPlayersPoule());

		launchPhase1(listePool); // call League for each pool
		show(0);
		this.joueursPhaseFinale = selection(this.joueursSelection);
		show(1);
		// put victories on 0 for ranking
		for (Competitor c : this.joueursPhaseFinale) {c.setNbVictory(0);}
		
		//call tournament
		launchFinalStep();
		super.setMap(garros.ranking());
	}

	/**
	 * Get the ranked Competitor only from a list of ranked competitors with their rank
	 * @param listeRanking : A ranking list with the rank of each ranked Competitor
	 * @return Return a list of ranked Competitor
	 */
	protected List <Competitor> selection(ArrayList<Map<Competitor, Integer>> listeRanking) {
		for (List<Integer> param : this.selection) { 
			// reset player list
			List <Competitor> tempRank = new ArrayList<>();
			
			for (Map<Competitor, Integer> rank : listeRanking) {
				ArrayList<Competitor> playersList = new ArrayList<>(rank.keySet());
				int posi = (param.get(0) == -1) ? (playersList.size()-1) : param.get(0);
				tempRank.add(playersList.get(posi)); 
			}
			
			// extract the good player (depend of the param)
			this.joueursPhaseFinale.addAll(extraction(tempRank, param));
		}
		return this.joueursPhaseFinale;
	}
	
	/**
	 * Take a list of competitors and filter them depending of the parameter set
	 * @param tempRank : The rankg after applying this param
	 * @param param	: The next param to apply
	 * @return Extracted competitors after applying the param
	 */
	protected List<Competitor> extraction(List<Competitor> tempRank, List<Integer> param) {
		
		// init 
		Map<Competitor, Integer> playersKept = new HashMap<>();
		
		// bad, but recreation d'une map pour call sortByDescendingValue 
		for (Competitor c : tempRank) { playersKept.put(c, c.getNbVictory()); }
		// bad, again on recast en list ...
		ArrayList<Competitor> playersList = new ArrayList<>(MapUtil.sortByDescendingValue(playersKept).keySet());
		
		// init
		List <Competitor> ectractPlayers = new ArrayList<>();
		
		if (param.get(2) == 0) { // on prends les mauvais et on reverse la liste sinon rien 
			Collections.reverse(playersList); 			
		}
		// on recupere les n premier Joueurs de la liste 
		int n = param.get(1);
		
		for (int i = 0; i < n; i++) {ectractPlayers.add(playersList.get(i));}
		
		return ectractPlayers;
	}
	
	/**
	 * Take a list of pool's competitors and play each one
	 * @param listePool	
	 */
	private void launchPhase1(ArrayList[] listePool) {
		
		// call ranking + selection
		for (List<Competitor> poule : listePool) {
			ldc = new League(poule);
			ldc.play();
			joueursSelection.add(ldc.ranking());
		}
	}
	
	/**
	 * Launch final step which means a Tournaments
	 */
	private void launchFinalStep() {
		garros = new Tournament(this.joueursPhaseFinale);
		garros.play(); 
	}
	
	/**  
	 * Show the different step of the Master
	 * @param n : Is 1 to print the Master trace and 2 to print the ranking
	 */
	public void show(int n) {
		
		if (n == 0) {
			System.out.println('\n'+" ******** DEBUT DU MASTER ******** ");
			System.out.println('\n'+" *** Phase 1 *** ");
			int i = 1;
			for (Map<Competitor, Integer> temp : joueursSelection) {
				System.out.println("\n***Ranking joueurs poule " +i+" : ********");
				for (Competitor c : temp.keySet()) {
					System.out.println(c.getNomJoueur()+ " - " + c.getNbVictory());
				}
				n++;
				i++;
			}
		}
		else if (n == 1){
			System.out.println('\n'+" *** Ranking : phase 1 *** ");
			for (Competitor c : this.joueursPhaseFinale) {
				System.out.println(c.getNomJoueur() +" - " + c.getNbVictory());
			}
			System.out.println('\n'+" *** Phase Finale *** ");
		}
	}

	/**
	 * Getter of the attribute number of players for each pool
	 * @return Return the number of players for each pool
	 */
	public int getNbPlayersPoule() {
		return nbPlayersPoule;
	}

	/**
	 * Change the attribute of the number of players for each pool
	 * @param nbPlayersPoule	New number of players for each pool to apply	
	 */
	public void setNbPlayersPoule(int nbPlayersPoule) throws MasterException {
		if (nbPlayersPoule > super.getNbPlayers()) {
			throw new MasterException("Nombre de joueurs par pool superieur au nombre total");
		} else {
			this.nbPlayersPoule = nbPlayersPoule;
		}
	}
	
}

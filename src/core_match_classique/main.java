package core_match_classique;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is the main class of this project and is the starting point of the program.
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class main {
	
	/**
	 * This main method is started at the beginning of the program. 
	 * His goal is to use the different classes to make a demo of the programm.
	 * @param args		Contain the arguments given at the start of the program but it's unused for the moment
	 */
	public static void main (String[] args) {
	 	List<Competitor> competitors = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Hulk"),	new Competitor("AntMan"),
				new Competitor("BlackWidow"), new Competitor("SpiderMan"),
				new Competitor("Locky"), new Competitor("McFly"));
		
		//Play a tournament
		System.out.println("  ****** Play a tournament  ****** ");
		Tournament tournoi = new Tournament(competitors);
		tournoi.play();
		System.out.println('\n'+" *** Ranking : "+ tournoi.getClass().getSimpleName() +" *** ");
		for (Competitor c : tournoi.ranking().keySet()) {
			System.out.println(c.getNomJoueur() +" - " + tournoi.ranking().get(c));
		}
		
		//Play a league
		System.out.println("  ****** Play a league  ****** ");
		League league = new League(competitors);
		league.play();
		System.out.println('\n'+" *** Ranking : "+ league.getClass().getSimpleName() +" *** ");
		for (Competitor c : league.ranking().keySet()) {
			System.out.println(c.getNomJoueur() +" - " + league.ranking().get(c));
		}
		
		// Play a Master
		List<Competitor> competitors2 = Arrays.asList(
				new Competitor("Abitbol"), new Competitor("Oss117"),
				new Competitor("Hulk"),	new Competitor("AntMan"),
				new Competitor("BlackWidow"), new Competitor("SpiderMan"),
				new Competitor("Locky"), new Competitor("McFly"),
				new Competitor("Carlito"), new Competitor("IronMan"),
				new Competitor("Tod"), new Competitor("Luigi"));
		
		// nombre de joueur par pool sous reserve que : 
		//	- 
		//	- cela donne une puissance de deux pour la phase Finale
		int nbPlayerParPool = 3; 
		int all = competitors2.size() / nbPlayerParPool; // tous les joueurs 

		/* // (postition , nbJoueurs a cette position, les best si 1 sinon les last)
		List<List<Integer>> selection = new ArrayList<>();
		List<Integer> param1 = Arrays.asList(0,all,1); // les 4 premiers
		selection.add(param1);		// liste[-1] = 1
		List<Integer> param2 = Arrays.asList(-1,all,1);// les 4 derniers
		selection.add(param2); 
		Master m1 = new Master(competitors2, nbPlayerParPool, selection);*/

		Master m1 = new MasterBuilder()
			.setCompetitors(competitors2)
			.setNbPlayerParPool(nbPlayerParPool)
			.setSelection(1, true, all, true) // les 4 premiers
			.setSelection(0, false, all, false) // les 4 derniers
			.build(); 

		m1.play();
		System.out.println('\n'+" *** Ranking : "+ m1.getClass().getSimpleName() +" *** ");
		for (Competitor c : m1.ranking().keySet()) {
			System.out.println(c.getNomJoueur() +" - " + m1.ranking().get(c));
		}
	
	}
	
}

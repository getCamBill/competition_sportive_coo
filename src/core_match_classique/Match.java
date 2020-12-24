package core_match_classique;

import java.util.ArrayList;

/**
 * This class represent a match with two rivals and the 
 * fight between them and the winner of this match.
 * We use a default constructor because we can create a 
 * new instance of Match without any particular operation or change in the attributes.
 * @author Cedric Bevilacqua, Camille Billouard
 * @version 1.0
 */
public class Match {

	private Competitor player1;
	private Competitor player2;
	private Competitor winner;
	private Competitor looser;

	private ArrayList<MatchListener> matchListeners = new ArrayList<MatchListener>();
	private Journalistes j = new Journalistes();
	private Bookmakers b = new Bookmakers();
	
	/**
	 * Add a match listner to get all the informations of a Match at any moment
	 * @param m : The MatchListener 
	 */
	public synchronized void addMatchListener(MatchListener m) {
		if (!matchListeners.contains(m)) { matchListeners.add(m); }
	}

	/**
	 * Remove a listener in this instance of Match
	 * @param m : The MatchListener 
	 */
	public synchronized void removeMatchListener(MatchListener m) {
		matchListeners.remove(m);
	}

	/**
	 * Create an event and put it in every listener
	 * @author Cédric Bevilacqua, Camille Billouard
	 */
	private void fireMatch() {
		ArrayList<MatchListener> ml = (ArrayList<MatchListener>) matchListeners.clone();
		if (!ml.isEmpty()) {
			MatchEvent event = new MatchEvent(this);
			for (MatchListener listener: ml) {
				listener.matchResult(event);
			}
		}
	}

	/**
	 * This accessor return the Competitor instance which 
	 * represent the player 1 of this instance of Match.
	 * @return Return the player 1 Competitor of this Match
	 */
	public Competitor getPlayer1() {
		return player1;
	}
	
	/**
	 * This accessor set an instance of Competitor in player 1 
	 * for this instance of Match.
	 * @param player1 : The Competitor player 1 against the player 2 is going to play
	 */
	public void setPlayer1(Competitor player1) {
		this.player1 = player1;
	}

	/**
	 * This accessor return the Competitor instance which represent 
	 * the player 2 of this instance of Match.
	 * @return Return the player 2 Competitor of this Match
	 */
	public Competitor getPlayer2() {
		return player2;
	}

	/**
	 * This accessor return the Competitor instance which has win the Match
	 * @return Competitor : Competitor instance who win this Match
	 */
	public Competitor getWinner() {
		return this.winner;
	}

	/**
	 * This accessor return the Competitor instance which has lost the Match
	 * @return Competitor : Competitor instance who loose this Match
	 */
	public Competitor getLooser() {
		return this.looser;
	}

	public ArrayList<MatchListener> getMatchListeners(){
		return this.matchListeners;
	}

	/**
	 * This accessor set an instance of Competitor in player 2 for 
	 * this instance of Match.
	 * @param player2 : The Competitor player 2 against the player 1 is going to play
	 */
	public void setPlayer2(Competitor player2) {
		this.player2 = player2;
	}
	
	/**
	 * This method play a Match to get a winner and put him in 
	 * the winner attribute. The winner Competitor's instance will be updated.
	 * The rate of win between the two players are 50%. 
	 * The Match will be printed in the terminal.
	 */
	public void play() throws UnautorizedPlayersMatchException {
		if (player1 != player2) { //Check if players are not the same

			addMatchListener(this.j); // journaliste 
			addMatchListener(this.b); // bookmakers 
			
			decideOfTheWinner();
			addPlayedMatchesInCompetitors();
			printMatch();		

			
		}
		else { throw new UnautorizedPlayersMatchException("Same players"); }
	}
	
	/**
	 * This method use a random of 50% rate to choose a winner when the match is played.
	 * @see this#giveTheWin(Competitor, Competitor)
	 */
	private void decideOfTheWinner() {
		if((float) Math.random() > 0.5) { giveTheWin(player1, player2); }
		else { giveTheWin(player2, player1); }
	}
	
	/**
	 * This method update the status number of victories of the winner and the looser player.
	 * It also put the winner in the attribute winner of the instance of Match.
	 * @param winner	Represent the Competitor instance which have win the match
	 * @param looser	Represent the Competitor instance which have lost the match
	 */
	private void giveTheWin(Competitor winner, Competitor looser) {
		winner.addVictory();
		looser.eliminate();
		this.winner = winner;
		this.looser = looser;
		fireMatch();
	}
	
	/**
	 * This method update the status of the number of played matches in the two players.
	 */
	private void addPlayedMatchesInCompetitors() {
		player1.addMatchJoue();
		player2.addMatchJoue();
	}
	/**
	 * Print in the terminal that a match has run witch the 
	 * name of the two players and the name of the winner.
	 */
	private void printMatch() { fireMatch();}
	
}

package core;

import org.junit.Test;

import junit.framework.TestCase;

public class GameControllerTest extends TestCase {
	
	
	public void testInvalidFile() {
		GameController game = new GameController("invalidfilename");
		//will pass as long as there is no error.
	}
	
	@Test
	public void testPlayerWinsInitially(){
		GameController game = new GameController("initialBlackjackPlayer.txt");
		assertTrue(game.getWinner().equals("Player"));	
		assertTrue(game.showHand().equals("Player: SQ CA | Dealer: S2 S5"));
	}

	@Test
	public void testDealerAndPlayerGetBlackjack(){
		GameController game = new GameController("initialBlackjackDealer.txt");
		assertTrue(game.getWinner().equals("Dealer"));	
		assertTrue(game.showHand().equals("Player: SQ CA | Dealer: DK SA"));
	}
	
	@Test
	public void testPlayerWinsMultipleHits(){
		GameController game = new GameController("playerHits.txt");
		assertTrue(game.getWinner().equals("Player"));	
		assertTrue(game.showHand().equals("Player: S2 S5 S3 S8 SA | Dealer: CK C8"));
		assertTrue(game.getFinalScore().equals("Player: 19 | Dealer: 18"));
	}
	
	@Test
	public void testPlayerWinsDealerBust(){
		GameController game = new GameController("dealerBust.txt");
		assertTrue(game.getWinner().equals("Player"));	
		assertTrue(game.showHand().equals("Player: S2 S5 S3 S8 | Dealer: C3 C2 DK DJ"));
		assertTrue(game.getFinalScore().equals("Player: 18 | Dealer: BUST"));
	}
	
	@Test
	public void testDealerWins() {
		GameController game = new GameController("dealerWins.txt");
		assertTrue(game.getWinner().equals("Dealer"));	
		assertTrue(game.showHand().equals("Player: S2 S5 | Dealer: C3 C2 DK D4"));
		assertTrue(game.getFinalScore().equals("Player: 7 | Dealer: 19"));
	}
		
	@Test
	public void testPlayerBlackjackAfterManyHits(){
		GameController game = new GameController("playerHitsBlackjack.txt");
		assertTrue(game.getWinner().equals("Player"));	
		assertTrue(game.showHand().equals("Player: S2 S5 S3 S8 D3 | Dealer: C10 C7"));
		assertTrue(game.getFinalScore().equals("Player: BLACKJACK | Dealer: 17"));
	}
	
	@Test
	public void testDealerBlackjackAfterHits() {
		GameController game = new GameController("dealerHitsBlackjack.txt");
		assertTrue(game.getWinner().equals("Dealer"));	
		assertTrue(game.showHand().equals("Player: S2 S5 S3 S8 C3 | Dealer: D3 D2 C4 C6 D6"));
	}
	
	@Test 
	public void testPlayerBust() {
		GameController game = new GameController("playerBust.txt");
		assertTrue(game.getWinner().equals("Dealer"));	
		assertTrue(game.showHand().equals("Player: S2 S5 S3 S8 DQ | Dealer: D2 C3"));
	}
		
	@Test 
	public void testTieGame() {
		GameController game = new GameController("tieGame.txt");
		assertTrue(game.getWinner().equals("Dealer"));	
		assertTrue(game.showHand().equals("Player: S2 S5 S3 S10 | Dealer: D2 D5 D3 D10"));
		assertTrue(game.getFinalScore().equals("Player: 20 | Dealer: 20"));
	}	
	
}
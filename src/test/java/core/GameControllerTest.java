package core;

import org.junit.Test;

import junit.framework.TestCase;

public class GameControllerTest extends TestCase {
	
	
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
	

	public void testPlayerSplitFirstHandWins() {
		GameController game = new GameController("playerSplit1.txt");
		assertTrue(game.getWinner().equals("Player"));	
		assertTrue(game.showHand().equals("Player: H5 HA H4 | Dealer: C2 C3 DQ D4"));
		assertTrue(game.getFinalScore().equals("Player: 20 | Dealer: 19"));
	}
	
	public void testPlayerSplitLoses() {
		GameController game = new GameController("playerSplit2.txt");
		assertTrue(game.getWinner().equals("Dealer"));	
		assertTrue(game.showHand().equals("Player: C5 HJ H2 | Dealer: C2 C3 DQ D4"));
		assertTrue(game.getFinalScore().equals("Player: 17 | Dealer: 19"));
	}
	
	//how blackjack works splitting?
	public void testPlayerSplitBlackjack() {
		GameController game = new GameController("playerSplit3.txt");
		assertTrue(game.getWinner().equals("Player"));
		assertTrue(game.showHand().equals("Player: H5 HA S5 | Dealer: C2 C3 DQ D4"));
		assertTrue(game.getFinalScore().equals("Player: BLACKJACK | Dealer: 19"));
	}
	
	public void testplayerSplitting() {
		GameController game = new GameController("playerSplit4.txt");
		assertTrue(game.getFinalScore().equals("Player: 20 | Dealer: 19"));
	}

	public void testPlayerSplittingStandsImmediately() {
		GameController game = new GameController("playerSplit5.txt");
		assertTrue(game.getWinner().equals("Dealer"));	
		assertTrue(game.showHand().equals("Player: H5 HQ | Dealer: C2 C3 DQ D4"));
		assertTrue(game.getFinalScore().equals("Player: 15 | Dealer: 19"));
	}
	
	public void testDealerDoesNotSplitIfOver17() {
		GameController game = new GameController("dealerSplit1.txt");
		assertTrue(game.getWinner().equals("Dealer"));	
		assertTrue(game.showHand().equals("Player: H5 C5 | Dealer: H9 C9"));
		assertTrue(game.getFinalScore().equals("Player: 10 | Dealer: 18"));
	}
	
	public void testDealerCanSplitWithBust() {
		GameController game = new GameController("dealerSplit2.txt");
		assertTrue(game.getWinner().equals("Player"));	
		assertTrue(game.showHand().equals("Player: SK H9 | Dealer: D5 CQ D2"));
		assertTrue(game.getFinalScore().equals("Player: 19 | Dealer: 17"));
	}
	
	public void testDealerCanSplitAndWin() {
		GameController game = new GameController("dealerSplit3.txt");
		assertTrue(game.getWinner().equals("Dealer"));	
		assertTrue(game.showHand().equals("Player: HQ H6 | Dealer: S5 D8 D7"));
		assertTrue(game.getFinalScore().equals("Player: 16 | Dealer: 20"));
		
	}

	public void testDealerANDPlayerSplitBlackjack() {
		GameController game = new GameController("dealerSplit4.txt");
		assertTrue(game.getWinner().equals("Dealer"));	
		assertTrue(game.showHand().equals("Player: H10 HA | Dealer: S5 DA D5"));
		assertTrue(game.getFinalScore().equals("Player: BLACKJACK | Dealer: BLACKJACK"));
	}
	
	public void testDealerANDPlayerSplitDealerLoses() {
		GameController game = new GameController("dealerSplit5.txt");
		assertTrue(game.getWinner().equals("Player"));	
		assertTrue(game.showHand().equals("Player: C10 D10 | Dealer: S5 CK C3"));
		assertTrue(game.getFinalScore().equals("Player: 20 | Dealer: 18"));
	}
	
}
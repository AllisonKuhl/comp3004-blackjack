package core;

import org.junit.Test;

import junit.framework.TestCase;

public class SplitTest extends TestCase {
	

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
		assertTrue(game.showHand().equals("Player: C10 H10 | Dealer: S5 D3 DQ"));
		assertTrue(game.getFinalScore().equals("Player: 20 | Dealer: 18"));
	}
	
	public void testDealerSplit() {
		Player dealer = new Player();
		dealer.addCards(new String[] {"S5","C5"});
		assertTrue(dealer.chooseDealerMove().equals("d"));
	}
	
	public void testDealerSplitCardsOver17() {
		Player dealer = new Player();
		dealer.addCards(new String[] {"S9","C9"});
		assertTrue(dealer.chooseDealerMove().equals("s"));
	}
		
	public void testChooseBestHand() {
		Player player = new Player();
		player.addCards(new String[] {"C5", "S5"});
		player.split();
		player.addCard("D6");
		player.addSplitCard("H2");
		player.addCards(new String[] {"S3", "S6"});
		player.getSecondHand();	
		player.addCards(new String[] {"SJ", "S2"});
		player.chooseBestHand();
		assertTrue(player.getScore() == 20);
		assertTrue(player.showHand().equals("C5 D6 S3 S6"));
		
	}
	
	public void testChooseBestHandSecond() {
		Player player = new Player();
		player.addCards(new String[] {"SK", "HK"});
		player.split();
		player.addCard("H6");
		player.addSplitCard("C5");
		player.addCard("D3");
		player.getSecondHand();

		player.addCard("D5");
		System.out.println(player.getScore());
		System.out.println(player.showHand());
		player.chooseBestHand();
		assertTrue(player.getScore() == 20);
		
	}
	
	public void testCanSplit() {
		Player player1 = new Player();
		player1.addCards(new String[] {"HK", "DK"});
		assertTrue(player1.canSplit());
		Player player2 = new Player();
		player2.addCards(new String[] {"HK", "H10"});
		assertFalse(player2.canSplit());
		Player player3 = new Player();
		player3.addCards(new String[] {"HK","CK","D5"});
		assertFalse(player3.canSplit());
	}
	
	@Test
	public void testCreateSecondHand() {
		Player player = new Player();
		player.addCards(new String[] {"C5", "S5"});
		player.split();
		player.addCards(new String[] {"SA", "SQ", "S4"});
		player.addSplitCard("H5");
		assertTrue(player.showHand().equals("C5 SA SQ S4"));
		player.getSecondHand();	
		assertTrue(player.getSplitScore() == 20);
		player.addCards(new String[] {"S2","S3"});
		assertTrue(player.getScore() == 15);
		assertTrue(player.showHand().equals("S5 H5 S2 S3"));
		
	}
	

}

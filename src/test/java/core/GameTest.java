package core;

import org.junit.Test;

import junit.framework.TestCase;

public class GameTest extends TestCase {
	
	
	//tests to see if players are properly initialized with starting hand
	
	@Test 
	public void testInitialHandFile() {
		Game fileGame = new Game("test1.txt");
		
		fileGame.initializeHands();
		String hand = fileGame.showHands();
		
		String expected = "Player: SK HQ | Dealer: SQ X";
		assertTrue(hand.equals(expected));	
		
	}
	
	@Test
	public void testInitialHandConsole() {
		
		Game consoleGame = new Game();
				
		//reads first card from deck
		//this should be the same as the players first card
		String topCard = consoleGame.getFirstCard();
		
		consoleGame.initializeHands();		
		String hand = consoleGame.showHands();
		
		//get first card from consoleHand
		String firstCard = hand.substring(8, hand.indexOf(' ', 8));	
		assertTrue(firstCard.equals(topCard));
		
		//check last card is not displayed
		
		assertTrue(hand.charAt(hand.length()-1)=='X');		
		
			
	}
	
	@Test
	public void testCalculateScore() {
		Player player = new Player();
						
		//ordinary case
		player.addCards(new String[] {"H5","S2"});
		player.calculateScore();
		assertTrue(player.getScore()==7);
		
		//add one ace where counts as 11
		player.addCard("HA");
		player.calculateScore();
		assertTrue(player.getScore()==18);
		
		//Ace now counts as 1
		player.addCard("HK");
		player.calculateScore();
		assertTrue(player.getScore()==18);
		
		//behaviour when there are two aces (no splitting) both == 1
		player.addCard("CA");
		player.calculateScore();
		assertTrue(player.getScore()==19);		
	}
	
	@Test
	public void testTwoAcesDifferentValues() {
		Player player = new Player();
		//SA = 11
		//add S5 = 16
		//add CA. will be bust if 11 so value should be 1
		//score is now 17
		//add another ace. DA. score is 18
		//add final ace, HA. score is 19
		player.addCards(new String[] {"SA", "S5", "CA", "DA","HA"});
		assertTrue(player.getScore()==19);
		//finally, if we add an 8, the ace that was once 11 should now be counted as 1
		//for a total of: 1 + 5 + 1 + 1 + 1 + 8 = 17
		player.addCard("S8");
		assertTrue(player.getScore()==17);
	}
	
	@Test
	public void testPlayerBust() {
		Player player = new Player();
		player.addCards(new String[] {"S8","S9", "SK"});// 8+9 = 17 + 10 = 27
		player.calculateScore();
		assertTrue(player.isBust());		
	}
	
	@Test
	public void testPlayerGetsBlackjack() {
		
		Player player = new Player();
		
		//calculates blackjack;
		player.addCards(new String[] {"SA", "SK"});
		player.calculateScore();
		assertTrue(player.hasBlackjack());
		
	}
	
	@Test
	public void testInitialPlayerBlackjack() {
		Game testGame = new Game("initialBlackjackPlayer.txt");	
		testGame.initializeHands();
		testGame.calculateWinner();
		assertTrue(testGame.playerHasBlackjack());
		assertTrue(testGame.getWinner().equals("Player"));
	}
	
	@Test
	public void testInitialDealerBlackjack() {
		Game testGame = new Game("initialBlackjackDealer.txt");
		testGame.initializeHands();
		testGame.calculateWinner();
		assertTrue(testGame.dealerHasBlackjack());
		assertTrue(testGame.getWinner().equals("Dealer"));
	}

	@Test
	public void testPlayerHit() {
		//file: S2 HQ SQ H5 H S5
		Game game = new Game("playerHit.txt");
		game.initializeHands();
		//will read hit from file
		game.nextTurn();
		assertTrue(game.getPlayerScore()==17);	
		String expected = "Player: S2 HQ S5 | Dealer: SQ X";	
		assertTrue(game.showHands().equals(expected));
	}
	
	public void testPlayerStand() {
		//file: S2 HQ SQ H5 S
		Game game = new Game("playerStand.txt");
		game.initializeHands();
		//will read stand from file
		game.nextTurn();
		assertTrue(game.getPlayerScore()==12);	
		String expected = "Player: S2 HQ | Dealer: SQ X";	
		assertTrue(game.showHands().equals(expected));
		//if stand then it should be dealers turn next
		assertTrue(game.whoseTurn()==1);
	}
	
	@Test
	public void testDealerHit() {
		Player dealer = new Player();
		System.out.println("dealer hit");
		dealer.addCards(new String[] {"S5","C7"});		
		assertTrue(dealer.hitOrStand());		
	}
		
	@Test 
	public void testDealerSoftHit() {
			Player dealer = new Player();
			dealer.addCards(new String[] {"S4","C2","DA"});
			assertTrue(dealer.hitOrStand());
		}
	
	@Test
	public void testDealerNonsoftSeventeen() {
		Player dealer = new Player();
		dealer.addCards(new String[] {"S5","C2","D10"});
		assertFalse(dealer.hitOrStand());
	}
			
	@Test
	public void testDealerStand() {
		Player dealer = new Player();
		dealer.addCards(new String[] {"S5","C2","DA","D2"});
		assertFalse(dealer.hitOrStand());
	}
	

	
}

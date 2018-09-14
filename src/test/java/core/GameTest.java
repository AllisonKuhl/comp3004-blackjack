package core;

import org.junit.Test;

import junit.framework.TestCase;

public class GameTest extends TestCase {
	
	@Test
	//tests to see if players are properly initialized with starting hand
	public void testInitialHand() {
		Game consoleGame = new Game("c");
		Game fileGame = new Game("f");	
		
		String consoleHand = consoleGame.showHand();
		String fileHand = fileGame.showHand();
		
		String expected = "Player hand: SK HQ | Dealer Hand: SQ X";
		
		//regardless of content of hands, the randomized hand from the console should be the same length as expected
		assertTrue(consoleHand.length = expected);
		
		assertTrue(fileHand.equals(expected));
			
	}
	
	public void testCalculateScore() {
		Player player = new Player();
						
		//ordinary case
		player.addCards(["H5","S2"]);
		assertTrue(player.calculateScore()==7);
		
		//add one ace where counts as 11
		player.addCard("HA");
		assertTrue(player.calculateScore()==18)
		
		//Ace now counts as 1
		player.addCard("HK");
		assertTrue(player.calculateScore()==18);
		
		//behaviour when there are two aces (no splitting)
		player.addCard("CA");
		assertTrue(player.calculateScore()==19);
		
		//player busts when over 21;
		player.addCard("SQ");
		player.calculateScore();
		assertTrue(player.getBust());
		
	}
	

	
}

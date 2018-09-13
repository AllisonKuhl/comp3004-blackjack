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
		Hand testScore = new Hand();
		
		testScore.add("SK").add("H2");
		
		assertTrue(testScore.getScore() == 12);
		
		testScore.add("S1").add("H5")
		
		assertTrue(testScore.getScore()==18);
		
		
	}
	

	
}

package core;

import org.junit.Test;

import junit.framework.TestCase;

public class DeckTest extends TestCase {
	
	@Test
	//tests to see if a randomized deck of cards is created properly
	public void testDeck() {
		Deck testDeck = new Deck();
		
		//make sure deck of cards contains total of 52 cards
		assertTrue(testDeck.getLength() == 52);
		
		//see if it contains certain cards
		assertTrue(testDeck.contains("HA"));
		assertTrue(testDeck.contains("C5"));
		
		//make sure it is randomized (i.e. if another is created it won't be the same order)
		Deck testDeck2 = new Deck();
		assertFalse(testDeck.isSameAs(testDeck2));
		
	}
	
	@Test 
	public void testReadFileInput() {
		Game game = new Game("test1.txt");
		System.out.println(game.inputToString());
		assertTrue(game.inputToString().equals("SK HQ SQ C5 H DJ"));
	}
	
	

}

package core;

import junit.framework.TestCase;

public class DeckTest extends TestCase {
	
	
	//tests to see if a randomized deck of cards is created properly
	public void RandomDeckTest() {
		Deck testDeck = new Deck();
		
		//make sure deck of cards contains total of 52 cards
		assertTrue(testDeck.getLength() == 52);
		
		//see if it contains certain cards
		assertTrue(testDeck.contains("HA"));
		assertTrue(testDeck.contains("C5"));
		
		//make sure it is randomized (i.e. if another is created it won't be the same order)
		Deck testDeck2 = new Deck();
		assertTrue(testDeck1 != testDeck2);
		
	}
	

}

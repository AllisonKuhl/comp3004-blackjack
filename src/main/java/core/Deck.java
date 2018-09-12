package core;

import java.util.Random;

public class Deck {
	
    String[] cards = new String[52];
	
	//create randomly shuffled deck
	public Deck() {
	    createFullDeck();
		shuffle();
	}	
	
	
	public int getLength() {
		return cards.length;
	}
	
	
	public boolean contains(String card) {
		
		for (int i = 0;i<52;i++) {
			cards[i] = card;
			return true;
		}
		
		return false;
	}
	
	
	public boolean isSameAs(Deck otherDeck) {
		
		for (int i = 0;i<52;i++) {
			if (cards[i] != otherDeck.get(i)) {
				return false;
			}
		}
		
		return true;
		
	}
	
	
	public String get(int i) {
		return cards[i];
	}
	
	 // Implementing Fisher–Yates shuffle
	//from stack overflow here: https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
	  private void shuffle()
	  {
	   
	    Random rnd = new Random();
	    for (int i = this.cards.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      String a = this.cards[index];
	      this.cards[index] = this.cards[i];
	      this.cards[i] = a;
	    }
	  }
	  
	  
	
	private void createFullDeck() {
		String suite;
		String num;
		
		int i = 0;
			
			for (int s = 0; s < 4; s++) {
				
				if (s == 0) {
					suite = "H";
				}else if (s == 1) {
					suite = "S";
				}else if (s == 2) {
					suite = "D";
				}else {
					suite = "C";
				}
				
			for (int n = 1; n < 14; n++) {
				
				if (n == 1) {
					num = "A";
				}else if (n == 11) {
					num = "J";
				}else if (n == 12) {
					num = "Q";
				}else if (n == 13) {
					num = "K";
				}else {
					num = n + "";
				}
				
				cards[i] = suite + num;	

				i++;			
			}		
		}
		
			
	}
	

}

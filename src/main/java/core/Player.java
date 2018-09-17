package core;

import java.util.Iterator;
import java.util.LinkedList;

public class Player {
	
	LinkedList<Card> hand = new LinkedList<Card>();
	boolean bust = false;
	boolean blackjack = false;
	int score = 0;
	
	
	public void addCard(String card) {
		hand.add(new Card(card));
		calculateScore();
	}
	
	public void addCards(String[] arr) {
		for (int i = 0; i < arr.length;i++) {
			hand.add(new Card(arr[i]));
		}
	}
	
	public void calculateScore() {
		
		int total = 0;
		int aces = 0;
		int current;
	
		
		Iterator<Card> Iterator = hand.iterator();
		while (Iterator.hasNext()) {
		   current = Iterator.next().getValue();
		   total += current;
		   if (current == 11) {
			   aces += 1;
		   }
		}
		
		//will change aces to 1 if over 21
		while (aces>0 && total > 21) {
			total-=10;
			aces-=1;
		}
		
		if (total>21) {
			bust = true;
		}else if (total==21) {
			blackjack = true;
		}
		
		score = total;
				
	}
	
	
	public boolean  hitOrStand() {
		int total = 0;
		int aces = 0;
		int current;
		
		Iterator<Card> Iterator = hand.iterator();
		while (Iterator.hasNext()) {
		   current = Iterator.next().getValue();
		   total += current;
		   if (current == 11) {
			   aces += 1;
		   }
		}
		//will change aces to 1 if over 21
		while (aces>0 && total > 21) {
			total-=10;
			aces-=1;
		}
		System.out.println("here we are");
		System.out.println(total);
		if (total == 17 && aces >= 1) {
			return true;
		}else if (total <= 16) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public String showHand() {

		return hand.toString().replaceAll(",","").replace("[","").replaceAll("]", "");
	
	}

	
	
	public int getScore() {
		return score;
	}
	
	public boolean isBust() {
		return bust;
	}
	
	public boolean hasBlackjack() {
		return blackjack;
	}
	

}

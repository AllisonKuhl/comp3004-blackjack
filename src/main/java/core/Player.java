package core;

import java.util.Iterator;
import java.util.LinkedList;

public class Player {
	
	LinkedList<Card> hand = new LinkedList<Card>();
	LinkedList<Card> splitHand = new LinkedList<Card>();
	boolean bust = false;
	boolean blackjack = false;
	int score = 0;
	int split = 0;
	int splitScore = 0;
	
	public void addCard(String card) {
		hand.add(new Card(card));
		calculateScore();
	}
	
	public void addCards(String[] arr) {
		for (int i = 0; i < arr.length;i++) {
			hand.add(new Card(arr[i]));
		}
		calculateScore();
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
	
	public boolean canSplit() {
		String firstCard = hand.get(0).toString();
		String secondCard = hand.get(1).toString();
		if (hand.size()==2) {
			return firstCard.substring(1,firstCard.length()).equals(secondCard.substring(1,secondCard.length()));
		}
		return false;
	}
	
	public void addSplitCard(String card) {
		splitHand.add(new Card(card));
	}
	
	public void split() {
		split = 1;
		splitHand.add(hand.removeLast());
	}
	
	public int getSplitScore() {
		return splitScore;
	}
	
	//transfers elements from first hand to second hand
	public void getSecondHand() {
		
		splitScore = score;
		
		Iterator<Card> Iterator = hand.iterator();
		while (Iterator.hasNext()) {
		  splitHand.add(Iterator.next());
		}
		split+=1;
		hand.clear();
		hand.add(splitHand.pop());
		hand.add(splitHand.pop());
		blackjack = false;
		bust = false;
	}
	
	public void chooseBestHand() {
	
		calculateScore();
		if (splitScore < 22 && splitScore > score) {
			hand.clear();
		   Iterator<Card> Iterator = splitHand.iterator();
			while (Iterator.hasNext()) {
			  hand.add(Iterator.next());
			}
			calculateScore();
			score = splitScore;
		}
		
	}
	
	public int isSplit() {
		return split;
	}
	
	public String getFinalScore() {
		if (bust) {
			return "BUST";
		}else if (blackjack) {
			return "BLACKJACK";
		}else {
			return "" + getScore();
		}
	}
	
	
	public String  chooseDealerMove() {
	
		if (canSplit()&& hand.peekFirst().getValue() < 9) {
			return "d";
		}
		
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
		if (total == 17 && aces >= 1) {
			return "h";
		}else if (total <= 16) {
			return "h";
		}else {
			return "s";
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

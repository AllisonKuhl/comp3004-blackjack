package core;

import java.util.Iterator;
import java.util.LinkedList;

public class Player {
	
	LinkedList<Card> hand = new LinkedList<Card>();
	boolean bust = false;
	boolean blackjack = false;
	int score = 0;
	
	//splitting variables
	LinkedList<Card> splitHand = new LinkedList<Card>();
	int split = 0;
	int splitScore = 0;
	
	
	//adds a single card to the player's hand
	//string is converted to Card class
	public void addCard(String card) {
		hand.add(new Card(card));
		calculateScore();//this updates score each time card is added
	}
	
	//adds multiple cards at once, for testing purposes
	public void addCards(String[] arr) {
		for (int i = 0; i < arr.length;i++) {
			hand.add(new Card(arr[i]));
		}
		calculateScore();
	}
	
	//adds card to second hand, for splitting purposes
	public void addSplitCard(String card) {
		splitHand.add(new Card(card));
	}
	
	//calculates current score of hand
	public void calculateScore() {
		
		int total = 0;
		int aces = 0;
		int current;
	
		//goes through each card in hand and adds its value
		Iterator<Card> Iterator = hand.iterator();
		while (Iterator.hasNext()) {
		   current = Iterator.next().getValue();
		   total += current;
		   if (current == 11) {
			   aces += 1;
		   }
		}
		
		//Aces of course are a special case
		//they can be 11 or 1
		//in this code they are automatically 11 
		//unless this makes the total go over 21
		//in which case they are changed one by one to 1 until there are no more aces or less then 21
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
	
	//before you split, first you have to check if you CAN split!
	//checks to make sure there are only the two initial cards
	//and that the value is the same for both
	public boolean canSplit() {
		String firstCard = hand.get(0).toString();
		String secondCard = hand.get(1).toString();
		if (hand.size()==2) {
			return firstCard.substring(1,firstCard.length()).equals(secondCard.substring(1,secondCard.length()));
		}
		return false;
	}
	
	//initiates splitting
	public void split() {
		split = 1;//indicates we are in a split situation
		splitHand.add(hand.removeLast()); //puts second card into other hand
	}
		
	//transfers elements from first hand to second hand
	//so we can continue hitting on the main hand
	public void getSecondHand() {
		
		splitScore = score;	
		Iterator<Card> Iterator = hand.iterator();
		while (Iterator.hasNext()) {
		  splitHand.add(Iterator.next());
		}
		split+=1;//indicates we are in second stage of splitting -ie on the second deck;
		//reset everything for round two
		hand.clear();
		blackjack = false;
		bust = false;
		//gets the two elements already in the second hand
		hand.add(splitHand.pop());
		hand.add(splitHand.pop());

	}
	
	//chooses the best hand out of the split hand
	public void chooseBestHand() {
	
		calculateScore();//make sure score is up to date
		//if first hand is better than the second, then that becomes the main hand
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
	
	//a player in this case is someone who plays a game of blackjack
	//and that includes the dealer
	//this is how the dealer chooses the best move
	public String  chooseDealerMove() {
	
		//will split if combined hand value is less then 18
		if (canSplit()&& hand.peekFirst().getValue() < 9) {
			return "d";
		}
		
		int total = 0;
		int aces = 0;
		int current;
		
		//gets score and checks if there are aces
		Iterator<Card> Iterator = hand.iterator();
		while (Iterator.hasNext()) {
		   current = Iterator.next().getValue();
		   total += current;
		   if (current == 11) {
			   aces += 1;
		   }
		}
		
		while (aces>0 && total > 21) {
			total-=10;
			aces-=1;
		}
		if (total == 17 && aces >= 1) {//soft 17
			return "h";
		}else if (total <= 16) {
			return "h";
		}else {
			return "s";
		}
		
	}
	
	
	//GETTERS
	

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
	
	public int getSplitScore() {
		return splitScore;
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
		

}

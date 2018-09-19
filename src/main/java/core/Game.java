package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
	
	boolean fromFile;
	LinkedList<String> input;
	Player player = new Player();
	Player dealer = new Player();
	String winner = "";
	int gameState = 0; //0 for player turn, 1 for dealer turn. should probably be enum
	boolean canSplit = false;
	
	public Game() {
		input = new Deck().toQueue();
		fromFile = false;
	}
	
	public Game(String file) {
		try {
			input = readFile(file);
			fromFile = true;
		} catch (Exception e) {
	
		}
	
	}
	
		
	public void initializeHands() {
		player.addCard(input.pop());
		player.addCard(input.pop());
		dealer.addCard(input.pop());
		dealer.addCard(input.pop());
	
	   if (player.hasBlackjack() == true || dealer.hasBlackjack() == true) {
		   gameState = 2;
	   }
	}
	
	
	public String showHands() {
		String hand = "Player: " + player.showHand() + " | Dealer: " + dealer.showHand();
		if (gameState == 0) {
			hand = hand.substring(0,hand.lastIndexOf(" ")) + " X";
		}
		return hand;
	}
			
	public int getPlayerScore() {
		return player.getScore();
	}
	
	public String getFirstCard() {
		return input.get(0);
	}
	
	public String getFinalScore() {
		
		return "Player: " + player.getFinalScore() + " | Dealer: " + dealer.getFinalScore();
	}
	
	
	public void nextTurn() {
		if (gameState == 0) {
			nextTurn(input.pop().toLowerCase());
		}else {
			nextTurn(dealer.chooseDealerMove());			
		}
	}
	
	public void nextTurn(String move) {
		if (move.equals("h")) {
			hit();
		}else if (move.equals("s")) {
			stand();
		}
		if (move.equals("d")) {
			System.out.println("splitting");
			split();
		}
	}	
	
	
	public void split() {
		
		if (gameState == 0) {
			player.split();
			player.addCard(input.pop());
			player.addSplitCard(input.pop());
			player.calculateScore();
			if (player.hasBlackjack()) {
				gameState+=1;
			}
		}else if (gameState == 1) {
			dealer.split();
			dealer.addCard(input.pop());
			dealer.addSplitCard(input.pop());
			dealer.calculateScore();
			if (dealer.hasBlackjack()) {
				gameState+=1;
			}
		}
		
	}
	
	
	public void hit() {
		if (gameState == 0) {
			System.out.println("You draw: " + input.peekFirst());
			player.addCard(input.pop());
			if (player.isBust()) {
				endTurn();
			}else if (player.hasBlackjack()) {
				endTurn();
			}
		}else if (gameState == 1) {
			System.out.println("Dealer hits. They draw: " + input.peekFirst());
			dealer.addCard(input.pop());
			if (dealer.isBust()||dealer.hasBlackjack()) {
				endTurn();
			}
		}
	}
	
	public void stand() {
		if (gameState == 0) {
			System.out.println("You stand. Your final hand is: " + player.showHand());
		}else if (gameState == 1) {
			System.out.println("Dealer stands. Their final hand is: " + dealer.showHand());
		}
		endTurn();
	}
	
	public void endTurn() {
				
		if (player.hasBlackjack()&&gameState==0||dealer.hasBlackjack()) {
			gameState+=1;
		}else if (gameState == 0 && player.isSplit()==1){
			System.out.println("Drawing for split hand");
			System.out.println("Original hand is: " + player.showHand());
			System.out.println("Score is: " + player.getScore());
			player.getSecondHand();
		}else if (gameState == 0 && player.isSplit() == 2) {
			player.chooseBestHand();
			System.out.println("End of turn. Best hand is: " + player.showHand());
			gameState += 1;
		}else if (player.isBust()) {
			gameState = 2;
		}else if (gameState == 1 && dealer.isSplit()==1) {
			System.out.println("Drawing for split hand");
			System.out.println("Original hand is: " + dealer.showHand());
			System.out.println("Score is: " + dealer.getScore());
			dealer.getSecondHand();
		}else if (gameState == 1 && dealer.isSplit()==2) {
			dealer.chooseBestHand();
			System.out.println("Dealer turns end. Best hand is:" + dealer.showHand());
			gameState += 1;
		}else {
			gameState +=1;
		}	
	}
	
	public int whoseTurn() {
		return gameState;
	}
	
	public boolean playerHasBlackjack() {
		return player.hasBlackjack();
	}
	
	public boolean dealerHasBlackjack() {
		return dealer.hasBlackjack();
	}
	
	public void calculateWinner() {
		
		player.calculateScore();
		dealer.calculateScore();
		
		if (player.isBust()) {
			winner= "Dealer";
		}else if (dealer.hasBlackjack()) {
			winner = "Dealer";		
		}else if (player.hasBlackjack() || dealer.isBust() || player.getScore()>dealer.getScore()){
			winner = "Player";
		}else {
			winner = "Dealer";
		}
		
	}
	
	public String getWinner() {
		return winner;
	}
	
	public boolean fromFile() {
		return fromFile;
	}
	
	public boolean playerCanSplit() {
		return player.canSplit();
	}
	
	private LinkedList<String> readFile(String filename) throws FileNotFoundException {
		
		LinkedList<String> q = new LinkedList<String>(); 
		String input;
		
		ClassLoader classLoader = getClass().getClassLoader();
		
		
		//error checking. If file does not exist, just move to random deck?
		try {
			File file = new File(classLoader.getResource(filename).getFile());
		
		try (Scanner scanner = new Scanner(file)) {

			//what if no input??
			input = scanner.nextLine();
			scanner.close();
			
		    String[] arr = input.split(" ");
           
		    for (int i = 0; i<arr.length; i++) {
		    	q.add(arr[i]);
		    }

		} catch (IOException e) {
			System.out.println("This file does not exist!");
		}		
		}finally { 
			return q;
		}
	}
	
	
	public String inputToString() {
		return input.toString().replaceAll(",","").replace("[","").replaceAll("]", "");
	}
	
	
	
	
}

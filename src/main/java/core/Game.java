package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Game {
	
	boolean fromFile;
	LinkedList<String> input;
	Player player = new Player();
	Player dealer = new Player();
	String winner = "";
	int gameState = 0; //0 for player turn, 1 for dealer turn. should probably be enum
	
	public Game() {
		input = new Deck().toQueue();
		fromFile = false;
	}
	
	public Game(String file) {
		input = readFile(file);
		fromFile = true;
	}
	
		
	public void initializeHands() {
		player.addCard(input.pop());
		player.addCard(input.pop());
		dealer.addCard(input.pop());
		dealer.addCard(input.pop());
	
	   if (player.hasBlackjack() == true || dealer.hasBlackjack() == true) {
		   gameState = 3;
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
	
	
	public void nextTurn() {
		if (gameState == 0) {
			nextTurn(input.pop().toLowerCase());
		}else {
			if (dealer.hitOrStand()) {
				nextTurn("h");
			}else {
				nextTurn("s");
			}
			
		}
	}
	
	public void nextTurn(String move) {
		if (move.equals("h")) {
			hit();
		}else if (move.equals("s")) {
			stand();
		}
		calculateScore();
	}	
	
	public void hit() {
		if (gameState == 0) {
			player.addCard(input.pop());
			if (player.isBust()) {
				gameState = 3;
			}else if (player.hasBlackjack()) {
				gameState +=1;
			}
		}else if (gameState == 1) {
			dealer.addCard(input.pop());
			if (dealer.isBust()||dealer.hasBlackjack()) {
				gameState +=1;
			}
		}
	}
	
	public void stand() {
		gameState += 1;
	}
	
	private void calculateScore() {
		
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
	
	
	private LinkedList<String> readFile(String filename) {
		
		LinkedList<String> q = new LinkedList<String>(); 
		String input;
		
		ClassLoader classLoader = getClass().getClassLoader();
		
		
		//error checking. If file does not exist, just move to random deck?
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
			e.printStackTrace();
		}
		 		 
		 return q;

	}
	
	
	public String inputToString() {
		return input.toString().replaceAll(",","").replace("[","").replaceAll("]", "");
	}
	
	
	
	
}

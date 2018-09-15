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
	String gameState = "player";
	
	public Game() {
		input = new Deck().toQueue();
		fromFile = false;
	}
	
	public Game(String file) {
		input = readFile(file);
		fromFile = true;
	}
	
		
	public void initializeHands() {
		System.out.println(input);
		player.addCard(input.pop());
		player.addCard(input.pop());
		dealer.addCard(input.pop());
		dealer.addCard(input.pop());
	}
	
	
	public String showHands() {
		String hand = "Player: " + player.showHand() + " | Dealer: " + dealer.showHand();
		if (gameState.equals("player")) {
			hand = hand.substring(0,hand.lastIndexOf(" ")) + " X";
		}
		return hand;
	}
			
	
	public String getFirstCard() {
		return input.get(0);
	}
	
	
	public boolean playerHasBlackjack() {
		return player.hasBlackjack();
	}
	
	public boolean dealerHasBlackjack() {
		return dealer.hasBlackjack();
	}
	
	public void calculateWinner() {
		
		if (dealer.hasBlackjack()) {
			winner = "Dealer";		
		}else if (player.hasBlackjack()){
			winner = "Player";
		}		
		
	}
	
	public String getWinner() {
		return winner;
	}
	
	
	
	
	private LinkedList<String> readFile(String filename) {
		
		LinkedList<String> q = new LinkedList<String>(); 
		String input;
		
		ClassLoader classLoader = getClass().getClassLoader();
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

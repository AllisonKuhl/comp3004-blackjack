package core;

import java.util.Scanner;

public class GameController {
	
	Game game;
	
	public GameController(String filename) {
		game = new Game(filename);
		startGame();
	}
	
	public GameController() {}
	
	public void startGame() {
		if (game == null) {
			initGame();	
		}
			System.out.println("Game is starting! The dealer deals the cards. Here are the initial hands:");
			game.initializeHands();
			System.out.println(game.showHands());
			
			if (game.playerCanSplit() && !game.fromFile()) {
				System.out.println("Looks like you have the option of splitting!");
				System.out.println("Would you like to split? (y/n): ");
				String input = getUserInput("y","n");
				if (input == "y") {
					game.nextTurn("d");
				}
			}
			
			
			while (game.whoseTurn() != 2){
				
				if (!game.fromFile() && game.whoseTurn()==0){
					System.out.println("Would you like to hit or stand?");
					System.out.println("(This is your current hand):" + game.showHands() );// + game.getPlayerHand());
					String input = getUserInput("h","s");
					game.nextTurn(input);
				}else{
					game.nextTurn();
				}
				
			}
			
			game.calculateWinner();
			System.out.println("Winner is... " + game.getWinner() + "!!");
			System.out.println("Final score is: ");
			System.out.println(game.getFinalScore());
			System.out.println(game.showHands());
			System.out.println("Thanks for playing!");
		
	}

	public String getWinner() {
		return game.getWinner();
	}
	
	public String showHand() {
		return game.showHands();
	}
	
	private void initGame(){
		System.out.println("Welcome to blackjack!");
		System.out.println("Would you like to play through the console (c) or do you have a file? (f)");

		String input = getUserInput("c","f");
		
		if (input.equals("f")){
			Scanner scan = new Scanner(System.in); 
			System.out.println("Enter the name of the file.");
			System.out.print("> ");
			String filename = scan.next();
			game = new Game(filename);
			scan.close();
		}else {
			game = new Game();
		}
		
		

	}
	
	public String getFinalScore() {
		return game.getFinalScore();
	}
	
	public String getUserInput(String opt1,String opt2){
		Scanner scan = new Scanner(System.in); 
		System.out.print("Enter your choice: ");
		String input = scan.next();
		while (!(input.equals(opt1) || input.equals(opt2))){
			System.out.println("Wrong input. Please try again.");
			System.out.print("Enter either " + opt1 + " or " + opt2 + ": ");
			input = scan.next();
		}
		
		return input;
	}

}

package core;

import java.util.Scanner;

public class GameController {
	
	Game game;
	
	//initalizes a game with a file right away without asking prefered input
	//for testing purposes
	public GameController(String filename) {
		try {
			game = new Game(filename);
			startGame();	
		}catch(Exception e) {
			System.out.println("Sorry, that file is invalid!");
		}
	}
	
	public GameController() {}
	
	
	public void startGame() {
		if (game == null) { //checks to make sure game isn't already initialized
			initGame();	 //otherwise checks for file or console input
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
					
			while (game.whoseTurn() != 2){ //2 == GAME OVER!
				
				if (!game.fromFile() && game.whoseTurn()==0){
					System.out.println("Would you like to hit or stand?");
					System.out.println("(This is your current hand):" + game.showHands() );
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
	
	//prompts user for file or console input
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
	
	//gets user input. Will only accept the two options passed in, otherwise will keep prompting user for correct info
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
	
	
	//GETTERS
	
	public String getWinner() {
		return game.getWinner();
	}
	
	public String showHand() {
		return game.showHands();
	}
	
	public String getFinalScore() {
		return game.getFinalScore();
	}
	
}

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
	LinkedList input;
	Player player;
	Player dealer;
	
	
	public Game() {
		input = new Deck().toQueue();
		fromFile = false;
	}
	
	public Game(String file) {
		input = readFile(file);
		fromFile = true;
	}
	
	
	/*
	public void initalizeHands() {
		player.addCards(input.pop(),input.pop());
		dealer.addCards(input.pop(),input.pop());
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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

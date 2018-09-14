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
	
	
	public Game() {
		input = new Deck().toQueue();
		fromFile = false;
	}
	
	public Game(String file) {
		input = readFile(file);
		fromFile = true;
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
		
		//need to update this
		return input.toString();
	}
	
	
	
	
}

package core;

public class Card {
	
	private String name;
	private String num;
	private int value;


	public Card(String name) {
		this.name = name;
		this.num = name.substring(1,name.length());
		calculateValue(num);
	}
	
	private void calculateValue(String cardnum) {
		
		if (cardnum.equals("K")||cardnum.equals("Q")||cardnum.equals("J")) {
			this.value = 10;
		}else if (cardnum.equals("A")) {
			this.value = 11;
		}else {
			this.value = Integer.parseInt(cardnum);
		}
		
	}
		
	public int getValue() {
		return value;
	}
	
	public String toString() {
		return name;
	}
		
		
	}
	
	

package org.polytech.nantes.monopoly.utils;


public class Dices {
	private int firstDice;
	private int secondDice;
	private boolean isDouble;
	
	public Dices() {
		firstDice = 0;
		secondDice = 0;
		isDouble = false;
	}
	
	public void throwDices() {
		firstDice = (int)(1+Math.random()*6);
		secondDice = (int)(1+Math.random()*6);
		
		if(firstDice == secondDice)
			isDouble = true;
		else
			isDouble = false;
	}
	
	public int getTotal() {
		return firstDice + secondDice;
	}
	
	public int getFirstDice() {
		return firstDice;
	}
	
	public int getSecondDice() {
		return secondDice;
	}
	
	public boolean isDouble() {
		return isDouble;
	}
	
}

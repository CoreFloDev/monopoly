package org.polytech.nantes.monopoly.manager;

/**
 * Classe abstraite Carte
 * @author Maxime
 * @author Florian
 */
public abstract class Carte 
{
	/*
	 * Enoncé de la carte
	 */
	protected String message;
	
	public Carte(String message) {
		this.message = message;
	}
	
	public abstract void action(Joueur j) throws NoMoreMoneyException;
	
	@Override
	public String toString() {
		return message;
	}
	
}

package org.polytech.nantes.monopoly.manager;

import org.polytech.nantes.monopoly.utils.Dices;

/**
 * Classe abstraite décrivant un case du plateau
 * @author Maxime
 * @author Florian
 */
public abstract class Case {
	/**
	 * Nom décrivant la case
	 */
	protected String nom;
	
	
	public Case(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Méthode à appeler lorsque le joueur tombe sur une case.
	 * Réalise tout le traitement correspondant à la case
	 * @param j, Joueur arrivant sur cette case
	 * @param dices, le lancer de dés réalisé
	 * @return si la case est achetable ou non
	 */
	public abstract boolean action(Joueur j, Dices dices) throws NoMoreMoneyException;
	
	public String getNom() {
		return nom;
	}
	
	@Override
	public String toString() {
		return nom;
	}
}

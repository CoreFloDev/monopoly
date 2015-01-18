package org.polytech.nantes.monopoly.manager;

/**
 * Sous-classe de Case, décrivant une case achetable
 * @author Florian
 */
public abstract class CaseAchetable extends Case 
{
	protected int prix;
	protected Joueur proprietaire;
	
	public CaseAchetable(String nom, int prix) {
		super(nom);
		this.prix = prix;
	}
	
	public abstract void acheter(Joueur j) throws NotEnoughMoneyException;
	
	public int getPrix() {
		return prix;
	}
	
	public Joueur getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}
}

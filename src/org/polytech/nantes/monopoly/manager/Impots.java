package org.polytech.nantes.monopoly.manager;

import org.polytech.nantes.monopoly.utils.Dices;

/**
 * Sous-classe de Case, décrivant une case Impot du plateau (perte d'argent)
 * @author Florian
 *
 */
public class Impots extends CaseNonAchetable
{
	private int montant;

	public Impots(String nom, int montant)
	{
		super(nom);
		this.montant = montant;
	}
	
	@Override
	public boolean action(Joueur j, Dices dices) throws NoMoreMoneyException
	{
		j.payer(montant);
		return false;
	}
	
	public int getMontant() {
		return montant;
	}
}

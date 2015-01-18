package org.polytech.nantes.monopoly.manager;

import org.polytech.nantes.monopoly.utils.Dices;

/**
 * Sous-classe de Case, décrivant la case Départ du plateau
 * @author Florian
 *
 */
public class Depart extends CaseNonAchetable 
{
	private int salaire;
	
	public Depart()
	{
		super("Depart");
		this.salaire = 200;
	}
	
	public boolean action(Joueur j, Dices dices)
	{
		j.gagnerArgent(salaire);
		return false;
	}
	
	public int getSalaire() {
		return salaire;
	}
}

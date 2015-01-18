package org.polytech.nantes.monopoly.manager;

import org.polytech.nantes.monopoly.utils.Dices;

/**
 * Sous-classe de Case, décrivant la case Parc Gratuit du plateau
 * @author Florian
 *
 */
public class ParcGratuit extends CaseNonAchetable 
{
	
	/**
	 * Référence sur le manager pour la gestion de l'argent du plateau
	 */
	private MainManager mm;
	
	public ParcGratuit(MainManager mm)
	{
		super("Parc Gratuit");
		this.mm = mm;
	}
	
	public boolean action(Joueur j, Dices dices)
	{
		j.gagnerArgent(mm.viderArgent());
		return false;
	}
}

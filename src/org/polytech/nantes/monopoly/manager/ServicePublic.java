package org.polytech.nantes.monopoly.manager;

import org.polytech.nantes.monopoly.utils.Dices;

/**
 * Sous-classe de Case, décrivant une case de service public du plateau
 * @author Florian
 * @author Maxime
 *
 */
public class ServicePublic extends CaseAchetable 
{
	
	public ServicePublic(String nom, int prix)
	{
		//Appel au super constructeur(nom, prix)
		super(nom, prix);
		
		this.proprietaire = null;
	}
	
	public boolean action(Joueur j, Dices dices) throws NoMoreMoneyException
	{
		if(proprietaire != null)
		{
			int multiplier = (proprietaire.getNbServicePublic() == 2) ? 10 : 4;
			j.payer(dices.getTotal()*multiplier, proprietaire);
						
			return false;
		}
		else
		{
			return true;
		}
	}


	public void acheter(Joueur j) throws NotEnoughMoneyException 
	{
		if(proprietaire == null) {
			j.acheterCase(prix);
			j.addService(this);
			proprietaire = j;
		}
	}
	
	
}

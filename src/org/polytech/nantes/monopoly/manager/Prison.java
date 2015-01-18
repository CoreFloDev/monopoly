package org.polytech.nantes.monopoly.manager;

import org.polytech.nantes.monopoly.utils.Dices;

/**
 * Sous-classe de Case, décrivant la case Prison du plateau
 * @author Florian
 *
 */
public class Prison extends CaseNonAchetable 
{
	
	public Prison()
	{
		super("Prison");
	}
	
	public boolean action(Joueur j, Dices dices) throws NoMoreMoneyException
	{
		if(j.estEnPrison())
		{
			j.essayerSortirPrison(dices);
		}
		return false;
	}


}

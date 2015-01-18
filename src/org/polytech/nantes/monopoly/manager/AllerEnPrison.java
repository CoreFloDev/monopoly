package org.polytech.nantes.monopoly.manager;

import org.polytech.nantes.monopoly.utils.Dices;

/**
 * Case Aller tout droit en prison
 * @author Florian
 * @author Maxime
 *
 */
public class AllerEnPrison extends CaseNonAchetable 
{
	public AllerEnPrison()
	{
		super("AllerEnPrison");
	}

	@Override
	public boolean action(Joueur j, Dices dices) 
	{
		j.allerEnPrison();
		return false;
	}
	
}

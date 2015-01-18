package org.polytech.nantes.monopoly.manager;

/**
 * Carte emmenant le joueur directement en prison
 * @author Maxime
 *
 */
public class CartePrison extends CarteDeplacement
{

	public CartePrison(String message, Case cible, MainManager mm) 
	{
		super(message, cible, mm);		
	}
	
	public void action(Joueur j) throws NoMoreMoneyException 
	{
		j.avancer(mm.indexOf(j.getCaseActuelle()) - mm.indexOf(caseCible));
		j.allerEnPrison();
	}

}

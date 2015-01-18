package org.polytech.nantes.monopoly.manager;

import org.polytech.nantes.monopoly.utils.Dices;


/**
 * Sous-classe de Case, décrivant la case Gare du plateau
 * @author Florian
 *
 */
public class Gare extends CaseAchetable 
{
	
	private int loyers[];
	
	public Gare(String nom)
	{
		//Appel au super constructeur (nom, prix)
		super(nom, 200);
		
		this.proprietaire = null;
		this.loyers = new int[4];
		loyers[0] = 25;
		loyers[1] = 50;
		loyers[2] = 100;
		loyers[3] = 200;
	}
	
	public boolean action(Joueur j, Dices dices) throws NoMoreMoneyException
	{
		if(proprietaire != null)
		{
			j.payer(loyers[proprietaire.getNbGares() - 1], proprietaire);
			
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public void acheter(Joueur j) throws NotEnoughMoneyException 
	{
		if(proprietaire == null) {
			j.acheterCase(prix);
			j.addGare(this);
			proprietaire = j;
		}
	}

	public int[] getLoyers() {
		return loyers;
	}
}

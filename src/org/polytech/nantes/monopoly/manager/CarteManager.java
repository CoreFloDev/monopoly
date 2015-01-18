package org.polytech.nantes.monopoly.manager;

import org.polytech.nantes.monopoly.utils.Dices;

/**
 * Gestionnaire pour le tirage de cartes
 * @author Florian
 * @author Maxime
 */
public class CarteManager extends CaseNonAchetable 
{
	/**
	 * Liste des cartes tirables
	 */
	private Carte cartes[];
	
	/**
	 * Constructeur de carte manager
	 * @param cartes listes des cartes tirables
	 */
	public CarteManager(Carte cartes[])
	{
		super("CarteManager");
		this.cartes = cartes;		
	}
	
	@Override
	public boolean action(Joueur j, Dices dices) throws NoMoreMoneyException
	{
		return false;
	}
	
	public Carte getCarteTire() 
	{
		//Tirage aléatoire d'une carte
		int randIndex = (int)(1+Math.random()*(cartes.length - 1));
		//On réalise l'action de la carte
		return cartes[randIndex];
	}
}

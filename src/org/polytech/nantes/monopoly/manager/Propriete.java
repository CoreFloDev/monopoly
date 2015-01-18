package org.polytech.nantes.monopoly.manager;

import java.awt.Color;

import org.polytech.nantes.monopoly.utils.Dices;

/**
 * Sous-classe de Case, décrivant les propriétés du plateau
 * @author Florian
 *
 */
public class Propriete extends CaseAchetable 
{
	private Color couleur;
	private int categorie;
	private int loyers[];
	private int nbMaison;	
	private int prixMaison;
	
	public Propriete(String nom, int prix, Color couleur, int categorie, int loyer0, int loyer1, int loyer2, int loyer3, int loyer4)
	{
		//Appel au super constructeur(nom, prix)
		super(nom, prix);
		
		this.proprietaire = null;
		this.couleur = couleur;
		this.categorie = categorie;
		
		this.loyers = new int[5];
		loyers[0] = loyer0;
		loyers[1] = loyer1;
		loyers[2] = loyer2;
		loyers[3] = loyer3;
		loyers[4] = loyer4;
		
		this.prixMaison = 50*categorie;
		this.nbMaison = 0;
	}
	
	public boolean action(Joueur j, Dices dices) throws NoMoreMoneyException
	{
		if(proprietaire != null)
		{
			j.payer(loyers[nbMaison], proprietaire);
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
			j.addPropriete(this);
			proprietaire = j;
		}
	}
	
	public void acheterMaison() throws NotEnoughMoneyException {
		if(nbMaison<4)
		{
			proprietaire.acheterCase(prixMaison);
			nbMaison++;
		}
	}

	public Color getCouleur() {
		return couleur;
	}

	public int getCategorie() {
		return categorie;
	}

	public int[] getLoyers() {
		return loyers;
	}

	public int getNbMaison() {
		return nbMaison;
	}

	public void setNbMaison(int nbMaison) {
		this.nbMaison = nbMaison;
	}

	public int getPrixMaison() {
		return prixMaison;
	}
}

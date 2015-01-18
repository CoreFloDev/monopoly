package org.polytech.nantes.monopoly.manager;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import org.polytech.nantes.monopoly.utils.Dices;

/**
 * Classe représentant un joueur avec ses états et toutes ses possession
 * @author Maxime
 * @author Florian
 */
public class Joueur extends Observable
{
	private String nom;
	private Color couleur;
	private int argent;
	private Case caseActuelle;
	
	private List<Gare> listeGares;
	private List<Propriete> listePropriete;
	private List<ServicePublic> listeService;
	
	private boolean enPrison;
	private int cptPrison;		// Compteur pour sortir de prison
	
	private boolean aPerdu;		// Si le joueur est éliminé ou non
	
	/**
	 * Référence sur le manager pour la gstion des cases du plateau
	 */
	private MainManager mm;
	
	/**
	 * Modele contenant la liste des possessions du joueur
	 */
	private ProprietesJoueurModel possessionsJoueur;
	
	/**
	 * Constructeur de la classe Joueur
	 * @param nom, nom du joueur
	 * @param couleur, couleur associée au joueur
	 * @param mm, référence du plateau
	 */
	public Joueur(String nom, Color couleur, MainManager mm)
	{
		this.nom = nom;
		this.couleur = couleur;
		this.mm = mm;
		argent = 1500;
		caseActuelle = mm.getCase(0);
		
		listeGares = new ArrayList<Gare>();
		listePropriete = new ArrayList<Propriete>();
		listeService = new ArrayList<ServicePublic>();
		possessionsJoueur = new ProprietesJoueurModel();
		
		enPrison = false;
		cptPrison = 0;
		
		aPerdu = false;
	}
	
	/**
	 * Mettre le joueur en prison
	 */
	public void allerEnPrison() 
	{
		enPrison = true;
		cptPrison = 0;	
		caseActuelle = mm.getCase(10);
		argent -= 200;
		
		setChanged();
		notifyObservers();
	}

	/**
	 * Faire gagner de l'argent au joueur
	 * @param somme, montant à ajouter au porte-feuille du Joueur
	 */
	public void gagnerArgent(int somme) 
	{
		argent += somme;
		
		setChanged();
		notifyObservers();
	}

	/**
	 * Ajouter une gare à sa liste des propriétés
	 * @param gare, la gare à ajouter
	 */
	public void addGare(Gare gare)
	{
		listeGares.add(gare);
		possessionsJoueur.addProp(gare);
	}
	
	/**
	 * Ajouter un terrain à sa liste des propriétés
	 * @param p, le terrain (propriete) à ajouter
	 */
	public void addPropriete(Propriete p)
	{
		listePropriete.add(p);
		possessionsJoueur.addProp(p);
	}
	
	/**
	 * Ajouter un service public à sa liste des propriétés
	 * @param s, le service public à ajouter
	 */
	public void addService(ServicePublic s)
	{
		listeService.add(s);
		possessionsJoueur.addProp(s);
	}

	/**
	 * Payer un certain montant (dépot de l'argent sur le plateau)
	 * @param montant, montant à débiter
	 * @throws NoMoreMoneyException, le joueur ne possède plus d'argent après ce paiement : faillite
	 */
	public void payer(int montant) throws NoMoreMoneyException
	{
		//Si le joueur n'a pas assez d'argent pour honorer sa dette
		if((argent - montant) < 0) {
			//On donne l'argent restant du joueur
			mm.addArgent(argent);
			//On déclare sa faillite
			throw new NoMoreMoneyException();
		}
		argent -= montant;
		mm.addArgent(montant);
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Payer un certain montant à un autre joueur
	 * @param montant, montant à débiter
	 * @param j, joueur créditeur
	 * @throws NoMoreMoneyException, le joueur ne possède plus d'argent après ce paiement : faillite
	 */
	public void payer(int montant, Joueur j) throws NoMoreMoneyException 
	{
		//Si le joueur n'a pas assez d'argent pour honorer sa dette
		if((argent - montant) < 0) {
			//On donne l'argent restant du joueur
			j.gagnerArgent(argent);
			//On déclare sa faillite
			throw new NoMoreMoneyException();
		}
		argent -= montant;
		j.gagnerArgent(montant);
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Payer un montant à la banque (achat de propriete)
	 * @param montant, montant à débiter
	 * @throws NotEnoughMoneyException, le joueur ne possède pas assez d'argent pour effectuer cet achat
	 */
	public void acheterCase(int montant) throws NotEnoughMoneyException
	{
		if((argent - montant) < 0) throw new NotEnoughMoneyException();
		argent -= montant;
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Essayer de sortir de prison
	 * @param dices, les dés utlisés pendant la partie
	 * @throws NoMoreMoneyException, le joueur ne possède plus d'argent après ce paiement : faillite
	 */
	public void essayerSortirPrison(Dices dices) throws NoMoreMoneyException 
	{
		//Si le nombre d'essai est dépassé
		
		// TODO : incohérence dans les notifications
		if(cptPrison == 3)
		{
			sortirPrison();
			payer(50);
			
			setChanged();
		}
		else
		{
			//Si on obtient un double
			if(dices.isDouble()) {
				sortirPrison();
				setChanged();
			}
			else {
				cptPrison++;
			}
		}
	
		notifyObservers();
	}

	/**
	 * Fonction privée qui sort le joueur de prison
	 */
	private void sortirPrison() 
	{
		enPrison = false;
		cptPrison = 0;
	}
	
	/**
	 * Déclarer faillite, le joueur cède toutes ses propriétés et sort du plateau
	 */
	public void perdu() {
		aPerdu = true;
		for(Propriete p : listePropriete)
			p.setProprietaire(null);
		
		for(Gare g : listeGares)
			g.setProprietaire(null);
		
		for(ServicePublic s : listeService) 
			s.setProprietaire(null);
		
		caseActuelle = null;
	}
	
	/**
	 * Faire avancer un joueur d'un certain nombre de cases
	 * @param nbCases, nombre de cases à avancer 
	 */
	public void avancer(int nbCases) {
		if(!enPrison) {
			int index = mm.indexOf(caseActuelle);
			if((index + nbCases) > 39) {
				try {
					mm.getCase(0).action(this, null);
				} catch (NoMoreMoneyException e) {
					e.printStackTrace();
				}
			}
			setCaseActuelle(mm.getCase((index + nbCases)%40));
		}
	}
	
	
	/////////////////////////
	// GETTERS AND SETTERS///
	/////////////////////////
	
	public String getNom() 
	{
		return nom;
	}
	
	public Color getCouleur() {
		return couleur;
	}

	public int getArgent() {
		return argent;
	}

	public Case getCaseActuelle() {
		return caseActuelle;
	}

	public void setCaseActuelle(Case caseActuelle) {
		this.caseActuelle = caseActuelle;
		setChanged();
		notifyObservers();
	}
	
	public int getNbGares() 
	{
		return listeGares.size();
	}
	
	public int getNbServicePublic()
	{
		return listeService.size();
	}
	
	public List<Gare> getListeGares() {
		return listeGares;
	}

	public List<Propriete> getListePropriete() {
		return listePropriete;
	}

	public List<ServicePublic> getListeService() {
		return listeService;
	}

	public boolean estEnPrison() 
	{
		return enPrison;
	}

	public int getCptPrison() {
		return cptPrison;
	}
	
	public boolean aPerdu() {
		return aPerdu;
	}
	
	public ProprietesJoueurModel getProprietesJoueurModel() {
		return possessionsJoueur;
	}
}

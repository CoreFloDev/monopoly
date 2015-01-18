package org.polytech.nantes.monopoly.manager;

import java.awt.Color;

import org.polytech.nantes.monopoly.utils.Dices;

public class MainManager 
{

	/**
	 * Argent total du plateau
	 */
	private int argentPlateau;
	
	/**
	 * Liste des cases du tableau
	 */
	private Case[] plateau;
	
	/**
	 * Liste des cartes chances
	 */
	private Carte[] cartes;
	
	/**
	 * Liste des joueurs
	 */
	private Joueur[] joueurs;
	
	/**
	 * Contient le nombre de tours restant dans la partie
	 */
	private int nbreToursRestant;
	
	/**
	 * initialisation de main manager
	 * sert pour la gestion des cartes et des propriétés du jeu
	 * @param nbtours nombre de tours restant
	 */
	public MainManager(int nbtours, String[] nomJoueurs)
	{
		initPlateau();
		
		//Couleurs à utiliser
		Color couleur[] = new Color[4];
		couleur[0] = Color.BLUE;
		couleur[1] = Color.GREEN;
		couleur[2] = Color.BLACK;
		couleur[3] = Color.RED;

		nbreToursRestant = nbtours;
		argentPlateau = 0;
		
		//Création des joueurs avec les noms des joueurs
		joueurs = new Joueur[nomJoueurs.length];
		for(int i = 0; i < nomJoueurs.length; i++) {
			joueurs[i] = new Joueur(nomJoueurs[i], couleur[i],this);
		}
	}
	
	/**
	 * Initialisation des cases du tableau
	 */
	private void initPlateau() {
		// Création du plateau
		plateau = new Case[40];
		
		plateau[0] = new Depart();
		
		plateau[1] = new Propriete("Boulevard de Belleville", 60, new Color(102, 51, 0), 1, 2, 10, 30, 90, 160);
		
		plateau[3] = new Propriete("Rue Lecourbe", 60, new Color(102, 51, 0), 1, 4, 20, 60, 180, 320);
		plateau[4] = new Impots("Impôts sur le revenu", 200);
		plateau[5] = new Gare("Gare Montparnasse");
		plateau[6] = new Propriete("Rue de Vaugirard", 100, Color.CYAN, 1, 6, 30, 90, 270, 400);
		
		plateau[8] = new Propriete("Rue de Courcelles", 100, Color.CYAN, 1, 6, 30, 90, 270, 400);
		plateau[9] = new Propriete("Avenue de la Republique", 120, Color.CYAN, 1, 8, 40, 100, 300, 450);
		plateau[10] = new Prison();
		plateau[11] = new Propriete("Boulevard de la Vilette", 140, Color.PINK, 2, 10, 50, 150, 450, 625);
		plateau[12] = new ServicePublic("Electricité", 150);
		plateau[13] = new Propriete("Avenue de Neuilly", 140, Color.PINK, 2, 10, 50, 150, 450, 625);
		plateau[14] = new Propriete("Rue de Paradis", 160, Color.PINK, 2, 12, 60, 180, 500, 700);
		plateau[15] = new Gare("Gare de Lyon");
		plateau[16] = new Propriete("Avenue Mozart", 180, Color.ORANGE, 2, 14, 70, 200, 550, 750);
		
		plateau[18] = new Propriete("Boulevard Saint Michel", 180, Color.ORANGE, 2, 14, 70, 200, 550, 750);
		plateau[19] = new Propriete("Place Pigalle", 200, Color.ORANGE, 2, 16, 80, 220, 600, 800);
		plateau[20] = new ParcGratuit(this);
		plateau[21] = new Propriete("Avenue Matignon", 220, Color.RED, 3, 18, 90, 250, 700, 875);
		plateau[23] = new Propriete("Boulevard Malesherbes", 220, Color.RED, 3, 18, 90, 250, 700, 875);
		plateau[24] = new Propriete("Avenue Henri Martin", 240, Color.RED, 3, 20, 100, 300, 750, 925);
		plateau[25] = new Gare("Gare du Nord");
		plateau[26] = new Propriete("Faubourg Saint Honoré", 260, Color.YELLOW, 3, 22, 110, 330, 800, 975);
		plateau[27] = new Propriete("Place de la Bourse", 260, Color.YELLOW, 3, 22, 110, 330, 800, 975);
		plateau[28] = new ServicePublic("Eau", 150);
		plateau[29] = new Propriete("Rue La Fayette", 280, Color.YELLOW, 3, 24, 120, 360, 850, 1025);
		
		plateau[30] = new AllerEnPrison();
		
		plateau[31] = new Propriete("Avenue de Breuteuil", 300, Color.GREEN, 4, 26, 130, 390, 900, 110);
		plateau[32] = new Propriete("Avenue Foch", 300, Color.GREEN, 4, 26, 130, 390, 900, 110);
		
		plateau[34] = new Propriete("Boulevard des Capucines", 320, Color.GREEN, 4, 28, 150, 450, 1000, 1200);
		plateau[35] = new Gare("Gare Saint Lazarre");
		
		plateau[37] = new Propriete("Avenue des Champs Elysées", 350, Color.BLUE, 4, 35, 175, 500, 1100, 1300);
		plateau[38] = new Impots("Impôt supplémentaire", 100);
		plateau[39] = new Propriete("Rue de la Paix", 400, Color.BLUE, 4, 50, 200, 600, 1400, 1700);

		/*
		 * Création des cartes
		 */
		cartes = new Carte[25];
		
		cartes[0] = new CartePrison("Allez en prison sans passer par la case départ et sans toucher votre salaire.", plateau[10], this);
		cartes[1] = new CartePrison("Allez en prison sans passer par la case départ et sans toucher votre salaire.", plateau[10], this);
		cartes[2] = new CarteDeplacement("Retournez au Boulevard de Belleville.", plateau[1], this);
		cartes[3] = new CarteDeplacement("Avancez au Boulevard de la Vilette. Si vous passez par la case départ, recevez 200€.", plateau[11], this);
		cartes[4] = new CarteDeplacement("Rendez-vous rue de la paix.", plateau[39], this);
		cartes[5] = new CarteDeplacement("Allez à la gare de Lyon. Si vous passez par la case départ, recevez 200€.", plateau[15], this);
		cartes[6] = new CarteDeplacement("Avancez jusqu'à la case départ.", plateau[0], this);
		cartes[7] = new CarteDeplacement("Rendez-vous Avenue Henri Martin. Si vous passez par la case départ, recevez 200€.", plateau[24], this);
			
		cartes[8] = new CarteGain("Les contributions vous remboursent la somme de 20€.", 20);
		cartes[9] = new CarteGain("Touchez votre intérêt sur l'emprunt à 7% 25€.", 25);
		cartes[10] = new CarteGain("Touchez votre revenu annuel 100€.", 100);
		cartes[11] = new CarteGain("La vente de votre stock vous rapporte 50€.", 50);
		cartes[12] = new CarteGain("Vous héritez de 100€.", 100);
		cartes[13] = new CarteGain("Vous avez gagné le deuxième prix de beauté, recevez 10€.", 10);
		cartes[14] = new CarteGain("La banque vous verse un dividende de 50€.", 50);
		cartes[15] = new CarteGain("Votre immeuble et votre prêt rapportent. Vous touchez 150€.", 150);
		cartes[16] = new CarteGain("Vous avez gagné le prix de mots croisés. Recevez 100é.", 100);
		cartes[17] = new CarteGain("Erreur de la banque en votre faveur. Recevez 200€.", 200);
		
		cartes[18] = new CartePaiement("Payez votre police d'assurance 50€.", 50);
		cartes[19] = new CartePaiement("Payez la note du médecin 50€.", 50);
		cartes[20] = new CartePaiement("Amende pour excès de vitesse 15€.", 15);
		cartes[21] = new CartePaiement("Amende pour ivresse 20€.", 20);
		cartes[22] = new CartePaiement("Payez pour frais de scolarité 150€.", 150);
		cartes[23] = new CartePaiement("Payez une amende de 10€.", 10);
		cartes[24] = new CartePaiement("Payez l'hôpital 100€.", 100);
	
		plateau[2] = new CarteManager(cartes);
		plateau[7] = new CarteManager(cartes);
		plateau[17] = new CarteManager(cartes);
		plateau[22] = new CarteManager(cartes);
		plateau[33] = new CarteManager(cartes);
		plateau[36] = new CarteManager(cartes);
			
	}
	
	/**
	 * Permet d'obtenir le numéro d'une case sur le plateau
	 * @param theCase, la case pour laquelle on veut le numero
	 */
	public int indexOf(Case theCase) {
		for(int i = 0; i < plateau.length; i++) {
			if(plateau[i] == theCase)
				return i;
		}
		return -1;
	}
	
	/**
	 * Ajouter de l'argent sur le plateau
	 * @param montant, montant à créditer
	 */
	public void addArgent(int montant) {
		argentPlateau += montant;
	}
	
	/**
	 * Vider le montant d'argent du plateau : Parc Gratuit
	 * @return le montant d'argent qu'il y avait sur le plateau au moment de la mise à zéro
	 */
	public int viderArgent() {
		int total = argentPlateau;
		argentPlateau = 0;
		return total;
	}
	
	/**
	 * Récupérer le montant d'argent du plateau
	 * @return
	 */
	public int getArgent() {
		return argentPlateau;
	}
	
	public Case[] getPlateau() {
		return plateau;
	}
	
	public Case getCase(int i) {
		return plateau[i];
	}
	
	public Carte[] getCartes() {
		return cartes;
	}
	
	public Joueur[] getJoueurs() {
		return joueurs;
	}
	
	/**
	 * Decremente le nombre de tours
	 * @return vrai si la partie est terminé
	 */
	public boolean decrementerTour() {
		return nbreToursRestant-- == 0;
	}
	
	/**
	 * Class de test
	 * @author florent
	 *
	 */
	@SuppressWarnings("unused")
	private static class Test {
		
		public static void main(String args[])
		{
			String j[] = new String[2];
			j[0] = "Paul";
			j[1] = "Jean";
			
			MainManager mm = new MainManager(10, j);
			
			Joueur j1 = mm.getJoueurs()[0];
			Joueur j2 = mm.getJoueurs()[1];
			
			try {
				System.out.println(j1.getCaseActuelle().getNom());
				
				System.out.println(mm.getPlateau()[1].getNom());
				
				j1.setCaseActuelle(mm.getPlateau()[1]);
				System.out.println("Case actuelle joueur 1 " + j1.getCaseActuelle().getNom());
				System.out.println("Argent joueur 1: " + j1.getArgent());
				if(j1.getCaseActuelle().action(j1,new Dices()))
				{
					((CaseAchetable)j1.getCaseActuelle()).acheter(j1);
				}
				System.out.println("Case actuelle joueur 1 => proprietaire: " + ((CaseAchetable)j1.getCaseActuelle()).getProprietaire().getNom());
				System.out.println("Argent joueur 1: " + j1.getArgent());
				
				j2.setCaseActuelle(mm.getPlateau()[1]);
				j2.getCaseActuelle().action(j2,new Dices());
				
				System.out.println("Argent joueur 1: " + j1.getArgent());
				System.out.println("Argent joueur 2: " + j2.getArgent());
				
				j1.setCaseActuelle(mm.getPlateau()[4]);
				if(j1.getCaseActuelle().action(j1,new Dices()))		
				{
					((CaseAchetable)j1.getCaseActuelle()).acheter(j1);
				}
				System.out.println("Argent joueur 1: " + j1.getArgent());
				
				j2.setCaseActuelle(mm.getPlateau()[5]);		
				System.out.println("Case actuelle joueur 2 " + j2.getCaseActuelle().getNom());
				System.out.println("Argent joueur 2: " + j2.getArgent());
				if(j2.getCaseActuelle().action(j2,new Dices()))
				{
					((CaseAchetable)j2.getCaseActuelle()).acheter(j2);
				}
				System.out.println("Case actuelle joueur 2 => proprietaire: " + ((CaseAchetable)j2.getCaseActuelle()).getProprietaire().getNom());
				System.out.println("Argent joueur 2: " + j2.getArgent());
				
				for(Gare g: j2.getListeGares())
				{
					System.out.println(g.getNom());
				}
				
				j1.setCaseActuelle(mm.getPlateau()[11]);
				System.out.println("Argent joueur 1: " + j1.getArgent());
				j1.getCaseActuelle().action(j1,new Dices());
				System.out.println("Argent joueur 1: " + j1.getArgent());
				
				for(Propriete p: j1.getListePropriete())
				{
					System.out.println(p.getNom());
				}
				
				j1.setCaseActuelle(mm.getPlateau()[12]);
				System.out.println("Argent joueur 1: " + j1.getArgent());
				j1.getCaseActuelle().action(j1,new Dices());
				System.out.println("Argent joueur 1: " + j1.getArgent());
				
				for(ServicePublic s: j1.getListeService())
				{
					System.out.println(s.getNom());
				}
				
				j2.setCaseActuelle(mm.getPlateau()[30]);
				System.out.println(j2.estEnPrison());
				j2.getCaseActuelle().action(j2,new Dices());
				System.out.println(j2.estEnPrison() + j2.getCaseActuelle().getNom());
				
				j1.setCaseActuelle(mm.getPlateau()[0]);
				System.out.println("Argent joueur 1: " + j1.getArgent());
				j1.getCaseActuelle().action(j1,new Dices());
				System.out.println("Argent joueur 1: " + j1.getArgent());
			} catch(NoMoreMoneyException e) {
				System.out.println("Le joueur a fait faillite.");
			} catch(NotEnoughMoneyException e) {
				System.out.println("Le joueur n'a pas assez d'argent pour cet achat.");
			}
			
			
		}
	}

}

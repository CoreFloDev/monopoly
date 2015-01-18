package org.polytech.nantes.monopoly.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.polytech.nantes.monopoly.manager.Gare;
import org.polytech.nantes.monopoly.manager.Impots;
import org.polytech.nantes.monopoly.manager.Joueur;
import org.polytech.nantes.monopoly.manager.Propriete;
import org.polytech.nantes.monopoly.manager.ServicePublic;

/**
 * Plateau de jeu
 * 
 * @author Jérémy
 *
 */
@SuppressWarnings("serial")
public class Plateau extends JPanel {
	
	/**
	 * Pions des joueurs
	 */
	public Pion[] pions;
	
	/**
	 * Cases du plateau (version graphique != modele)
	 */
	private Vector<Case> cases;
	
	/**
	 * Zone d'affichage des cartes
	 */
	private JTextArea cartesAffiche;
	
	/**
	 * Valeur du premier des
	 */
	private JLabel des1;
	
	/**
	 * Valeur du second des
	 */
	private JLabel des2;
	
	
	/**
	 * Constructeur de plateau
	 * @param nbPlayers nombre de joueurs (2 ou 4)
	 */
	public Plateau(Joueur[] joueurs, org.polytech.nantes.monopoly.manager.Case[] cases2) {
		pions = new Pion[joueurs.length];
		cases = new Vector<Case>();
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.setPreferredSize(new Dimension(1000,700));
		
		initCases(cases2);
		
	    this.add(nord(), BorderLayout.NORTH);
	    this.add(ouest(), BorderLayout.WEST);
	    this.add(centre(), BorderLayout.CENTER);
	    this.add(est(), BorderLayout.EAST);
	    this.add(sud(), BorderLayout.SOUTH);
	    
	    initPions(joueurs);
	}
	
	/**
	 * Initialisation des cases du plateau
	 */
	private void initCases(org.polytech.nantes.monopoly.manager.Case[] casesPlateau) {
		// sud 0 à 10
		cases.add(new CaseCoin(casesPlateau[0].getNom()));
		cases.add(new CaseHB(((Propriete) casesPlateau[1]).getCouleur(), casesPlateau[1].getNom(), ((Propriete)casesPlateau[1]).getPrix()));
		cases.add(new CaseChanceHB());
		cases.add(new CaseHB(((Propriete)casesPlateau[3]).getCouleur(), casesPlateau[3].getNom(), ((Propriete)casesPlateau[3]).getPrix()));
		cases.add(new CaseHB(null, casesPlateau[4].getNom(), ((Impots)casesPlateau[4]).getMontant()));
		cases.add(new CaseGareHB(casesPlateau[5].getNom(), ((Gare)casesPlateau[5]).getPrix()));
		cases.add(new CaseHB(((Propriete)casesPlateau[6]).getCouleur(), casesPlateau[6].getNom(), ((Propriete)casesPlateau[6]).getPrix()));
		cases.add(new CaseChanceHB());
		cases.add(new CaseHB(((Propriete)casesPlateau[8]).getCouleur(), casesPlateau[8].getNom(), ((Propriete)casesPlateau[8]).getPrix()));
		cases.add(new CaseHB(((Propriete)casesPlateau[9]).getCouleur(), casesPlateau[9].getNom(), ((Propriete)casesPlateau[9]).getPrix()));
		cases.add(new CaseCoin(casesPlateau[10].getNom()));
		// ouest 11 à 19
		cases.add(new CaseG(((Propriete)casesPlateau[11]).getCouleur(), casesPlateau[11].getNom(), ((Propriete)casesPlateau[11]).getPrix()));
		cases.add(new CaseG(null, casesPlateau[12].getNom(), ((ServicePublic)casesPlateau[12]).getPrix()));
		cases.add(new CaseG(((Propriete)casesPlateau[13]).getCouleur(), casesPlateau[13].getNom(), ((Propriete)casesPlateau[13]).getPrix()));
		cases.add(new CaseG(((Propriete)casesPlateau[14]).getCouleur(), casesPlateau[14].getNom(), ((Propriete)casesPlateau[14]).getPrix()));
		cases.add(new CaseGareGD(casesPlateau[15].getNom(), ((Gare)casesPlateau[15]).getPrix()));
		cases.add(new CaseG(((Propriete)casesPlateau[16]).getCouleur(), casesPlateau[16].getNom(), ((Propriete)casesPlateau[16]).getPrix()));
		cases.add(new CaseChanceGD());
		cases.add(new CaseG(((Propriete)casesPlateau[18]).getCouleur(), casesPlateau[18].getNom(), ((Propriete)casesPlateau[18]).getPrix()));
		cases.add(new CaseG(((Propriete)casesPlateau[19]).getCouleur(), casesPlateau[19].getNom(), ((Propriete)casesPlateau[19]).getPrix()));
		// nord 20 à 30
		cases.add(new CaseCoin(casesPlateau[20].getNom()));
		cases.add(new CaseHB(((Propriete)casesPlateau[21]).getCouleur(), casesPlateau[21].getNom(), ((Propriete)casesPlateau[21]).getPrix()));
		cases.add(new CaseChanceHB());
		cases.add(new CaseHB(((Propriete)casesPlateau[23]).getCouleur(), casesPlateau[23].getNom(), ((Propriete)casesPlateau[23]).getPrix()));
		cases.add(new CaseHB(((Propriete)casesPlateau[24]).getCouleur(), casesPlateau[24].getNom(), ((Propriete)casesPlateau[24]).getPrix()));
		cases.add(new CaseGareHB(casesPlateau[25].getNom(), ((Gare)casesPlateau[25]).getPrix()));
		cases.add(new CaseHB(((Propriete)casesPlateau[26]).getCouleur(), casesPlateau[26].getNom(), ((Propriete)casesPlateau[26]).getPrix()));
		cases.add(new CaseHB(((Propriete)casesPlateau[27]).getCouleur(), casesPlateau[27].getNom(), ((Propriete)casesPlateau[27]).getPrix()));
		cases.add(new CaseHB(null, casesPlateau[28].getNom(), ((ServicePublic)casesPlateau[28]).getPrix()));
		cases.add(new CaseHB(((Propriete)casesPlateau[29]).getCouleur(), casesPlateau[29].getNom(), ((Propriete)casesPlateau[29]).getPrix()));
		cases.add(new CaseCoin(casesPlateau[30].getNom()));
		// est 31 à 39
		cases.add(new CaseD(((Propriete)casesPlateau[31]).getCouleur(), casesPlateau[31].getNom(), ((Propriete)casesPlateau[31]).getPrix()));
		cases.add(new CaseD(((Propriete)casesPlateau[32]).getCouleur(), casesPlateau[32].getNom(), ((Propriete)casesPlateau[32]).getPrix()));
		cases.add(new CaseChanceGD());
		cases.add(new CaseD(((Propriete)casesPlateau[34]).getCouleur(), casesPlateau[34].getNom(), ((Propriete)casesPlateau[34]).getPrix()));
		cases.add(new CaseGareGD(casesPlateau[35].getNom(), ((Gare)casesPlateau[35]).getPrix()));
		cases.add(new CaseChanceGD());
		cases.add(new CaseD(((Propriete)casesPlateau[37]).getCouleur(), casesPlateau[37].getNom(), ((Propriete)casesPlateau[37]).getPrix()));
		cases.add(new CaseD(null, casesPlateau[38].getNom(), ((Impots)casesPlateau[38]).getMontant()));
		cases.add(new CaseD(((Propriete)casesPlateau[39]).getCouleur(), casesPlateau[39].getNom(), ((Propriete)casesPlateau[39]).getPrix()));
	}
	
	/**
	 * Affichage des cases du bas
	 */
	private JPanel sud() {
		JPanel sud = new JPanel();
		sud.setLayout(new GridLayout(1,11));
		sud.setSize(1100,80);
		
		for(int i = 10; i >= 0; i--)
			sud.add(cases.get(i));
		
		return sud;
	}
	
	/**
	 * Affichage des cases de gauche
	 */
	private JPanel ouest() {
		JPanel ouest = new JPanel();
		ouest.setLayout(new GridLayout(9,1));
		ouest.setSize(100,360);

		for(int i = 19; i >= 11; i--)
			ouest.add(cases.get(i));
		
		return ouest;
	}
	
	/**
	 * Affichage des cases du haut
	 */
	private JPanel nord() {
		JPanel nord = new JPanel();
		nord.setLayout(new GridLayout(1,11));
		nord.setSize(1100,80);
		
		for(int i = 20; i <= 30; i++)
			nord.add(cases.get(i));
		
		return nord;
	}
	
	/**
	 * Affichage des cases de droite
	 */
	private JPanel est() {
		JPanel est = new JPanel();
		est.setLayout(new GridLayout(9,1));
		est.setSize(100,360);		

		for(int i = 31; i <= 39; i++)
			est.add(cases.get(i));
		
		return est;
	}

	/**
	 * Affichage du centre
	 */
	private JPanel centre() {
		JPanel centre = new JPanel();
		centre.setLayout(new BorderLayout());
		
		JPanel des = new JPanel();
		des.add(new JLabel("Résultat des dés : "));
		des1 = new JLabel();
		des1.setText("0");
		des.add(des1);
		des2 = new JLabel();
		des2.setText("0");
		des.add(des2);
		centre.add(des,BorderLayout.NORTH);
		
		centre.add( new JLabel("Résultat de la carte tiré : "),BorderLayout.CENTER);
		cartesAffiche = new JTextArea();
		cartesAffiche.setEditable(false);
		cartesAffiche.setPreferredSize(new Dimension(150, 100));
		cartesAffiche.setLineWrap(true);
		cartesAffiche.setWrapStyleWord(true);
		centre.add(cartesAffiche,BorderLayout.SOUTH);
		
		JPanel centreur = new JPanel();
		centreur.add(centre);
		
		return centreur;
	}
	
	/**
	 * Initialisation des pions
	 * @param Joueur[] liste des joueurs
	 * 
	 */
	public void initPions(Joueur[] joueurs) {
		for(int i = 0; i < pions.length; i++) {
			pions[i] = new Pion(joueurs[i].getCouleur(), 3, 3, 5);
			Case c = cases.get(0);
			c.contenu.add(pions[i]);
			pions[i].repaint();
		}
	}
	
	/**
	 * Déplacement des pions
	 * @param int numero de la case actuelle du joueur
	 */
	public void majPion(int caseActuelleDuJoueur, int numeroDujoueur) {

		JPanel oldC = (JPanel) pions[numeroDujoueur].getParent();
		oldC.remove(pions[numeroDujoueur]);
		oldC.repaint();
		Case c = cases.get(caseActuelleDuJoueur);
		c.contenu.add(pions[numeroDujoueur]);
		pions[numeroDujoueur].repaint();
	}
	
	/**
	 * Getter cartesAffiche
	 * @return cartesAffiche
	 */
	public JTextArea getCartesAffiche() {
		return cartesAffiche;
	}

	/**
	 * Getter dé 1
	 * @return Dé 1
	 */
	public JLabel getDes1() {
		return des1;
	}

	/**
	 * Getter dé 2
	 * @return Dé 2
	 */
	public JLabel getDes2() {
		return des2;
	}
	
	/**
	 * Getter case
	 * @return cases du plateau
	 */
	public Vector<Case> getCases() {
		return cases;
	}
}

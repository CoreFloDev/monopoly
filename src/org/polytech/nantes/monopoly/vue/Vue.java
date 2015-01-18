package org.polytech.nantes.monopoly.vue;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.polytech.nantes.monopoly.manager.Joueur;

/**
 * Fenetre du monopoly
 * 
 * @author Jérémy & Florent
 *
 */
@SuppressWarnings("serial")
public class Vue extends JFrame {
	
	/**
	 * Liste des zone graphique des joueurs
	 */
	private JoueurArea[] zoneDesJoueurs;
	
	/**
	 * Fait clignoter le nom du joueur actif
	 */
	private Timer clignotementJoueur;
	
	/**
	 * Plateau de jeu
	 */
	private Plateau plateau;
	
	/**
	 * Affiche le plateau de jeu
	 * @param string[] nomJoueur liste des noms des joueurs
	 * @throws Exception 
	 */
	public Vue(Joueur[] joueurs, org.polytech.nantes.monopoly.manager.Case[] cases) throws Exception {
		
		// controle des paramètres
		if(!(joueurs.length == 2 || joueurs.length == 4)) {
			throw new Exception("nombre de joueurs renseigné incorrect");
		}
		
		this.setTitle("Monopoly");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		zoneDesJoueurs = new JoueurArea[joueurs.length];
		
		if(joueurs.length == 4) {
			this.setSize(1300,700);
		} else {
			this.setSize(930,700);
		}
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// container principal
		Container main = this.getContentPane();
		main.setLayout(new BorderLayout());
		
		// zone haut et bas
		zoneDesJoueurs[0] = new JoueurArea(new GridLayout(1,6), joueurs[0]);
		main.add(zoneDesJoueurs[0],BorderLayout.SOUTH);
		
		
		if(joueurs.length == 2) {
			zoneDesJoueurs[1] = new JoueurArea(new GridLayout(1,6), joueurs[1]);
			main.add(zoneDesJoueurs[1],BorderLayout.NORTH);
		}
		
		// zone gauche et droite
		if(joueurs.length == 4) {
			zoneDesJoueurs[1] = new JoueurArea(new GridLayout(6,1), joueurs[1]);
			main.add(zoneDesJoueurs[1],BorderLayout.WEST);
			zoneDesJoueurs[2] = new JoueurArea(new GridLayout(1,6), joueurs[2]);
			main.add(zoneDesJoueurs[2],BorderLayout.NORTH);
			zoneDesJoueurs[3] = new JoueurArea(new GridLayout(6,1), joueurs[3]);
			main.add(zoneDesJoueurs[3],BorderLayout.EAST);
		}
		
		// clignotement du joueur actif
		clignotementJoueur = new Timer(500,zoneDesJoueurs[0].getActionClignotement());
		clignotementJoueur.start();
		
		// ajout du plateau
		plateau = new Plateau(joueurs,cases);
		main.add(plateau, BorderLayout.CENTER);
		
		this.setFocusable(true);
		
		
		this.setVisible(true);
	}
	
	
	/**
	 * Active la zone du joueur
	 * @param numeroJoueur numero du joueur ou tour de partie
	 */
	public void setActifJoueur(int numeroJoueur) {
		
		// activation du clignotement
		clignotementJoueur.stop();
		ActionListener[] vieuClignotement = clignotementJoueur.getActionListeners();
		if(vieuClignotement != null) {
			for(int i=0;i<vieuClignotement.length;i++) {
				clignotementJoueur.removeActionListener(vieuClignotement[i]);
			}
		}
		
		zoneDesJoueurs[ (4+numeroJoueur-1)%zoneDesJoueurs.length].afficheNomJoueur();
		
		clignotementJoueur.addActionListener(zoneDesJoueurs[numeroJoueur%zoneDesJoueurs.length].getActionClignotement());
		clignotementJoueur.start();
	}
	
	/**
	 * Getter zone des joueurs
	 * @return l'interface graphique contenant la zone des joueurs
	 */
	public JoueurArea[] getZoneDesJoueurs() {
		return zoneDesJoueurs;
	}
	
	/**
	 * getter plateau
	 * @return l'interface du plateau
	 */
	public Plateau getPlateau() {
		return plateau;
	}
	
	/**
	 * Termine la partie en affichant une boite de dialog
	 */
	public void terminer() {
		JOptionPane.showMessageDialog(this, "La partie est terminée");
	}
}

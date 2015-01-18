package org.polytech.nantes.monopoly.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.polytech.nantes.monopoly.manager.CaseAchetable;
import org.polytech.nantes.monopoly.manager.Joueur;

/**
 * Contient l'interface graphique d'un joueur
 * @author florent
 *
 */
@SuppressWarnings("serial")
public class JoueurArea extends JPanel{

	/**
	 * Affiche le nom du joueur
	 */
	private JLabel nomJoueur;
	
	/**
	 * Afficher la somme d'argent disponible pour le joueur
	 */
	private JLabel sommeArgent;
	
	/**
	 * liste des propriétés du joueur
	 */
	private JComboBox<CaseAchetable> proprietes;
	
	/**
	 * Bouton d'achat d'une maison
	 */
	private JButton achatMaison;
	
	/**
	 * Bouton achat de propriété
	 */
	private JButton achatPropriete;
	
	/**
	 * Action qui fait clignoter le label du joueur actif
	 */
	private ActionListener blinkJoueurAction;
	
	/**
	 * Contient la couleur du joueur
	 */
	private Color couleurJoueur;
	
	/**
	 * Constructeur de la classe joueur
	 */
	public JoueurArea(GridLayout layout,Joueur j) {
		
		this.couleurJoueur = j.getCouleur();
		nomJoueur = new JLabel(j.getNom());
		nomJoueur.setForeground(j.getCouleur());
		// gestion du clignotement
		final Color couleurParDefautLabelJoueur = nomJoueur.getForeground();
		final Color couleurDeClignotement = nomJoueur.getBackground();
		blinkJoueurAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(nomJoueur.getForeground().equals(couleurParDefautLabelJoueur)) {
					nomJoueur.setForeground(couleurDeClignotement);
				} else {
					nomJoueur.setForeground(couleurParDefautLabelJoueur);
				}
				
			}
		};
		
		
		sommeArgent = new JLabel();
		setArgent(j.getArgent());
		
		proprietes = new JComboBox<CaseAchetable>(j.getProprietesJoueurModel());
		proprietes.setPreferredSize(new Dimension(180, 25));
		
		achatMaison = new JButton("Acheter une maison");
		achatMaison.setEnabled(false);
		achatPropriete = new JButton("acheter la case");
		achatPropriete.setEnabled(false);
		
		JPanel panelJoueur = new JPanel();
		panelJoueur.add(nomJoueur);
		panelJoueur.add(sommeArgent);
		panelJoueur.add(proprietes);
		panelJoueur.add(achatMaison);
		panelJoueur.add(achatPropriete);
		panelJoueur.setLayout(layout);
		
		this.add(panelJoueur);
	}
	
	/**
	 * Met à jour l'affichage de l'argent
	 * @param argent quantité d'argent en euro
	 */
	public void setArgent(int argent) {
		sommeArgent.setText(argent+ " €");
	}
	
	/**
	 * Sert à afficher le nom du joueur à la fin du clignotement
	 */
	public void afficheNomJoueur() {
		nomJoueur.setForeground(couleurJoueur);
	}
	
	/**
	 * Renvoie l'action de clignotement du texte
	 * @return ActionListener
	 */
	public ActionListener getActionClignotement() {
		return blinkJoueurAction;
	}
	
	/**
	 * Renvoie le jCombobox de la zone de jeu du joueur
	 * @return JComboBox
	 */
	public JComboBox<CaseAchetable> getListePropriete() {
		return proprietes;
	}
	
	/**
	 * Renvoie le bouton acheter maison de la zone de jeu du joueur
	 * @return JButton
	 */
	public JButton getBoutonAcheterMaison() {
		return achatMaison;
	}
	
	/**
	 * Renvoie le bouton d'achat d'une case de la zone de jeu du joueur
	 * @return JButton
	 */
	public JButton getBoutonAcheterCase() {
		return achatPropriete;
	}
}

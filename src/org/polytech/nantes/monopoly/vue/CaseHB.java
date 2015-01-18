package org.polytech.nantes.monopoly.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * 
 * @author Jérémy
 *
 */
@SuppressWarnings("serial")
public class CaseHB extends Case {
	/**
	 * Création d'une case de propriété achetable (haut ou bas)
	 * @param couleur Couleur de l'entete de la propriété
	 * @param titre Titre de la propriété
	 * @param prix Prix de la propriété
	 */
	public CaseHB(Color couleur, String titre, int prix) {
		this.setLayout(new GridLayout(2,1));
		this.setPreferredSize(new Dimension(100, 80));
		
		this.add(entete(100, 20, couleur));
		this.add(contenu(100, 60, titre, prix));
	}
}

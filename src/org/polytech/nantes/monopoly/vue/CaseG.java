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
public class CaseG extends Case {
	/**
	 * Création d'une case de propriété achetable (coté gauche)
	 * @param couleur Couleur de l'entete de la propriété
	 * @param titre Titre de la propriété
	 * @param prix Prix de la propriété
	 */
	public CaseG(Color couleur, String titre, int prix) {		
		this.setLayout(new GridLayout(1,2));
		this.setPreferredSize(new Dimension(100, 40));

		this.add(contenu(75, 40, titre, prix));
		this.add(entete(25, 40, couleur));
	}
}
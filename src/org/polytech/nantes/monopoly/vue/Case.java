package org.polytech.nantes.monopoly.vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Type case commun à toutes les sortes de cases
 * 
 * @author Jérémy
 *
 */

@SuppressWarnings("serial")
public abstract class Case extends JPanel {
	/**
	 * Panel d'entete pour les propriétés achetables
	 */
	public JPanel entete;
	
	/**
	 * Panel du contenu de la case
	 */
	public JPanel contenu;
	
	/**
	 * Création du panel d'entete pour les propriétés achetables
	 * 
	 * @param width Largeur de la case
	 * @param height Hauteur de l'entete
	 * @param couleur Couleur de l'entete de la propriété
	 * @return Panel d'entete
	 */
	protected JPanel entete(int width, int height, Color couleur) {
		entete = new JPanel();
		entete.setPreferredSize(new Dimension(width, height));
		if(couleur != null)
			entete.setBackground(couleur);
		else
			entete.setOpaque(false);
		entete.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		entete.setLayout(new FlowLayout());
		
		return entete;
	}
	
	/**
	 * Création du panel de contenu pour les propriétés achetables
	 * 
	 * @param width Largeur de la case
	 * @param height Hauteur du contenu
	 * @param titre Titre de propriété
	 * @param prix Prix de la propriété
	 * @return Panel du contenu
	 */
	protected JPanel contenu(int width, int height, String titre, int prix) {
		contenu = new JPanel();
		contenu.setLayout(new GridLayout(3,1));
		contenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		contenu.setPreferredSize(new Dimension(width, height));
		
		JTextArea t = new JTextArea(titre);
		t.setEditable(false);
		t.setFont(new Font("plain", Font.PLAIN, 10));
		t.setOpaque(false);
		t.setToolTipText(titre);
		JTextArea p = new JTextArea(prix+" €");
		p.setEditable(false);
		p.setFont(new Font("plain", Font.PLAIN, 10));
		p.setOpaque(false);
		
		contenu.add(t);
		contenu.add(p);
		
		return contenu;
	}
	
	/**
	 * Ajout d'une maison à l'entete d'une propriété achetable
	 */
	public void addHouse() {
		if(entete != null) {
			entete.add(new Maison());
			entete.repaint();
		}
	}
}

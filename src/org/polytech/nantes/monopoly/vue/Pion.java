package org.polytech.nantes.monopoly.vue;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Pion représentant l'avancement des joueurs sur le plateau
 * 
 * @author Jérémy & Maxime
 *
 */
@SuppressWarnings("serial")
public class Pion extends JPanel {
	/**
	 * Couleur du pion
	 */
	private Color c;
	
	/**
	 * Coordonnées (x,y) et rayon r du pion
	 */
	private int x, y, r;

	/**
	 * Création du pion
	 * 
	 * @param couleur Couleur du pion
	 * @param x Position x du pion
	 * @param y Position y du pion
	 * @param r Rayon r du pion
	 */
	public Pion(Color couleur, int x, int y, int r) {
		c = couleur;
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	/**
	 * Affichage du pion
	 */
	public void paintComponent(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, r, r);
	}

}

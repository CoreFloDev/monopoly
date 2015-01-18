package org.polytech.nantes.monopoly.vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Maison à poser sur une case de propriété
 * 
 * @author Jérémy
 *
 */
@SuppressWarnings("serial")
public class Maison extends JPanel {
	/**
	 * Créer un carré de 8x8 pixels blanc
	 */
	public Maison() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(8, 8));
	}
}

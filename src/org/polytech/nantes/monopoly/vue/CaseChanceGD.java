package org.polytech.nantes.monopoly.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Jérémy
 */
@SuppressWarnings("serial")
public class CaseChanceGD extends Case {
	/**
	 * Création du contenu d'une case chance (de côtés)
	 */
	public CaseChanceGD() {
		this.setPreferredSize(new Dimension(100, 40));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
		JLabel t = new JLabel("Chance");
		t.setFont(new Font("plain", Font.PLAIN, 10));
			
		this.add(t);
		
		contenu = new JPanel();
		contenu.setLayout(new FlowLayout());
		contenu.setPreferredSize(new Dimension(80, 15));
		
		this.add(contenu);
	}
}

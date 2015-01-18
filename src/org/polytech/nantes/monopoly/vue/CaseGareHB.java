package org.polytech.nantes.monopoly.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Jérémy
 *
 */
@SuppressWarnings("serial")
public class CaseGareHB extends Case {
	/**
	 * Création d'une case de gare du haut ou bas
	 * 
	 * @param titre Nom de la gare
	 * @param prix Prix de la gare
	 */
	public CaseGareHB(String titre, int prix) {
		this.setPreferredSize(new Dimension(100, 80));
		this.setLayout(new GridLayout(3,1));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		JLabel t = new JLabel(titre);
		t.setFont(new Font("plain", Font.PLAIN, 10));
		JLabel p = new JLabel("" + prix + " €");
		p.setFont(new Font("plain", Font.PLAIN, 10));

		this.add(t);
		this.add(p);
		
		contenu = new JPanel();
		contenu.setLayout(new FlowLayout());
		contenu.setPreferredSize(new Dimension(80, 60));
		
		this.add(contenu);
	}

}

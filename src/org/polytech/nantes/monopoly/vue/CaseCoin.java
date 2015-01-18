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
 *
 */
@SuppressWarnings("serial")
public class CaseCoin extends Case {
	/**
	 * Création du contenu d'une case de coin
	 * 
	 * @param titre Texte de la case
	 */
	public CaseCoin(String titre) {
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.setPreferredSize(new Dimension(100, 100));
			
		JLabel t = new JLabel(titre);
		t.setFont(new Font("plain", Font.PLAIN, 10));
				
		this.add(t);
		
		contenu = new JPanel();
		contenu.setLayout(new FlowLayout());
		contenu.setPreferredSize(new Dimension(80, 60));
		
		this.add(contenu);
	}
}

package org.polytech.nantes.monopoly.control;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;

import org.polytech.nantes.monopoly.manager.Carte;
import org.polytech.nantes.monopoly.manager.CarteManager;
import org.polytech.nantes.monopoly.manager.Case;
import org.polytech.nantes.monopoly.manager.CaseAchetable;
import org.polytech.nantes.monopoly.manager.Joueur;
import org.polytech.nantes.monopoly.manager.MainManager;
import org.polytech.nantes.monopoly.manager.NoMoreMoneyException;
import org.polytech.nantes.monopoly.manager.NotEnoughMoneyException;
import org.polytech.nantes.monopoly.manager.Propriete;
import org.polytech.nantes.monopoly.utils.Dices;
import org.polytech.nantes.monopoly.utils.ScoreBoard;
import org.polytech.nantes.monopoly.vue.JoueurArea;
import org.polytech.nantes.monopoly.vue.Vue;

public class AppLauncher {
	
	/**
	 * Référence sur la vue
	 */
	private Vue vue;
	
	/**
	 * Référence sur le manager
	 */
	private MainManager manager;
	
	
	/**
	 * Lanceur de l'application
	 * @param args
	 */
	public static void main(String args[]) {
		new AppLauncher(args);
	}
		
	/**
	 * Constructeur de AppLauncher
	 * @param args
	 */
	public AppLauncher(String args[]) {
		
		if(args.length == 3 || args.length == 5) {
		} else {
			messageErreur("Nombre d'arguments incorrect");
		}
		
		
		String[] nomJoueurs = new String[args.length-1];
		for(int i=0;i<(args.length-1);i++) {
			nomJoueurs[i] = args[i];
		}
		
		// initialisation du modèle
		int nombreTours = -1;
		try {
			nombreTours = Integer.parseInt(args[args.length-1]);
		} catch(NumberFormatException e) {
			messageErreur("Le nombre de tours doit être un nombre");
		}
		
		if(nombreTours < 1) {
			messageErreur("Le nombre de tours de jeu doit être supérieur à 0");
		}
		
		manager = new MainManager(nombreTours, nomJoueurs);
		
		// initialisation du plateau
		try {
			vue = new Vue(manager.getJoueurs(), manager.getPlateau());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		

		// attachement des réactions à la vue
		attacherReaction();
	}
	
	/**
	 * Message affiché en cas d'erreur sur les arguments de lancement du programmes
	 * @param message 
	 */
	private void messageErreur(String message) {
		System.out.println("Erreur : " +message);
		System.out.println("Usage : ./monopoly <nomJoueur1> <nomJoueur2> <nombreDeTours>");
		System.out.println("Usage : ./monopoly <nomJoueur1> <nomJoueur2> <nomJoueur3> <nomJoueur4> <nombreDeTours>");
		System.exit(-1);
	}
	
	/**
	 * Attache les reactions à l'interface
	 */
	private void attacherReaction() {

		vue.addKeyListener( new EcouteurNextTurn());
		
		
		// fix le bug de focus avec le jComboBox
		vue.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {
				vue.requestFocus();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});
		
		int nbjoueurs = manager.getJoueurs().length;
		for(int i=0; i<nbjoueurs;i++) {
			// bouton d'achat d'une propriété
			vue.getZoneDesJoueurs()[i].getBoutonAcheterCase().setAction(new AcheterProprieteAction(i));
			// bouton d'achat de maison
			vue.getZoneDesJoueurs()[i].getBoutonAcheterMaison().setAction(new AcheterMaisonAction(i));
			// scroll de zone des joueurs
			vue.getZoneDesJoueurs()[i].getListePropriete().addItemListener(new SelectionPropriete(i));
			// mise à jour de l'argent
			manager.getJoueurs()[i].addObserver(new miseAJourArgentJoueur(vue.getZoneDesJoueurs()[i]));
			// mise à jour des pions
			manager.getJoueurs()[i].addObserver(new UpdatePions(i));
		}
	}
	
	/**
	 * Action qui gère le déroulement d'un tour
	 * @author florent
	 *
	 */
	private class EcouteurNextTurn implements KeyListener{

		/**
		 * Tour du joueur
		 */
		private int tourJoueur = 0;
		
		@Override
		public void keyPressed(KeyEvent arg0) {}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyChar() == ' ') {
				int numeroJoueurActuel = tourJoueur%manager.getJoueurs().length;
				// on saute les joueurs qui ont perdu
				int nbTour = 0;
				while(manager.getJoueurs()[numeroJoueurActuel].aPerdu()) {
					tourJoueur++;
					numeroJoueurActuel = tourJoueur%manager.getJoueurs().length;
					if(nbTour == manager.getJoueurs().length) {
						// la partie est terminé (tout le monde a perdu \o/ )
						ScoreBoard.addScore(Arrays.asList(manager.getJoueurs()));
						vue.terminer();
						System.exit(0);
					}
					nbTour++;
				}
				vue.getPlateau().getCartesAffiche().setText("");
				
				vue.setActifJoueur(tourJoueur);
				// déactivation des boutons
				for(int i =0;i<vue.getZoneDesJoueurs().length;i++) {
					vue.getZoneDesJoueurs()[i].getBoutonAcheterCase().setEnabled(false);
					vue.getZoneDesJoueurs()[i].getBoutonAcheterMaison().setEnabled(false);
				}
				
				Joueur joueurActuel = manager.getJoueurs()[numeroJoueurActuel];
				
				Dices des = new Dices();
				des.throwDices();
				joueurActuel.avancer(des.getTotal());
				
				try {					
					if(joueurActuel.getCaseActuelle().action(joueurActuel,des)) {
						
						vue.getZoneDesJoueurs()[numeroJoueurActuel].getBoutonAcheterCase().setEnabled(true);
					}
					
					// si l'on tombe sur une carte chance
					if(joueurActuel.getCaseActuelle() instanceof CarteManager) {
						
						Carte carteTire = ((CarteManager)joueurActuel.getCaseActuelle()).getCarteTire();
						vue.getPlateau().getCartesAffiche().setText(carteTire.toString());
						// on exécute l'action de la carte 
						carteTire.action(joueurActuel);
					}
				} catch (NoMoreMoneyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				vue.getPlateau().getDes1().setText(des.getFirstDice()+"");
				vue.getPlateau().getDes2().setText(des.getSecondDice()+"");
				

				
				if(tourJoueur != 0 && numeroJoueurActuel == 0) {
					if(manager.decrementerTour()) {
						// la partie est terminé
						ScoreBoard.addScore(Arrays.asList(manager.getJoueurs()));
						vue.terminer();
						System.exit(0);
					}
				}
				
				if(!des.isDouble())
					tourJoueur++;
			}
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {}

	}
	
	/**
	 * Action qui gère l'achat d'une propriété pour un joueur
	 * @author florent
	 *
	 */
	@SuppressWarnings("serial")
	private class AcheterProprieteAction extends AbstractAction {
		
		/**
		 * numero du joueur
		 */
		private int nbJoueur;
		
		/**
		 * Ecouteur du bouton d'achat de propriété
		 * @param nbjoueur numero du joueur
		 */
		public AcheterProprieteAction(int nbJoueur) {
			super("Acheter la case");
			this.nbJoueur = nbJoueur;
			this.setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try {
				((CaseAchetable)manager.getJoueurs()[nbJoueur].getCaseActuelle()).acheter(manager.getJoueurs()[nbJoueur]);
			} catch (NotEnoughMoneyException e) {
			}
			vue.getZoneDesJoueurs()[nbJoueur].getBoutonAcheterCase().setEnabled(false);
			
		}
		
	}
	
	/**
	 * Action du bouton d'achat de maison
	 * @author florent
	 *
	 */
	@SuppressWarnings("serial")
	private class AcheterMaisonAction extends AbstractAction {
		
		/**
		 * Numero du joueur
		 */
		private int nbJoueur;
		
		/**
		 * Ecouteur du bouton d'achat de propriété
		 * @param j bouton du joueur
		 */
		public AcheterMaisonAction(int nbJoueur) {
			super("Acheter une maison");
			this.nbJoueur = nbJoueur;
			this.setEnabled(false);
		}
		
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Propriete p = (Propriete) manager.getJoueurs()[nbJoueur].getProprietesJoueurModel().getSelectedPropriete();
			if(p.getNbMaison() <= 3) {
				try {
					p.acheterMaison();
					int numeroProp = manager.indexOf(p);
					vue.getPlateau().getCases().elementAt(numeroProp).addHouse();
				} catch (NotEnoughMoneyException e) {
					vue.getZoneDesJoueurs()[nbJoueur].getBoutonAcheterMaison().setEnabled(false);
				}
				if(p.getNbMaison() == 4) {
					vue.getZoneDesJoueurs()[nbJoueur].getBoutonAcheterMaison().setEnabled(false);
				}
			} else {
				vue.getZoneDesJoueurs()[nbJoueur].getBoutonAcheterMaison().setEnabled(false);
			}
		}
	}
	
	/**
	 * 
	 * @author florent
	 * Action qui gère la sélection de propriété dans le jcombobox de la zone des joueurs
	 */
	private class SelectionPropriete implements ItemListener {

		/**
		 * Zone du joueur associe
		 */
		private int nbJoueur;
		
		/**
		 * Constructeur de selection propriete
		 */
		public SelectionPropriete(int nbJoueur) {
			this.nbJoueur = nbJoueur;
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			vue.getZoneDesJoueurs()[nbJoueur].getBoutonAcheterMaison().setEnabled(false);
			if(e.getStateChange() == ItemEvent.SELECTED && e.getItem() instanceof Propriete) {
				Propriete p = (Propriete)e.getItem();
				if(p.getNbMaison() != 4) {
					vue.getZoneDesJoueurs()[nbJoueur].getBoutonAcheterMaison().setEnabled(true);
				}
			}
		}
		
	}
	
	/**
	 * Met à jour l'argent possédé par le joueur
	 * @author florent
	 *
	 */
	private class miseAJourArgentJoueur implements Observer{

		/**
		 * Zone du joueur
		 */
		private JoueurArea ja;
		
		/**
		 * constructeur de mise à jour argent
		 * @param j zone du joueur ou il faut mettre à jour l'argent
		 */
		public miseAJourArgentJoueur(JoueurArea j) {
			this.ja = j;
		}
		
		@Override
		public void update(Observable o, Object arg) {
			int argent = ((Joueur)o).getArgent();
			ja.setArgent(argent);
			
		}
		
	}
	
	/**
	 * Met à jour la position des pions sur le plateau
	 * @author florent
	 *
	 */
	private class UpdatePions implements Observer {

		private int numeroJoueur;
		
		/**
		 * Constructeur de update pions
		 * @param numeroJoueur le numéro du joueur
		 */
		public UpdatePions(int numeroJoueur) {
			this.numeroJoueur = numeroJoueur;
		}
		
		@Override
		public void update(Observable arg0, Object arg1) {
			vue.getPlateau().majPion(manager.indexOf(manager.getJoueurs()[numeroJoueur].getCaseActuelle()), numeroJoueur);
		}

	}
	
}

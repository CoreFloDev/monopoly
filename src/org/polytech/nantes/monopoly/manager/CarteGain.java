package org.polytech.nantes.monopoly.manager;

/**
 * Sous-classe de Carte, décrivant une carte engrangeant un gain d'argent
 * @author Florian
 */
public class CarteGain extends Carte 
{
	private int montant;
	
	public CarteGain(String message, int montant)
	{
		super(message);
		this.montant = montant;
	}

	@Override
	public void action(Joueur j) {
		j.gagnerArgent(montant);		
	}
	
	public int getMontant() {
		return montant;
	}
}

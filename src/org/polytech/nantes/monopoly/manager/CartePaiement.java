package org.polytech.nantes.monopoly.manager;

/**
 * Sous-classe de Carte, décrivant une carte engrangeant une perte d'argent
 * @author Florian
 */
public class CartePaiement extends Carte 
{
	private int montant;
	
	public CartePaiement(String message, int montant)
	{
		super(message);
		this.montant = montant;
	}

	@Override
	public void action(Joueur j) throws NoMoreMoneyException {
		j.payer(montant);
	}
	
	public int getMontant() {
		return montant;
	}
}

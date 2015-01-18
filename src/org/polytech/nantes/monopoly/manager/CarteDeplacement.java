package org.polytech.nantes.monopoly.manager;

/**
 * Sous-classe de Carte, décrivant une carte engrangeant un déplacement
 * @author Maxime
 * @author Florian
 */
public class CarteDeplacement extends Carte 
{
	protected Case caseCible;
	protected MainManager mm;
	
	public CarteDeplacement(String message, Case cible, MainManager mm)
	{
		super(message);
		this.caseCible = cible;
		this.mm = mm;
	}

	@Override
	public void action(Joueur j) throws NoMoreMoneyException 
	{
		j.avancer(mm.indexOf(j.getCaseActuelle()) - mm.indexOf(caseCible));
		j.setCaseActuelle(caseCible);
		caseCible.action(j, null);
	}
	
	public Case getCaseCible() {
		return caseCible;
	}
}

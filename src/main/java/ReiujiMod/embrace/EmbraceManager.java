package ReiujiMod.embrace;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.cardmodifier.StackableCardModifierManager;
import ReiujiMod.cardmodifier.modifiers.EmbraceOfTheVoidCardModifier;
import ReiujiMod.patches.field.EmbracedCountField;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class EmbraceManager {
	public EmbraceManager() {
	
	}
	
	public static int getEmbrace(AbstractCard card) {
		return StackableCardModifierManager.getStackedValue(
				card, EmbraceOfTheVoidCardModifier.ID);
	}
	
	public static int getMaxEmbrace(AbstractCard card) {
		if (card instanceof AbstractReiujiCard &&
				((AbstractReiujiCard) card).cantBePlayed) {
			return 0;
		}
		
		if (card.costForTurn < 0)
			return 0;
		
		return card.cost - EmbraceManager.getEmbrace(card);
	}
	
	public static void addEmbrace(AbstractCard card, int amt) {
		amt = Integer.min(amt, EmbraceManager.getMaxEmbrace(card));
		
		StackableCardModifierManager.addModifier(
				card, new EmbraceOfTheVoidCardModifier(amt));
	}
	
	public static void calcEmbraced(AbstractCard card) {
		assert card.cost >= 0;
		
		if (card.costForTurn <= 0)
			return;
		
		int amt = Integer.min(card.costForTurn, EmbraceManager.getEmbrace(card));
		card.updateCost(-amt);
		
		EmbracedCountField.embraced.set(card, amt);
	}
	
	public static void resetEmbraced(AbstractCard card) {
		int amt = EmbracedCountField.embraced.get(card);
		if (amt > 0)
			card.updateCost(amt);
		
		EmbracedCountField.embraced.set(card, 0);
	}
	
	public static void updateEmbraced(AbstractCard card) {
		EmbraceManager.resetEmbraced(card);
		EmbraceManager.calcEmbraced(card);
	}
}

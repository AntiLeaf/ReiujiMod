package ReiujiMod.cardmodifier;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class StackableCardModifier extends AbstractCardModifier {
	public int amount = 1;
	public boolean canGoNegative = false;

	public StackableCardModifier(int amount, boolean canGoNegative) {
		this.amount = amount;
		this.canGoNegative = canGoNegative;
	}

	public StackableCardModifier(int amount) {
		this.amount = amount;
	}

	public StackableCardModifier() {
	}

	public void stack(AbstractCard card, int amt) {
		amount += amt;
	}

	public void reduce(AbstractCard card, int amt) {
		amount -= amt;

		if (!canGoNegative) {
			amount = Integer.max(amount, 0);

			if (amount == 0)
				CardModifierManager.removeSpecificModifier(
						card, this, true);
		}
	}
}

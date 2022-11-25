package ReiujiMod.cardmodifier.modifiers;

import ReiujiMod.cards.Reiuji.UniverseSingsToMe;
import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.AlternateCardCostModifier;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustHandAlternateCostCardModifier extends AbstractCardModifier
		implements AlternateCardCostModifier {

	public ExhaustHandAlternateCostCardModifier() {
	}

	@Override
	public boolean isInherent(AbstractCard card) {
		return card instanceof UniverseSingsToMe;
	}

	@Override
	public int getAlternateResource(AbstractCard card) {
		CardGroup hand = AbstractDungeon.player.hand;
		return hand.size() - (hand.contains(card) ? 0 : 1);
	}

	@Override
	public int spendAlternateCost(AbstractCard card, int amt) {
		if (amt > 0) {
			AbstractDungeon.actionManager.addToTop(
				new SelectCardsInHandAction(
					amt, "", false, false,
					(c) -> c != card,
					(cards) -> {
						for (AbstractCard c : cards)
							AbstractDungeon.player.hand.moveToExhaustPile(c);
					}
			));
		}

		return 0;
	}

	@Override
	public AbstractCardModifier makeCopy() {
		return new ExhaustHandAlternateCostCardModifier();
	}
}
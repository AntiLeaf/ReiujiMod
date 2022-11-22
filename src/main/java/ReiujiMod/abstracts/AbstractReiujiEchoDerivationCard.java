package ReiujiMod.abstracts;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static ReiujiMod.ReiujiMod.CHILLED_FLAVOR;

public abstract class AbstractReiujiEchoDerivationCard extends AbstractReiujiEchoCard {
	AbstractReiujiEchoCard original;

	public AbstractReiujiEchoDerivationCard(
			AbstractReiujiEchoCard original
	) {

		this.original = original;
	}

	@Override
	public void triggerOnEndOfPlayerTurn() {

	}
	
	@Override
	public AbstractCard makeStatEquivalentCopy() {
		return this.original.makeStatEquivalentCopy();

//		AbstractReiujiEchoDerivationCard card = (AbstractReiujiEchoDerivationCard) super.makeStatEquivalentCopy();
//
//		card.original = (AbstractReiujiEchoCard) this.original.makeStatEquivalentCopy();
//
//		return card;
	}
	
	@Override
	public void triggerOnGlowCheck() {
		this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
	}

	@Override
	 public boolean canPlay(AbstractCard card) {
		if (!super.canPlay(card))
			return false;

		return true;
	}
}

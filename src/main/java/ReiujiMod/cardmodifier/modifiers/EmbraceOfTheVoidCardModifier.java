package ReiujiMod.cardmodifier.modifiers;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.cardmodifier.StackableCardModifier;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EmbraceOfTheVoidCardModifier extends StackableCardModifier {
	private static final String SIMPLE_NAME = EmbraceOfTheVoidCardModifier.class.getSimpleName();
	public static final String ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;

	public EmbraceOfTheVoidCardModifier(int amt) {
		super(amt);
	}

	public EmbraceOfTheVoidCardModifier() {
		super(1);
	}

	@Override
	public String identifier(AbstractCard card) {
		return ID;
	}

	@Override
	public boolean removeOnCardPlayed(AbstractCard card) {
		return !((card instanceof AbstractReiujiCard) &&
				((AbstractReiujiCard) card).removeEmbraceAfterPlayed());
	}

	@Override
	public boolean isInherent(AbstractCard card) {
		return false;
	}

	@Override
	public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(
				new VoidCard(), this.amount, true, true));
	}

	public AbstractCardModifier makeCopy() {
		return new EmbraceOfTheVoidCardModifier();
	}

	@Override
	public boolean shouldApply(AbstractCard card) {
		return !((card instanceof AbstractReiujiCard) &&
				((AbstractReiujiCard) card).maxEmbrace() <= 0);
	}
}
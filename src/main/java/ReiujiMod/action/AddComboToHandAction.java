package ReiujiMod.action;

import ReiujiMod.abstracts.AbstractReiujiCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

import javax.smartcardio.Card;

import static basemod.BaseMod.MAX_HAND_SIZE;
import static com.megacrit.cardcrawl.cards.CardGroup.CardGroupType.UNSPECIFIED;

public class AddComboToHandAction extends AbstractGameAction {

	AbstractReiujiCard card;

	public AddComboToHandAction(AbstractReiujiCard card) {
		this.card = card;
	}
	
	public void update() {
		if (!this.isDone) {
			if (AbstractDungeon.player.hand.size() < MAX_HAND_SIZE)
				this.addToTop(new MakeTempCardInHandAction(card));
			else {
				AbstractDungeon.player.createHandIsFullDialog();
				this.addToTop(new ReiujiExhaustSpecificCardAction(card, null));
			}

			this.isDone = true;
		}
	}
}

package ReiujiMod.action;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import java.util.function.Consumer;

public class ReiujiSelectAndMoveAction extends ReiujiSelectSomeAndMoveAction {
	
	public ReiujiSelectAndMoveAction(CardGroup src, CardGroup dest, int amount, String text,
									 Consumer<AbstractCard> work) {
		super(src.group, src, dest, amount, text, work);
		
		this.actionType = ActionType.CARD_MANIPULATION;
	}
	
	public ReiujiSelectAndMoveAction(CardGroup src, CardGroup dest, int amount, String text) {
		super(src.group, src, dest, amount, text);
	}
}

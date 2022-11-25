package ReiujiMod.abstracts;

import ReiujiMod.ReiujiMod;
import ReiujiMod.cardmodifier.StackableCardModifier;
import ReiujiMod.cardmodifier.StackableCardModifierManager;
import ReiujiMod.cardmodifier.modifiers.EmbraceOfTheVoidCardModifier;
import ReiujiMod.powers.HasUsedSpellPower;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

import static ReiujiMod.ReiujiMod.CHILLED_FLAVOR;

public abstract class AbstractReiujiCard extends CustomCard implements SpawnModificationCard {
	protected static final Color CYAN_COLOR = new Color(0f, 204f / 255f, 0f, 1f);

	public boolean cantBePlayed = false;
	public boolean isSpellCard = false;
	public boolean isSupplement = false;
	
	public AbstractReiujiCard(
			String id,
			String name,
			String img,
			int cost,
			String rawDescription,
			AbstractCard.CardType type,
			AbstractCard.CardColor color,
			AbstractCard.CardRarity rarity,
			AbstractCard.CardTarget target
	) {
		super(
				id,
				name,
				"img/cards/Cirno/Th123Cirno.png",
				cost,
				rawDescription,
				type,
				color,
				rarity,
				target
		);
		
//		FlavorText.AbstractCardFlavorFields.textColor.set(this, CHILLED);
		FlavorText.AbstractCardFlavorFields.boxColor.set(this, CHILLED_FLAVOR);
	}

	@Override
	public void triggerWhenDrawn() {
		if (this.isSupplement)
			this.addToTop(new DrawCardAction(1));

		super.triggerWhenDrawn();
	}

	@Override
	 public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		if (!super.canUse(p, m))
			return false;

		if (this.cantBePlayed)
			return false;

		if (this.isSpellCard &&
				AbstractDungeon.player.hasPower(HasUsedSpellPower.POWER_ID))
					return false;

		return true;
	}

	@Override
	public void triggerOnGlowCheck() {
		this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
	}

	@Override
	public AbstractCard makeStatEquivalentCopy() {
		AbstractReiujiCard card = (AbstractReiujiCard) super.makeStatEquivalentCopy();

		card.cantBePlayed = this.cantBePlayed;
		card.isSpellCard = this.isSpellCard;
		card.isSupplement = this.isSupplement;
//		card.isCombo = this.isCombo;

		return card;
	}

	public void triggerOnLeaveHand(boolean isExhaust, boolean isEndOfTurn) {

	}

	public void triggerOnLeaveHand(boolean isExhaust) {
		this.triggerOnLeaveHand(isExhaust, false);
	}

	@Override
	public void triggerOnExhaust() {
		this.triggerOnLeaveHand(true);
		super.triggerOnExhaust();
	}

	@Override
	public void triggerOnManualDiscard() {
		this.triggerOnLeaveHand(false, false);
		super.triggerOnManualDiscard();
	}

	@Override
	public void triggerOnEndOfPlayerTurn() {
		this.triggerOnLeaveHand(false, true);
		super.triggerOnEndOfPlayerTurn();
	}

	public int getEmbrace() {
		return StackableCardModifierManager.getStackedValue(
				this, EmbraceOfTheVoidCardModifier.ID);
	}

	public int maxEmbrace() {
		return this.cost - this.getEmbrace();
	}

	public void addEmbrace(int amt) {
		amt = Integer.min(amt, this.maxEmbrace());

		if (amt > 0)
			StackableCardModifierManager.addModifier(
					this, new EmbraceOfTheVoidCardModifier(amt));
	}

	public boolean removeEmbraceAfterPlayed() {
		return false;
	}

	public void addActionsToTop(AbstractGameAction... actions) {
		ReiujiMod.addActionsToTop(actions);
	}

	public String bracketedName() {
		return "(" + this.name + ")";
	}
}

package ReiujiMod.abstracts;

import ReiujiMod.ReiujiMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;

import java.util.ArrayList;
import java.util.UUID;

import static ReiujiMod.ReiujiMod.CAUTION_FLAVOR;

public abstract class AbstractReiujiEchoCard extends AbstractReiujiCard {
	public boolean used = false;
	public AbstractReiujiEchoCard original = null;

	public AbstractReiujiEchoCard(
			String id,
			String name,
			String img,
			int cost,
			String rawDescription,
			CardType type,
			CardColor color,
			CardRarity rarity,
			CardTarget target
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
	}
	
	public AbstractReiujiEchoCard(AbstractReiujiEchoCard original) {
		super(
				original.cardID,
				original.name,
				"img/cards/Cirno/Th123Cirno.png",
				original.cost,
				original.rawDescription,
				original.type,
				original.color,
				original.rarity,
				original.target
		);
		
		this.original = original;
		this.used = false;
		
		this.cardsToPreview = original;
		
		for (int i = 0; i < original.timesUpgraded; i++)
			this.upgrade();
	}
	
	public void initAsDerivation() {
		this.chargeCost = -1;
		this.isCostModified = false;
		this.isCostModifiedForTurn = false;
		this.retain = false;
		this.upgraded = false;
		this.timesUpgraded = 0;
		this.misc = 0;
		this.upgradedCost = false;
		this.upgradedDamage = false;
		this.upgradedBlock = false;
		this.upgradedMagicNumber = false;
		this.isSelected = false;
		
		CardModifierManager.removeAllModifiers(this, false);
	}
	
	abstract public AbstractReiujiEchoCard makeDerivation();
	
	@Override
	public void triggerOnGlowCheck() {
		super.triggerOnGlowCheck();
		
		if (this.original != null)
			this.glowColor = GREEN_BORDER_GLOW_COLOR;
	}
	
	@Override
	public AbstractCard makeStatEquivalentCopy() {
		if (this.original != null)
			return this.original.makeStatEquivalentCopy();
		else
			return super.makeStatEquivalentCopy();
	}
	
	public void initAfterPlayed() {
		if (this.original == null)
			return;
		
		this.initAsDerivation();
	}
	
	public void triggerOnDerivationLeavesHand(boolean exhausted,
              boolean discarded, boolean manuallyDiscarded) {
		
		if (this.original != null)
			this.original.triggerOnDerivationLeavesHand(exhausted, discarded, manuallyDiscarded);
		else {
			if (this.type == CardType.POWER)
				return;
			
			if (this.exhaust)
				exhausted = true;
			
			if (exhausted)
				ReiujiMod.echoTempGroup.moveToExhaustPile(this);
			else if (discarded) {
				if (manuallyDiscarded)
					this.triggerOnManualDiscard();
				ReiujiMod.echoTempGroup.moveToDiscardPile(this);
			}
			else {
				if (this.shuffleBackIntoDrawPile)
					ReiujiMod.echoTempGroup.moveToDeck(this, false);
				else if (this.returnToHand)
					ReiujiMod.echoTempGroup.moveToHand(this);
				else
					ReiujiMod.echoTempGroup.moveToDiscardPile(this);
			}
		}
	}
}

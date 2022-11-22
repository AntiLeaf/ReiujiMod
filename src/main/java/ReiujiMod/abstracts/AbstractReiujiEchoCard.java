package ReiujiMod.abstracts;

import ReiujiMod.powers.HasUsedSpellPower;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static ReiujiMod.ReiujiMod.CHILLED_FLAVOR;

public abstract class AbstractReiujiEchoCard extends AbstractReiujiCard {
	boolean used = false;

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
		
//		FlavorText.AbstractCardFlavorFields.textColor.set(this, CHILLED);
		FlavorText.AbstractCardFlavorFields.boxColor.set(this, CHILLED_FLAVOR);
	}
	
	@Override
	public AbstractCard makeStatEquivalentCopy() {
		AbstractReiujiEchoCard card = (AbstractReiujiEchoCard) super.makeStatEquivalentCopy();

		card.used = this.used;

		return card;
	}
	
	@Override
	public void triggerOnGlowCheck() {
		this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
	}

	@Override
	 public boolean canPlay(AbstractCard card) {
		if (!super.canPlay(card))
			return false;

		return true;
	}
}

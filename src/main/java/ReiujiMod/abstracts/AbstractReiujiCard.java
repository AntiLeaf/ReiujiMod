package ReiujiMod.abstracts;

import ReiujiMod.ReiujiMod;
import ReiujiMod.powers.HasUsedSpellPower;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static ReiujiMod.ReiujiMod.CHILLED_FLAVOR;

public abstract class AbstractReiujiCard extends CustomCard implements SpawnModificationCard {
	protected static final Color CYAN_COLOR = new Color(0f, 204f / 255f, 0f, 1f);

	public boolean isSpellCard = false;
	public boolean isCombo = false;
	public int comboCnt = 0;
	
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
	public AbstractCard makeStatEquivalentCopy() {
		AbstractReiujiCard card = (AbstractReiujiCard) super.makeStatEquivalentCopy();

		card.isSpellCard = this.isSpellCard;
		card.isCombo = this.isCombo;

		return card;
	}
	
	@Override
	public void triggerOnGlowCheck() {
		if (this.isCombo)
			this.glowColor = Color.WHITE;
		else
			this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
	}

	@Override
	 public boolean canPlay(AbstractCard card) {
		if (!super.canPlay(card))
			return false;

		if (card instanceof AbstractReiujiCard &&
				((AbstractReiujiCard) card).isSpellCard &&
				AbstractDungeon.player.hasPower(HasUsedSpellPower.POWER_ID))
					return false;

		return true;
	}

	public void comboInit() {
		this.isCombo = true;
		this.comboCnt = 1;
		this.exhaust = true;
		this.isEthereal = true;

		if (AbstractDungeon.player != null) {
			AbstractPlayer p = AbstractDungeon.player;

//			if (p.hasPower())
		}
	}

	public boolean comboCheck() {
		return --this.comboCnt == 0;
	}

	public void updateComboCnt () {
		// TODO
	}
}

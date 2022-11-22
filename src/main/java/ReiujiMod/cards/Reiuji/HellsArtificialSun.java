package ReiujiMod.cards.Reiuji;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.patches.AbstractCardEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class HellsArtificialSun extends AbstractReiujiCard {
	public static final String SIMPLE_NAME = HellsArtificialSun.class.getSimpleName();

	public static final String ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int DAMAGE = 11;

	public HellsArtificialSun() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.REIUJI_COLOR,
			CardRarity.RARE,
			CardTarget.ALL_ENEMY
		);

		this.damage = this.baseDamage = DAMAGE;
		this.isSpellCard = true;
		this.returnToHand = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.calculateCardDamage(null);

		this.addToBot(new ExhaustAction(1, !this.upgraded, false));
		this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage,
				this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));

		this.isEthereal = true;
	}

	@Override
	public void triggerOnGlowCheck() {
		if (this.isEthereal)
			this.glowColor = Color.GREEN;
		else
			super.triggerOnGlowCheck();
	}

	@Override
	public void triggerOnManualDiscard() {
		this.addToTop(new ExhaustSpecificCardAction(
				this, AbstractDungeon.player.hand));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new HellsArtificialSun();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.initializeDescription();
		}
	}
}
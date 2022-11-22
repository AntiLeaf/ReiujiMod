package ReiujiMod.cards.Reiuji;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.action.ReiujiAnonymousAction;
import ReiujiMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NuclearRupture extends AbstractReiujiCard {
	public static final String SIMPLE_NAME = NuclearRupture.class.getSimpleName();

	public static final String ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int DAMAGE = 4;
	private static final int TIMES = 4;
	private static final int DRAW = 1;
	private static final int UPG_DRAW = 1;
	private static final int UPG_COST = 0;

	public NuclearRupture() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.REIUJI_COLOR,
			CardRarity.COMMON,
			CardTarget.ENEMY
		);

		this.damage = this.baseDamage = DAMAGE;
		this.magicNumber = this.baseMagicNumber = DRAW;
		this.draw = this.baseDraw = DRAW;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < TIMES; i++)
			this.addToBot(new DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.BLUNT_HEAVY));

		this.addToBot(new DrawCardAction(this.magicNumber));
		this.addToBot(new MakeTempCardInDrawPileAction(
				new VoidCard(), 1, true, true));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new NuclearRupture();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(UPG_COST);
			this.initializeDescription();
		}
	}
}
package ReiujiMod.cards.Reiuji;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SpreadSpark extends AbstractReiujiCard {
	public static final String SIMPLE_NAME = SpreadSpark.class.getSimpleName();

	public static final String ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int DAMAGE = 1;
	private static final int TIMES = 10;
	private static final int UPG_TIMES = 4;

	public SpreadSpark() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.REIUJI_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.ENEMY
		);

		this.damage = this.baseDamage = DAMAGE;
		this.magicNumber = this.baseMagicNumber = TIMES;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < this.magicNumber; i++)
			this.addToBot(new DamageAction(m,
				new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
	}

	@Override
	public void applyPowers() {
		// pass
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new SpreadSpark();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(UPG_TIMES);
			this.initializeDescription();
		}
	}
}
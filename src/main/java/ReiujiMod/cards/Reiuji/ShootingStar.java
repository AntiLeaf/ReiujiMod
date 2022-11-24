package ReiujiMod.cards.Reiuji;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.patches.AbstractCardEnum;
import ReiujiMod.powers.SecondCircuitPower;
import ReiujiMod.powers.ShootingStarPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShootingStar extends AbstractReiujiCard {
	public static final String SIMPLE_NAME = ShootingStar.class.getSimpleName();

	public static final String ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	public static final String IMG_PATH = "img/cards/" + SIMPLE_NAME + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int BLOCK = 6;
	private static final int UPG_BLOCK = 2;
	private static final int AMT = 2;
	private static final int UPG_AMT = 1;

	public ShootingStar() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.REIUJI_COLOR,
			CardRarity.BASIC,
			CardTarget.SELF
		);

		this.block = this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new GainBlockAction(p, p, this.block));
		this.addToBot(new DrawCardAction(1));
		this.addToBot(new ApplyPowerAction(p, p,
				new ShootingStarPower(this.magicNumber)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ShootingStar();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBlock(UPG_BLOCK);
			this.upgradeMagicNumber(UPG_AMT);
			this.initializeDescription();
		}
	}
}
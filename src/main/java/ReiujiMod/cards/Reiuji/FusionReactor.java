package ReiujiMod.cards.Reiuji;

import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.cards.ReiuijiDerivation.Spark;
import ReiujiMod.patches.enums.AbstractCardEnum;
import ReiujiMod.powers.EnchantPower;
import ReiujiMod.powers.FusionReactorPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FusionReactor extends AbstractReiujiCard {

	public static final String ID = FusionReactor.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int AMT = 3;
	private static final int UPG_AMT = 1;

	public FusionReactor() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.POWER,
			AbstractCardEnum.REIUJI_COLOR,
			CardRarity.COMMON,
			CardTarget.SELF
		);

		this.magicNumber = this.baseMagicNumber = AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p,
				new FusionReactorPower(this.magicNumber)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new FusionReactor();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(UPG_AMT);
			this.initializeDescription();
		}
	}
}
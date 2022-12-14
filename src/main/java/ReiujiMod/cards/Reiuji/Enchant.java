package ReiujiMod.cards.Reiuji;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.cards.ReiuijiDerivation.BlackHole;
import ReiujiMod.cards.ReiuijiDerivation.Spark;
import ReiujiMod.patches.enums.AbstractCardEnum;
import ReiujiMod.powers.EnchantPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Enchant extends AbstractReiujiCard {
	
	public static final String SIMPLE_NAME = Enchant.class.getSimpleName();
	
	public static final String ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	public static final String IMG_PATH = "img/cards/" + SIMPLE_NAME + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;

	public Enchant() {
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

		this.cardsToPreview = new Spark();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p,
				new EnchantPower(1)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Enchant();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.isInnate = true;
			this.initializeDescription();
		}
	}
}
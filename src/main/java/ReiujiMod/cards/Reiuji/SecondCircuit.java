package ReiujiMod.cards.Reiuji;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.patches.AbstractCardEnum;
import ReiujiMod.powers.HeatPower;
import ReiujiMod.powers.SecondCircuitPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class SecondCircuit extends AbstractReiujiCard {
	public static final String SIMPLE_NAME = SecondCircuit.class.getSimpleName();

	public static final String ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	public static final String IMG_PATH = "img/cards/" + SIMPLE_NAME + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int RATE = 30;
	private static final int UPG_RATE = 10;

	public SecondCircuit() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.POWER,
			AbstractCardEnum.REIUJI_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.SELF
		);

		this.magicNumber = this.baseMagicNumber = RATE;
		this.tags.add(CardTags.HEALING);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p,
				new SecondCircuitPower(this.magicNumber)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new SecondCircuit();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(UPG_RATE);
			this.initializeDescription();
		}
	}
}
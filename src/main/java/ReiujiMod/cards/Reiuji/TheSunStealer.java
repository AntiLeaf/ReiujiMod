package ReiujiMod.cards.Reiuji;

import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.patches.AbstractCardEnum;
import ReiujiMod.powers.SeptentrionPower;
import ReiujiMod.powers.TheSunStealerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TheSunStealer extends AbstractReiujiCard {

	public static final String ID = TheSunStealer.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;

	public TheSunStealer() {
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

		this.isSpellCard = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p,
				new TheSunStealerPower(1)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new TheSunStealer();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.isInnate = true;
			this.initializeDescription();
		}
	}
}
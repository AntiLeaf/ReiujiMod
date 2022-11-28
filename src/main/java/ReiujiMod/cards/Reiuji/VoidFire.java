package ReiujiMod.cards.Reiuji;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.embrace.EmbraceManager;
import ReiujiMod.patches.enums.AbstractCardEnum;
import ReiujiMod.powers.HeatPower;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VoidFire extends AbstractReiujiCard {
	public static final String SIMPLE_NAME = VoidFire.class.getSimpleName();

	public static final String ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	public static final String IMG_PATH = "img/cards/" + SIMPLE_NAME + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int AMT = 2;
	private static final int UPG_AMT = 1;

	public VoidFire() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.REIUJI_COLOR,
			CardRarity.COMMON,
			CardTarget.SELF
		);

		this.magicNumber = this.baseMagicNumber = AMT;
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		return super.canUse(p, m) && EmbraceManager.getEmbrace(this) > 0;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower(HeatPower.POWER_ID))
			this.addToBot(new ApplyPowerAction(p, p, new HeatPower(
					p.getPower(HeatPower.POWER_ID).amount *
							(this.magicNumber - 1))));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new VoidFire();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			AlwaysRetainField.alwaysRetain.set(this, true);
			this.initializeDescription();
		}
	}
}
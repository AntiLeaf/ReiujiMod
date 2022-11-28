package ReiujiMod.cards.ReiuijiDerivation;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.action.AddComboToHandAction;
import ReiujiMod.patches.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class ConsecutiveAttack3 extends AbstractReiujiCard {

	public static final String ID = ConsecutiveAttack3.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int DAMAGE = 5;
	private static final int VUL = 1;
	private static final int UPG_DAMAGE = 1;
	private static final int UPG_VUL = 1;

	public ConsecutiveAttack3() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.REIUJI_DERIVATION_COLOR,
			CardRarity.SPECIAL,
			CardTarget.ENEMY
		);
		
		this.damage = this.baseDamage = DAMAGE;
		this.magicNumber = this.baseMagicNumber = VUL;
//		this.comboInit();
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new DamageAction(m, new DamageInfo(p,
				this.damage, this.damageTypeForTurn)));

		this.addToBot(new ApplyPowerAction(m, p,
				new VulnerablePower(m, this.magicNumber, false)));

		this.addToBot(new AddComboToHandAction(
				ReiujiMod.upgraded(new ConsecutiveAttack4(), this.upgraded)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ConsecutiveAttack3();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(UPG_DAMAGE);
			this.upgradeMagicNumber(UPG_VUL);
			this.initializeDescription();
		}
	}
}
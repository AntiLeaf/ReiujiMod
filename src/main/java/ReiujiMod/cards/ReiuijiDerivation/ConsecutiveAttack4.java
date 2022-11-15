package ReiujiMod.cards.ReiuijiDerivation;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.action.AddComboToHandAction;
import ReiujiMod.patches.AbstractCardEnum;
import ReiujiMod.powers.CantUseAttackPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ConsecutiveAttack4 extends AbstractReiujiCard {

	public static final String ID = ConsecutiveAttack4.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int DAMAGE = 17;
	private static final int UPG_DAMAGE = 5;

	public ConsecutiveAttack4() {
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
		this.comboInit();
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new DamageAction(m, new DamageInfo(p,
				this.damage, this.damageTypeForTurn)));

		this.addToBot(new ApplyPowerAction(p, p, new CantUseAttackPower()));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ConsecutiveAttack4();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(UPG_DAMAGE);
			this.initializeDescription();
		}
	}
}
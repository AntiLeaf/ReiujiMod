package ReiujiMod.cards.ReiuijiDerivation;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.action.AddComboToHandAction;
import ReiujiMod.patches.enums.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ConsecutiveAttack2 extends AbstractReiujiCard {
	
	public static final String ID = ConsecutiveAttack2.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int DAMAGE = 6;
	private static final int UPG_DAMAGE = 3;
	
	public ConsecutiveAttack2() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.REIUJI_DERIVATION_COLOR,
			CardRarity.SPECIAL,
			CardTarget.ALL_ENEMY
		);
		
		this.damage = this.baseDamage = DAMAGE;
		this.isMultiDamage = true;
//		this.comboInit();
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		this.calculateCardDamage(null);

		this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage,
				this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

		this.addToBot(new AddComboToHandAction(
				ReiujiMod.upgraded(new ConsecutiveAttack3(), this.upgraded)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ConsecutiveAttack2();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(UPG_DAMAGE);
			this.initializeDescription();
		}
	}
}
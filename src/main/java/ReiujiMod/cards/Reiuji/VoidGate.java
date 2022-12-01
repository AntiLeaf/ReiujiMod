package ReiujiMod.cards.Reiuji;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.action.AddEmbraceAction;
import ReiujiMod.embrace.EmbraceManager;
import ReiujiMod.patches.enums.AbstractCardEnum;
import ReiujiMod.powers.HeatPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class VoidGate extends AbstractReiujiCard {
	public static final String SIMPLE_NAME = VoidGate.class.getSimpleName();

	public static final String ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	public static final String IMG_PATH = "img/cards/" + SIMPLE_NAME + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 2;
	private static final int BLOCK = 14;
	private static final int UPG_BLOCK = 2;
	private static final int AMT = 2;

	public VoidGate() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.REIUJI_COLOR,
			CardRarity.COMMON,
			CardTarget.NONE
		);
		
		this.block = this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new GainBlockAction(p, p, this.block));
		
		if (!this.upgraded) {
			ArrayList<AbstractCard> cards = new ArrayList<>();
			for (AbstractCard c : p.hand.group)
				if (c != this && EmbraceManager.getMaxEmbrace(c) > 0)
					cards.add(c);
			
			if (cards.size() > 0) {
				AbstractCard card = cards.get(
						AbstractDungeon.cardRandomRng.random(cards.size() - 1));
				this.addToBot(new AddEmbraceAction(card, this.magicNumber));
			}
		}
		else
			this.addToBot(new SelectCardsInHandAction(
					1, "", true, true,
					c -> EmbraceManager.getMaxEmbrace(c) > 0,
					(cards) -> {
						if (cards.size() > 0)
							this.addToBot(new AddEmbraceAction(cards.get(0), this.magicNumber));
					}
			));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new VoidGate();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeBlock(UPG_BLOCK);
			this.initializeDescription();
		}
	}
}
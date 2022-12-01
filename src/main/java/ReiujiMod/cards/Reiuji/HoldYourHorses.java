package ReiujiMod.cards.Reiuji;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.action.AnonymousAction;
import ReiujiMod.action.ReiujiExhaustSpecificCardAction;
import ReiujiMod.patches.enums.AbstractCardEnum;
import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HoldYourHorses extends AbstractReiujiCard {
	public static final String SIMPLE_NAME = HoldYourHorses.class.getSimpleName();
	
	public static final String ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	public static final String IMG_PATH = "img/cards/" + SIMPLE_NAME + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;

	public HoldYourHorses() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.REIUJI_COLOR,
			CardRarity.RARE,
			CardTarget.NONE
		);
		
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		Map<CardType, ArrayList<AbstractCard>> map = new HashMap<>();
		for (CardType t : new CardType[]{CardType.ATTACK, CardType.SKILL, CardType.POWER})
			map.put(t, new ArrayList<>());
		
		for (AbstractCard c : p.drawPile.group) {
			if (c.type != CardType.ATTACK && c.type != CardType.SKILL && c.type != CardType.POWER)
				continue;
			
			ArrayList<AbstractCard> list = map.get(c.type);
			
			if (!list.isEmpty() && c.costForTurn > list.get(0).costForTurn)
				list.clear();
			
			list.add(c);
		}
		
		ArrayList<AbstractCard> cardsToDraw = new ArrayList<>();
		for (ArrayList<AbstractCard> list : map.values())
			if (!list.isEmpty())
				cardsToDraw.add(list.get(AbstractDungeon.cardRng.random(0, list.size() - 1)));
		
		this.addToBot(new MoveCardsAction(p.drawPile, p.hand,
				cardsToDraw::contains, cardsToDraw.size()));
		
		if (this.upgraded)
			this.addToBot(new AnonymousAction(() -> {
				for (AbstractCard card : cardsToDraw)
					if (p.hand.contains(card))
						CardModifierManager.addModifier(card, new RetainMod());
			}));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new HoldYourHorses();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.initializeDescription();
		}
	}
}
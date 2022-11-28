package ReiujiMod.relics;

import ReiujiMod.ReiujiMod;
import ReiujiMod.abstracts.AbstractReiujiCard;
import ReiujiMod.powers.HeatPower;
import ReiujiMod.powers.InvisibleStarryCloakPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class StarryCloak extends CustomRelic {
	public static final String SIMPLE_NAME = StarryCloak.class.getSimpleName();

	public static final String ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	private static final String IMG = "img/relics/" + SIMPLE_NAME + ".png";
	private static final String IMG_OTL = "img/relics/outline/" + SIMPLE_NAME + ".png";

	public StarryCloak() {
		super(
				ID,
				ImageMaster.loadImage(IMG),
				ImageMaster.loadImage(IMG_OTL),
				RelicTier.RARE,
				LandingSound.MAGICAL
		);
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	@Override
	public void atBattleStart() {
		this.addToTop(new ApplyPowerAction(
				AbstractDungeon.player,
				AbstractDungeon.player,
				new InvisibleStarryCloakPower(1)));
		
		this.beginLongPulse();
	}
	
	@Override
	public void onVictory() {
		this.stopPulse();
	}
	
	@Override
	public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
		boolean flag = false;
		
		if (targetCard.block > 0)
			flag = true;
		else if (targetCard instanceof AbstractReiujiCard) {
			AbstractReiujiCard card = (AbstractReiujiCard) targetCard;
			
			if (card.heat > 0 || card.tempHP > 0)
				flag = true;
		}
		
		if (flag)
			this.flash();
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new StarryCloak();
	}
}
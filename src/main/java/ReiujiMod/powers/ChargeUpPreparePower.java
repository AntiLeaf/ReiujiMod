package ReiujiMod.powers;

import ReiujiMod.ReiujiMod;
import basemod.BaseMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ChargeUpPreparePower extends AbstractPower {
	public static final String SIMPLE_NAME = ChargeUpPreparePower.class.getSimpleName();

	public static final String POWER_ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	public static final String IMG_PATH = "img/powers/" + SIMPLE_NAME + ".png";
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;

	public ChargeUpPreparePower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture(IMG_PATH);
	}
	
	@Override
	public void stackPower(int amt) {
		if (amt > this.amount) {
			this.fontScale = 8.0F;
			this.amount = amt;
		}
	}
	
	@Override
	public void atStartOfTurn() {
		this.addToTop(new ApplyPowerAction(this.owner, this.owner, new ChargeUpPower(this.amount)));
		this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
	}
	
	@Override
	public void updateDescription() {
//		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		// TODO
	}
}
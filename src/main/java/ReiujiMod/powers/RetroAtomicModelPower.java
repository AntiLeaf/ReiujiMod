package ReiujiMod.powers;

import ReiujiMod.ReiujiMod;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RetroAtomicModelPower extends AbstractPower implements InvisiblePower {
	public static final String SIMPLE_NAME = RetroAtomicModelPower.class.getSimpleName();

	public static final String POWER_ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	public static final String IMG_PATH = "img/powers/" + SIMPLE_NAME + ".png";
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;

	public RetroAtomicModelPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture(IMG_PATH);
	}

	@Override
	public int onAttacked(DamageInfo info, int damageAmount) {
		if (info.type != DamageInfo.DamageType.THORNS &&
				info.type != DamageInfo.DamageType.HP_LOSS) {
			AbstractPlayer p = AbstractDungeon.player;
			this.addToBot(new AddTemporaryHPAction(p, p, this.amount));
		}

		return damageAmount;
	}
	
	@Override
	public void updateDescription() {
//		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
		// TODO
	}

	@Override
	public void atStartOfTurn() {
		this.addToBot(new RemoveSpecificPowerAction(
				AbstractDungeon.player, AbstractDungeon.player,
				RetroAtomicModelPower.POWER_ID));
	}
}
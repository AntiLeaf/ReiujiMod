package ReiujiMod.relics;

import ReiujiMod.ReiujiMod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class RedHotEyeOfYatagarasu extends CustomRelic {
	public static final String SIMPLE_NAME = RedHotEyeOfYatagarasu.class.getSimpleName();

	public static final String ID = ReiujiMod.SIMPLE_NAME + ":" + SIMPLE_NAME;
	private static final String IMG = "img/relics/" + SIMPLE_NAME + ".png";
	private static final String IMG_OTL = "img/relics/outline/" + SIMPLE_NAME + ".png";

	public static final int AMT = 4;

	public RedHotEyeOfYatagarasu() {
		super(
				ID,
				ImageMaster.loadImage(IMG),
				ImageMaster.loadImage(IMG_OTL),
				RelicTier.STARTER,
				LandingSound.HEAVY
		);

		this.counter = 0;
	}
	
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new RedHotEyeOfYatagarasu();
	}

	@Override
	public void onPlayerEndTurn() {
		int maxHeal = (AbstractDungeon.player.maxHealth + 4) / 5;

		if (AbstractDungeon.player.energy.energy > 0) {
			this.counter += 4;
			this.counter = Integer.min(this.counter, maxHeal);

			this.beginLongPulse();
		}
	}

	@Override
	public void onVictory() {
		if (this.counter > 0)
			AbstractDungeon.player.heal(this.counter, true);

		this.counter = 0;
		this.stopPulse();
	}
}
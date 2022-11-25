package ReiujiMod.patches;

import ReiujiMod.powers.TheSunStealerPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class VoidCardPatch {
	
	@SpirePatch(clz = VoidCard.class, method = "triggerWhenDrawn")
	public static class VoidCardTriggerWhenDrawnPatch {
		@SpirePrefixPatch
		public static SpireReturn<Void> Insert(VoidCard c) {
			if (AbstractDungeon.player.hasPower(TheSunStealerPower.POWER_ID))
				return SpireReturn.Return();
			else
				return SpireReturn.Continue();
		}
	}
}

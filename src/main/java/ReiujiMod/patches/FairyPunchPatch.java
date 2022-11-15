package ReiujiMod.patches;

import ReiujiMod.ThMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class FairyPunchPatch {
	
	@SpirePatch(clz = AbstractCard.class, method = "applyPowers")
	public static class FairyPunchApplyPowersPatch {
		@SpirePrefixPatch
		public static void Prefix(AbstractCard card) {
			ReiujiMod.fairyPunch(card);
		}
	}
}

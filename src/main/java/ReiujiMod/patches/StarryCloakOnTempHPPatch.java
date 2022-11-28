package ReiujiMod.patches;

import ReiujiMod.cardmodifier.StackableCardModifierManager;
import ReiujiMod.cardmodifier.modifiers.EmbraceOfTheVoidCardModifier;
import ReiujiMod.relics.StarryCloak;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import javax.swing.text.AbstractDocument;

@Deprecated
public class StarryCloakOnTempHPPatch {
	
//	@SpirePatch(clz = AddTemporaryHPAction.class, method = "AddTemporaryHPAction")
//	public static class StarryClockOnTempHPPatch {
//		@SpirePostfixPatch
//		public static void Postfix(AddTemporaryHPAction act) {
//			if (act.target == AbstractDungeon.player &&
//					AbstractDungeon.player.hasRelic(StarryCloak.ID))
//				act.amount += 1;
//		}
//	}
}

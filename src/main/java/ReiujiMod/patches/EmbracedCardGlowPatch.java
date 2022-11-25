package ReiujiMod.patches;

import ReiujiMod.cardmodifier.StackableCardModifier;
import ReiujiMod.cardmodifier.StackableCardModifierManager;
import ReiujiMod.cardmodifier.modifiers.EmbraceOfTheVoidCardModifier;
import ReiujiMod.powers.TheSunStealerPower;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EmbracedCardGlowPatch {
	
	@SpirePatch(clz = AbstractCard.class, method = "triggerOnGlowCheck")
	public static class EmbracedCardGlowPurplePatch {
		@SpirePrefixPatch
		public static void Insert(AbstractCard card) {
			if (StackableCardModifierManager.hasModifier(
					card, EmbraceOfTheVoidCardModifier.ID))
				card.glowColor = Color.VIOLET;
		}
	}
}

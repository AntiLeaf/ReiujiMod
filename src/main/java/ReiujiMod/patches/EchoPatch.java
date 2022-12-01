package ReiujiMod.patches;

import ReiujiMod.abstracts.AbstractReiujiEchoCard;
import ReiujiMod.action.MakeEchoDerivationInHandAction;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

public class EchoPatch {
	
	@SpirePatch(clz = UseCardAction.class, method = "<ctor>",
			paramtypez = {AbstractCard.class, AbstractCreature.class})
	public static class UseCardActionExhaustCardPatch {
		private static class TopLocator extends SpireInsertLocator {
			public int[] Locate(CtBehavior ctMethodToPatch)
					throws CannotCompileException, PatchingException {
				int[] loc = LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList<>(),
						new Matcher.MethodCallMatcher(
								UseCardAction.class, "setValues"));
				return new int[]{loc[0]};
			}
		}

		@SpireInsertPatch(locator = TopLocator.class)
		public static void Insert(UseCardAction act, AbstractCard card, AbstractCreature target) {
			if (card instanceof AbstractReiujiEchoCard)
				act.exhaustCard = false;
		}
	}
	
	@SpirePatch(clz = UseCardAction.class, method = "update")
	public static class UseCardActionUpdatePowerPatch {
		private static class TopLocator extends SpireInsertLocator {
			public int[] Locate(CtBehavior ctMethodToPatch)
					throws CannotCompileException, PatchingException {
				int[] loc = LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList<>(),
						new Matcher.MethodCallMatcher(
								CardGroup.class, "empower"));
				return new int[]{loc[0]};
			}
		}
		
		@SpireInsertPatch(locator = TopLocator.class)
		public static void Insert(UseCardAction act, AbstractCard ___targetCard) {
			if (___targetCard instanceof AbstractReiujiEchoCard) {
				AbstractReiujiEchoCard card = (AbstractReiujiEchoCard) ___targetCard;
				
				if (card.original != null)
					AbstractDungeon.actionManager.addToTop(
							new MakeEchoDerivationInHandAction(card.makeDerivation()));
				else
					AbstractDungeon.actionManager.addToTop(
							new MakeEchoDerivationInHandAction(card));
			}
		}
	}
	
	@SpirePatch(clz = UseCardAction.class, method = "update")
	public static class UseCardActionUpdateNotPowerPatch {
		private static class CustomLocator extends SpireInsertLocator {
			public int[] Locate(CtBehavior ctMethodToPatch)
					throws CannotCompileException, PatchingException {
				int[] loc = LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList<>(),
						new Matcher.MethodCallMatcher(
								CardGroup.class, "moveToDeck"));
				return new int[]{loc[0] - 1};
			}
		}
		
		@SpireInsertPatch(locator = CustomLocator.class)
		public static void Insert(UseCardAction act, AbstractCard ___targetCard) {
			if (___targetCard instanceof AbstractReiujiEchoCard) {
				AbstractReiujiEchoCard card = (AbstractReiujiEchoCard) ___targetCard;
				
				if (card.original != null)
					AbstractDungeon.actionManager.addToTop(
							new MakeEchoDerivationInHandAction(card.makeDerivation()));
				else
					AbstractDungeon.actionManager.addToTop(
							new MakeEchoDerivationInHandAction(card));
			}
		}
	}
}

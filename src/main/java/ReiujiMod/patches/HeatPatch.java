package ReiujiMod.patches;

import ReiujiMod.powers.HeatPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardAtBottomOfDeckAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HeatPatch {
	
	@SpirePatch(clz = DamageAction.class, method = "update")
	public static class HeatDamageSinglePatch {
		private static class BottomLocator extends SpireInsertLocator {
			public int[] Locate(CtBehavior ctMethodToPatch)
					throws CannotCompileException, PatchingException {
				int[] loc = LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList<>(),
						new Matcher.MethodCallMatcher(
								AbstractCreature.class, "damage"));
				return new int[]{loc[loc.length - 1]};
			}
		}

		@SpireInsertPatch(locator = BottomLocator.class)
		public static void Insert(DamageAction act) {
			if (act.damageType != DamageInfo.DamageType.NORMAL)
				return;

			AbstractPlayer p = AbstractDungeon.player;

			if (p.hasPower(HeatPower.POWER_ID)) {
				int amt = p.getPower(HeatPower.POWER_ID).amount;

				AbstractDungeon.actionManager.addToTop(new ReducePowerAction(
						p, p, HeatPower.POWER_ID, 1));

				AbstractDungeon.actionManager.addToTop(new DamageAction(
						act.target,
						new DamageInfo(p, amt, DamageInfo.DamageType.THORNS),
						AbstractGameAction.AttackEffect.FIRE));
			}
		}
	}

	@SpirePatch(clz = DamageAllEnemiesAction.class, method = "update")
	public static class HeatDamageAllPatch {
		private static class BottomLocator extends SpireInsertLocator {
			public int[] Locate(CtBehavior ctMethodToPatch)
					throws CannotCompileException, PatchingException {
				int[] loc = LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList<>(),
						new Matcher.MethodCallMatcher(
								MonsterGroup.class, "areMonstersBasicallyDead"));
				return new int[]{loc[loc.length - 1]};
			}
		}

		@SpireInsertPatch(locator = BottomLocator.class)
		public static void Insert(DamageAllEnemiesAction act) {
			if (act.damageType != DamageInfo.DamageType.NORMAL)
				return;

			AbstractPlayer p = AbstractDungeon.player;

			if (p.hasPower(HeatPower.POWER_ID)) {
				int amt = p.getPower(HeatPower.POWER_ID).amount;

				AbstractDungeon.actionManager.addToTop(new ReducePowerAction(
						p, p, HeatPower.POWER_ID, 1));

				int cnt = AbstractDungeon.getCurrRoom().monsters.monsters.size();

				int[] a = new int[cnt];
				for (int i = 0; i < cnt; i++)
					a[i] = amt;

				AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(
						p, a, DamageInfo.DamageType.THORNS,
						AbstractGameAction.AttackEffect.FIRE));
			}
		}
	}
}

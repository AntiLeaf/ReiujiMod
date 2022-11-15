package ReiujiMod.characters;

import ReiujiMod.ReiujiMod;
import ReiujiMod.cards.Reiuji.Defend_Reiuji;
import ReiujiMod.cards.Reiuji.Strike_Reiuji;
import ReiujiMod.patches.AbstractCardEnum;
import ReiujiMod.patches.ReiujiModClassEnum;
import ReiujiMod.relics.EyeOfYatagarasu;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Reiuji extends CustomPlayer {
	
	private static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
	private static final String REIUJI_SHOULDER_2 = "img/char/Reiuji/shoulder2.png"; // shoulder2 / shoulder_1
	private static final String REIUJI_SHOULDER_1 = "img/char/Reiuji/shoulder1.png"; // shoulder1 / shoulder_2
	private static final String REIUJI_CORPSE = "img/char/Reiuji/fallen.png"; // dead corpse
	public static final Logger logger = LogManager.getLogger(ReiujiMod.class.getName());
	//private static final float[] layerSpeeds = { 20.0F, 0.0F, -40.0F, 0.0F, 0.0F, 5.0F, 0.0F, -8.0F, 0.0F, 8.0F };
//	private static final String REIUJI_SKELETON_ATLAS = "img/char/Reiuji/MarisaModelv3.atlas";// Marisa_v0 / MarisaModel_v02 /MarisaModelv3
//	private static final String REIUJI_SKELETON_JSON = "img/char/Reiuji/MarisaModelv3.json";
	private static final String REIUJI_ANIMATION = "Idle";// Sprite / Idle
	private static final String[] ORB_TEXTURES = {
			"img/UI/EPanel/layer5.png",
			"img/UI/EPanel/layer4.png",
			"img/UI/EPanel/layer3.png",
			"img/UI/EPanel/layer2.png",
			"img/UI/EPanel/layer1.png",
			"img/UI/EPanel/layer0.png",
			"img/UI/EPanel/layer5d.png",
			"img/UI/EPanel/layer4d.png",
			"img/UI/EPanel/layer3d.png",
			"img/UI/EPanel/layer2d.png",
			"img/UI/EPanel/layer1d.png"
	};
	private static final String ORB_VFX = "img/UI/energyBlueVFX.png";
	private static final float[] LAYER_SPEED =
			{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
	//public static final String SPRITER_ANIM_FILEPATH = "img/char/MyCharacter/marisa_test.scml"; // spriter animation scml
	
	public Reiuji(String name) {
		//super(name, setClass, null, null , null ,new SpriterAnimation(SPRITER_ANIM_FILEPATH));
		super(name, ReiujiModClassEnum.REIUJI, ORB_TEXTURES, ORB_VFX, LAYER_SPEED, null, null);
		//super(name, setClass, null, null, (String) null, null);
		
		this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
		this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values
		
		logger.info("init Reiuji");
		
		initializeClass(
				"img/char/Reiuji/reiuji.png",
				REIUJI_SHOULDER_2, // required call to load textures and setup energy/loadout
				REIUJI_SHOULDER_1,
				REIUJI_CORPSE,
				getLoadout(),
				20.0F, -10.0F, 220.0F, 290.0F,
				new EnergyManager(ENERGY_PER_TURN)
		);
		
//		loadAnimation(REIUJI_SKELETON_ATLAS, REIUJI_SKELETON_JSON, 2.0F);
		// if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines
		/*
		AnimationState.TrackEntry e = this.state.setAnimation(0, REIUJI_ANIMATION, true);
		e.setTime(e.getEndTime() * MathUtils.random());
		this.stateData.setMix("Hit", "Idle", 0.1F);
		e.setTimeScale(1.0F);
		 */
		logger.info("init finish");
	}
	
	public ArrayList<String> getStartingDeck() { // 初始卡组
		ArrayList<String> ret = new ArrayList<>();
		
		for (int i = 0; i < 5; i++)
			ret.add(Strike_Reiuji.ID);
		for (int i = 0; i < 5; i++)
			ret.add(Defend_Reiuji.ID);
		
//		ret.add("IcicleShot");
//		ret.add("ShowOff");
		
		return ret;
	}
	
	public ArrayList<String> getStartingRelics() { // 初始遗物
		ArrayList<String> ret = new ArrayList<>();
		ret.add(EyeOfYatagarasu.ID);
		UnlockTracker.markRelicAsSeen(EyeOfYatagarasu.ID);
		return ret;
	}
	
	private static final int STARTING_HP = 80;
	private static final int MAX_HP = 80;
	private static final int STARTING_GOLD = 99;
	private static final int HAND_SIZE = 5;
	private static final int ASCENSION_MAX_HP_LOSS = 6;
	
	public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
		String title;
		String flavor;
		if (Settings.language == Settings.GameLanguage.ZHS || Settings.language == Settings.GameLanguage.ZHT) {
			title = "灵乌路空";
			flavor = "拥有操控核融合程度的能力的地狱鸦。体内寄宿着名为八咫乌的神明。";
		} else {
			title = "Reiuji Utsuho";
			flavor = "The Hell raven who is able to manipulate nuclear fusion, with Yatagarasu inside her.";
		}
		return new CharSelectInfo(
				title,
				flavor,
				STARTING_HP,
				MAX_HP,
				0,
				STARTING_GOLD,
				HAND_SIZE,
				this,
				getStartingRelics(),
				getStartingDeck(),
				false
		);
	}
	
	public AbstractCard.CardColor getCardColor() {
		return AbstractCardEnum.REIUJI_COLOR;
	}
	
	public AbstractCard getStartCardForEvent() {
		return new Strike_Reiuji();
	}
	
	public String getTitle(PlayerClass playerClass) {
		String title;
		if (Settings.language == GameLanguage.ZHS ||
				Settings.language == GameLanguage.ZHT)
			title = "难以驾驭的神之火";
		else if (Settings.language == GameLanguage.JPN)
			title = "熱かい悩む神の火";
		else
			title = "Scorching, Troublesome Divine Flame";

		return title;
	}
	
	public Color getCardTrailColor() {
		return ReiujiMod.CAUTION;
	}
	
	public int getAscensionMaxHPLoss() {
		return ASCENSION_MAX_HP_LOSS;
	}
	
	public BitmapFont getEnergyNumFont() {
		return FontHelper.energyNumFontBlue;
	}
	
	public void doCharSelectScreenSelectEffect() {
		CardCrawlGame.sound.playA("SELECT_REIUJI", MathUtils.random(-0.1F, 0.1F));
		CardCrawlGame.screenShake.shake(
				ScreenShake.ShakeIntensity.MED,
				ScreenShake.ShakeDur.SHORT,
				false
		);
	}
	
	public String getCustomModeCharacterButtonSoundKey() {
		return "SELECT_REIUJI";
	}
	
	public String getLocalizedCharacterName() {
		String char_name;
		if ((Settings.language == Settings.GameLanguage.ZHS)
				|| (Settings.language == Settings.GameLanguage.ZHT)) {
			char_name = "灵乌路空";
		} else if (Settings.language == Settings.GameLanguage.JPN) {
			char_name = "霊烏路 空";
		} else {
			char_name = "Reiuji Utsuho";
		}
		return char_name;
	}
	
	public AbstractPlayer newInstance() {
		return new Reiuji(this.name);
	}
	
	@Override
	public String getVampireText() {
		return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[1];
	}
	
	public Color getCardRenderColor() {
		return ReiujiMod.CAUTION;
	}
	
	public void updateOrb(int orbCount) {
		this.energyOrb.updateOrb(orbCount);
	}
	
	public TextureAtlas.AtlasRegion getOrb() {
		return new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ReiujiMod.CARD_ENERGY_ORB), 0, 0, 24, 24);
	}
	
	public Color getSlashAttackColor() {
		return ReiujiMod.CAUTION;
	}
	
	public AttackEffect[] getSpireHeartSlashEffect() {
		return new AttackEffect[]{
				AttackEffect.SLASH_HEAVY,
				AttackEffect.FIRE,
				AttackEffect.SLASH_DIAGONAL,
				AttackEffect.SLASH_HEAVY,
				AttackEffect.FIRE,
				AttackEffect.SLASH_DIAGONAL
		};
	}
	
	public String getSpireHeartText() {
		return com.megacrit.cardcrawl.events.beyond.SpireHeart.DESCRIPTIONS[10];
	}
	
	public void damage(DamageInfo info) {
//		if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS)
//				&& (info.output - this.currentBlock > 0)) {
//			AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
//			this.state.addAnimation(0, "Idle", true, 0.0F);
//			e.setTimeScale(1.0F);
//		}
		super.damage(info);
	}
	
	public void applyPreCombatLogic() {
		super.applyPreCombatLogic();
	}
	
}

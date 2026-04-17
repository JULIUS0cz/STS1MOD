// // Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
// package SlayTheSpire2Mod.characters;

// import com.badlogic.gdx.graphics.Color;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.graphics.g2d.TextureAtlas;
// import com.badlogic.gdx.math.MathUtils;
// import com.esotericsoftware.spine.AnimationState;
// import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
// import com.megacrit.cardcrawl.actions.AbstractGameAction;
// import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
// import com.megacrit.cardcrawl.cards.AbstractCard;
// import com.megacrit.cardcrawl.cards.DamageInfo;
// import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
// import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
// import com.megacrit.cardcrawl.cards.red.Bash;
// import com.megacrit.cardcrawl.characters.AbstractPlayer;
// import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
// import com.megacrit.cardcrawl.characters.Ironclad;
// import com.megacrit.cardcrawl.core.CardCrawlGame;
// import com.megacrit.cardcrawl.core.EnergyManager;
// import com.megacrit.cardcrawl.core.Settings;
// import com.megacrit.cardcrawl.events.beyond.SpireHeart;
// import com.megacrit.cardcrawl.events.city.Vampires;
// import com.megacrit.cardcrawl.helpers.CardLibrary;
// import com.megacrit.cardcrawl.helpers.FontHelper;
// import com.megacrit.cardcrawl.helpers.ImageMaster;
// import com.megacrit.cardcrawl.helpers.ModHelper;
// import com.megacrit.cardcrawl.helpers.Prefs;
// import com.megacrit.cardcrawl.helpers.SaveHelper;
// import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
// import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
// import com.megacrit.cardcrawl.localization.CharacterStrings;
// import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
// import com.megacrit.cardcrawl.screens.CharSelectInfo;
// import com.megacrit.cardcrawl.screens.stats.CharStat;
// import com.megacrit.cardcrawl.screens.stats.StatsScreen;
// import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
// import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbRed;
// import com.megacrit.cardcrawl.unlock.UnlockTracker;

// import basemod.abstracts.CustomPlayer;

// import java.util.ArrayList;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

// public class Ironclad2 extends CustomPlayer {
//     private static final Logger logger = LogManager.getLogger(Ironclad2.class.getName());
//     private static final CharacterStrings characterStrings;
//     public static final String[] NAMES;
//     public static final String[] TEXT;
//     private static EnergyOrbInterface energyOrb = new EnergyOrbRed();
//     private Prefs prefs;
//     private CharStat charStat = new CharStat(this);
//     // private static final String[] ORB_TEXTURES = new String[] {
//     //         "SlayTheSpire2ModResources/img/UI/orb/layer5.png",
//     //         "SlayTheSpire2ModResources/img/UI/orb/layer4.png",
//     //         "SlayTheSpire2ModResources/img/UI/orb/layer3.png",
//     //         "SlayTheSpire2ModResources/img/UI/orb/layer2.png",
//     //         "SlayTheSpire2ModResources/img/UI/orb/layer1.png",
//     //         "SlayTheSpire2ModResources/img/UI/orb/layer6.png",
//     //         "SlayTheSpire2ModResources/img/UI/orb/layer5d.png",
//     //         "SlayTheSpire2ModResources/img/UI/orb/layer4d.png",
//     //         "SlayTheSpire2ModResources/img/UI/orb/layer3d.png",
//     //         "SlayTheSpire2ModResources/img/UI/orb/layer2d.png",
//     //         "SlayTheSpire2ModResources/img/UI/orb/layer1d.png"
//     // };
//     // private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F,
//     //         -5.0F, 0.0F };

//     public Ironclad2(String playerName) {
//         super(playerName, PlayerColorEnum.IRONCLAD2, energyOrb, null);
//         this.dialogX = this.drawX + 0.0F * Settings.scale;
//         this.dialogY = this.drawY + 220.0F * Settings.scale;
//         this.initializeClass((String) null, "images/characters/ironclad/shoulder2.png",
//                 "images/characters/ironclad/shoulder.png", "images/characters/ironclad/corpse.png", this.getLoadout(),
//                 -4.0F, -16.0F, 220.0F, 290.0F, new EnergyManager(3));
//         this.loadAnimation("images/characters/ironclad/idle/skeleton.atlas",
//                 "images/characters/ironclad/idle/skeleton.json", 1.0F);
//         AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
//         this.stateData.setMix("Hit", "Idle", 0.1F);
//         e.setTimeScale(0.6F);
//         if (ModHelper.enabledMods.size() > 0 && (ModHelper.isModEnabled("Diverse") || ModHelper.isModEnabled("Chimera")
//                 || ModHelper.isModEnabled("Blue Cards"))) {
//             this.masterMaxOrbs = 1;
//         }

//     }

//     public String getPortraitImageName() {
//         return "ironcladPortrait.jpg";
//     }

//     public ArrayList<String> getStartingRelics() {
//         ArrayList<String> retVal = new ArrayList();
//         retVal.add("Burning Blood");
//         UnlockTracker.markRelicAsSeen("Burning Blood");
//         return retVal;
//     }

//     public ArrayList<String> getStartingDeck() {
//         ArrayList<String> retVal = new ArrayList();
//         retVal.add("Strike_R");
//         retVal.add("Strike_R");
//         retVal.add("Strike_R");
//         retVal.add("Strike_R");
//         retVal.add("Strike_R");
//         retVal.add("Defend_R");
//         retVal.add("Defend_R");
//         retVal.add("Defend_R");
//         retVal.add("Defend_R");
//         retVal.add("Bash");
//         return retVal;
//     }

//     public AbstractCard getStartCardForEvent() {
//         return new Bash();
//     }

//     public CharSelectInfo getLoadout() {
//         return new CharSelectInfo(NAMES[0], TEXT[0], 80, 80, 0, 99, 5, this, this.getStartingRelics(),
//                 this.getStartingDeck(), false);
//     }

//     public String getTitle(AbstractPlayer.PlayerClass plyrClass) {
//         return AbstractPlayer.uiStrings.TEXT[1];
//     }

//     public AbstractCard.CardColor getCardColor() {
//         return PlayerColorEnum.RED_2;
//     }

//     public Color getCardRenderColor() {
//         return Color.SCARLET;
//     }

//     public String getAchievementKey() {
//         return "RUBY";
//     }

//     public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
//         CardLibrary.addRedCards(tmpPool);
//         if (ModHelper.isModEnabled("Green Cards")) {
//             CardLibrary.addGreenCards(tmpPool);
//         }

//         if (ModHelper.isModEnabled("Blue Cards")) {
//             CardLibrary.addBlueCards(tmpPool);
//         }

//         if (ModHelper.isModEnabled("Purple Cards")) {
//             CardLibrary.addPurpleCards(tmpPool);
//         }

//         return tmpPool;
//     }

//     public Color getCardTrailColor() {
//         return new Color(1.0F, 0.4F, 0.1F, 1.0F);
//     }

//     public String getLeaderboardCharacterName() {
//         return "IRONCLAD2";
//     }

//     public Texture getEnergyImage() {
//         return ImageMaster.RED_ORB_FLASH_VFX;
//     }

//     public int getAscensionMaxHPLoss() {
//         return 5;
//     }

//     public BitmapFont getEnergyNumFont() {
//         return FontHelper.energyNumFontRed;
//     }

//     public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
//         this.energyOrb.renderOrb(sb, enabled, current_x, current_y);
//     }

//     public void updateOrb(int orbCount) {
//         this.energyOrb.updateOrb(orbCount);
//     }

//     public Prefs getPrefs() {
//         if (this.prefs == null) {
//             logger.error("prefs need to be initialized first!");
//         }

//         return this.prefs;
//     }

//     public void loadPrefs() {
//         this.prefs = SaveHelper.getPrefs("STSDataVagabond");
//     }

//     public CharStat getCharStat() {
//         return this.charStat;
//     }

//     public int getUnlockedCardCount() {
//         return UnlockTracker.unlockedRedCardCount;
//     }

//     public int getSeenCardCount() {
//         return CardLibrary.seenRedCards;
//     }

//     public int getCardCount() {
//         return CardLibrary.redCards;
//     }

//     public boolean saveFileExists() {
//         return SaveAndContinue.saveExistsAndNotCorrupted(this);
//     }

//     public String getWinStreakKey() {
//         return "win_streak_ironclad";
//     }

//     public String getLeaderboardWinStreakKey() {
//         return "IRONCLAD_CONSECUTIVE_WINS";
//     }

//     public void renderStatScreen(SpriteBatch sb, float screenX, float renderY) {
//         StatsScreen.renderHeader(sb, StatsScreen.NAMES[2], screenX, renderY);
//         this.charStat.render(sb, screenX, renderY);
//     }

//     public void doCharSelectScreenSelectEffect() {
//         CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2F, 0.2F));
//         CardCrawlGame.screenShake.shake(ShakeIntensity.MED, ShakeDur.SHORT, true);
//     }

//     public String getCustomModeCharacterButtonSoundKey() {
//         return "ATTACK_HEAVY";
//     }

//     public Texture getCustomModeCharacterButtonImage() {
//         return ImageMaster.FILTER_IRONCLAD;
//     }

//     public CharacterStrings getCharacterString() {
//         return CardCrawlGame.languagePack.getCharacterString("Ironclad");
//     }

//     public String getLocalizedCharacterName() {
//         return NAMES[0];
//     }

//     public void refreshCharStat() {
//         this.charStat = new CharStat(this);
//     }

//     public AbstractPlayer newInstance() {
//         return new Ironclad2(this.name);
//     }

//     public TextureAtlas.AtlasRegion getOrb() {
//         return AbstractCard.orb_red;
//     }

//     public void damage(DamageInfo info) {
//         if (info.owner != null && info.type != DamageType.THORNS && info.output - this.currentBlock > 0) {
//             AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
//             this.state.addAnimation(0, "Idle", true, 0.0F);
//             e.setTimeScale(0.6F);
//         }

//         super.damage(info);
//     }

//     public String getSpireHeartText() {
//         return SpireHeart.DESCRIPTIONS[8];
//     }

//     public Color getSlashAttackColor() {
//         return Color.RED;
//     }

//     public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
//         return new AbstractGameAction.AttackEffect[] { AttackEffect.SLASH_HEAVY, AttackEffect.FIRE,
//                 AttackEffect.BLUNT_HEAVY, AttackEffect.SLASH_HEAVY, AttackEffect.FIRE, AttackEffect.BLUNT_HEAVY };
//     }

//     public String getVampireText() {
//         return Vampires.DESCRIPTIONS[0];
//     }

//     static {
//         characterStrings = CardCrawlGame.languagePack.getCharacterString("Ironclad");
//         NAMES = characterStrings.NAMES;
//         TEXT = characterStrings.TEXT;
//     }

//     public static class PlayerColorEnum {
//         // 修改为你的颜色名称，确保不会与其他mod冲突
//         @SpireEnum
//         public static PlayerClass IRONCLAD2;

//         // ***将CardColor和LibraryType的变量名改为你的角色的颜色名称，确保不会与其他mod冲突***
//         // ***并且名称需要一致！***
//         @SpireEnum
//         public static AbstractCard.CardColor RED_2;
//     }

//     public static class PlayerLibraryEnum {
//         // ***将CardColor和LibraryType的变量名改为你的角色的颜色名称，确保不会与其他mod冲突***
//         // ***并且名称需要一致！***

//         // 这个变量未被使用（呈现灰色）是正常的
//         @SpireEnum
//         public static CardLibrary.LibraryType RED_2;
//     }
// }

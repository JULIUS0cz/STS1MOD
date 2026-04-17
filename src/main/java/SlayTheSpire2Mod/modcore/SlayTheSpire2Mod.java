package SlayTheSpire2Mod.modcore;

import java.lang.reflect.Field;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import SlayTheSpire2Mod.cards.Aggression;
import SlayTheSpire2Mod.cards.AshenStrike;
import SlayTheSpire2Mod.cards.BloodLetting2;
import SlayTheSpire2Mod.cards.BloodWall;
import SlayTheSpire2Mod.cards.Brand;
import SlayTheSpire2Mod.cards.BreakThrough;
import SlayTheSpire2Mod.cards.Bully;
import SlayTheSpire2Mod.cards.Cascade;
import SlayTheSpire2Mod.cards.Cinder;
import SlayTheSpire2Mod.cards.Colossus;
import SlayTheSpire2Mod.cards.Conflagration;
import SlayTheSpire2Mod.cards.CrimsonMantle;
import SlayTheSpire2Mod.cards.Cruelty;
import SlayTheSpire2Mod.cards.Dismantle;
import SlayTheSpire2Mod.cards.Dominate;
import SlayTheSpire2Mod.cards.DrumOfBattle;
import SlayTheSpire2Mod.cards.EvilEye;
import SlayTheSpire2Mod.cards.ExpectAFight;
import SlayTheSpire2Mod.cards.FightMe;
import SlayTheSpire2Mod.cards.ForgottenRitual;
import SlayTheSpire2Mod.cards.GiantRock;
import SlayTheSpire2Mod.cards.Grapple;
import SlayTheSpire2Mod.cards.Hellraiser;
import SlayTheSpire2Mod.cards.HowlFromBeyond;
import SlayTheSpire2Mod.cards.Inferno;
import SlayTheSpire2Mod.cards.Juggling;
import SlayTheSpire2Mod.cards.Mangle;
import SlayTheSpire2Mod.cards.MoltenFist;
import SlayTheSpire2Mod.cards.PactsEnd;
import SlayTheSpire2Mod.cards.PerfectedStrike2;
import SlayTheSpire2Mod.cards.Pillage;
import SlayTheSpire2Mod.cards.PrimalForce;
import SlayTheSpire2Mod.cards.Pyre;
import SlayTheSpire2Mod.cards.SetUpStrike;
import SlayTheSpire2Mod.cards.Spite;
import SlayTheSpire2Mod.cards.Stampede;
import SlayTheSpire2Mod.cards.Stoke;
import SlayTheSpire2Mod.cards.Stomp;
import SlayTheSpire2Mod.cards.StoneArmor;
import SlayTheSpire2Mod.cards.Strike;
import SlayTheSpire2Mod.cards.Taunt;
import SlayTheSpire2Mod.cards.TearAsunder;
import SlayTheSpire2Mod.cards.Thrash;
import SlayTheSpire2Mod.cards.Tremble;
import SlayTheSpire2Mod.cards.Unmovable;
import SlayTheSpire2Mod.cards.Unrelenting;
import SlayTheSpire2Mod.cards.Vicious;
import SlayTheSpire2Mod.powers.ColossusPower;

import com.megacrit.cardcrawl.core.AbstractCreature;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;

import basemod.interfaces.EditCardsSubscriber;

import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnPlayerDamagedSubscriber;
import basemod.interfaces.PostExhaustSubscriber;
import basemod.interfaces.OnPlayerTurnStartSubscriber;
// import SlayTheSpire2Mod.characters.Ironclad2;
import basemod.BaseMod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
// import static SlayTheSpire2Mod.characters.Ironclad2.PlayerColorEnum.Ironclad2;

@SpireInitializer // 加载mod的注解

public class SlayTheSpire2Mod
        implements EditCardsSubscriber, EditStringsSubscriber, OnPlayerTurnStartSubscriber, PostExhaustSubscriber,
        OnPlayerDamagedSubscriber
/* ,EditCharactersSubscriber */ { // 实现接口

    public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);

    public static int exhaustCountThisTurn = 0;
    public static int tookDamageCountThisBattle = 0;
    public static int hpLossThisTurn = 0;

    public SlayTheSpire2Mod() {
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件

    }

    public static void initialize() {
        new SlayTheSpire2Mod();

        // logger.info("本场战斗掉血量:" + GameActionManager.hpLossThisCombat);

    }

    // 当basemod开始注册mod卡牌时，便会调用这个函数
    @Override
    public void receiveEditCards() {

        // TODO 这里写添加你卡牌的代码
        // BaseMod.addCard(new Strike());
        BaseMod.addCard(new BreakThrough());
        BaseMod.addCard(new SetUpStrike());
        BaseMod.addCard(new MoltenFist());
        BaseMod.addCard(new Cinder());
        BaseMod.addCard(new Tremble());
        BaseMod.addCard(new BloodWall());
        BaseMod.addCard(new Bully());
        BaseMod.addCard(new Spite());
        BaseMod.addCard(new AshenStrike());
        BaseMod.addCard(new Dismantle());
        BaseMod.addCard(new Pillage());
        BaseMod.addCard(new Unrelenting());
        BaseMod.addCard(new Grapple());
        BaseMod.addCard(new FightMe());
        BaseMod.addCard(new HowlFromBeyond());
        BaseMod.addCard(new Stomp());
        BaseMod.addCard(new PerfectedStrike2());
        BaseMod.addCard(new Dominate());
        BaseMod.addCard(new EvilEye());
        BaseMod.addCard(new ForgottenRitual());
        BaseMod.addCard(new Taunt());
        BaseMod.addCard(new ExpectAFight());
        BaseMod.addCard(new DrumOfBattle());
        BaseMod.addCard(new Inferno());
        BaseMod.addCard(new Juggling());
        BaseMod.addCard(new Vicious());
        BaseMod.addCard(new StoneArmor());
        BaseMod.addCard(new Stampede());
        BaseMod.addCard(new PactsEnd());
        BaseMod.addCard(new Conflagration());
        BaseMod.addCard(new Thrash());
        BaseMod.addCard(new TearAsunder());
        BaseMod.addCard(new Mangle());
        BaseMod.addCard(new Brand());
        BaseMod.addCard(new Cascade());
        BaseMod.addCard(new Colossus());
        BaseMod.addCard(new Stoke());
        BaseMod.addCard(new Aggression());
        BaseMod.addCard(new CrimsonMantle());
        BaseMod.addCard(new Cruelty());
        BaseMod.addCard(new Hellraiser());
        BaseMod.addCard(new Pyre());
        BaseMod.addCard(new Unmovable());
        BaseMod.addCard(new PrimalForce());
        BaseMod.addCard(new GiantRock());
        BaseMod.addCard(new BloodLetting2());
    }

    // @Override
    // public void receiveEditCharacters() {
    // // 向basemod注册人物
    // BaseMod.addCharacter(new Ironclad2(CardCrawlGame.playerName),
    // MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT,
    // Ironclad2);
    // }

    @Override
    public void receiveEditStrings() {
        String lang;
        if (Settings.language == GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ZHS"; // 如果没有相应语言的版本，默认加载英语
        }
        BaseMod.loadCustomStringsFile(CardStrings.class,
                "SlayTheSpire2ModResources/localization/" + lang + "/cards.json"); // 加载相应语言的卡牌本地化内容。
        // 如果是中文，加载的就是"ExampleResources/localization/ZHS/cards.json"
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                "SlayTheSpire2ModResources/localization/" + lang + "/powers.json");
    }

    @Override
    public void receiveOnPlayerTurnStart() {
        // logger.info("本回合掉血1：" + hpLossThisTurn);
        exhaustCountThisTurn = 0;
        hpLossThisTurn = 0;
        // logger.info("新的回合开始了！！！！！！！！！！！！！！！");
        // logger.info("本回合消耗卡牌数：" + exhaustCountThisTurn);
        // logger.info("本回合掉血2：" + hpLossThisTurn);
    }

    @Override
    public void receivePostExhaust(AbstractCard c) {
        exhaustCountThisTurn++;
        // logger.info("消耗时：" + exhaustCountThisTurn);

    }

    @Override
    public int receiveOnPlayerDamaged(int var1, DamageInfo var2) {

        hpLossThisTurn += var1;
        // logger.info("掉血时：" + hpLossThisTurn);

        return var1;
    }

    @SpirePatch(clz = VulnerablePower.class, method = "atDamageReceive")
    public static class CrueltyPostfixPatch {
        @SpirePostfixPatch
        public static float damage(float finalDamage, VulnerablePower _inst, float damage, DamageInfo.DamageType type) {

            if (_inst.owner != null && !_inst.owner.isPlayer
                    && AbstractDungeon.player.hasPower("SlayTheSpire2Mod:CrueltyPower")) {
                System.out.println(
                        (float) AbstractDungeon.player.getPower("SlayTheSpire2Mod:CrueltyPower").amount / 100.0);
                finalDamage = finalDamage
                        * (1 + (float) (AbstractDungeon.player.getPower("SlayTheSpire2Mod:CrueltyPower").amount
                                / 100.0));
                return finalDamage;
            }
            return finalDamage;
        }
    }

    @SpirePatch(clz = AbstractMonster.class, method = "calculateDamage")
    public static class ColossusIntentInterPatch {
        @SpireInsertPatch(rloc = 47)
        public static void ColossusIntentInter(AbstractMonster __inst, int dmg) {
            if (!__inst.isDeadOrEscaped()
                    && __inst.hasPower(VulnerablePower.POWER_ID)
                    && AbstractDungeon.player.hasPower(ColossusPower.POWER_ID)) {
                try {
                    Field intentDmgField = AbstractMonster.class.getDeclaredField("intentDmg");
                    intentDmgField.setAccessible(true); // 绕过 private 限制

                    int currentIntentDmg = intentDmgField.getInt(__inst);

                    int modifiedIntentDmg = Math.max(0, currentIntentDmg / 2);
                    intentDmgField.set(__inst, modifiedIntentDmg);
                    // System.out.println("原本伤害： " + currentIntentDmg + " to "
                    // + modifiedIntentDmg + " using reflection in Insert.");

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @SpirePatch(clz = DamageInfo.class, method = "applyPowers")
    public static class ColossusInterPatch {
        @SpireInsertPatch(rloc = 57)
        public static void ColossusInter(DamageInfo __inst, AbstractCreature owner, AbstractCreature target) {
            if (owner instanceof AbstractMonster && target.isPlayer && !owner.isDeadOrEscaped()
                    && owner.hasPower(VulnerablePower.POWER_ID) && target.hasPower(ColossusPower.POWER_ID)) {

                int damage = __inst.output;
                int finaldamage = Math.max(0, damage / 2);
                __inst.output = finaldamage;
                // System.out.println("原本伤害 ：" + damage + "现在伤害：" + finaldamage);
            }
        }
    }

}

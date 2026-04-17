// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package SlayTheSpire2Mod.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;

import SlayTheSpire2Mod.helpers.ModHelper;
import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;

import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InfernoPower extends AbstractPower {

    public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);

    public static final String POWER_ID = ModHelper.FormatID(InfernoPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int hpLoss;

    public InfernoPower(AbstractCreature owner, int amount, int hpLoss) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.hpLoss = hpLoss;
        this.updateDescription();
        String path128 = "SlayTheSpire2ModResources/img/powers/InfernoPower84.png";
        String path48 = "SlayTheSpire2ModResources/img/powers/InfernoPower32.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84,
                84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
    }

    public void atStartOfTurn() {
        // logger.info("回合开始！！！！！！！！！！！！！！！！");
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            // logger.info("掉血");
            this.flash();
            this.addToBot(new LoseHPAction(this.owner, this.owner, this.hpLoss, AttackEffect.FIRE));
        }

    }

    public void wasHPLost(DamageInfo info, int damageAmount) {

        if (damageAmount > 0 && info.owner == this.owner) {
            this.flash();
            this.addToBot(new DamageAllEnemiesAction((AbstractCreature) null,
                    DamageInfo.createDamageMatrix(this.amount, true), DamageType.THORNS, AttackEffect.FIRE));
        }

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        this.hpLoss++;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.hpLoss + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
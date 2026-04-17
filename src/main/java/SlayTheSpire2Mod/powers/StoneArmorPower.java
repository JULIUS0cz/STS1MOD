// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package SlayTheSpire2Mod.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;

import SlayTheSpire2Mod.helpers.ModHelper;
import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StoneArmorPower extends AbstractPower {

    public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);

    public static final String POWER_ID = ModHelper.FormatID(StoneArmorPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public StoneArmorPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        String path128 = "SlayTheSpire2ModResources/img/powers/StoneArmorPower84.png";
        String path48 = "SlayTheSpire2ModResources/img/powers/StoneArmorPower32.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84,
                84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
    }

    public void atStartOfTurn() {
        // logger.info("回合开始！！！！！！！！！");
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        // logger.info("回合结束！！！！！！！！！");
        this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
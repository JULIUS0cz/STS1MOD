// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package SlayTheSpire2Mod.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;

import SlayTheSpire2Mod.helpers.ModHelper;
import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import com.megacrit.cardcrawl.helpers.ImageMaster;

import com.megacrit.cardcrawl.localization.PowerStrings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnmovablePower extends AbstractPower {

    public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);

    public static final String POWER_ID = ModHelper.FormatID(UnmovablePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int powerAmount = 0;

    public UnmovablePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.powerAmount = amount;
        this.updateDescription();
        String path128 = "SlayTheSpire2ModResources/img/powers/UnmovablePower84.png";
        String path48 = "SlayTheSpire2ModResources/img/powers/UnmovablePower32.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84,
                84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        this.powerAmount += stackAmount;
    }

    public float modifyBlock(float blockAmount) {
        // logger.info("111111 " + this.powerAmount);
        if (this.powerAmount > 0) {
            return blockAmount * 2.0f;
        }
        return blockAmount;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.baseBlock > 0) {
            this.powerAmount--;
        }
    }

    public void atStartOfTurn() {
        // logger.info("回合开始 " + this.powerAmount);
        this.powerAmount = this.amount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
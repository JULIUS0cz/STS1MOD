package SlayTheSpire2Mod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import SlayTheSpire2Mod.action.HowlFromBeyondAction;
import SlayTheSpire2Mod.helpers.ModHelper;
import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HowlFromBeyondPower extends AbstractPower {

    public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);

    public static final String POWER_ID = ModHelper.FormatID(HowlFromBeyondPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public AbstractCreature target;

    public HowlFromBeyondPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        String path128 = "SlayTheSpire2ModResources/img/powers/HowlFromBeyondPower84.png";
        String path48 = "SlayTheSpire2ModResources/img/powers/HowlFromBeyondPower32.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84,
                84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();

    }

    public void atStartOfTurn() {

        // logger.info("StartOfTurn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        this.addToBot(new HowlFromBeyondAction());
        // 要改，减层数，需要测试

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    // static {
    // powerStrings =
    // CardCrawlGame.languagePack.getPowerStrings("HowlFromBeyondPower");
    // NAME = powerStrings.NAME;
    // DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    // }
}

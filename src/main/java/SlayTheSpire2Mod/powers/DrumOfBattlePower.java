package SlayTheSpire2Mod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import SlayTheSpire2Mod.action.ExhaustTopCardAction;
import SlayTheSpire2Mod.helpers.ModHelper;

public class DrumOfBattlePower extends AbstractPower {
    public static final String POWER_ID = ModHelper.FormatID(DrumOfBattlePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public AbstractCreature target;

    public DrumOfBattlePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;

        String path128 = "SlayTheSpire2ModResources/img/powers/DrumOfBattlePower84.png";
        String path48 = "SlayTheSpire2ModResources/img/powers/DrumOfBattlePower32.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84,
                84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();

    }

    public void atStartOfTurn() {
        for (int i = 0; i < this.amount; i++) {
            this.addToTop(new ExhaustTopCardAction(true));
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}

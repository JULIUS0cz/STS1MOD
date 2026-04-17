package SlayTheSpire2Mod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import SlayTheSpire2Mod.helpers.ModHelper;

public class GrapplePower extends AbstractPower {
    public static final String POWER_ID = ModHelper.FormatID(GrapplePower.class.getSimpleName());
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public AbstractCreature target;

    public GrapplePower(AbstractCreature target, AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "GrapplePower";
        this.owner = owner;
        this.amount = amount;
        this.target = target;

        String path128 = "SlayTheSpire2ModResources/img/powers/GrapplePower84.png";
        String path48 = "SlayTheSpire2ModResources/img/powers/GrapplePower32.png";
        this.region128 = new AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();

    }

    public void onGainedBlock(float blockAmount) {
        if (blockAmount > 0.0F) {
            this.flash();
            if (!this.target.isDeadOrEscaped() && this.target != null) {
                this.addToBot(new DamageAction(target, new DamageInfo(this.owner, this.amount, DamageType.THORNS),
                        AttackEffect.SLASH_HORIZONTAL));
            }
        }

    }

    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "GrapplePower"));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("GrapplePower");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}

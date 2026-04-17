package SlayTheSpire2Mod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;

import com.megacrit.cardcrawl.core.Settings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StokeAction extends AbstractGameAction {
    public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);

    public StokeAction() {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;

    }

    public void update() {

        int count = AbstractDungeon.player.hand.size();

        for (int i = 0; i < count; i++) {
            if (Settings.FAST_MODE) {
                this.addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
            } else {
                this.addToTop(new ExhaustAction(1, true, true));
            }
        }

        for (int i = 0; i < count; i++) {
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
        }

        this.isDone = true;
    }

}
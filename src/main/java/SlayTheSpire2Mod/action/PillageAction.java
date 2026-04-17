package SlayTheSpire2Mod.action;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.core.Settings;



public class PillageAction extends AbstractGameAction {

    // public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);

    public PillageAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
    }

    public void update() {

        AbstractCard TopCard = AbstractDungeon.player.hand.getTopCard();

        // SlayTheSpire2Mod.logger.info("TopCardType1:" + TopCard.type);
        if (TopCard != null && TopCard.type == CardType.ATTACK) {
            if (AbstractDungeon.player.hand.size() < 10) {
                // SlayTheSpire2Mod.logger.info("TopCardType2:" + TopCard.type);
                this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
                // SlayTheSpire2Mod.logger.info("TopCardType3:" + TopCard.type);
                AbstractDungeon.actionManager.addToBottom(new PillageAction());
                // SlayTheSpire2Mod.logger.info("TopCardType4:" + TopCard.type);
                this.isDone = true;
            }
        }
        // SlayTheSpire2Mod.logger.info("TopCardType5:" + TopCard.type);
        this.isDone = true;
    }
}

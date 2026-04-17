package SlayTheSpire2Mod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import SlayTheSpire2Mod.cards.HowlFromBeyond;
import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;

import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HowlFromBeyondAction extends AbstractGameAction {

    public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);
    private AbstractPlayer p;

    public HowlFromBeyondAction() {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;

    }

    public void update() {

        ArrayList<AbstractCard> cardToProcess = new ArrayList<>();
        this.p = AbstractDungeon.player;

        for (AbstractCard card : AbstractDungeon.player.exhaustPile.group) {
            logger.info("消耗牌堆里的所有牌：" + card.cardID);
            if (card.cardID.equals(HowlFromBeyond.ID)) {
                logger.info("消耗堆里的：     " + card.cardID + "    UUID: " + card.uuid);
                cardToProcess.add(card);
            }
        }

        if (cardToProcess.isEmpty()) {
            logger.info("没有HowlFromBeyond");
            this.isDone = true;
        }

        for (AbstractCard card : cardToProcess) {
            logger.info("准备打出：" + card.cardID + "   UUID: " + card.uuid);

            this.addToTop(new NewQueueCardAction(card, true, false, true));
            AbstractDungeon.player.exhaustPile.group.remove(card);
            card.unfadeOut();

            if (!Settings.FAST_MODE) {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }

        }

        this.isDone = true;

    }

}

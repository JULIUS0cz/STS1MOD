package SlayTheSpire2Mod.action;

import java.util.ArrayList;
import java.util.HashSet;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StampedeAction extends AbstractGameAction {
    public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);
    private AbstractCard theCard = null;
    private AbstractCreature owner;
    private int amount;
    private HashSet<AbstractCard> usedCardsThisRun;

    public StampedeAction(AbstractCreature owner, int amount, HashSet<AbstractCard> usedCardsSet) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.owner = owner;
        this.amount = amount;
        this.usedCardsThisRun = usedCardsSet;

    }

    public StampedeAction(AbstractCreature owner, int amount) {
        this(owner, amount, new HashSet<>());
    }

    public void update() {

        if (this.amount <= 0) {

            this.isDone = true;
            return;
        }
        ArrayList<AbstractCard> attackCards = new ArrayList();
        // logger.info("0000000000000000            " + this.usedCardsThisRun);
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type.equals(CardType.ATTACK) && !this.usedCardsThisRun.contains(c)) {
                attackCards.add(c);
                // logger.info("111111             " + c.uuid);
            }

        }
        if (attackCards.isEmpty()) {

            this.isDone = true;
            return;
        }
        this.theCard = (AbstractCard) attackCards
                .get(AbstractDungeon.miscRng.random(0, attackCards.size() - 1));
        logger.info("2222222       " + this.theCard.uuid);
        this.usedCardsThisRun.add(this.theCard);
        this.theCard.freeToPlayOnce = true;

        // switch (this.theCard.target) {
        // case ENEMY:
        AbstractDungeon.actionManager.cardQueue
                .add(new CardQueueItem(this.theCard, AbstractDungeon.getRandomMonster()));

        this.amount--;

        // break;
        // case ALL_ENEMY:
        // AbstractDungeon.actionManager.cardQueue
        // .add(new CardQueueItem(this.theCard));
        // default:
        // AbstractDungeon.actionManager.cardQueue
        // .add(new CardQueueItem(this.theCard, (AbstractMonster) null));
        if (this.amount > 0) {

            this.addToBot(new StampedeAction(this.owner, this.amount, this.usedCardsThisRun));
        }

        this.isDone = true;
    }

}
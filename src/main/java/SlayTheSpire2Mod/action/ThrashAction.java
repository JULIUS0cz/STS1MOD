package SlayTheSpire2Mod.action;

import java.util.ArrayList;
import java.util.UUID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThrashAction extends AbstractGameAction {
    public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);
    private AbstractCard theCard = null;
    private UUID uuid;

    public ThrashAction(UUID uuid) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.uuid = uuid;

    }

    public void update() {

        ArrayList<AbstractCard> attackCards = new ArrayList();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type.equals(CardType.ATTACK)) {
                attackCards.add(c);
                // logger.info("uuid:   " + c.uuid + "cardId:   " + c.cardID);
            }

        }
        if (attackCards.isEmpty()) {

            this.isDone = true;
            return;
        }

        this.theCard = (AbstractCard) attackCards
                .get(AbstractDungeon.miscRng.random(0, attackCards.size() - 1));
        // logger.info("this.theCard.uuid:    " + this.theCard.uuid);
        AbstractDungeon.player.hand.moveToExhaustPile(this.theCard);
        this.addToBot(new ModifyDamageAction(this.uuid, this.theCard.damage));
        // logger.info("Thrash.uuid:    " + this.uuid);

        this.isDone = true;
    }

}
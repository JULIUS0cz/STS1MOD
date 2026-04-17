// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package SlayTheSpire2Mod.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AggressionAction extends AbstractGameAction {
    public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);
    private AbstractCard theCard = null;

    public AggressionAction() {

    }

    public void update() {
        ArrayList<AbstractCard> attackCards = new ArrayList();
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
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
        AbstractDungeon.player.discardPile.moveToHand(this.theCard);
        if (this.theCard.canUpgrade()) {
            this.theCard.upgrade();
            this.theCard.superFlash();
            this.theCard.applyPowers();
        }

        this.isDone = true;
    }

}

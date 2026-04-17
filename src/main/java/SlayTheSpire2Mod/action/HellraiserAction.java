// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package SlayTheSpire2Mod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HellraiserAction extends AbstractGameAction {
    public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);
    private AbstractCard theCard = null;

    public HellraiserAction(AbstractCard card) {
        this.theCard = card;

    }

    public void update() {
        if (this.theCard.hasTag(CardTags.STRIKE)) {
            this.theCard.freeToPlayOnce = true;

            AbstractDungeon.actionManager.cardQueue
                    .add(new CardQueueItem(this.theCard, AbstractDungeon.getRandomMonster()));
        }

        this.isDone = true;
    }

}

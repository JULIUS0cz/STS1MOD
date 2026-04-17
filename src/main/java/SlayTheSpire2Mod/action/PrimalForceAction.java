package SlayTheSpire2Mod.action;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import SlayTheSpire2Mod.cards.GiantRock;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

public class PrimalForceAction extends AbstractGameAction {
    boolean upgraded = false;

    public PrimalForceAction(boolean upgraded) {
        this.actionType = ActionType.WAIT;
        this.upgraded = upgraded;

    }

    public void update() {

        ArrayList<AbstractCard> handCopy = new ArrayList<>(AbstractDungeon.player.hand.group);
        for (AbstractCard c : handCopy) {
            if (c.type == CardType.ATTACK) {

                AbstractDungeon.player.hand.removeCard(c);
                if (!upgraded) {
                    AbstractDungeon.player.hand.addToTop(new GiantRock());
                } else {
                    AbstractCard g = (new GiantRock()).makeCopy();
                    g.upgrade();
                    AbstractDungeon.player.hand.addToTop(g);
                }
            }
        }
        this.isDone = true;

    }

}

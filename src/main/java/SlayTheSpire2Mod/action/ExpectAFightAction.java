package SlayTheSpire2Mod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;


public class ExpectAFightAction extends AbstractGameAction {

    int attackAmount = 0;

    public ExpectAFightAction(int attackAmount) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.attackAmount = attackAmount;
    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == CardType.ATTACK) {
                this.addToTop(new GainEnergyAction(this.attackAmount));
            }
        }
        this.isDone = true;

    }

}

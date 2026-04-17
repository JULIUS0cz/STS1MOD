package SlayTheSpire2Mod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;


import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class FightMeAction extends AbstractGameAction {
    private int damageIncreaseSource;
    private int damageIncreaseTarget;
    private AbstractMonster targetMonster;

    public FightMeAction(int damageIncreaseSource, int damageIncreaseTarget, AbstractMonster m) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.damageIncreaseSource = damageIncreaseSource;
        this.damageIncreaseTarget = damageIncreaseTarget;
        this.targetMonster = m;
    }

    public void update() {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new StrengthPower(AbstractDungeon.player, this.damageIncreaseSource), this.damageIncreaseSource));
        if (this.targetMonster != null && !this.targetMonster.isDeadOrEscaped()) {
            this.addToBot(new ApplyPowerAction(this.targetMonster, AbstractDungeon.player,
                    new StrengthPower(this.targetMonster, this.damageIncreaseTarget), this.damageIncreaseTarget));
        }
        this.isDone = true;

    }

}

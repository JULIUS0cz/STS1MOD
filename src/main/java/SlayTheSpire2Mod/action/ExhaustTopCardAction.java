// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package SlayTheSpire2Mod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;

import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExhaustTopCardAction extends AbstractGameAction {

   public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);

   private boolean exhaustCards;

   public ExhaustTopCardAction(boolean exhausts) {
      this.duration = Settings.ACTION_DUR_FAST;
      this.actionType = ActionType.WAIT;
      this.exhaustCards = exhausts;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
            this.isDone = true;
            return;
         }

         if (AbstractDungeon.player.drawPile.isEmpty()) {
            this.addToTop(new ExhaustTopCardAction(this.exhaustCards));
            this.addToTop(new EmptyDeckShuffleAction());
            this.isDone = true;
            return;
         }

         if (!AbstractDungeon.player.drawPile.isEmpty()) {
            AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
            AbstractDungeon.player.drawPile.group.remove(card);
            AbstractDungeon.getCurrRoom().souls.remove(card);
            card.exhaustOnUseOnce = this.exhaustCards;
            AbstractDungeon.player.limbo.group.add(card);

            // logger.info("name:{0} , uuid:{1}", card.name, card.uuid);
            // logger.info("1111 current_y:" + card.current_y);
            // logger.info("1111 target_x:" + card.target_x);
            // logger.info("1111 target_y:" + card.target_y);

            card.current_y = -200.0F * Settings.scale;
            card.target_x = (float) Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            card.target_y = (float) Settings.HEIGHT / 2.0F;

            // logger.info("2222 current_y:" + card.current_y);
            // logger.info("2222 target_x:" + card.target_x);
            // logger.info("2222 target_y:" + card.target_y);

            card.targetAngle = 0.0F;
            card.lighten(false);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;

            AbstractDungeon.player.drawPile.moveToExhaustPile(card);
            // 差消耗的逻辑

            this.addToTop(new UnlimboAction(card));
            if (!Settings.FAST_MODE) {
               this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
               this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }
         }

         this.isDone = true;
      }

   }
}

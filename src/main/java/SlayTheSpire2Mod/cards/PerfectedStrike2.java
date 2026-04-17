// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package SlayTheSpire2Mod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import SlayTheSpire2Mod.helpers.ModHelper;
import basemod.abstracts.CustomCard;

public class PerfectedStrike2 extends CustomCard {
   public static final String ID = ModHelper.FormatID(PerfectedStrike2.class.getSimpleName());
   private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
   private static final String NAME = CARD_STRINGS.NAME;
   private static final String IMG_PATH = "SlayTheSpire2ModResources/img/cards/PerfectedStrike2.png";
   private static final int COST = 2;
   private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
   private static final CardType TYPE = CardType.ATTACK;
   private static final CardColor COLOR = CardColor.RED;
   private static final CardRarity RARITY = CardRarity.COMMON;
   private static final CardTarget TARGET = CardTarget.ENEMY;

   public PerfectedStrike2() {
      super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
      this.baseDamage = 6;
      this.baseMagicNumber = 2;
      this.magicNumber = this.baseMagicNumber;
      this.tags.add(CardTags.STRIKE);
   }

   public static int countCards() {
      int count = 0;

      for (AbstractCard c : AbstractDungeon.player.hand.group) {
         if (isStrike(c)) {
            ++count;
         }
      }

      for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
         if (isStrike(c)) {
            ++count;
         }
      }

      for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
         if (isStrike(c)) {
            ++count;
         }
      }

      for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
         if (isStrike(c)) {
            ++count;
         }
      }
      return count;
   }

   public static boolean isStrike(AbstractCard c) {
      return c.hasTag(CardTags.STRIKE);
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      this.addToBot(
            new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.SLASH_DIAGONAL));
   }

   public void calculateCardDamage(AbstractMonster mo) {
      int realBaseDamage = this.baseDamage;
      this.baseDamage += this.magicNumber * countCards();
      super.calculateCardDamage(mo);
      this.baseDamage = realBaseDamage;
      this.isDamageModified = this.damage != this.baseDamage;
   }

   public void applyPowers() {
      int realBaseDamage = this.baseDamage;
      this.baseDamage += this.magicNumber * countCards();
      super.applyPowers();
      this.baseDamage = realBaseDamage;
      this.isDamageModified = this.damage != this.baseDamage;
   }

   public AbstractCard makeCopy() {
      return new PerfectedStrike2();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeMagicNumber(1);
         this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
         this.initializeDescription();
      }

   }

}

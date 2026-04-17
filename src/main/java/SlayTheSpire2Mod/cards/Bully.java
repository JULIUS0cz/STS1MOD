package SlayTheSpire2Mod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import SlayTheSpire2Mod.helpers.ModHelper;
import basemod.abstracts.CustomCard;

public class Bully extends CustomCard {
    public static final String ID = ModHelper.FormatID(Bully.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "SlayTheSpire2ModResources/img/cards/Bully.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColor.RED;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Bully() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 4;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);

            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.NONE));
        // if (!m.hasPower("Vulnerable")) {
        // this.addToBot(
        // new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
        // AttackEffect.NONE));
        // } else {
        // if (!this.upgraded) {
        // this.damage += 2 * m.getPower("Vulnerable").amount;
        // this.addToBot(
        // new DamageAction(m, new DamageInfo(p, this.damage,
        // this.damageTypeForTurn), AttackEffect.NONE));
        // } else {
        // this.damage += 3 * m.getPower("Vulnerable").amount;
        // this.addToBot(
        // new DamageAction(m, new DamageInfo(p, this.damage,
        // this.damageTypeForTurn), AttackEffect.NONE));
        // }

        // }
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'use'");
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        if (mo != null) {
            if (mo.hasPower("Vulnerable")) {
                this.baseDamage += this.magicNumber * mo.getPower("Vulnerable").amount;
            }
        }

        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public AbstractCard makeCopy() {
        return new Bully();
    }

    // public void applyPowers() {
    // int realBaseDamage = this.baseDamage;
    // AbstractMonster mo = AbstractDungeon.getMonsters().getTargetMonster();
    // this.baseDamage += this.magicNumber * getPower("Vulnerable").amount;
    // super.applyPowers();
    // this.baseDamage = realBaseDamage;
    // this.isDamageModified = this.damage != this.baseDamage;
    // }
}

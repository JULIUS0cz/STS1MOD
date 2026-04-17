package SlayTheSpire2Mod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import SlayTheSpire2Mod.helpers.ModHelper;
import SlayTheSpire2Mod.modcore.SlayTheSpire2Mod;
import basemod.abstracts.CustomCard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ForgottenRitual extends CustomCard {

    public static final Logger logger = LogManager.getLogger(SlayTheSpire2Mod.class);

    public static final String ID = ModHelper.FormatID(ForgottenRitual.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "SlayTheSpire2ModResources/img/cards/ForgottenRitual.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.RED;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public ForgottenRitual() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1); // 检查

            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        // logger.info("使用时：" + SlayTheSpire2Mod.exhaustCountThisTurn);
        if (SlayTheSpire2Mod.exhaustCountThisTurn > 0) {
            this.addToBot(new GainEnergyAction(this.magicNumber));
        }
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'use'");

    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (SlayTheSpire2Mod.exhaustCountThisTurn > 0) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }

    }

    public AbstractCard makeCopy() {
        return new ForgottenRitual();
    }

}

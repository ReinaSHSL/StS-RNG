import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class RNG extends CustomRelic {
    public static final String ID = "RNG:RNG";
    private static final String IMG = "relics/RNG.png";

    public RNG() {
        super(ID, new Texture(IMG), RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public void onEquip() {
        final EnergyManager energy = AbstractDungeon.player.energy;
        ++energy.energyMaster;
        ++energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        final EnergyManager energy = AbstractDungeon.player.energy;
        --energy.energyMaster;
        --energy.energyMaster;
    }

    @Override
    public void atBattleStart() {
        int rng = AbstractDungeon.monsterRng.random(99);
        float offsetX = 0;
        if (rng <= 30) {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                offsetX -= 250F;
            }
            AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new GremlinNob
                    (offsetX - 100F, 15.0f), false));
        }
    }

    @Override
    public String getUpdatedDescription() {
        if (AbstractDungeon.player != null) {
            return this.setDescription(AbstractDungeon.player.chosenClass);
        }
        return this.setDescription(null);
    }

    private String setDescription(final AbstractPlayer.PlayerClass c) {
        if (c == null) {
            return this.DESCRIPTIONS[0];
        }
        switch (c) {
            case IRONCLAD: {
                return this.DESCRIPTIONS[0];
            }
            case THE_SILENT: {
                return this.DESCRIPTIONS[1];
            }
            case DEFECT: {
                return this.DESCRIPTIONS[2];
            }
            default: {
                return this.DESCRIPTIONS[0];
            }
        }
    }

    @Override
    public void updateDescription(final AbstractPlayer.PlayerClass c) {
        this.description = this.setDescription(c);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RNG();
    }
}

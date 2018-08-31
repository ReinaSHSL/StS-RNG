import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.RelicStrings;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class ModInitializer implements EditRelicsSubscriber, EditStringsSubscriber {


    public ModInitializer(){
        //Use this for when you subscribe to any hooks offered by BaseMod.
        BaseMod.subscribe(this);
    }

    //Used by @SpireInitializer
    public static void initialize(){

        //This creates an instance of our classes and gets our code going after BaseMod and ModTheSpire initialize.
        ModInitializer modInitializer = new ModInitializer();
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new RNG(), RelicType.SHARED);
    }

    @Override
    public void receiveEditStrings() {
        String relicStrings = Gdx.files.internal("RNG-Relics.json").readString(
        String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
    }
}
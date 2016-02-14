package le.mysquishyturt.mysquishymodtools;

import le.mysquishyturt.mysquishymodtools.proxies.CommonProxy;
import le.mysquishyturt.mysquishymodtools.reference.StringReferences;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = StringReferences.MODID, name = StringReferences.NAME, version = StringReferences.VERSION)
public class MySquishyModTools {

    private static MySquishyModTools instance;
    public String[] pairs;
    public String[] hacks;

    public static MySquishyModTools getInstance() {
        return instance;
    }

    @SidedProxy(clientSide = StringReferences.clientSide, serverSide = StringReferences.serverSide)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        instance = this;
        proxy.preInit(event);

        Configuration config = new Configuration(event.getSuggestedConfigurationFile());

        pairs = config.get(Configuration.CATEGORY_GENERAL, "Punishment_Reasons", StringReferences.punishReasons).getStringList();
        hacks = config.get(Configuration.CATEGORY_GENERAL, "Hacks", StringReferences.hacks).getStringList();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}

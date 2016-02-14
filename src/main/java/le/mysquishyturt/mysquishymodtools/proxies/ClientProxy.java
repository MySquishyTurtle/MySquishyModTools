package le.mysquishyturt.mysquishymodtools.proxies;

import le.mysquishyturt.mysquishymodtools.MySquishyModTools;
import le.mysquishyturt.mysquishymodtools.connectionHandler.ConnectionHandler;
import le.mysquishyturt.mysquishymodtools.guiHandler.GuiHandler;
import le.mysquishyturt.mysquishymodtools.keyBindings.KeyBindings;
import le.mysquishyturt.mysquishymodtools.modTools.ModTools;
import le.mysquishyturt.mysquishymodtools.modTools.toolKit.ToolKit;
import le.mysquishyturt.mysquishymodtools.modTools.tools.FreezeKey;
import le.mysquishyturt.mysquishymodtools.modTools.tools.GamemodeChanger;
import le.mysquishyturt.mysquishymodtools.modTools.tools.LatchTool;
import le.mysquishyturt.mysquishymodtools.modTools.tools.LookupTool;
import le.mysquishyturt.mysquishymodtools.modTools.tools.PunishmentTool;
import le.mysquishyturt.mysquishymodtools.modTools.tools.StaffInfoFilter;
import le.mysquishyturt.mysquishymodtools.rendering.RenderHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

        FMLCommonHandler.instance().bus().register(ConnectionHandler.getInstance());
        FMLCommonHandler.instance().bus().register(FreezeKey.getInstance());
        FMLCommonHandler.instance().bus().register(GamemodeChanger.getInstance());
        FMLCommonHandler.instance().bus().register(LatchTool.getInstance());
        FMLCommonHandler.instance().bus().register(LookupTool.getInstance());
        FMLCommonHandler.instance().bus().register(ModTools.getInstance());
        FMLCommonHandler.instance().bus().register(PunishmentTool.getInstance());
        FMLCommonHandler.instance().bus().register(StaffInfoFilter.getInstance());
        FMLCommonHandler.instance().bus().register(ToolKit.getInstance());

        MinecraftForge.EVENT_BUS.register(LatchTool.getInstance());
        MinecraftForge.EVENT_BUS.register(LookupTool.getInstance());
        MinecraftForge.EVENT_BUS.register(ModTools.getInstance());
        MinecraftForge.EVENT_BUS.register(PunishmentTool.getInstance());
        MinecraftForge.EVENT_BUS.register(RenderHandler.getInstance());
        MinecraftForge.EVENT_BUS.register(StaffInfoFilter.getInstance());
        MinecraftForge.EVENT_BUS.register(ToolKit.getInstance());

        NetworkRegistry.INSTANCE.registerGuiHandler(MySquishyModTools.getInstance(), new GuiHandler());

        KeyBindings.init();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}

package le.mysquishyturt.mysquishymodtools.connectionHandler;

import le.mysquishyturt.mysquishymodtools.MySquishyModTools;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.logging.Logger;

public class ConnectionHandler {

    private static ConnectionHandler instance;
    public boolean isPlayingOvercast = false;

    public static ConnectionHandler getInstance() {
        if (instance == null) {
            instance = new ConnectionHandler();
        }
        return instance;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void clientConnectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        IThreadListener mainThread = Minecraft.getMinecraft();
        mainThread.addScheduledTask(() -> {
            ServerData serverData = Minecraft.getMinecraft().getCurrentServerData();
            if (!(serverData == null) && serverData.serverIP.contains("oc.tc")) {
                Logger.getGlobal().info("Connected to an Overcast service");
                isPlayingOvercast = true;
                MySquishyModTools.isEnabled = true;
            } else {
                System.out.println("Uh oh");
                MySquishyModTools.isEnabled = false;
            }
        });
    }
}

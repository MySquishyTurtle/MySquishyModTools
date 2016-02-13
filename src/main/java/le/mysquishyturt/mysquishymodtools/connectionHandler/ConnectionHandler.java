package le.mysquishyturt.mysquishymodtools.connectionHandler;

import le.mysquishyturt.mysquishymodtools.modTools.ModTools;
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
    public boolean isPlayingOvercast;

    public static ConnectionHandler getInstance() {
        if (instance == null) {
            instance = new ConnectionHandler();
        }
        return instance;
    }

    public boolean isPlayingOvercast() {
        return isPlayingOvercast;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void clientConnectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        IThreadListener mainThread = Minecraft.getMinecraft();
        mainThread.addScheduledTask(() -> {
            ServerData serverData = Minecraft.getMinecraft().getCurrentServerData();
            if (!(serverData == null) && serverData.serverIP.equals("us.oc.tc")) {
                Logger.getGlobal().info("Connected to us.oc.tc");
                isPlayingOvercast = true;
                ModTools.getInstance().setEnabled();
            } else {
                System.out.println("Uh oh");
            }
        });
    }
}

package le.mysquishyturt.mysquishymodtools.modTools;

import le.mysquishyturt.mysquishymodtools.MySquishyModTools;
import le.mysquishyturt.mysquishymodtools.connectionHandler.ConnectionHandler;
import le.mysquishyturt.mysquishymodtools.events.PlayerGamemodeChangeEvent;
import le.mysquishyturt.mysquishymodtools.keyBindings.KeyBindings;
import le.mysquishyturt.mysquishymodtools.modTools.toolKit.ToolKit;
import le.mysquishyturt.mysquishymodtools.modTools.tools.LatchTool;
import le.mysquishyturt.mysquishymodtools.reference.StringReferences;
import le.mysquishyturt.mysquishymodtools.utils.StringManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import scala.reflect.runtime.ThreadLocalStorage;

public class ModTools {

    private static ModTools instance;
    Minecraft minecraft = Minecraft.getMinecraft();
    public WorldSettings.GameType gameType;

    public static ModTools getInstance() {
        if (instance == null) {
            instance = new ModTools();
        }
        return instance;
    }

    public static boolean featuresAreEnabled;

    public void setEnabled(boolean state) {
        ModTools.featuresAreEnabled = state;
        LatchTool.getInstance().isAttached = false;
        LatchTool.getInstance().targetName = null;
    }

    @SubscribeEvent
    public void onInputKey(InputEvent.KeyInputEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (KeyBindings.toggleMod.isPressed()) {
            if (MySquishyModTools.isEnabled) {
                player.addChatComponentMessage(new ChatComponentText(StringManager.EnumTextColor.AQUA.ColorString("[MSMT] " + StringManager.EnumTextColor.RED.ColorString(StringReferences.modDisabled))));
                MySquishyModTools.getInstance().setIsEnabled(false);
            } else if (ConnectionHandler.getInstance().isPlayingOvercast) {
                player.addChatComponentMessage(new ChatComponentText(StringManager.EnumTextColor.AQUA.ColorString("[MSMT] " + StringManager.EnumTextColor.BRIGHT_GREEN.ColorString(StringReferences.modEnabled))));
                MySquishyModTools.getInstance().setIsEnabled(true);
                ToolKit.getInstance().giveInventory();
            }
        }
    }

    /*@SubscribeEvent
    public void playerReceiveChat(ClientChatReceivedEvent event) {
        if (ModTools.isEnabled && event.message.getUnformattedText().matches(StringReferences.joinMessage)) {
            ModTools.isEnabled = false;
            LatchTool.getInstance().isAttached = false;
            LatchTool.getInstance().targetName = null;
        }
    }*/

    @SubscribeEvent
    public void onClientTickUpdate(TickEvent.ClientTickEvent event) {
        if (ConnectionHandler.getInstance().isPlayingOvercast) {
            if (gameType == null) {
                gameType = minecraft.playerController.getCurrentGameType();
            }
            if (gameType != minecraft.playerController.getCurrentGameType()) {
                FMLCommonHandler.instance().bus().post(new PlayerGamemodeChangeEvent());
                gameType = minecraft.playerController.getCurrentGameType();
            }
        }
    }

    @SubscribeEvent
    public void onGamemodeChange(PlayerGamemodeChangeEvent event) {
        if (minecraft.playerController.getCurrentGameType().isSurvivalOrAdventure()) {
            if (MySquishyModTools.isEnabled && ModTools.featuresAreEnabled) {
                setEnabled(false);
            }
        }
        if (minecraft.playerController.getCurrentGameType().isCreative()) {
            if (MySquishyModTools.isEnabled && !ModTools.featuresAreEnabled) {
                setEnabled(true);
                ToolKit.getInstance().giveInventory();
            }
        }
    }
}

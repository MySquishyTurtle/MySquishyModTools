package le.mysquishyturt.mysquishymodtools.modTools;

import le.mysquishyturt.mysquishymodtools.connectionHandler.ConnectionHandler;
import le.mysquishyturt.mysquishymodtools.keyBindings.KeyBindings;
import le.mysquishyturt.mysquishymodtools.modTools.tools.LatchTool;
import le.mysquishyturt.mysquishymodtools.reference.StringReferences;
import le.mysquishyturt.mysquishymodtools.utils.StringManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class ModTools {

    private static ModTools instance;

    public static ModTools getInstance() {
        if (instance == null) {
            instance = new ModTools();
        }
        return instance;
    }

    public static boolean isEnabled;

    public void setEnabled() { // Make it so that you can decide whether it enables by default in the config.
        if (ConnectionHandler.getInstance().isPlayingOvercast) {
            isEnabled = true;
        }
    }

    @SubscribeEvent
    public void onInputKey(InputEvent.KeyInputEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (KeyBindings.toggleMod.isPressed()) {
            if (isEnabled) {
                player.addChatComponentMessage(new ChatComponentText(StringManager.EnumTextColor.AQUA.ColorString("[MSMT] " + StringManager.EnumTextColor.RED.ColorString(StringReferences.modDisabled))));
                LatchTool.getInstance().isAttached = false;
                LatchTool.getInstance().targetName = null;
                isEnabled = false;
            } else if (ConnectionHandler.getInstance().isPlayingOvercast()) {
                player.addChatComponentMessage(new ChatComponentText(StringManager.EnumTextColor.AQUA.ColorString("[MSMT] " + StringManager.EnumTextColor.BRIGHT_GREEN.ColorString(StringReferences.modEnabled))));
                isEnabled = true;
            }
        }
    }


}
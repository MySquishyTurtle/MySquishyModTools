package le.mysquishyturt.mysquishymodtools.modTools.tools;

import le.mysquishyturt.mysquishymodtools.keyBindings.KeyBindings;
import le.mysquishyturt.mysquishymodtools.modTools.ModTools;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class GamemodeTool {

    private static GamemodeTool instance;

    public static GamemodeTool getInstance() {
        if (instance == null) {
            instance = new GamemodeTool();
        }
        return instance;
    }

    Minecraft minecraft = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (ModTools.isEnabled) {
            if (KeyBindings.gamemode.isPressed()) {
                if (minecraft.playerController.isInCreativeMode()) {
                    minecraft.thePlayer.sendChatMessage("/gamemode 3");
                } else if (minecraft.playerController.isSpectatorMode()) {
                    minecraft.thePlayer.sendChatMessage("/gamemode 1");
                }
            }
        }
    }
}

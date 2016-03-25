package le.mysquishyturt.mysquishymodtools.modTools.tools;

import le.mysquishyturt.mysquishymodtools.keyBindings.KeyBindings;
import le.mysquishyturt.mysquishymodtools.utils.StringManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class ToggleTextKey {

    private static ToggleTextKey instance;
    private boolean isEnabled = true;

    public static ToggleTextKey getInstance() {
        if (instance == null) {
            instance = new ToggleTextKey();
        }
        return instance;
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.toggleScreenText.isPressed()) {
            isEnabled = !isEnabled;
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(StringManager.EnumTextColor.AQUA.ColorString("[MSMT] " + StringManager.EnumTextColor.INDIGO.ColorString("Text is now toggled ") + StringManager.EnumTextColor.ORANGE.ColorString(String.valueOf(isEnabled)))));
        }
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}

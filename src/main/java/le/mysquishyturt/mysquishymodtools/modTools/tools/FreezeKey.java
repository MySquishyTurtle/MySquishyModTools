package le.mysquishyturt.mysquishymodtools.modTools.tools;

import le.mysquishyturt.mysquishymodtools.keyBindings.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class FreezeKey {

    private static FreezeKey instance;

    public static FreezeKey getInstance() {
        if (instance == null) {
            instance = new FreezeKey();
        }
        return instance;
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.freeze.isPressed()) {
            if (LatchTool.getInstance().isAttached && LatchTool.getInstance().targetName != null) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/freeze " + LatchTool.getInstance().targetName);
            }
        }
    }
}

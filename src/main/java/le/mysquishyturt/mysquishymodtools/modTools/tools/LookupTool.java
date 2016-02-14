package le.mysquishyturt.mysquishymodtools.modTools.tools;

import le.mysquishyturt.mysquishymodtools.keyBindings.KeyBindings;
import le.mysquishyturt.mysquishymodtools.modTools.ModTools;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class LookupTool {

    private static LookupTool instance;

    public static LookupTool getInstance() {
        if (instance == null) {
            instance = new LookupTool();
        }
        return instance;
    }

    @SubscribeEvent
    public void onPlayerHit(AttackEntityEvent event) {
        String targetName;
        if (ModTools.isEnabled) {
            if (event.entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.entity;
                if (player.getHeldItem() == null || !(event.target instanceof EntityPlayer)) {
                    return;
                }
                if (player.getHeldItem().getItem().equals(Item.getByNameOrId("340"))) {
                    targetName = event.target.getName();
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/lookup " + targetName);
                }
            }
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (ModTools.isEnabled) {
            if (KeyBindings.lookup.isPressed()) {
                if (LatchTool.getInstance().isAttached && LatchTool.getInstance().targetName != null) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/lookup " + LatchTool.getInstance().targetName);
                }
            }
        }
    }
}

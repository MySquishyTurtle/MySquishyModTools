package le.mysquishyturt.mysquishymodtools.modTools.tools;

import le.mysquishyturt.mysquishymodtools.keyBindings.KeyBindings;
import le.mysquishyturt.mysquishymodtools.modTools.ModTools;
import le.mysquishyturt.mysquishymodtools.utils.StringManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class LatchTool {

    private static LatchTool instance;

    public static  LatchTool getInstance() {
        if (instance == null) {
            instance = new LatchTool();
        }
        return instance;
    }

    public boolean isAttached;
    public String targetName;

    public String getTargetName() {
        return targetName;
    }

    @SubscribeEvent
    public void onPlayerHit(AttackEntityEvent event) {
        if (ModTools.isEnabled) {
            if (event.entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.entity;
                if (player.getHeldItem() == null || !(event.target instanceof EntityPlayer)) {
                    return;
                }
                if (player.getHeldItem().getItem().equals(Item.getByNameOrId("420"))) {
                    this.targetName = event.target.getName();
                    isAttached = true;
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(StringManager.EnumTextColor.AQUA.ColorString("[MSMT] " + StringManager.EnumTextColor.INDIGO.ColorString("Latched on to " + targetName))));
                }
            }
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (ModTools.isEnabled && isAttached) {
            if (targetName != null) {
                if (KeyBindings.teleport.isPressed()) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/rtp " + targetName);
                }
            }
        }
    }
}

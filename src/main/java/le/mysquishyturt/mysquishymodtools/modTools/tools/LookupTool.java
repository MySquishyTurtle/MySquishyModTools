package le.mysquishyturt.mysquishymodtools.modTools.tools;

import le.mysquishyturt.mysquishymodtools.modTools.ModTools;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
}

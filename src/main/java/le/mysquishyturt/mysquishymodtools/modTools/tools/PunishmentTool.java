package le.mysquishyturt.mysquishymodtools.modTools.tools;

import le.mysquishyturt.mysquishymodtools.MySquishyModTools;
import le.mysquishyturt.mysquishymodtools.modTools.ModTools;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PunishmentTool {

    private static PunishmentTool instance;

    public static PunishmentTool getInstance() {
        if (instance == null) {
            instance = new PunishmentTool();
        }
        return instance;
    }

    Minecraft minecraft = Minecraft.getMinecraft();
    public String target;

    @SubscribeEvent
    public void onPlayerHit(AttackEntityEvent event) {
        if (ModTools.isEnabled) {
            if (event.entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.entity;
                if (player.getHeldItem() == null || !(event.target instanceof EntityPlayer)) {
                    return;
                }
                if (player.getHeldItem().getItem().equals(Item.getByNameOrId("279"))) {
                    this.target = event.target.getName();
                    minecraft.thePlayer.openGui(MySquishyModTools.getInstance(), 2, minecraft.thePlayer.getEntityWorld(), (int) minecraft.thePlayer.posX, (int) minecraft.thePlayer.posY, (int) minecraft.thePlayer.posZ);
                }
            }
        }
    }
}

package le.mysquishyturt.mysquishymodtools.modTools.tools;

import le.mysquishyturt.mysquishymodtools.MySquishyModTools;
import le.mysquishyturt.mysquishymodtools.keyBindings.KeyBindings;
import le.mysquishyturt.mysquishymodtools.modTools.ModTools;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

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
        if (ModTools.featuresAreEnabled) {
            if (event.entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.entity;
                if (player.getHeldItem() == null || !(event.target instanceof EntityPlayer)) {
                    return;
                }
                if (player.getHeldItem().getItem().equals(Item.getByNameOrId("279"))) {
                    this.target = event.target.getName();
                    minecraft.thePlayer.openGui(MySquishyModTools.getInstance(), 0, minecraft.thePlayer.getEntityWorld(), (int) minecraft.thePlayer.posX, (int) minecraft.thePlayer.posY, (int) minecraft.thePlayer.posZ);
                }
            }
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.punish.isPressed()) {
            if (LatchTool.getInstance().isAttached && LatchTool.getInstance().targetName != null) {
                this.target = LatchTool.getInstance().targetName;
                Minecraft.getMinecraft().thePlayer.openGui(MySquishyModTools.getInstance(), 0, minecraft.thePlayer.getEntityWorld(), (int) minecraft.thePlayer.posX, (int) minecraft.thePlayer.posY, (int) minecraft.thePlayer.posZ);
            }
        }
    }
}

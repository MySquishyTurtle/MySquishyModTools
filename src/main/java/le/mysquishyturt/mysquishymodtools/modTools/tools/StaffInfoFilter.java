package le.mysquishyturt.mysquishymodtools.modTools.tools;

import le.mysquishyturt.mysquishymodtools.MySquishyModTools;
import le.mysquishyturt.mysquishymodtools.keyBindings.KeyBindings;
import le.mysquishyturt.mysquishymodtools.reference.StringReferences;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class StaffInfoFilter {

    Minecraft minecraft = Minecraft.getMinecraft();
    private static StaffInfoFilter instance;
    public boolean adminChat = true;
    public boolean ratings = true;
    public boolean reports = true;

    public static StaffInfoFilter getInstance() {
        if (instance == null) {
            instance = new StaffInfoFilter();
        }
        return instance;
    }

    @SubscribeEvent
    public void onChatReceieve(ClientChatReceivedEvent event) {
        if (!adminChat && event.message.getUnformattedText().matches(StringReferences.adminChatMessage)) {
            event.setCanceled(true);
        }
        if (!ratings && event.message.getUnformattedText().matches(StringReferences.ratingMessage)) {
            event.setCanceled(true);
        }
        if (!reports && event.message.getUnformattedText().matches(StringReferences.reportMessage)) {
            event.setCanceled(true);
        }
        if (!reports && event.message.getUnformattedText().matches(StringReferences.reportMessage2)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.filter.isPressed()) {
            minecraft.thePlayer.openGui(MySquishyModTools.getInstance(), 1, minecraft.thePlayer.getEntityWorld(), (int) minecraft.thePlayer.posX, (int) minecraft.thePlayer.posY, (int) minecraft.thePlayer.posZ);
        }
    }
}

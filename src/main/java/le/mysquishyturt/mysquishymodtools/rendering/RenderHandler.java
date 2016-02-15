package le.mysquishyturt.mysquishymodtools.rendering;

import le.mysquishyturt.mysquishymodtools.MySquishyModTools;
import le.mysquishyturt.mysquishymodtools.modTools.ModTools;
import le.mysquishyturt.mysquishymodtools.modTools.tools.LatchTool;
import le.mysquishyturt.mysquishymodtools.utils.StringManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHandler {

    private static RenderHandler instance;

    public static RenderHandler getInstance() {
        if (instance == null) {
            instance = new RenderHandler();
        }
        return instance;
    }

    @SubscribeEvent
    public void RenderGameOverlayEvent(RenderGameOverlayEvent event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            if (MySquishyModTools.isEnabled) {
                Drawing.renderModStatusToGui(StringManager.EnumTextColor.INDIGO.ColorString("Mod: ") + StringManager.EnumTextColor.BRIGHT_GREEN.ColorString("Enabled"));
            } else if (!MySquishyModTools.isEnabled) {
                Drawing.renderModStatusToGui(StringManager.EnumTextColor.INDIGO.ColorString("Mod: ") + StringManager.EnumTextColor.RED.ColorString("Disabled"));
            }
            if (ModTools.featuresAreEnabled) {
                Drawing.renderStatusToHud(StringManager.EnumTextColor.INDIGO.ColorString("Features: ") + StringManager.EnumTextColor.BRIGHT_GREEN.ColorString("Enabled"));
            } else if (!ModTools.featuresAreEnabled) {
                Drawing.renderStatusToHud(StringManager.EnumTextColor.INDIGO.ColorString("Features: ") + StringManager.EnumTextColor.RED.ColorString("Disabled"));
            }
            if (LatchTool.getInstance().isAttached) {
                Drawing.renderLatchingToHud(StringManager.EnumTextColor.INDIGO.ColorString("Latched: ") + StringManager.EnumTextColor.DARK_AQUA.ColorString(LatchTool.getInstance().getTargetName()));
            }
        }
    }
}

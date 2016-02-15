package le.mysquishyturt.mysquishymodtools.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;

public class Drawing {

    public static void renderStatusToHud(String text) {
        Minecraft minecraft = Minecraft.getMinecraft();
        if ((minecraft.inGameHasFocus || (minecraft.currentScreen != null && (minecraft.currentScreen instanceof GuiChat))) && !minecraft.gameSettings.showDebugInfo) {
            ScaledResolution res = new ScaledResolution(minecraft, minecraft.displayWidth, minecraft.displayHeight);
            int width = res.getScaledWidth();
            int height = res.getScaledHeight();

            int x = width - 100;
            int y = height - 13;
            int color = 0xffffff;
            minecraft.fontRendererObj.drawStringWithShadow(text, x, y, color);
        }
    }

    public static void renderLatchingToHud(String text) {
        Minecraft minecraft = Minecraft.getMinecraft();
        if ((minecraft.inGameHasFocus || (minecraft.currentScreen != null && (minecraft.currentScreen instanceof GuiChat))) && !minecraft.gameSettings.showDebugInfo) {
            int x = 5;
            int y = 5;
            int color = 0xffffff;
            minecraft.fontRendererObj.drawStringWithShadow(text, x, y, color);
        }
    }

    public static void renderModStatusToGui(String text) {
        Minecraft minecraft = Minecraft.getMinecraft();
        if ((minecraft.inGameHasFocus || (minecraft.currentScreen != null && (minecraft.currentScreen instanceof GuiChat))) && !minecraft.gameSettings.showDebugInfo) {
            ScaledResolution res = new ScaledResolution(minecraft, minecraft.displayWidth, minecraft.displayHeight);
            int width = res.getScaledWidth();
            int height = res.getScaledHeight();

            int x = width - 100;
            int y = height - 25;
            int color = 0xffffff;
            minecraft.fontRendererObj.drawStringWithShadow(text, x, y, color);
        }
    }
}

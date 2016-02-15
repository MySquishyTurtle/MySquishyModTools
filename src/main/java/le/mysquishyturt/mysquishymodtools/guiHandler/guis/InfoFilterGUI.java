package le.mysquishyturt.mysquishymodtools.guiHandler.guis;

import le.mysquishyturt.mysquishymodtools.MySquishyModTools;
import le.mysquishyturt.mysquishymodtools.connectionHandler.ConnectionHandler;
import le.mysquishyturt.mysquishymodtools.modTools.ModTools;
import le.mysquishyturt.mysquishymodtools.modTools.tools.LatchTool;
import le.mysquishyturt.mysquishymodtools.modTools.tools.StaffInfoFilter;
import le.mysquishyturt.mysquishymodtools.utils.StringManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class InfoFilterGUI extends GuiScreen {

    public GuiButton adminChat;
    public GuiButton modTools;
    public GuiButton ratings;
    public GuiButton reports;
    Minecraft minecraft = Minecraft.getMinecraft();
    StaffInfoFilter filter = StaffInfoFilter.getInstance();

    @Override
    public void initGui() {
        int xoffset = minecraft.currentScreen.width / 2 - 50;
        int yoffset = minecraft.currentScreen.height / 2 - 10;

        adminChat = new GuiButton(0, xoffset - 175, yoffset, 100, 20, "Admin Chat");
        this.buttonList.add(adminChat);
        modTools = new GuiButton(1, xoffset - 62, yoffset, 100, 20, "Mod");
        this.buttonList.add(modTools);
        ratings = new GuiButton(2, xoffset + 62, yoffset, 100, 20, "Ratings");
        this.buttonList.add(ratings);
        reports = new GuiButton(3, xoffset + 175, yoffset, 100, 20, "Reports");
        this.buttonList.add(reports);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int xoffset = minecraft.currentScreen.width / 2;
        int yoffset = minecraft.currentScreen.height / 2 - 10;

        this.drawDefaultBackground();

        GlStateManager.color(1f, 1f, 1f, 1f);

        minecraft.fontRendererObj.drawString(StringManager.colorBoolean(String.valueOf(filter.adminChat)), xoffset - 185, yoffset + 25, 0xFFFFFF);
        minecraft.fontRendererObj.drawString(StringManager.colorBoolean(String.valueOf(MySquishyModTools.isEnabled)), xoffset - 75, yoffset + 25, 0xFFFFFF);
        minecraft.fontRendererObj.drawString(StringManager.colorBoolean(String.valueOf(filter.ratings)), xoffset + 50, yoffset + 25, 0xFFFFFF);
        minecraft.fontRendererObj.drawString(StringManager.colorBoolean(String.valueOf(filter.reports)), xoffset + 163, yoffset + 25, 0xFFFFFF);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button == adminChat) {
            filter.adminChat = !filter.adminChat;
        }
        if (button == modTools) {
            if (!ConnectionHandler.getInstance().isPlayingOvercast) {
                return;
            }
            MySquishyModTools.isEnabled = !MySquishyModTools.isEnabled;
            if (!MySquishyModTools.isEnabled) {
                ModTools.featuresAreEnabled = false;
            }
            if (MySquishyModTools.isEnabled) {
                if (minecraft.playerController.isInCreativeMode() || minecraft.playerController.isSpectatorMode()) {
                    ModTools.featuresAreEnabled = true;
                }
            }
            LatchTool.getInstance().isAttached = false;
            LatchTool.getInstance().targetName = null;
        }
        if (button == ratings) {
            filter.ratings = !filter.ratings;
        }
        if (button == reports) {
            filter.reports = !filter.reports;
        }
    }
}

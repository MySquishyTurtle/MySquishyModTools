package le.mysquishyturt.mysquishymodtools.guiHandler.guis;

import le.mysquishyturt.mysquishymodtools.MySquishyModTools;
import le.mysquishyturt.mysquishymodtools.guiHandler.CustomScroller;
import le.mysquishyturt.mysquishymodtools.guiHandler.MultiSelectCustomScroller;
import le.mysquishyturt.mysquishymodtools.modTools.tools.PunishmentTool;
import le.mysquishyturt.mysquishymodtools.utils.StringManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class PunishmentGui extends GuiScreen {
    CustomScroller scroller;
    MultiSelectCustomScroller hackScroller;
    public GuiButton warnButton;
    public GuiButton punishButton;
    public GuiButton banButton;
    public GuiButton hackerButton;
    public GuiButton confirmButton;
    public GuiButton cancelButton;
    public boolean hacking;
    String buttonPressed = "";
    Minecraft minecraft = Minecraft.getMinecraft();
    EntityPlayerSP player = minecraft.thePlayer;
    PunishmentTool punishmentTool = PunishmentTool.getInstance();

    @Override
    public void initGui() {
        scroller = new CustomScroller(mc, 150, mc.currentScreen.height, 0, mc.currentScreen.height, mc.currentScreen.width - 150, 20);
        hackScroller = new MultiSelectCustomScroller(mc, 150, mc.currentScreen.height, 0, mc.currentScreen.height, mc.currentScreen.width - 150, 20);

        for (String string : MySquishyModTools.getInstance().pairs) {
            String[] reason = string.split(";");
            if (reason.length == 2) {
                scroller.addReason(reason[0], reason[1]);
            }
        }

        for (String string : MySquishyModTools.getInstance().hacks) {
            hackScroller.addReason(string, string);
        }

        int xoffset = minecraft.currentScreen.width / 8;
        int yoffset = minecraft.currentScreen.height / 2 - 10;

        warnButton = new GuiButton(0, xoffset, yoffset - 75, 150, 20, "Warn");
        this.buttonList.add(warnButton);
        punishButton = new GuiButton(1, xoffset, yoffset - 25, 150, 20, "Punish");
        this.buttonList.add(punishButton);
        banButton = new GuiButton(2, xoffset, yoffset + 25, 150, 20, "Permanent Ban");
        this.buttonList.add(banButton);
        hackerButton = new GuiButton(3, xoffset, yoffset + 75, 150, 20, "Hacking");
        this.buttonList.add(hackerButton);
        confirmButton = new GuiButton(4, xoffset * 4 - 87, yoffset, 150, 20, "Confirm");
        cancelButton = new GuiButton(5, xoffset * 4 + 87, yoffset, 150, 20, "Cancel");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        GlStateManager.color(1f, 1f, 1f, 1f);

        if (!scroller.getSelectedReason().equals("")) {
            mc.fontRendererObj.drawString(StringManager.EnumTextColor.INDIGO.ColorString("Punishment Reason: ") + "" + StringManager.EnumTextColor.ORANGE.ColorString(scroller.getSelectedReason()), 5, 20, 0xFFFFFF);
        }

        if (hackScroller.getAllSelectedReason().size() != 0) {
            mc.fontRendererObj.drawString(StringManager.EnumTextColor.INDIGO.ColorString("Punishment Reason: ") + "" + StringManager.EnumTextColor.ORANGE.ColorString("Hacking - " + StringManager.join(hackScroller.getAllSelectedReason())), 5, 20, 0xFFFFFF);
        }

        if (!hacking) {
            scroller.drawScreen(mouseX, mouseY, partialTicks);
        } else {
            hackScroller.drawScreen(mouseX, mouseY, partialTicks);
        }

        mc.fontRendererObj.drawString(StringManager.EnumTextColor.INDIGO.ColorString("Player in question: ") + "" + StringManager.EnumTextColor.RED.ColorString(punishmentTool.target), 5, 5, 0xFFFFFF);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button != confirmButton && button != cancelButton) {
            buttonPressed = button.displayString;
        }
        if (button == warnButton) {
            buttonPressed = warnButton.displayString;
            if (!scroller.getSelectedReason().equals("")) {
                this.buttonList.add(confirmButton);
                this.buttonList.add(cancelButton);
            }
        }
        if (button == punishButton) {
            buttonPressed = punishButton.displayString;
            if (!scroller.getSelectedReason().equals("")) {
                this.buttonList.add(confirmButton);
                this.buttonList.add(cancelButton);
            }
        }
        if (button == banButton) {
            buttonPressed = banButton.displayString;
            if (!scroller.getSelectedReason().equals("")) {
                this.buttonList.add(confirmButton);
                this.buttonList.add(cancelButton);
            } else if (hackScroller.areReasonsSelected()) {
                this.buttonList.add(confirmButton);
                this.buttonList.add(cancelButton);
            }
        }
        if (button == hackerButton) {
            this.hacking = true;
            scroller.selectedIndex = -1;
        }
        if (button == confirmButton) {
            this.buttonList.remove(confirmButton);
            this.buttonList.remove(cancelButton);
            if (buttonPressed == null) {
                return;
            }
            if (buttonPressed.equals(warnButton.displayString)) {
                player.sendChatMessage("/warn " + punishmentTool.target + " " + scroller.getSelectedReason());
            }
            if (buttonPressed.equals(punishButton.displayString)) {
                player.sendChatMessage("/punish " + punishmentTool.target + " " + scroller.getSelectedReason());
            }
            if (buttonPressed.equals(banButton.displayString)) {
                if (this.hacking) {
                    player.sendChatMessage("/pb " + punishmentTool.target + " Hacking - " + StringManager.join(hackScroller.getAllSelectedReason()));
                    return;
                }
                player.sendChatMessage("/permaban " + punishmentTool.target + " " + scroller.getSelectedReason());
            }
            this.hacking = false;
        }
        if (button == cancelButton) {
            this.buttonList.remove(confirmButton);
            this.buttonList.remove(cancelButton);
            this.hacking = false;
        }
    }
}
package le.mysquishyturt.mysquishymodtools.guiHandler;

import le.mysquishyturt.mysquishymodtools.utils.StringManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

import java.util.HashMap;
import java.util.Map;

public class CustomScroller extends GuiScrollingList {
    Map<String, String> punishmentMessages;
    Minecraft mc;
    public int selectedIndex = -1;
    String shortName;

    public CustomScroller(Minecraft client, int width, int height, int top, int bottom, int left, int entryHeight) {
        super(client, width, height, top, bottom, left, entryHeight);
        this.mc = client;
        punishmentMessages = new HashMap<String, String>();
    }

    @Override
    protected int getSize() {
        return punishmentMessages.size();
    }

    @Override
    protected void elementClicked(int index, boolean doubleClick) {
        this.selectedIndex = index;
        this.shortName = String.valueOf(punishmentMessages.keySet().toArray()[selectedIndex]);
    }

    @Override
    protected boolean isSelected(int index) {
        return selectedIndex == index;
    }

    @Override
    protected void drawBackground() {

    }

    public void addReason(String name, String reason) {
        punishmentMessages.put(name, reason);
    }

    @Override
    protected void drawSlot(int index, int width, int height, int var4, Tessellator var5) {
        String shortName = StringManager.EnumTextColor.AQUA.ColorString(String.valueOf(punishmentMessages.keySet().toArray()[index]));

        mc.fontRendererObj.drawString(shortName, width - (listWidth - 20), height + (slotHeight / 4), 0xFFFFFF);
    }

    public String getSelectedReason() {
        if(selectedIndex == -1)
            return "";
        else {
            return punishmentMessages.get(shortName);
        }
    }
}

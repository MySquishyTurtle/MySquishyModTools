package le.mysquishyturt.mysquishymodtools.guiHandler;

import le.mysquishyturt.mysquishymodtools.utils.StringManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MultiSelectCustomScroller extends GuiScrollingList {
    Map<String, String> hackTypes;
    Minecraft mc;
    List<Integer> selected = new ArrayList<>();
    String shortName;

    public MultiSelectCustomScroller(Minecraft client, int width, int height, int top, int bottom, int left, int entryHeight) {
        super(client, width, height, top, bottom, left, entryHeight);
        this.mc = client;
        hackTypes = new HashMap<String, String>();
        selected = new ArrayList<>();
    }

    @Override
    protected int getSize() {
        return hackTypes.size();
    }

    @Override
    protected void elementClicked(int index, boolean doubleClick) {
        Number optionSelected = index;
        if (selected.contains(index)) {
            selected.remove(optionSelected);
        } else {
            selected.add(optionSelected.intValue());
        }
    }

    @Override
    protected boolean isSelected(int index) {
        return selected.contains(index);
    }

    @Override
    protected void drawBackground() {

    }

    public void addReason(String name, String reason) {
        hackTypes.put(name, reason);
    }

    @Override
    protected void drawSlot(int index, int width, int height, int var4, Tessellator var5) {
        String shortName = StringManager.EnumTextColor.AQUA.ColorString(String.valueOf(hackTypes.keySet().toArray()[index]));

        mc.fontRendererObj.drawString(shortName, width - (listWidth - 20), height + (slotHeight / 4), 0xFFFFFF);
    }

    public boolean areReasonsSelected() {
        return selected.size() > 0;
    }

    public List<String> getAllSelectedReason() {
        List<String> selectedHacks = new ArrayList<>();
        List<String> allReasons = hackTypes.keySet().stream().collect(Collectors.toList());
        for(int index : selected) {
            selectedHacks.add(allReasons.get(index));
        }
        return selectedHacks;
    }
}

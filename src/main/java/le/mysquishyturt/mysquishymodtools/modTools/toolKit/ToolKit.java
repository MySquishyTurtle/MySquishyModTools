package le.mysquishyturt.mysquishymodtools.modTools.toolKit;

import le.mysquishyturt.mysquishymodtools.keyBindings.KeyBindings;
import le.mysquishyturt.mysquishymodtools.modTools.GiveItemRunnable;
import le.mysquishyturt.mysquishymodtools.modTools.ModTools;
import le.mysquishyturt.mysquishymodtools.modTools.tools.LatchTool;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.CreativeCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class ToolKit {

    private static ToolKit instance;
    private World lastWorld = null;
    Minecraft minecraft = Minecraft.getMinecraft();

    public static ToolKit getInstance() {
        if (instance == null) {
            instance = new ToolKit();
        }
        return instance;
    }

    @SubscribeEvent
     public void onInputEvent(InputEvent.KeyInputEvent event) {
        if (KeyBindings.kit.isPressed()) {
            new Thread(new GiveItemRunnable()).start();
        }
     }

    @SubscribeEvent
    public void onEntityWorldJoin(EntityJoinWorldEvent event) {
        if (lastWorld != event.world) {
            if (ModTools.featuresAreEnabled) {
                new Thread(new GiveItemRunnable()).start();
            }
            lastWorld = event.world;
        }
    }

    public void giveInventory() {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (ModTools.featuresAreEnabled && player.capabilities.isCreativeMode) {
            CreativeCrafting crafting = new CreativeCrafting(minecraft);
            player.inventoryContainer.addCraftingToCrafters(crafting);
            player.inventory.setInventorySlotContents(0, new ItemStack(Item.getByNameOrId("345")));
            player.inventory.setInventorySlotContents(1, new ItemStack(Item.getByNameOrId("414")));
            player.inventory.setInventorySlotContents(2, new ItemStack(Item.getByNameOrId("359")));
            player.inventory.setInventorySlotContents(3, null);
            player.inventory.setInventorySlotContents(4, new ItemStack(Item.getByNameOrId("79")));
            player.inventory.setInventorySlotContents(5, null);
            player.inventory.setInventorySlotContents(6, new ItemStack(Item.getByNameOrId("420")));
            player.inventory.setInventorySlotContents(7, new ItemStack(Item.getByNameOrId("340")));
            player.inventory.setInventorySlotContents(8, new ItemStack(Item.getByNameOrId("279")));
            player.inventoryContainer.detectAndSendChanges();
            player.inventoryContainer.removeCraftingFromCrafters(crafting);
        }
    }
}

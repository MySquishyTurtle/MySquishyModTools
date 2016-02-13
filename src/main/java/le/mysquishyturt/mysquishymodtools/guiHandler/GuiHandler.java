package le.mysquishyturt.mysquishymodtools.guiHandler;

import le.mysquishyturt.mysquishymodtools.guiHandler.guis.PunishmentGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    private static final int PERMANENT_BAN_GUI = 2;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == PERMANENT_BAN_GUI) {
            return new PunishmentGui();
        }
        return null;
    }
}

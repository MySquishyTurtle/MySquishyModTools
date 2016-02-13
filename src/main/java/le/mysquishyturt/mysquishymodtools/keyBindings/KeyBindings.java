package le.mysquishyturt.mysquishymodtools.keyBindings;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyBindings {

    private static KeyBindings instance;

    public static KeyBindings getInstance() {
        if (instance == null) {
            instance = new KeyBindings();
        }
        return instance;
    }

    public static KeyBinding gamemode;
    public static KeyBinding kit;
    public static KeyBinding teleport;
    public static KeyBinding toggleMod;

    public static void init() {

        gamemode = new KeyBinding("key.tools.gamemode", Keyboard.KEY_G, "key.categories.MySquishyModTools");
        kit = new KeyBinding("key.tools.kit", Keyboard.KEY_R, "key.categories.MySquishyModTools");
        teleport = new KeyBinding("key.tools.teleport", Keyboard.KEY_X, "key.categories.MySquishyModTools");
        toggleMod = new KeyBinding("key.tools.toggle", Keyboard.KEY_Y, "key.categories.MySquishyModTools");

        ClientRegistry.registerKeyBinding(gamemode);
        ClientRegistry.registerKeyBinding(kit);
        ClientRegistry.registerKeyBinding(teleport);
        ClientRegistry.registerKeyBinding(toggleMod);
    }
}

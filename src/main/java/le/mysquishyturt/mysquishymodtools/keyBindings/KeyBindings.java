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

    public static KeyBinding filter;
    public static KeyBinding freeze;
    public static KeyBinding gamemode;
    public static KeyBinding kit;
    public static KeyBinding lookup;
    public static KeyBinding punish;
    public static KeyBinding teleport;
    public static KeyBinding toggleMod;
    public static KeyBinding toggleScreenText;

    public static void init() {

        filter = new KeyBinding("key.tools.filter", Keyboard.KEY_U, "key.categories.MySquishyModTools");
        freeze = new KeyBinding("key.tools.freeze", Keyboard.KEY_J, "key.categories.MySquishyModTools");
        gamemode = new KeyBinding("key.tools.gamemode", Keyboard.KEY_G, "key.categories.MySquishyModTools");
        kit = new KeyBinding("key.tools.kit", Keyboard.KEY_R, "key.categories.MySquishyModTools");
        lookup = new KeyBinding("key.tools.lookup", Keyboard.KEY_Z, "key.categories.MySquishyModTools");
        punish =  new KeyBinding("key.tools.punish", Keyboard.KEY_K, "key.categories.MySquishyModTools");
        teleport = new KeyBinding("key.tools.teleport", Keyboard.KEY_X, "key.categories.MySquishyModTools");
        toggleMod = new KeyBinding("key.tools.toggle", Keyboard.KEY_Y, "key.categories.MySquishyModTools");
        toggleScreenText = new KeyBinding("key.tools.toggleScreenText", Keyboard.KEY_I, "key.categories.MySquishyModTools");

        ClientRegistry.registerKeyBinding(filter);
        ClientRegistry.registerKeyBinding(freeze);
        ClientRegistry.registerKeyBinding(gamemode);
        ClientRegistry.registerKeyBinding(kit);
        ClientRegistry.registerKeyBinding(lookup);
        ClientRegistry.registerKeyBinding(punish);
        ClientRegistry.registerKeyBinding(teleport);
        ClientRegistry.registerKeyBinding(toggleMod);
        ClientRegistry.registerKeyBinding(toggleScreenText);
    }
}

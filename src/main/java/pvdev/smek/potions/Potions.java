package pvdev.smek.potions;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pvdev.smek.potions.manager.ResourceManager;

public final class Potions extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        loadResources();
    }

    @Override
    public void onDisable() {

    }

    public static void loadResources() {
        ResourceManager.registerResources();
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}

package pvdev.smek.potions;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pvdev.smek.potions.listeners.TestingListener;
import pvdev.smek.potions.resources.manager.IngredientManager;
import pvdev.smek.potions.resources.manager.RecipeManager;

import java.util.Arrays;
import java.util.logging.Level;

public final class Potions extends JavaPlugin {

    private static Plugin plugin;
    private static IngredientManager ingredientManager;
    private static RecipeManager recipeManager;

    private static final TextColor INFO = TextColor.fromHexString("#2dcc71");
    private static final TextColor WARNING = TextColor.fromHexString("#cc2d48");

    @Override
    public void onEnable() {
        plugin = this;
        loadASCII();
        registerResources();
        registerEvents(new TestingListener());
    }

    @Override
    public void onDisable() {

    }

    private void loadASCII() {
        String ascii = """
                                  __\s
                          bruh.  <- )
                                 /( \\
                                 \\_\\_>
                                 " "\
                        """;
        ascii.lines().forEach(string -> getPlugin().getComponentLogger().info(Component.text(string).color(INFO)));
    }

    private static void registerResources() {
        ingredientManager = new IngredientManager();
        recipeManager = new RecipeManager();

        ingredientManager.registerIngredients();
        recipeManager.registerRecipes();
    }

    private void registerEvents(Listener... listeners) {
        Arrays.stream(listeners).forEach(listener ->
                Bukkit.getServer().getPluginManager().registerEvents(listener, plugin));
    }

    public static void reloadResources() {
        Potions.log("Resources are being reloaded.", Level.INFO);
        registerResources();
    }

    public static void log(String message, Level level) {
        getPlugin().getComponentLogger().info(Component.text(message).color(level == Level.INFO ? INFO : WARNING));
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static IngredientManager getIngredientManager() {
        return ingredientManager;
    }

    public static RecipeManager getRecipeManager() {
        return recipeManager;
    }
}

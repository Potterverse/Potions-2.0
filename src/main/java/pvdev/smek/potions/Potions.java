package pvdev.smek.potions;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pvdev.smek.potions.listeners.TestingListener;
import pvdev.smek.potions.resources.manager.IngredientManager;
import pvdev.smek.potions.resources.manager.RecipeManager;

import java.util.Arrays;

public final class Potions extends JavaPlugin {

    private static Plugin plugin;
    private static IngredientManager ingredientManager;
    private static RecipeManager recipeManager;

    @Override
    public void onEnable() {
        plugin = this;
        registerResources();
        registerEvents(new TestingListener());
    }

    @Override
    public void onDisable() {

    }

    public void reloadPlugin() {
        registerResources();
    }

    private void registerEvents(Listener... listeners) {
        Arrays.stream(listeners).forEach(listener ->
                Bukkit.getServer().getPluginManager().registerEvents(listener, plugin));
    }

    private void registerResources() {
        ingredientManager = new IngredientManager();
        recipeManager = new RecipeManager();

        ingredientManager.registerIngredients();
        recipeManager.registerRecipes();
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

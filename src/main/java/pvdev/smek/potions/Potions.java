package pvdev.smek.potions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pvdev.smek.potions.ingredient.IngredientManager;
import pvdev.smek.potions.listeners.TestingListener;
import pvdev.smek.potions.recipe.RecipeManager;

public final class Potions extends JavaPlugin {
    private static Plugin plugin;
    @Override
    public void onEnable() {
        plugin = this;

        IngredientManager.registerIngredients();
        RecipeManager.registerRecipes();
        Bukkit.getPluginManager().registerEvents(new TestingListener(), this);
    }
    @Override
    public void onDisable() {

    }

    public static Plugin getPlugin() {
        return plugin;
    }
}

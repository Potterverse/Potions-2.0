package pvdev.smek.potions.recipe;

import pvdev.smek.potions.Potions;
import pvdev.smek.potions.util.JSONUtil;

import java.util.HashMap;

public class RecipeManager {
    private static final HashMap<String, Recipe> recipes = new HashMap<>();
    public static void registerRecipes() {
        JSONUtil.retrieveJSONFiles("recipes").forEach(jsonObject -> {
            Recipe recipe = new Recipe(jsonObject);
            Potions.getPlugin().getLogger().info( "Registering \"" + recipe.getName() + "\"");
            recipes.put(recipe.getName(), recipe);
        });
    }
    public static Recipe getRecipe(String key) {
        return new Recipe(recipes.get(key).getJsonRecipe());
    }
}

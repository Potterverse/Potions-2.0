package pvdev.smek.potions.ingredient;

import pvdev.smek.potions.Potions;
import pvdev.smek.potions.util.JSONUtil;

import java.util.HashMap;

public class IngredientManager {
    private static final HashMap<String, Ingredient> ingredients = new HashMap<>();
    public static void registerIngredients() {
        JSONUtil.retrieveJSONFiles("ingredients").forEach(jsonObject -> {
            Ingredient ingredient = new Ingredient(jsonObject);
            Potions.getPlugin().getLogger().info( "Registering \"" + ingredient.getName() + "\"");
            ingredients.put(ingredient.getName(), ingredient);
        });
    }
    // Can retrieve this one directly since we make no modifications to it unlike Recipes.
    public static Ingredient getIngredient(String key) {
        return ingredients.get(key);
    }
}

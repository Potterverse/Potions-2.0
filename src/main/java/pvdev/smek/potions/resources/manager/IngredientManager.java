package pvdev.smek.potions.resources.manager;

import pvdev.smek.potions.Potions;
import pvdev.smek.potions.resources.resource.Ingredient;
import pvdev.smek.potions.resources.validator.resource.IngredientValidator;

import java.util.HashMap;
import java.util.logging.Level;

/**
 * Responsible for providing reference to all registered ingredients used by Potions.
 */
public class IngredientManager extends ResourceManager {
    private static final String INGREDIENTS_DIRECTORY_NAME = "ingredients";
    private final HashMap<String, Ingredient> ingredients = new HashMap<>();

    public void registerIngredients() {
        Potions.log("Loading Ingredients:", Level.INFO);
        registerResources(INGREDIENTS_DIRECTORY_NAME, ingredients, new IngredientValidator());
        Potions.log("| done! :)", Level.INFO);
    }

    /**
     * Retrieves a copy of the ingredient retrieved from its Hashmap.
     * @param key       The key associated with the ingredient.
     * @return          A new instance of the retrieved ingredient if found. Else returns null.
     */
    public Ingredient getIngredientCopy(String key) {
        if (key == null || ingredients.get(key) == null) return null;
        return new Ingredient(ingredients.get(key));
    }

}

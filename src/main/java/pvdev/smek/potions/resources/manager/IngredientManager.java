package pvdev.smek.potions.resources.manager;

import pvdev.smek.potions.resources.resource.Ingredient;
import pvdev.smek.potions.resources.validator.resource.IngredientValidator;

import java.util.HashMap;

/**
 * Responsible for providing reference to all registered ingredients used by Potions.
 */
public class IngredientManager extends ResourceManager {
    private static final String INGREDIENTS_DIRECTORY_NAME = "ingredients";
    private final HashMap<String, Ingredient> ingredients = new HashMap<>();

    public void registerIngredients() {
        registerResources(INGREDIENTS_DIRECTORY_NAME, ingredients, new IngredientValidator());
    }

    /**
     * Retrieves a copy of the ingredient retrieved from its Hashmap.
     * @param key       The key associated with the ingredient.
     * @return          A new instance of the retrieved ingredient if found. Else returns null.
     */
    public Ingredient getIngredientCopy(String key) {
        if (key == null) return null;
        return ingredients.get(key) == null ? null : new Ingredient(ingredients.get(key));
    }

}

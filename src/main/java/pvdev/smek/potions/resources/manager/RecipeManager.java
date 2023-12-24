package pvdev.smek.potions.resources.manager;

import pvdev.smek.potions.Potions;
import pvdev.smek.potions.resources.resource.Recipe;
import pvdev.smek.potions.resources.validator.resource.RecipeValidator;

import java.util.HashMap;
import java.util.logging.Level;

/**
 * Responsible for providing reference to all registered recipes used by Potions.
 */
public class RecipeManager extends ResourceManager {
    private static final String RECIPES_DIRECTORY_NAME = "recipes";
    private final HashMap<String, Recipe> recipes = new HashMap<>();

    public void registerRecipes() {
        Potions.log("Loading Recipes:", Level.INFO);
        registerResources(RECIPES_DIRECTORY_NAME, recipes, new RecipeValidator());
        Potions.log("| done! :)", Level.INFO);
    }

    /**
     * Retrieves a copy of the recipe retrieved from its Hashmap.
     * @param key       The key associated with the recipe.
     * @return          A new instance of the retrieved recipe if found. Else returns null.
     */
    public Recipe getRecipeCopy(String key) {
        if (key == null) return null;
        return recipes.get(key) == null ? null : new Recipe(recipes.get(key));
    }
}

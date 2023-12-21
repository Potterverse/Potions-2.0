package pvdev.smek.potions.manager;

import pvdev.smek.potions.json.JSONUtil;
import pvdev.smek.potions.resource.Ingredient;
import pvdev.smek.potions.resource.Recipe;
import pvdev.smek.potions.resource.Resource;
import pvdev.smek.potions.resource.validator.IngredientValidator;
import pvdev.smek.potions.resource.validator.RecipeValidator;
import pvdev.smek.potions.resource.validator.Validator;

import java.util.HashMap;

/**
 * Responsible for handling all the intractable resources used by Potions.
 */
public class ResourceManager {

    private static final String INGREDIENTS_DIRECTORY_NAME = "ingredients";
    private static final String RECIPES_DIRECTORY_NAME = "recipes";
    private static final HashMap<String, Ingredient> ingredients = new HashMap<>();
    private static final HashMap<String, Recipe> recipes = new HashMap<>();

    private void registerResources() {
        registerResources(INGREDIENTS_DIRECTORY_NAME, ingredients, new IngredientValidator());
        registerResources(RECIPES_DIRECTORY_NAME, recipes, new RecipeValidator());
    }

    /**
     * Generic method that registers resources from a given directory.
     * JSON objects are validated and mapped to a specific Resource instance
     * to be stored for reference.
     * @param directoryName     The name of the directory to reference from.
     * @param resourceMap       The HashMap to store specific Resource instances.
     * @param validator         The associated validator to verify JSON structure.
     * @param <T>               Any generic type that extends off the Resource class.
     */
    private <T extends Resource> void registerResources(
            String directoryName, HashMap<String, T> resourceMap, Validator<T> validator) {

        JSONUtil.createDirectory(directoryName);
        JSONUtil.listJSONFromDirectory(directoryName).forEach(jsonObject -> {
            T resource = validator.validateAndReturnResource(jsonObject);
            if (resource == null) return;
            if (resourceMap.containsKey(resource.getName())) return;
            resourceMap.put(resource.getName(), resource);
        });
    }

    /**
     * Retrieves a copy of the Ingredient retrieved from its Hashmap.
     * @param key       The key associated with the Ingredient.
     * @return          A new instance of the retrieved Ingredient if found. Else returns null.
     */
    public static Ingredient getIngredientCopy(String key) {
        return ingredients.get(key) == null ? null : new Ingredient(ingredients.get(key));
    }
}

package pvdev.smek.potions.resource.validator;

import com.google.gson.JsonObject;
import pvdev.smek.potions.resource.Recipe;

/**
 * The validator that checks whether a Recipe instance
 * can be created with the given JSON object values.
 */
public class RecipeValidator implements Validator<Recipe> {

    @Override
    public Recipe validateAndReturnResource(JsonObject jsonObject) {
        return null;
    }
}

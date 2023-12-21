package pvdev.smek.potions.resource.validator;

import com.google.gson.JsonObject;
import pvdev.smek.potions.resource.Ingredient;

/**
 * The validator that checks whether an Ingredient instance
 * can be created with the given JSON object values.
 */
public class IngredientValidator implements Validator<Ingredient> {

    @Override
    public Ingredient validateAndReturnResource(JsonObject jsonObject) {
        return null;
    }
}

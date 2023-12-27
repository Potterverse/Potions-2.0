package pvdev.smek.potions.resources.validator.resource;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pvdev.smek.potions.Potions;
import pvdev.smek.potions.json.JSONUtil;
import pvdev.smek.potions.resources.resource.Recipe;
import pvdev.smek.potions.resources.validator.Validator;

import java.util.logging.Level;

/**
 * The validator that checks whether a Recipe instance
 * can be created with the given JSON object values.
 */
public class RecipeValidator implements Validator<Recipe> {

    @Override
    public Recipe validateAndReturnResource(JsonObject jsonObject) {
        String name = JSONUtil.validateAndReturnType(jsonObject, "name", JsonElement::getAsString);
        String hex = JSONUtil.validateAndReturnType(jsonObject, "hex", JsonElement::getAsString);
        JsonArray steps = JSONUtil.validateAndReturnType(jsonObject, "steps", JsonElement::getAsJsonArray);

        if (name == null || hex == null || steps == null)  {
            Potions.log(" > Failed to validate recipe parameters. " + jsonObject,
                    Level.WARNING);
            return null;
        }
        return new Recipe(name, hex, steps);
    }
}

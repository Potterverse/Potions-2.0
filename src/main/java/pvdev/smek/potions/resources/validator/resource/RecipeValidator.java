package pvdev.smek.potions.resources.validator.resource;

import com.google.gson.JsonArray;
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
        String name = JSONUtil.findJSONString(jsonObject, "name");
        JsonArray steps = JSONUtil.findJSONArray(jsonObject, "steps");

        if (name == null || steps == null) return null;
        Potions.log("| Registering resource: \"" + name + "\".", Level.INFO);
        return new Recipe(name, steps);
    }
}

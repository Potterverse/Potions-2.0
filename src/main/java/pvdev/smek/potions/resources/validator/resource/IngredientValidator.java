package pvdev.smek.potions.resources.validator.resource;

import com.google.gson.JsonObject;
import pvdev.smek.potions.json.JSONUtil;
import pvdev.smek.potions.resources.resource.Ingredient;
import pvdev.smek.potions.resources.validator.Validator;

/**
 * The validator that checks whether an Ingredient instance
 * can be created with the given JSON object values.
 */
public class IngredientValidator implements Validator<Ingredient> {

    @Override
    public Ingredient validateAndReturnResource(JsonObject jsonObject) {
        String name = JSONUtil.findJSONString(jsonObject, "name");
        String description = JSONUtil.findJSONString(jsonObject, "description");
        String hex = JSONUtil.findJSONString(jsonObject, "hex");
        String material = JSONUtil.findJSONString(jsonObject, "material");

        if (name == null || description == null || hex == null || material == null) return null;
        return new Ingredient(name, description, hex, material);
    }
}

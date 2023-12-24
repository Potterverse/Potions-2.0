package pvdev.smek.potions.resources.validator.step;

import com.google.gson.JsonObject;
import pvdev.smek.potions.Potions;
import pvdev.smek.potions.json.JSONUtil;
import pvdev.smek.potions.resources.resource.Ingredient;
import pvdev.smek.potions.resources.step.IngredientStep;
import pvdev.smek.potions.resources.validator.Validator;

/**
 * The validator that checks whether a IngredientStep instance
 * can be created with the given JSON object values.
 */
public class IngredientStepValidator implements Validator<IngredientStep> {

    @Override
    public IngredientStep validateAndReturnResource(JsonObject jsonObject) {
        String name = JSONUtil.findJSONString(jsonObject, "ingredient");
        Ingredient ingredient = Potions.getIngredientManager().getIngredientCopy(name);
        Integer quantity = JSONUtil.findJSONInteger(jsonObject, "quantity");

        if (name == null || ingredient == null || quantity == null || quantity <= 0) return null;
        return new IngredientStep(ingredient, quantity);
    }
}

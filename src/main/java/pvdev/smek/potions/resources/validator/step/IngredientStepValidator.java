package pvdev.smek.potions.resources.validator.step;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pvdev.smek.potions.Potions;
import pvdev.smek.potions.json.JSONUtil;
import pvdev.smek.potions.resources.resource.Ingredient;
import pvdev.smek.potions.resources.step.IngredientStep;
import pvdev.smek.potions.resources.validator.Validator;

import java.util.logging.Level;

/**
 * The validator that checks whether a IngredientStep instance
 * can be created with the given JSON object values.
 */
public class IngredientStepValidator implements Validator<IngredientStep> {

    @Override
    public IngredientStep validateAndReturnResource(JsonObject jsonObject) {
        String name = JSONUtil.validateAndReturnType(jsonObject, "ingredient", JsonElement::getAsString);
        Ingredient ingredient = Potions.getIngredientManager().getIngredientCopy(name);
        Integer quantity = JSONUtil.validateAndReturnType(jsonObject, "quantity", JsonElement::getAsInt);

        if (name == null || ingredient == null || quantity == null || quantity <= 0) {
            Potions.log(" > Failed to validate ingredient step parameters. " + jsonObject, Level.WARNING);
            return null;
        }
        return new IngredientStep(ingredient, quantity);
    }
}

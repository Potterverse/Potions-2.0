package pvdev.smek.potions.resources.step;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pvdev.smek.potions.Potions;
import pvdev.smek.potions.json.JSONUtil;
import pvdev.smek.potions.resources.validator.step.IngredientStepValidator;
import pvdev.smek.potions.resources.validator.step.StirStepValidator;
import pvdev.smek.potions.resources.validator.step.WaitStepValidator;

import java.util.logging.Level;

/**
 * Step creation factory that validates JSON objects
 * before inserting values for class creation.
 */
public class StepFactory {

    /**
     * Given a specific JSON layout, check whether the provided JSON object
     * is sufficient into creating an instance of the target Step type.
     * @param jsonObject    The JSON object to be validated
     * @return              Step instance.
     */
    public static Step validateAndReturnStep(JsonObject jsonObject) {
        String type = JSONUtil.validateAndReturnType(jsonObject, "type", JsonElement::getAsString);
        if (type == null) {
            Potions.log(" > Failed to register step. " + jsonObject, Level.WARNING);
            return null;
        }

        return switch (type) {
            case "ingredient" -> new IngredientStepValidator().validateAndReturnResource(jsonObject);
            case "stir" -> new StirStepValidator().validateAndReturnResource(jsonObject);
            case "wait" -> new WaitStepValidator().validateAndReturnResource(jsonObject);
            default -> {
                Potions.log(" > Failed to find step: \"" + type + "\". " + jsonObject, Level.WARNING);
                yield null;
            }
        };
    }
}

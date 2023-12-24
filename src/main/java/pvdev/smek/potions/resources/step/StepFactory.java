package pvdev.smek.potions.resources.step;

import com.google.gson.JsonObject;
import pvdev.smek.potions.resources.validator.step.IngredientStepValidator;

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
        String type = jsonObject.get("type").getAsString();
        if (type == null) return null;

        switch (type) {
            case "ingredient" : return new IngredientStepValidator().validateAndReturnResource(jsonObject);
            default : return null;
        }
    }
}

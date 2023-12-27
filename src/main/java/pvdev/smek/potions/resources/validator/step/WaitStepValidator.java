package pvdev.smek.potions.resources.validator.step;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pvdev.smek.potions.Potions;
import pvdev.smek.potions.json.JSONUtil;
import pvdev.smek.potions.resources.step.WaitStep;
import pvdev.smek.potions.resources.validator.Validator;

import java.util.logging.Level;

/**
 * The validator that checks whether a WaitStep instance
 * can be created with the given JSON object values.
 */
public class WaitStepValidator implements Validator<WaitStep> {

    @Override
    public WaitStep validateAndReturnResource(JsonObject jsonObject) {
        Float duration = JSONUtil.validateAndReturnType(jsonObject, "duration", JsonElement::getAsFloat);

        if (duration == null || duration <= 0) {
            Potions.log(" > Failed to validate ingredient step parameters. " + jsonObject, Level.WARNING);
            return null;
        }
        return new WaitStep(duration);
    }
}

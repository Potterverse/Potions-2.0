package pvdev.smek.potions.resources.validator.step;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pvdev.smek.potions.Potions;
import pvdev.smek.potions.json.JSONUtil;
import pvdev.smek.potions.resources.step.StirStep;
import pvdev.smek.potions.resources.step.enums.Direction;
import pvdev.smek.potions.resources.step.enums.Speed;
import pvdev.smek.potions.resources.validator.Validator;

import java.util.logging.Level;

/**
 * The validator that checks whether a IngredientStep instance
 * can be created with the given JSON object values.
 */
public class StirStepValidator implements Validator<StirStep> {

    @Override
    public StirStep validateAndReturnResource(JsonObject jsonObject) {

        Direction direction = JSONUtil.validateAndReturnEnum(
                JSONUtil.validateAndReturnType(jsonObject, "direction", JsonElement::getAsString),
                Direction.class);

        Speed speed = JSONUtil.validateAndReturnEnum(
                JSONUtil.validateAndReturnType(jsonObject, "speed", JsonElement::getAsString),
                Speed.class);

        Integer rotations = JSONUtil.validateAndReturnType(jsonObject, "rotations", JsonElement::getAsInt);

        if (direction == null || rotations == null || rotations <= 0 || speed == null) {
            Potions.log(" > Failed to validate stir step parameters. " + jsonObject, Level.WARNING);
            return null;
        }
        return new StirStep(direction, rotations, speed);
    }
}

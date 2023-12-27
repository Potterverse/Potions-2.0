package pvdev.smek.potions.resources.validator.resource;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pvdev.smek.potions.Potions;
import pvdev.smek.potions.json.JSONUtil;
import pvdev.smek.potions.resources.resource.enums.Process;
import pvdev.smek.potions.resources.resource.Ingredient;
import pvdev.smek.potions.resources.validator.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * The validator that checks whether an Ingredient instance
 * can be created with the given JSON object values.
 */
public class IngredientValidator implements Validator<Ingredient> {

    @Override
    public Ingredient validateAndReturnResource(JsonObject jsonObject) {

        String name = JSONUtil.validateAndReturnType(jsonObject, "name", JsonElement::getAsString);
        String description = JSONUtil.validateAndReturnType(jsonObject, "description", JsonElement::getAsString);
        String hex = JSONUtil.validateAndReturnType(jsonObject, "hex", JsonElement::getAsString);
        String material = JSONUtil.validateAndReturnType(jsonObject, "material", JsonElement::getAsString);
        Integer customModelData = JSONUtil.validateAndReturnType(jsonObject, "custom", JsonElement::getAsInt);

        if (name == null || description == null || hex == null || material == null || customModelData == null) {
            Potions.log(" > Failed to validate ingredient parameters. " + jsonObject,
                    Level.WARNING);
            return null;
        }

        // Optional process parameter.
        JsonObject processes = JSONUtil.validateAndReturnType(jsonObject, "process", JsonElement::getAsJsonObject);
        Map<Process, String> output = new HashMap<>();
        if (processes != null) {
            processes.getAsJsonObject().entrySet().forEach(key -> {
                Process process = JSONUtil.validateAndReturnEnum(key.getKey(), Process.class);
                String ingredient = JSONUtil.validateAndReturnType(processes, key.getKey(), JsonElement::getAsString);

                if (process == null || ingredient == null) {
                    Potions.log(" > Failed to validate processing parameters. " + processes, Level.WARNING);
                    return;
                }
                output.put(process, ingredient);
            });
        }
        return new Ingredient(name, description, hex, material, customModelData, output);
    }
}

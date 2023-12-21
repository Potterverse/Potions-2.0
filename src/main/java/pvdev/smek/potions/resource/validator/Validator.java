package pvdev.smek.potions.resource.validator;

import com.google.gson.JsonObject;

/**
 * A generic interface that validates and returns instances from a given JSON object.
 * Note this may be switched out in favor of the JSON Schema package. At the moment,
 * more explicit validation is used here.
 * @param <T> Instances to be validated with a specific JSON model.
 */
public interface Validator<T> {
    /**
     * Given a specific JSON layout, check whether the provided JSON object
     * is sufficient into creating an instance of the target object type.
     * @param jsonObject    The JSON object to be validated
     * @return              Object instance.
     */
    T validateAndReturnResource(JsonObject jsonObject);
}

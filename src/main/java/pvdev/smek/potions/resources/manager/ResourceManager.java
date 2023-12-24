package pvdev.smek.potions.resources.manager;

import pvdev.smek.potions.json.JSONUtil;
import pvdev.smek.potions.resources.resource.Resource;
import pvdev.smek.potions.resources.validator.Validator;

import java.util.HashMap;

/**
 * Responsible for handling all the intractable resources used by Potions.
 */
public abstract class ResourceManager {
    /**
     * Generic method that registers resources from a given directory.
     * JSON objects are validated and mapped to a specific Resource instance
     * to be stored for reference.
     * @param directoryName     The name of the directory to reference from.
     * @param resourceMap       The HashMap to store specific Resource instances.
     * @param validator         The associated validator to verify JSON structure.
     * @param <T>               Any generic type that extends off the Resource class.
     */
    protected <T extends Resource> void registerResources(
            String directoryName, HashMap<String, T> resourceMap, Validator<T> validator) {

        JSONUtil.createDirectory(directoryName);
        JSONUtil.listJSONFromDirectory(directoryName).forEach(jsonObject -> {
            T resource = validator.validateAndReturnResource(jsonObject);
            if (resource == null) return;
            if (resourceMap.containsKey(resource.getName())) return;
            resourceMap.put(resource.getName(), resource);
        });
    }
}

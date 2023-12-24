package pvdev.smek.potions.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pvdev.smek.potions.Potions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Utility class for JSON related file-handling. This may be switched out
 * later as this is merely a basic draft of functionality.
 */
public class JSONUtil {

    /**
     * Creates a directory of the provided name within the plugin data folder.
     * @param directoryName     The name of the directory to create.
     */
    public static void createDirectory(String directoryName) {
        File directory = new File(Potions.getPlugin().getDataFolder() + "/" + directoryName);
        if (directory.mkdirs())
            Potions.log(("Creating new " + directoryName + " directory."), Level.INFO);
    }

    /**
     * List all JSON files detected from the given directory.
     * @param directoryName     The name of the directory to reference from.
     * @return                  List of JSON files detected in the provided directory.
     */
    public static List<JsonObject> listJSONFromDirectory(String directoryName) {
        File directory = new File(Potions.getPlugin().getDataFolder() + "/" + directoryName);
        List<JsonObject> jsonFiles = new ArrayList<>();


        File[] files = directory.listFiles();
        if (files == null) {
            Potions.log("\tFailed to access this directory.", Level.WARNING);
            return jsonFiles;
        }
        for (File file : files) {
            try {
                jsonFiles.add(JsonParser.parseReader(new FileReader(file)).getAsJsonObject());
            } catch (Exception e) {
                Potions.log("| Failed to load file \""
                        + file.getName() + "\". Reason: " + e.getMessage(), Level.WARNING);
            }
        }
        return jsonFiles;
    }

    public static String findJSONString(JsonObject jsonObject, String key) {
        JsonElement jsonElement = jsonObject.get(key);
        if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString())
            return jsonElement.getAsString();
        return null;
    }

    public static Integer findJSONInteger(JsonObject jsonObject, String key) {
        JsonElement jsonElement = jsonObject.get(key);
        if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isNumber())
            return jsonElement.getAsInt();
        return null;
    }

    public static JsonArray findJSONArray(JsonObject jsonObject, String key) {
        JsonElement jsonElement = jsonObject.get(key);
        if (jsonElement.isJsonArray()) return jsonElement.getAsJsonArray();
        return null;
    }
}

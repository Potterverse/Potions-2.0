package pvdev.smek.potions.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pvdev.smek.potions.Potions;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
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
            Potions.log(" > Failed to access this directory.", Level.WARNING);
            return jsonFiles;
        }
        for (File file : files) {
            try {
                jsonFiles.add(parseJsonFile(file));
            } catch (Exception e) {
                Potions.log(" > Failed to load file \""
                        + file.getName() + "\". Reason: " + e.getMessage(), Level.WARNING);
            }
        }
        return jsonFiles;
    }

    private static JsonObject parseJsonFile(File file) throws Exception {
        try (FileReader reader = new FileReader(file)) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        }
    }

    public static <T> T validateAndReturnType(JsonObject jsonObject, String key, Function<JsonElement, T> extractor) {
        JsonElement jsonElement = jsonObject.get(key);
        if (jsonElement != null) try { return extractor.apply(jsonElement); } catch (Exception ignored) {}
        return null;
    }

    public static <T extends Enum<T>> T validateAndReturnEnum(String name, Class<T> enumType) {
        try {
            return Enum.valueOf(enumType, name.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}

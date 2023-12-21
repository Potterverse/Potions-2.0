package pvdev.smek.potions.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pvdev.smek.potions.Potions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            Potions.getPlugin().getLogger().warning("Creating new " + directoryName + " directory.");
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
            Potions.getPlugin().getLogger().warning("Failed to access this directory.");
            return jsonFiles;
        }
        for (File file : files) {
            try {
                jsonFiles.add(JsonParser.parseReader(new FileReader(file)).getAsJsonObject());
            } catch (IOException e) {
                Potions.getPlugin().getLogger()
                        .warning("Failed to load file \"" + file.getName() + "\". Reason: " + e.getMessage());
            }
        }
        return jsonFiles;
    }
}

package pvdev.smek.potions.util;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import pvdev.smek.potions.Potions;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONUtil {
    public static List<JSONObject> retrieveJSONFiles(String directory) {
        File recipesDirectory = new File(Potions.getPlugin().getDataFolder() + "/" + directory + "/");
        if (!recipesDirectory.exists()) {
            Potions.getPlugin().getLogger().warning("Creating new " + directory + " directory.");
            recipesDirectory.getParentFile().mkdirs();
            recipesDirectory.mkdirs();
            return new ArrayList<>();
        }
        File[] files = recipesDirectory.listFiles();
        if (files == null) return new ArrayList<>();
        List<JSONObject> jsonFiles = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {
            try {
                jsonFiles.add(new JSONObject(new JSONParser().parse(new FileReader(file.getPath())).toString()));
            } catch (Exception e) {
                Potions.getPlugin().getLogger()
                        .warning("Failed to load file \"" + file.getName() + "\"." + e.getMessage());
            }
        });
        return jsonFiles;
    }
}

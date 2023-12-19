package pvdev.smek.potions.step;


import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.json.JSONObject;
import pvdev.smek.potions.ingredient.Ingredient;
import pvdev.smek.potions.ingredient.IngredientManager;

public class StepFactory {
    public static Step getStep(JSONObject jsonObject) {
        switch(jsonObject.getString("type").toLowerCase()) {
            case "ingredient" -> {
                String ingredient = jsonObject.getString("ingredient");
                int quantity =  jsonObject.getInt("quantity");
                if (IngredientManager.getIngredient(ingredient) == null) return null;

                return new IngredientStep(IngredientManager.getIngredient(ingredient), quantity);
            }
        }
        return null;
    }
}

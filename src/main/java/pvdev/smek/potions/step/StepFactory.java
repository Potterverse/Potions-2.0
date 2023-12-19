package pvdev.smek.potions.step;


import org.json.JSONObject;
import pvdev.smek.potions.ingredient.IngredientManager;

public class StepFactory {
    public static Step getStep(JSONObject jsonObject) {
        // More steps will be added soon.
        switch(jsonObject.getString("type").toLowerCase()) {
            case "ingredient" -> {
                String ingredient = jsonObject.getString("ingredient");
                int quantity =  jsonObject.getInt("quantity");
                if (IngredientManager.getIngredient(ingredient) == null) break;

                return new IngredientStep(IngredientManager.getIngredient(ingredient), quantity);
            }
        }
        return null;
    }
}

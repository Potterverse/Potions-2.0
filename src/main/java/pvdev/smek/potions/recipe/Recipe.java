package pvdev.smek.potions.recipe;

import org.json.JSONObject;
import pvdev.smek.potions.Potions;
import pvdev.smek.potions.step.Step;
import pvdev.smek.potions.step.StepFactory;

import java.util.LinkedList;
import java.util.Queue;

public class Recipe {
    private final String name;
    private final Queue<Step> recipe = new LinkedList<>();
    public Recipe(JSONObject jsonRecipe) {
        this.name = jsonRecipe.getString("name");
        registerSteps(jsonRecipe);
    }
    public void registerSteps(JSONObject jsonRecipe) {
        jsonRecipe.getJSONArray("steps").forEach(object -> {
            Step step = StepFactory.getStep((JSONObject) object);
            if (step == null) {
                Potions.getPlugin().getLogger()
                        .warning("Step could not be found. Please check the JSON file.");
                return;
            }
            step.registerRecipe(this);
            recipe.add(step);
        });
    }
    public void executeStep(Step step) {
        if (recipe.isEmpty()) return;
        if (!recipe.peek().processStep(step)) Potions.getPlugin().getLogger().warning("RECIPE FAILED");
        if (isCompleted()) Potions.getPlugin().getLogger().warning("RECIPE COMPLETED");
    }
    public Step currentStep() {
        return recipe.peek();
    }
    public void nextStep() {
        recipe.poll();
    }
    public boolean isCompleted() {
        return recipe.isEmpty();
    }
    public String getName() {
        return name;
    }
}

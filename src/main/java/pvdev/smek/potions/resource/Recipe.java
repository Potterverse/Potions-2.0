package pvdev.smek.potions.resource;

import com.google.gson.JsonArray;
import pvdev.smek.potions.step.Step;

import java.util.LinkedList;
import java.util.Queue;

/***
 * The resource responsible for representing potion recipes.
 */
public class Recipe implements Resource {

    private final String name;
    private Queue<Step> recipe = new LinkedList<>();

    /***
     * Constructs a recipe with the given name and JSON array
     * retrieved to generate each step of the Recipe.
     * @param name      The name of the potion this recipe is intended for.
     * @param recipe    The JSON array used to generate each step of the Recipe.
     */
    public Recipe(String name, JsonArray recipe) {
        this.name =  name;
        registerSteps(recipe);
    }
    /**
     * Constructs a recipe using the data of another recipe.
     * Primarily used as a copy constructor.
     * @param recipe    The recipe instance being copied from.
     */
    public Recipe(Recipe recipe) {
        this.name = recipe.getName();
        this.recipe = recipe.getRecipe();
    }

    /**
     * Builds the steps outlined by the JSON array.
     * @param recipe    The JSON array used to generate each step of the Recipe.
     */
    private void registerSteps(JsonArray recipe) {

    }

    /**
     * Given the provided Step, processes it with the current Step
     * and updates whether the attempt has failed.
     * @param step  Provided Step to a process with the current Step.
     */
    public void executeStep(Step step) {
        if (isCompleted()) return;

    }

    public void nextStep() {
        recipe.poll();
    }

    public boolean isCompleted() {
        return recipe.isEmpty();
    }

    @Override
    public String getName() {
        return name;
    }

    public Queue<Step> getRecipe() {
        return recipe;
    }
}

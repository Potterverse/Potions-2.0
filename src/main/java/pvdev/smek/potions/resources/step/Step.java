package pvdev.smek.potions.resources.step;

import pvdev.smek.potions.resources.resource.Recipe;

/**
 * Template for Step instances where recipes can be registered
 * to be notified of an update to the current process.
 */
public abstract class Step {

    private Recipe parentRecipe;

    public void registerRecipe(Recipe recipe) {
        parentRecipe = recipe;
    }

    protected void notifyRecipeNextStep() {
        if (parentRecipe != null) parentRecipe.nextStep();
    }

    protected void executeStep(Step step) {
        if (parentRecipe != null) parentRecipe.executeStep(step);
    }

    /**
     * Compares and processes the incoming step against this instance.
     * @param step      The other Step instance to compare with this instance.
     * @return          Boolean value to indicate the success or failure of this process.
     */
    public abstract boolean processStep(Step step);
}
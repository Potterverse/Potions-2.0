package pvdev.smek.potions.step;

import pvdev.smek.potions.recipe.Recipe;

public abstract class Step {
    private Recipe parentRecipe;
    public void registerRecipe(Recipe recipe) {
        parentRecipe = recipe;
    }
    protected void notifyRecipe() {
        if (parentRecipe != null) parentRecipe.nextStep();
    }
    public abstract boolean processStep(Step step);
    public abstract String toString();
}

package pvdev.smek.potions.resources.step;

import pvdev.smek.potions.resources.resource.Ingredient;

/**
 * Brewing step that involves the process
 * of placing ingredients into the recipe.
 */
public class IngredientStep extends Step {

    private final Ingredient ingredient;
    private final int quantity;
    private int quantityRegistered;

    /**
     * Constructs an IngredientStep with the given
     * ingredient and quantity needed to proceed.
     * @param ingredient    The ingredient needed by this step.
     * @param quantity      The quantity needed by this step.
     */
    public IngredientStep(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.quantityRegistered = 0;
    }

    @Override
    public boolean processStep(Step step) {
        if (!(step instanceof IngredientStep ingredientStep)) return false;
        if (!ingredientStep.getIngredient().equals(ingredient)) return false;
        if (ingredientStep.getQuantity() + quantityRegistered > quantity) return false;

        quantityRegistered += ingredientStep.getQuantity();
        if (quantityRegistered == quantity) notifyRecipe();

        return true;
    }

    public String toString() {
        return ingredient.getName() + " " + quantityRegistered + "/" + quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }
}

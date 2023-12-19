package pvdev.smek.potions.step;

import pvdev.smek.potions.ingredient.Ingredient;

public class IngredientStep extends Step {
    private final Ingredient ingredient;
    private final int quantity;
    private int quantityRegistered;
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

    @Override
    public String toString() {
        return "Ingredient: " + ingredient.getName() + " x" + quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
    public int getQuantity() {
        return quantity;
    }
}

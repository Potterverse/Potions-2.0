package pvdev.smek.potions.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import pvdev.smek.potions.ingredient.IngredientManager;
import pvdev.smek.potions.recipe.Recipe;
import pvdev.smek.potions.recipe.RecipeManager;
import pvdev.smek.potions.step.IngredientStep;

public class TestingListener implements Listener {
    private Recipe recipe;
    @EventHandler
    public void onSwing(PlayerAnimationEvent event) {
        Player player = event.getPlayer();
        if (event.getAnimationType().equals(PlayerAnimationType.ARM_SWING)) {

            if (player.getInventory().getItemInMainHand().getType().equals(Material.STICK)) {
                player.getInventory().addItem(IngredientManager.getIngredient("Salamander Blood").getItemStack());
            }
            if (player.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD)) {
                recipe = RecipeManager.getRecipe("Cure for Boils");
            }
            if (recipe != null && !player.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD)) {
                recipe.executeStep(new IngredientStep(IngredientManager.getIngredient("Salamander Blood"), 1));
                player.sendMessage(recipe.currentStep() == null ? "NULL" : recipe.currentStep().toString());
            }
        }
    }
}

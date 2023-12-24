package pvdev.smek.potions.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import pvdev.smek.potions.Potions;
import pvdev.smek.potions.resources.resource.Recipe;
import pvdev.smek.potions.resources.step.IngredientStep;

/**
 * TESTING PURPOSES ONLY
 */
public class TestingListener implements Listener {

    private Recipe recipe;

    @EventHandler
    public void onSwing(PlayerAnimationEvent event) {
        Player player = event.getPlayer();
        if (event.getAnimationType().equals(PlayerAnimationType.ARM_SWING)) {

            if (player.getInventory().getItemInMainHand().getType().equals(Material.STICK)) {
                player.getInventory().addItem(Potions.getIngredientManager().getIngredientCopy("Salamander Blood").getItemStack());
            }
            if (player.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD)) {
                recipe = null;
                recipe = Potions.getRecipeManager().getRecipeCopy("Cure for Boils");
            }
            if (recipe != null && !player.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD)) {
                recipe.executeStep(new IngredientStep(Potions.getIngredientManager().getIngredientCopy("Salamander Blood"), 1));
                player.sendMessage(recipe.currentStep() == null ? "NULL" : recipe.currentStep().toString());
            }
        }
    }
}

package pvdev.smek.potions.resources.resource;

import com.google.gson.JsonArray;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import pvdev.smek.potions.Potions;
import pvdev.smek.potions.resources.manager.MistakeManager;
import pvdev.smek.potions.resources.step.Step;
import pvdev.smek.potions.resources.step.StepFactory;
import pvdev.smek.potions.resources.step.WaitStep;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;

/***
 * The resource responsible for representing potion recipes.
 */
public class Recipe extends Resource {

    private final String name;
    private final TextColor color;
    private final JsonArray jsonRecipe;
    private final Queue<Step> recipe = new LinkedList<>();
    private Player brewer;

    /***
     * Constructs a recipe with the given name and JSON array
     * retrieved to generate each step of the Recipe.
     * @param name      The name of the potion this recipe is intended for.
     * @param recipe    The JSON array used to generate each step of the Recipe.
     */
    public Recipe(String name, String hex, JsonArray recipe) {
        Potions.log("| Registering recipe: \"" + name + "\".", Level.INFO);
        this.name =  name;
        this.color = validateAndReturnColor(hex);
        this.jsonRecipe = recipe;
        registerSteps(recipe);
    }
    /**
     * Constructs a recipe using the data of another recipe.
     * Primarily used as a copy constructor.
     * @param recipe    The recipe instance being copied from.
     */
    public Recipe(Recipe recipe) {
        Potions.log("| Copying recipe: \"" + recipe.getName() + "\".", Level.INFO);
        this.name = recipe.getName();
        this.color = recipe.getColor();
        this.jsonRecipe = recipe.getRecipe();
        this.brewer = recipe.getBrewer();
        registerSteps(recipe.getRecipe());
    }

    /**
     * Builds the steps outlined by the JSON array.
     * @param recipe    The JSON array used to generate each step of the Recipe.
     */
    private void registerSteps(JsonArray recipe) {
        recipe.forEach(jsonElement -> {
            Step step = StepFactory.validateAndReturnStep(jsonElement.getAsJsonObject());
            if (step == null) return;
            step.registerRecipe(this);

            this.recipe.add(step);
        });
    }

    /**
     * Given the provided Step, processes it with the current Step
     * and updates whether the attempt has failed.
     * @param step  Provided Step to a process with the current Step.
     */
    public void executeStep(Step step) {
        if (step == null) return;
        if (isCompleted()) return;
        if (!currentStep().processStep(step)) MistakeManager.failBrewing(brewer);
        if (isCompleted()) brewer.getInventory().addItem(getItemStack());
    }

    public Step currentStep() {
        return recipe.peek();
    }

    public void nextStep() {
        recipe.poll();
        if (currentStep() != null && currentStep() instanceof WaitStep waitStep) waitStep.setStartTime();
    }

    public boolean isCompleted() {
        return recipe.isEmpty();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ItemStack getItemStack() {
        // Might be optional.
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(color.red(), color.green(), color.blue()));
        meta.displayName(Component.text(name)
                .decoration(TextDecoration.ITALIC, false)
                .decorate(TextDecoration.BOLD).color(color));

        List<Component> lore = new ArrayList<>();
        // Implement description into Potion?
        lore.add(Component.text(name).decoration(TextDecoration.ITALIC, false).color(color));
        lore.add(Component.text("Official Potterverse Potion")
                .decorate(TextDecoration.ITALIC)
                .color(DEFAULT_OFFICIAL_COLOR));
        meta.lore(lore);

        item.setItemMeta(meta);

        return item;
    }

    public TextColor getColor() {
        return color;
    }

    public JsonArray getRecipe() {
        return jsonRecipe;
    }

    public Player getBrewer() {
        return brewer;
    }

    public void setBrewer(Player brewer) {
        this.brewer = brewer;
    }
}

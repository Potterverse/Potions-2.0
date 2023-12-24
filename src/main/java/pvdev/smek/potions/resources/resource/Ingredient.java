package pvdev.smek.potions.resources.resource;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/***
 * The resource responsible for representing potion ingredients.
 */
public class Ingredient implements Resource {

    private final String name, description;
    private final TextColor color;
    private final Material material;

    public static final TextColor DEFAULT_COLOR = TextColor.fromHexString("#FFFFFF");
    public static final Material DEFAULT_MATERIAL = Material.GRASS_BLOCK;

    /**
     * Constructs an ingredient with the given name, description,
     * hexadecimal color code and material.
     * @param name          The name of the potion ingredient.
     * @param description   The description of the potion ingredient.
     * @param hex           Hexadecimal code beginning with a "#" symbol.
     * @param material      The name of the Minecraft material representing the ingredient.
     */
    public Ingredient(String name, String description, String hex, String material) {
        this.name =  name;
        this.description = description;
        this.color = validateAndReturnColor(hex);
        this.material = validateAndReturnMaterial(material.toUpperCase());
    }

    /**
     * Constructs an ingredient using the data of another ingredient.
     * Primarily used as a copy constructor.
     * @param ingredient    The ingredient instance being copied from.
     */
    public Ingredient(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.description = ingredient.getDescription();
        this.color = ingredient.getColor();
        this.material = ingredient.getMaterial();
    }

    public ItemStack getItemStack() {
        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name)
                .decoration(TextDecoration.ITALIC, false)
                .decorate(TextDecoration.BOLD).color(color));

        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(description).decoration(TextDecoration.ITALIC, false).color(color));
        lore.add(Component.text("Official Potterverse Ingredient")
                .decorate(TextDecoration.ITALIC)
                .color(TextColor.fromHexString("#b986f0")));
        meta.lore(lore);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (getClass() != object.getClass()) return false;

        Ingredient ingredient = (Ingredient) object;
        return ingredient.getName().equals(name)
                && ingredient.getDescription().equals(description)
                && ingredient.getColor().equals(color)
                && ingredient.getMaterial().name().equals(material.name());
    }

    private TextColor validateAndReturnColor(String hex) {
        return TextColor.fromHexString(hex) == null ? DEFAULT_COLOR : TextColor.fromHexString(hex);
    }

    private Material validateAndReturnMaterial(String material) {
        return Material.getMaterial(material) == null ? DEFAULT_MATERIAL : Material.getMaterial(material);
    }

    @Override
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TextColor getColor() {
        return color;
    }

    public Material getMaterial() {
        return material;
    }
}

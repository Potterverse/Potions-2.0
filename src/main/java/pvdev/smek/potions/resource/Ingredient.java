package pvdev.smek.potions.resource;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;

/***
 * The resource responsible for representing potion ingredients.
 */
public class Ingredient implements Resource {

    private final String name, description;
    private final TextColor color;
    private final Material material;

    private static final TextColor DEFAULT_COLOR = TextColor.fromHexString("#FFFFFF");
    private static final Material DEFAULT_MATERIAL = Material.GRASS_BLOCK;

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

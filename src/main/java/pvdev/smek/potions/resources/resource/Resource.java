package pvdev.smek.potions.resources.resource;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.inventory.ItemStack;

/***
 * An interface representing resources that need to be instantiated on startup for reference.
 * Implementations require that the name be used as a key of reference when retrieving.
 * Additionally, this interface is referenced when specifying the type for generics.
 */
public abstract class Resource {

    protected static final TextColor DEFAULT_COLOR = TextColor.fromHexString("#FFFFFF");
    protected static final TextColor DEFAULT_OFFICIAL_COLOR = TextColor.fromHexString("#b986f0");

    /**
     * Returns the name of the resource to be used as a point of reference.
     * @return The name of the resource.
     */
    public abstract String getName();

    public abstract ItemStack getItemStack();

    protected TextColor validateAndReturnColor(String hex) {
        return TextColor.fromHexString(hex) == null ? DEFAULT_COLOR : TextColor.fromHexString(hex);
    }
}

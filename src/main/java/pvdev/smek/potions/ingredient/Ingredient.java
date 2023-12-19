package pvdev.smek.potions.ingredient;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private final String name;
    private final String description;
    private final TextColor color;
    private final Material baseMaterial;
    public Ingredient(JSONObject jsonObject) {
        this.name = jsonObject.getString("name");
        this.description = jsonObject.getString("description");
        this.color = TextColor.fromHexString(jsonObject.getString("hex"));
        this.baseMaterial = Material.getMaterial(jsonObject.getString("material"));
    }
    public ItemStack getItemStack() {
        ItemStack item = new ItemStack(baseMaterial);

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
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public TextColor getColor() {
        return color;
    }
    public Material getBaseMaterial() {
        return baseMaterial;
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
                && ingredient.getBaseMaterial().name().equals(baseMaterial.name());
    }
}

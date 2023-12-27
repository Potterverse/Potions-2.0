package pvdev.smek.potions.resources.manager;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class MistakeManager {
    //  DRAFT
    public static void failBrewing(Player player) {
        if (player == null) return;
        player.sendMessage(Component.text("Your potion blew up :(").color(TextColor.fromHexString("#c4525a")));
    }
}

package me.zenox.superitems.items;

import me.zenox.superitems.util.Executable;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public interface SuperItemInterface {
    String getName();
    String getId();
    BasicItem.Rarity getRarity();
    SuperItem.Type getType();
    List<ItemAbility> getItemAbilities();
    ItemMeta getItemMeta();
    Material getMaterial();
}

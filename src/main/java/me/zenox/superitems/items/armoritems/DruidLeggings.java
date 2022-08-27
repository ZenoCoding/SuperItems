package me.zenox.superitems.items.armoritems;

import com.archyx.aureliumskills.stats.Stats;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.zenox.superitems.items.ArmorItem;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DruidLeggings extends ArmorItem {
    public DruidLeggings() {
        super("Druid Leggings", "druid_leggings", Rarity.RARE, Type.LEGGINGS, Material.LEATHER_LEGGINGS, Map.of(Stats.STRENGTH, 2d, Stats.HEALTH, 10d, Stats.REGENERATION, 20d));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Made of bark???");
        this.getMeta().setLore(lore);
        ((LeatherArmorMeta) this.getMeta()).setColor(Color.OLIVE);
        this.getMeta().addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        this.getMeta().addEnchant(Enchantment.THORNS, 1, true);
        Multimap<Attribute, AttributeModifier> attributeMap = ArrayListMultimap.create();
        attributeMap.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "superitems:armor", 6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
        attributeMap.put(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "superitems:armor_toughness", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));

        this.getMeta().setAttributeModifiers(attributeMap);
        this.getMeta().setUnbreakable(true);
    }

    @Override
    public List<Recipe> getRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(this.getKey(), this.getItemStack(1));
        return List.of();
    }
}

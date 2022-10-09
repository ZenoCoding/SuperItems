package me.zenox.superitems.abilities;

import me.zenox.superitems.item.ComplexItem;
import me.zenox.superitems.item.ItemRegistry;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;
import java.util.function.Consumer;

public class AttackAbility extends Ability {

    public AttackAbility(String id, int manaCost, double cooldown) {
        super(id, manaCost, cooldown, EntityDamageByEntityEvent.class, Slot.MAIN_HAND);
    }

    public AttackAbility(String id, int manaCost, double cooldown, Slot slot) {
        super(id, manaCost, cooldown, EntityDamageByEntityEvent.class, slot);
    }

    public AttackAbility(String id, int manaCost, double cooldown, Consumer<Event> exectuable) {
        super(id, manaCost, cooldown, EntityDamageByEntityEvent.class, Slot.MAIN_HAND, exectuable);
    }

    // Static ability executables
    public static void justiceAbility(Event event) {
        EntityDamageByEntityEvent e = ((EntityDamageByEntityEvent) event);
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            ItemStack item = p.getInventory().getItemInMainHand();
            ComplexItem complexItem = ItemRegistry.getBasicItemFromItemStack(item);
            if (!(complexItem == null) && complexItem.getId() == "sword_of_justice" && e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 0));
                p.playSound(p.getLocation(), Sound.ITEM_AXE_SCRAPE, 1, 1.4f);
                if (new Random().nextInt(3) == 0) e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
            }
        }
    }

    @Override
    public boolean checkEvent(Event e) {
        return super.checkEvent(e);
    }

}

package me.zenox.superitems;

import com.archyx.aureliumskills.AureliumSkills;
import com.archyx.aureliumskills.modifier.Modifiers;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.mojang.bridge.game.Language;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.ticxo.modelengine.api.ModelEngineAPI;
import me.zenox.superitems.command.MainCommand;
import me.zenox.superitems.data.ConfigLoader;
import me.zenox.superitems.data.LanguageLoader;
import me.zenox.superitems.events.InventoryListener;
import me.zenox.superitems.events.OtherEvent;
import me.zenox.superitems.events.PlayerUseItemEvent;
import me.zenox.superitems.item.ItemRegistry;
import me.zenox.superitems.network.GlowFilter;
import me.zenox.superitems.recipe.RecipeRegistry;
import org.bukkit.plugin.java.JavaPlugin;
import org.enginehub.piston.config.Config;

public final class SuperItems extends JavaPlugin {

    private static SuperItems plugin;

    private LanguageLoader languageLoader;
    private ConfigLoader configLoader;
    private ProtocolManager protocolManager;

    public boolean isUsingWorldGuard;

    public Modifiers modifiers;

    public static SuperItems getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        plugin.getLogger().info("SuperItems v" + plugin.getDescription().getVersion() + " loaded.");

        // Dependencies
        protocolManager = ProtocolLibrary.getProtocolManager();

        // Dependency check
        try {
            WorldGuard.getInstance();
            WorldGuardPlugin.inst();
            isUsingWorldGuard = true;

        } catch (NoClassDefFoundError e) {
            isUsingWorldGuard = false;
        }

        modifiers = new Modifiers(AureliumSkills.getPlugin(AureliumSkills.class));

        configLoader = new ConfigLoader(plugin);
        languageLoader = new LanguageLoader(plugin);

        // Item Packet/Network filters
        new GlowFilter(this, protocolManager);

        ItemRegistry.registerRecipes();
        ItemRegistry.registerItems();
        RecipeRegistry.registerRecipes();

        new MainCommand(plugin);

        registerListeners();

    }

    private void registerListeners(){
        new PlayerUseItemEvent(plugin);
        new OtherEvent(plugin);
        new InventoryListener(plugin);
    }

    public LanguageLoader getLang(){
        return this.languageLoader;
    }

    public ConfigLoader getConfigLoader() {
        return configLoader;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public void reload(){
        this.reloadConfig();
        this.languageLoader = new LanguageLoader(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

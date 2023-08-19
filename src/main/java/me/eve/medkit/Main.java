package me.eve.medkit;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Medkit plugin geladen");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

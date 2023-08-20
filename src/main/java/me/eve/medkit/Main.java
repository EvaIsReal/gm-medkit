package me.eve.medkit;

import me.eve.medkit.events.BlockInteraction;
import me.eve.medkit.events.PlayerInteract;
import me.eve.medkit.events.PlayerMove;
import me.eve.medkit.items.GMItems;
import me.eve.medkit.items.MedkitItem;
import me.eve.medkit.items.SmallChestItem;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        GMItems.register();
        getCommand("giveitem").setExecutor((sender, command, label, args) -> {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                switch (args[0]) {
                    case "1":
                        p.getInventory().addItem(new MedkitItem().item());
                        break;
                    case "2":
                        p.getInventory().addItem(new SmallChestItem().item());
                        break;
                    default:
                        p.sendMessage(GMUtils.color("&cKein solches item gefunden."));
                        break;
                }
            }
            return false;
        });

        getServer().getPluginManager().registerEvents(new PlayerInteract(), instance);
        getServer().getPluginManager().registerEvents(new BlockInteraction(), instance);
        getServer().getPluginManager().registerEvents(new PlayerMove(), instance);

        getLogger().info("Medkit plugin geladen");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance != null ? instance : new Main();
    }
}

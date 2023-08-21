package me.eve.medkit;

import me.eve.medkit.events.BlockInteraction;
import me.eve.medkit.events.PlayerInteract;
import me.eve.medkit.events.PlayerMove;
import me.eve.medkit.items.*;
import me.eve.medkit.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

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



        getRecipes();

        getServer().getPluginManager().registerEvents(new PlayerInteract(), instance);
        getServer().getPluginManager().registerEvents(new BlockInteraction(), instance);
        //getServer().getPluginManager().registerEvents(new PlayerMove(), instance);

        getLogger().info("Medkit plugin geladen");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void getRecipes() {
        ShapedRegister bandAidRecipe = new ShapedRegister(new BandAid().item());
        bandAidRecipe.shape("ppp", "pap", "ppp");
        bandAidRecipe.setIngredient('p', Material.PAPER);
        bandAidRecipe.setIngredient('a', Material.GOLDEN_APPLE);
        bandAidRecipe.register();

        ShapedRegister medkitRecipe = new ShapedRegister(new MedkitItem().item());
        medkitRecipe.shape("www", "bbb", "www");
        medkitRecipe.setIngredient('w', Material.WOOD);
        medkitRecipe.setIngredient('b', new BandAid().item());
        medkitRecipe.register();
    }

    public static Main getInstance() {
        return instance != null ? instance : new Main();
    }
}

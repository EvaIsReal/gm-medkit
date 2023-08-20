package me.eve.medkit.items;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class GMItem {

    public abstract String key();
    public abstract Material material();
    public abstract ItemStack item();
}

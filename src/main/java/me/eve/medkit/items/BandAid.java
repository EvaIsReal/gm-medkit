package me.eve.medkit.items;

import me.eve.medkit.GMUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BandAid extends GMItem {

    @Override
    public String key() {
        return "band_aid";
    }

    @Override
    public Material material() {
        return Material.PAPER;
    }

    @Override
    public ItemStack item() {
        ItemStack item = new ItemStack(material());
        ItemMeta im = item.getItemMeta();

        im.setDisplayName(GMUtils.color("&eVerband"));

        item.setItemMeta(im);

        return item;
    }
}

package me.eve.medkit.items;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import me.eve.medkit.GMUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class SmallChestItem extends GMItem implements Interactable {

    @Override
    public String key() {
        return "small_chest";
    }

    @Override
    public Material material() {
        return Material.SKULL_ITEM;
    }

    @Override
    public ItemStack item() {
        ItemStack item = new ItemStack(material());
        item.setDurability((short) 3);

        ItemMeta im = item.getItemMeta();
        im.setDisplayName(GMUtils.color("&eKleine Kiste"));
        item.setItemMeta(im);

        NBT.modify(item, nbt -> {
            nbt.setString("interactable", key());

            final ReadWriteNBT skullOwnerCompound = nbt.getOrCreateCompound("SkullOwner");
            skullOwnerCompound.setUUID("Id", UUID.randomUUID());
            skullOwnerCompound.getOrCreateCompound("Properties")
                    .getCompoundList("textures")
                    .addCompound()
                    .setString("Value", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDVjNmRjMmJiZjUxYzM2Y2ZjNzcxNDU4NWE2YTU2ODNlZjJiMTRkNDdkOGZmNzE0NjU0YTg5M2Y1ZGE2MjIifX19");
        });

        return item;
    }

    @Override
    public void execute(PlayerInteractEvent e) {
        Inventory inv = Bukkit.createInventory(e.getPlayer(), 9, "Kleine Kiste");
        e.getPlayer().openInventory(inv);
    }
}

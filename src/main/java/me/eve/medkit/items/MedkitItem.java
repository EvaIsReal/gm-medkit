package me.eve.medkit.items;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import me.eve.medkit.GMUtils;
import me.eve.medkit.player.PlayerManager;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class MedkitItem extends GMItem implements Interactable {


    @Override
    public String key() {
        return "medkit";
    }

    @Override
    public ItemStack item() {
        ItemStack item = new ItemStack(material());
        item.setDurability((short) 3);

        ItemMeta im = item.getItemMeta();
        im.setDisplayName(GMUtils.color("&eVerbandskasten"));
        item.setItemMeta(im);

        NBT.modify(item, nbt -> {
            nbt.setString("interactable", "medkit");

            final ReadWriteNBT skullOwnerCompound = nbt.getOrCreateCompound("SkullOwner");
            skullOwnerCompound.setUUID("Id", UUID.randomUUID());
            skullOwnerCompound.getOrCreateCompound("Properties")
                    .getCompoundList("textures")
                    .addCompound()
                    .setString("Value", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc5M2VlZjQ4NDkwNzhkZjRjY2Q0OTgwNTRjNzRlMjE3MWM3NzFmMjczMDk0NDE2NGE4ZWU3YjJkNTYzODMyIn19fQ==");
        });

        return item;
    }

    @Override
    public Material material() {
        return Material.SKULL_ITEM;
    }

    @Override
    public void execute(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(!PlayerManager.onCooldown(p.getUniqueId())) {
            GMUtils.sendActionbar(p, GMUtils.color("&eDu wirst geheilt bewege dich nicht..."));
            p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 0);

            PlayerManager.setDelayedPlayerTask(p, 10*20, () -> {
                p.setHealth(20.0);
                p.setFoodLevel(20);
                GMUtils.sendActionbar(p, GMUtils.color("&eGeheilt!"));
                p.spawnParticle(Particle.HEART, p.getLocation(), 6, .5, 1, .5);
            });
            PlayerManager.setMedkitCooldown(p.getUniqueId(), 90*1000);
        } else {
            GMUtils.sendActionbar(p, GMUtils.color("&cBitte warte eine Weile, bevor du den Verbandskasten erneut benutzt."));
            p.playSound(p.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1, 2);
        }
    }
}

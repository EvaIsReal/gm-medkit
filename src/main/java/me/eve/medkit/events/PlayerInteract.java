package me.eve.medkit.events;

import de.tr7zw.nbtapi.NbtApiException;
import me.eve.medkit.GMUtils;
import me.eve.medkit.items.GMItems;
import me.eve.medkit.items.MedkitItem;
import me.eve.medkit.items.SmallChestItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getHand() == EquipmentSlot.OFF_HAND) {
            e.setCancelled(true);
            return;
        }

        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        // Damit NBT-API keine NPE bei
        //if(e.getClickedBlock().getType() != Material.SKULL) return;

        try {
            String value = GMUtils.getSkullValue(e.getClickedBlock());

            if(value.equals(GMItems.ITEMS.get("medkit"))) {
                new MedkitItem().execute(e);
            } else if (value.equals(GMItems.ITEMS.get("small_chest"))) {
                new SmallChestItem().execute(e);
            }
        } catch (NbtApiException ignored) {}


    }

}

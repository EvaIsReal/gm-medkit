package me.eve.medkit.events;

import me.eve.medkit.GMUtils;
import me.eve.medkit.exceptions.InteractionFailedException;
import me.eve.medkit.items.GMItems;
import me.eve.medkit.items.MedkitItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        //if(e.hasItem()) return;

        if(e.getHand() == EquipmentSlot.OFF_HAND) {
            e.setCancelled(true);
            return;
        }

        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(e.getClickedBlock().getType() != Material.SKULL) return;

        String value = GMUtils.getSkullValue(e.getClickedBlock());

        if(value.equals(GMItems.ITEMS.get("medkit"))) {
            MedkitItem medkit = new MedkitItem();
            try {
                medkit.execute(e);
            } catch (InteractionFailedException ex) {}
        }


    }

}

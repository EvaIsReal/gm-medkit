package me.eve.medkit.events;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NbtApiException;
import me.eve.medkit.GMUtils;
import me.eve.medkit.items.GMItems;
import me.eve.medkit.items.MedkitItem;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockInteraction implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        ItemStack blockItem = e.getItemInHand();
        String key = NBT.get(blockItem, nbt -> { return nbt.getString("interactable"); });

        switch (key) {
            case "medkit":
                p.sendMessage(GMUtils.color("&bVerbandskasten wurde platziert."));
                break;
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Block block = e.getBlock();

        try {
            String value = GMUtils.getSkullValue(e.getBlock());
            if(value.equals(GMItems.ITEMS.get("medkit"))) {
                e.setDropItems(false);
                p.sendMessage(GMUtils.color("&bVerbandskasten entfernt."));

                if(!(p.getGameMode() == GameMode.CREATIVE))
                    e.getBlock().getWorld().dropItem(e.getBlock().getLocation().add(.5, .3, .5),
                            new MedkitItem().item());
            }

        } catch (NbtApiException ignored) {}


    }

}

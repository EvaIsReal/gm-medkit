package me.eve.medkit.events;

import me.eve.medkit.GMUtils;
import me.eve.medkit.player.PlayerManager;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(e.getFrom().getX() != e.getTo().getX() && e.getFrom().getZ() != e.getTo().getZ()) {
            if(PlayerManager.PLAYER_TASKS.containsKey(e.getPlayer().getUniqueId())) {

                PlayerManager.PLAYER_TASKS.get(e.getPlayer().getUniqueId()).cancel();
                PlayerManager.PLAYER_TASKS.remove(e.getPlayer().getUniqueId());

                GMUtils.sendActionbar(e.getPlayer(), GMUtils.color("&cHeilung abgebrochen!"));
                PlayerManager.MEDKIT_COOLDOWNS.remove(e.getPlayer().getUniqueId());
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
            }
        }
    }

}

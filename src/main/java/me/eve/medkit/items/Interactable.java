package me.eve.medkit.items;

import org.bukkit.event.player.PlayerInteractEvent;

public interface Interactable {

    void execute(PlayerInteractEvent e);

}

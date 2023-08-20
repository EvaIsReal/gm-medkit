package me.eve.medkit.items;

import me.eve.medkit.exceptions.InteractionFailedException;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public interface Interactable {

    boolean execute(PlayerInteractEvent e) throws InteractionFailedException;

}

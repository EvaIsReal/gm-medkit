package me.eve.medkit.player;

import me.eve.medkit.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Predicate;

public class PlayerManager {

    public static final HashMap<UUID, Long> MEDKIT_COOLDOWNS = new HashMap<>();
    public static final HashMap<UUID, BukkitTask> PLAYER_TASKS = new HashMap<>();

    public static void setMedkitCooldown(UUID uuid, long cooldownMillis) {
        MEDKIT_COOLDOWNS.put(uuid, cooldownMillis + System.currentTimeMillis());
    }

    public static void setDelayedPlayerTask(Player p, long delay, Runnable task) {
        BukkitTask bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                task.run();
                PLAYER_TASKS.remove(p.getUniqueId());
            }
        }.runTaskLater(Main.getInstance(), delay);
        PLAYER_TASKS.put(p.getUniqueId(), bukkitTask);

    }

    public static boolean onCooldown(UUID uuid) {
        return MEDKIT_COOLDOWNS.getOrDefault(uuid, -1L) > System.currentTimeMillis();
    }

}

package fr.xephi.authme.listener;

import fr.xephi.authme.service.TeleportationService;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import io.papermc.paper.event.player.AsyncPlayerSpawnLocationEvent;

import javax.inject.Inject;

public class PlayerListener19Spigot implements Listener {

    private static boolean isPlayerSpawnLocationEventCalled = false;

    @Inject
    private TeleportationService teleportationService;

    public static boolean isPlayerSpawnLocationEventCalled() {
        return isPlayerSpawnLocationEventCalled;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerSpawn(AsyncPlayerSpawnLocationEvent event) {
        isPlayerSpawnLocationEventCalled = true;

        Player player = event.getConnection().getPlayer();
        Location customSpawnLocation = teleportationService.prepareOnJoinSpawnLocation(player);

        if (customSpawnLocation != null) {
            event.setSpawnLocation(customSpawnLocation);
        }
    }
}

package me.ibims1player.tracking.listener;

import me.ibims1player.tracking.Main;
import me.ibims1player.tracking.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class TrackingListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().getInventory().setItem(4, new ItemBuilder(Material.COMPASS).setDisplayName("§cTracker §7[Rechtsklick]").setLore("", "§7Der nächste Spieler wird mit", "§7der Kompassnadel angezeigt.", "").build());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player nearest = getNearestPlayer(event.getPlayer(), 100);
        if(nearest != null) event.getPlayer().setCompassTarget(nearest.getLocation());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        try {
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if(event.getItem().getItemMeta().getDisplayName().equals("§cTracker §7[Rechtsklick]")) {
                    Player nearest = getNearestPlayer(event.getPlayer(), 100);
                    if(nearest != null) {
                        int distance = (int) event.getPlayer().getLocation().distance(getNearestPlayer(event.getPlayer(), 1000).getLocation());
                        event.getPlayer().sendMessage(Main.prefix + "§b" + nearest.getName() + " §7ist §e" + distance + " Blöcke §7von dir entfernt.");
                    }
            }
            }
        }catch (NullPointerException exception) {

        }
    }

    private Player getNearestPlayer(Player player, double radius) {
        double distance = Double.POSITIVE_INFINITY;
        Player target = null;
        for (Entity entity : player.getNearbyEntities(radius, radius, radius)) {
            if (!(entity instanceof Player)) continue;
            if (entity == player) continue;
            double distanceTo = player.getLocation().distance(entity.getLocation());
            if (distanceTo > distance) continue;
            distance = distanceTo;
            target = (Player) entity;
        }
        return target;
    }

}

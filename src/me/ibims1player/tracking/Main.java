package me.ibims1player.tracking;

import me.ibims1player.tracking.listener.TrackingListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static String prefix = "§7[§cTracking§7] §r";

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new TrackingListener(), this);
        Bukkit.getConsoleSender().sendMessage(prefix + "§a" + getName() + " wurde aktiviert.");
    }

}

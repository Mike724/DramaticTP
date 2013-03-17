package com.mike724.dramatictp;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class DTPEvents implements Listener {

    private DramaticTP plugin;

    public DTPEvents(DramaticTP plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player p = event.getPlayer();
        if(!plugin.isEnabledForPlayer(p.getName())) {
            return;
        }
        Location[] locations = new Location[2];
        locations[0] = event.getFrom();
        locations[1] = event.getTo();
        for(Location loc : locations) {
            World world = loc.getWorld();
            //Smoke in all directions
            for(int i = 0; i <= 8; i++) {
                world.playEffect(loc, Effect.SMOKE, i);
            }
            world.playSound(loc, Sound.PORTAL_TRAVEL, 0.25f, 0.5f);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {
        if(plugin.isEnabledForPlayer(event.getPlayer().getName())) {
            plugin.disableForPlayer(event.getPlayer().getName());
        }
    }

}

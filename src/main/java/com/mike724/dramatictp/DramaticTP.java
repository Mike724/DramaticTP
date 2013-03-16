/*
    This file is part of DramaticTP.

    DramaticTP is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    DramaticTP is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with DramaticTP.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.mike724.dramatictp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;

import java.io.IOException;
import java.lang.Override;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class DramaticTP extends JavaPlugin {

    private Set<String> players = new HashSet<String>();

    @Override
    public void onDisable() {
        this.getLogger().info("Disabled successfully");
    }

    @Override
    public void onEnable() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }

        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        this.saveConfig();

        Logger log = this.getLogger();

        //Enable plugin metrics
        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.getCommand("dramatictp").setExecutor(new DTPCommands(this));
        log.info("Enabled successfully");
    }

    public boolean isEnabledForPlayer(String p) {
        return players.contains(p);
    }
    public boolean enableForPlayer(String p) {
        return players.add(p);
    }
    public boolean disableForPlayer(String p) {
        return players.remove(p);
    }
}

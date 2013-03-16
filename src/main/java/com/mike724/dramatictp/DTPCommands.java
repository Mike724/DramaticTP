package com.mike724.dramatictp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DTPCommands implements CommandExecutor {

    private DramaticTP plugin;

    public DTPCommands(DramaticTP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("dramatictp")) {
            if(args.length != 1) {
                return false;
            }
            boolean isPlayer = sender instanceof Player;
            String msgNotPlayer = ChatColor.RED+"Only players can do that!";
            String permDenied   = ChatColor.RED+"You do not have permission to do that!";
            String permRoot = "DTPCommands.";
            String cmdRoot  = "/dramatictp";
            if(args[0].equalsIgnoreCase("help") && sender.hasPermission(permRoot+"help")) {
                sender.sendMessage(ChatColor.GREEN+"~~~~~ DramaticTP Help Page ~~~~~");
                sender.sendMessage(ChatColor.GRAY+"Tip: You can use /dtp instead of "+cmdRoot);
                if(sender.hasPermission(permRoot+"on")) {
                    sender.sendMessage(ChatColor.AQUA+"To enable Dramatic Teleport: ");
                    sender.sendMessage(ChatColor.YELLOW+cmdRoot+" on");
                }
                if(sender.hasPermission(permRoot+"off")) {
                    sender.sendMessage(ChatColor.AQUA+"To disable Dramatic Teleport: ");
                    sender.sendMessage(ChatColor.YELLOW+cmdRoot+" off");
                }
                //For toggle, they should be able to enable and disable dramatic teleport
                if(sender.hasPermission(permRoot+"on") && sender.hasPermission(permRoot+"off")) {
                    sender.sendMessage(ChatColor.AQUA+"To toggle Dramatic Teleport: ");
                    sender.sendMessage(ChatColor.YELLOW+cmdRoot+" toggle");
                }
                return true;
            } else if(args[0].equalsIgnoreCase("on")) {
                if(!isPlayer) {
                    sender.sendMessage(msgNotPlayer);
                    return true;
                }
                if(!sender.hasPermission(permRoot+"on")) {
                    sender.sendMessage(permDenied);
                    return true;
                }
                sender.sendMessage((plugin.enableForPlayer(sender.getName())) ?
                        ChatColor.GREEN+"Enabled!" :
                        ChatColor.RED+"Already enabled!");
                return true;
            } else if(args[0].equalsIgnoreCase("off")) {
                if(!isPlayer) {
                    sender.sendMessage(msgNotPlayer);
                    return true;
                }
                if(!sender.hasPermission(permRoot+"off")) {
                    sender.sendMessage(permDenied);
                    return true;
                }
                sender.sendMessage((plugin.disableForPlayer(sender.getName())) ?
                        ChatColor.GREEN+"Disabled!" :
                        ChatColor.RED+"Already disabled!");
                return true;
            } else if(args[0].equalsIgnoreCase("toggle")) {
                if(!isPlayer) {
                    sender.sendMessage(msgNotPlayer);
                    return true;
                }
                if(!(sender.hasPermission(permRoot+"on") && sender.hasPermission(permRoot+"off"))) {
                    sender.sendMessage(permDenied);
                    return true;
                }
                if(!plugin.isEnabledForPlayer(sender.getName())) {
                    plugin.enableForPlayer(sender.getName());
                    sender.sendMessage(ChatColor.GREEN+"Enabled!");
                } else {
                    plugin.disableForPlayer(sender.getName());
                    sender.sendMessage(ChatColor.GREEN+"Disabled!");
                }
                return true;
            }
        }
        return false;
    }
}

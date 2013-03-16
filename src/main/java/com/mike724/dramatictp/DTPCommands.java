package com.mike724.dramatictp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DTPCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("dramatictp")) {
            if(args.length != 1) {
                return false;
            }
            boolean isPlayer = sender instanceof Player;
            String msgNotPlayer = ChatColor.RED+"Only players can do that!";
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
                return true;
            } else if(args[0].equalsIgnoreCase("on")) {
                if(!isPlayer) {
                    sender.sendMessage(msgNotPlayer);
                    return true;
                }
                sender.sendMessage("TODO: Turn on for player");
            } else if(args[0].equalsIgnoreCase("off")) {
                if(!isPlayer) {
                    sender.sendMessage(msgNotPlayer);
                    return true;
                }
                sender.sendMessage("TODO: Turn off for player");
            }
        }
        return false;
    }
}

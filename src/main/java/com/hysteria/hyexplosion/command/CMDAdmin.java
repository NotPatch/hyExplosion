package com.hysteria.hyexplosion.command;

import com.hysteria.hyexplosion.hyExplosion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class CMDAdmin implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            hyExplosion.getInstance().saveConfig();
            Bukkit.getConsoleSender().sendMessage("[" + hyExplosion.getInstance().getDescription().getName() + "] " + ChatColor.GREEN + "configuration was successfully reloaded!");
        } else {
            Player p = (Player) sender;
            if (p.hasPermission("hyexplosion.reload")) {
                hyExplosion.getInstance().saveConfig();
                p.sendMessage("[" + hyExplosion.getInstance().getDescription().getName() + "] " + ChatColor.GREEN + "configuration was successfully reloaded!");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        return null;
    }
}

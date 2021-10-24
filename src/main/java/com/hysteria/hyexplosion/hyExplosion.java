package com.hysteria.hyexplosion;

import com.hysteria.hyexplosion.command.CMDAdmin;
import com.hysteria.hyexplosion.listener.EntityExplodeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class hyExplosion extends JavaPlugin {

    private static hyExplosion instance;

    public static hyExplosion getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Set instance
        instance = this;
        //Setting Config
        saveDefaultConfig();
        //Event listener
        Bukkit.getPluginManager().registerEvents(new EntityExplodeListener(), this);
        //Register command
        getCommand("hyexplosion").setExecutor(new CMDAdmin());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

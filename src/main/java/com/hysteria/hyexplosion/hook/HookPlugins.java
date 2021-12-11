package com.hysteria.hyexplosion.hook;

import com.hysteria.hyexplosion.hyExplosion;
import org.bukkit.Bukkit;

public class HookPlugins {

    hyExplosion main;
    private boolean useWorldGuard;
    private boolean useUltimateClaims;

    public HookPlugins(hyExplosion main){
        this.main = main;
        if(Bukkit.getPluginManager().getPlugin("WorldGuard").getDescription().getVersion().startsWith("7.")){
            useWorldGuard = true;
        }
        if(Bukkit.getPluginManager().getPlugin("UltimateClaims") != null){
            useUltimateClaims = true;
        }

    }

    public boolean isUseWorldGuard() {
        return useWorldGuard;
    }

    public boolean isUseUltimateClaims() {
        return useUltimateClaims;
    }
}

package com.hysteria.hyexplosion.hook.impl;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;

public class WorldGuardHook {

    public static boolean canExplode(Location l){
        RegionManager rm = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(l.getWorld()));
        ApplicableRegionSet set = rm.getApplicableRegions(BukkitAdapter.asBlockVector(l));
        for (ProtectedRegion rg : set) {
            if(rg != null){
                return false;
            }
        }
        return true;
    }

}

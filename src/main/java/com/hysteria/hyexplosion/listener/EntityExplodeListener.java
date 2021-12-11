package com.hysteria.hyexplosion.listener;

import com.hysteria.hyexplosion.ExplosionManager;
import com.hysteria.hyexplosion.hook.impl.WorldGuardHook;
import com.hysteria.hyexplosion.hyExplosion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class EntityExplodeListener implements Listener {

    public static ExplosionManager manager = new ExplosionManager();

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {


        if (!manager.getApplicable().contains(e.getEntity().getName().replace(" ", "").toUpperCase())) {
            return;
        }
        e.setCancelled(true);
        if (manager.getSound() != null) {
            e.getEntity().getWorld().playSound(e.getLocation(), manager.getSound(), 3, 3);
        }
        if (manager.getParticle() != null) {
            e.getEntity().getWorld().spawnParticle(manager.getParticle(), e.getLocation(), 30);
        }
        e.getEntity().getWorld().createExplosion(e.getLocation(), manager.getForce(), manager.getFire(), true);

        if (manager.getFallingblock()) {
            for (Block block : getNearbyBlocks(e.getLocation(), manager.getRadius())) {
                FallingBlock fallingBlock = e.getLocation().getWorld().spawnFallingBlock(block.getLocation(), block.getBlockData());
                fallingBlock.setVelocity(new Vector(
                        -0.3F + Math.random() * manager.getViolance(),
                        -0.4F + Math.random() * manager.getViolance(),
                        -0.2F + Math.random() * manager.getViolance()));
                fallingBlock.setDropItem(false);
                fallingBlock.setHurtEntities(manager.getHurt());
                block.setType(Material.AIR);
            }
        }


    }

    public static ArrayList<Block> getNearbyBlocks(Location loc, int radius) {
        ArrayList<Block> blocks = new ArrayList<>();

        for (int x = (loc.getBlockX() - radius); x <= (loc.getBlockX() + radius); x++) {
            for (int y = (loc.getBlockY() - (radius - 2)); y <= (loc.getBlockY() + radius); y++) {
                for (int z = (loc.getBlockZ() - radius); z <= (loc.getBlockZ() + radius); z++) {
                    Location l = new Location(loc.getWorld(), x, y, z);
                    if(l.getBlock().getType().isSolid()){
                        if(!manager.getBlacklistBlocks().contains(l.getBlock().getType().toString().toUpperCase())){
                            if(hyExplosion.getInstance().getHookPlugins().isUseWorldGuard() && WorldGuardHook.canExplode(loc)){
                                if (l.distance(loc) <= radius) {
                                    blocks.add(l.getBlock());
                                }
                            }
                        }

                    }
                }
            }
        }

        return blocks;
    }

}

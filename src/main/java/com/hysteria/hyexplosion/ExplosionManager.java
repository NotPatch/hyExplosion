package com.hysteria.hyexplosion;

import org.bukkit.Particle;
import org.bukkit.Sound;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class ExplosionManager {

    public String explosion;
    public String path;

    public ExplosionManager() {
        Set<String> section = hyExplosion.getInstance().getConfig().getConfigurationSection("explosions").getKeys(false);
        path = (String) section.toArray()[new Random().nextInt(section.size())];
        explosion = "explosions." + path;
    }


    public int getRadius() {
        return hyExplosion.getInstance().getConfig().getInt(explosion + ".radius");
    }

    public Particle getParticle() {
        return Particle.valueOf(hyExplosion.getInstance().getConfig().getString(explosion + ".particle"));
    }

    public Sound getSound() {
        return Sound.valueOf(hyExplosion.getInstance().getConfig().getString(explosion + ".sound"));
    }

    public boolean getHurt() {
        return hyExplosion.getInstance().getConfig().getBoolean(explosion + ".hurtEntities");
    }

    public boolean getFire() {
        return hyExplosion.getInstance().getConfig().getBoolean(explosion + ".spreadFire");
    }

    public List<String> getApplicable() {
        return hyExplosion.getInstance().getConfig().getStringList(explosion + ".applicable");
    }
    public double getViolance() {
        return hyExplosion.getInstance().getConfig().getDouble(explosion + ".violance");
    }

    public float getForce() {
        return (float) hyExplosion.getInstance().getConfig().getDouble(explosion + ".force");
    }

    public boolean getFallingblock() {
        return hyExplosion.getInstance().getConfig().getBoolean(explosion + ".fallingBlocks");
    }



}

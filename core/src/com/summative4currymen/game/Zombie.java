/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

import com.badlogic.gdx.math.Rectangle;

/**
 * Subclass of Player class, this class represents the zombies
 *
 * @author riepj9547
 */
public class Zombie extends Player {
    private int rotation;
    private long hitRate;
    private int damage;

    /**
     * Initializes Zombie data
     *
     * @param x The x position of the zombie
     * @param y The y position of the zombie
     * @param width The width of the zombie
     * @param height The height of the zombie
     * @param health The health the of the zombie
     * @param speed The speed the zombie will move at
     * @param name The name of the zombie
     * @param damage The amount of damage the zombie can attack with
     */
    public Zombie(float x, float y, int width, int height, int health, double speed, String name, int damage, int rotation, long hitRate) {
        super(x, y, width, height, health, speed, name);
        this.rotation = rotation;
        this.hitRate = hitRate;
        this.damage = damage;
    }

    

    public int getRotation() {
        return this.rotation;
    }

    public void setRotation(int num) {
        this.rotation = num;
    }

    public long getHitRate() {
        return this.hitRate;
    }

    /**
     * The method when the zombie is being hit
     *
     * @param damage the amount of damage the zombie is taking
     * @return if the zombie has died or not
     */
    @Override
    public int hit(int damage) {
        super.setHealth(super.getHealth() - damage);
        if (super.getHealth() <= 0) {
            this.die();
        }
        return super.getHealth();
    }

    /**
     * The method returns how much damage the zombie will give when attacking
     *
     * @return The damage given
     */
    public int attack() {
        return damage;
    }

    /**
     * Checking to see if the zombie whose calling on the method and the zombie
     * that is being passed in are overlapping
     *
     * @param zombie2 Another zombie that is being passed in
     * @return True if there is overlap and false if there is not overlap
     */
    public boolean collidesWith(Zombie zombie2) {
        return super.collides(zombie2.getBounds());
    }

}

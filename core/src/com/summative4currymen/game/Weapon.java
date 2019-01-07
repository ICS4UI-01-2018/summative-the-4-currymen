/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.ArrayList;

/**
 * This class represents the Weapons that will be used by the User
 *
 * @author riepj9547
 */
public class Weapon {

    private int damage;
    private float x;
    private float y;
    private int bulletSpeed;
    private int fireRate;
    private String name;
    private Texture gun1Texture;
    private int numBullets;
    private ArrayList<Bullet> bullets;

    /**
     * Initializes Weapon data
     *
     * @param name The name of the weapon
     * @param bulletSpeed The speed at which the bullets will travel
     * @param fireRate How quickly bullets will exit the weapon
     * @param damage The amount of damage the weapon will do on contact with an
     * enemy
     * @param numBullets The number of bullets the weapon is capable of firing
     * @param x The x position of the weapon
     * @param y The y position of the weapon
     */
    public Weapon(String name, int bulletSpeed, int fireRate, int damage, int numBullets, float x, float y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.numBullets = numBullets;
        this.damage = damage;
        this.fireRate = fireRate;
        this.bulletSpeed = bulletSpeed;
        gun1Texture = new Texture("gun1.png");
        bullets = new ArrayList<Bullet>();

    }

    /**
     * A getter for the name of the weapon
     *
     * @return the name of the weapon
     */
    public String getName() {
        return this.name;
    }

    /**
     * A getter for the amount of bullets the weapon has
     *
     * @return the number of bullets of the weapon
     */
    public int numBullets() {
        return this.numBullets;
    }

    /**
     * A getter for the bullets speed
     *
     * @return the bullets speed of the weapon
     */
    public int bulletSpeed() {
        return this.bulletSpeed;
    }

    /**
     * A getter for the fire rate of the weapon
     *
     * @return the fire rate of the weapon
     */
    public int fireRate() {
        return this.fireRate;
    }

    /**
     * A getter for the damage of the weapon
     *
     * @return the damage of the weapon
     */
    public int damage() {
        return this.damage;
    }

    /**
     * A getter for the x position of the weapon
     *
     * @return the x position of the weapon
     */
    public float getX() {
        return this.x;
    }

    /**
     * A getter for the y position of the weapon
     *
     * @return the y position of the weapon
     */
    public float getY() {
        return this.y;
    }

    public void drawWeapon(SpriteBatch batch) {
        batch.draw(gun1Texture, this.x, this.y, 55, 55);
    }

    public ArrayList<Bullet> shootWeapon(String name, int rotation, float x, float y, int bulletSpeed, int damage, int fireRate, int numBullets) {

        if (rotation == 0) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) 1, 0);
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                Bullet[] bArray = new Bullet[numBullets - 1];
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (1 - 0.5)) + (float) 0.5, (float) (Math.random() * (0.35 - 0.15)) + 0);
                    Bullet b2 = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (1 - 0.5)) + (float) 0.5, (float) (Math.random() * (-0.35 + 0.15)) + 0);
                    bullets.add(b2);
                    bullets.add(b);
                    
                }
                return bullets;
            }
        }
        if (rotation == 45) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) 0.5, (float) 0.5);
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (1 - 0.25)) + (float) 0.5, (float) (Math.random() * (0.35 - 0.15)) + (float)0.5);
                    Bullet b2 = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (1 - 0.5)) + (float) 0.5, (float) (Math.random() * (-0.35 + 0.15)) + (float)0.5);
                    bullets.add(b2);
                    bullets.add(b);
                }
                return bullets;
            }
        }
        if (rotation == 90) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, 0, (float) 0.5);
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (0.5 - 0))+ (float) -0.25, (float) (Math.random() * (0.35 - 0.15)) + (float) 1);
                    Bullet b2 = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (-0 + 0.5)) + (float) 0.25, (float) (Math.random() * (-0.35 + 0.15)) +(float) 1);
                    bullets.add(b2);
                    bullets.add(b);
                }
                return bullets;
            }
        }
        if (rotation == 135) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) -0.5, (float) 0.5);
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (-1.25 + 0.75)) + (float) -0.5, (float) (Math.random() * (1.35 - 1.15)) + 1);
                    Bullet b2 = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (1 - 0.5)) + (float) -0.5, (float) (Math.random() * (-1.35 + 1.15)) + 1);
                    bullets.add(b2);
                    bullets.add(b);
                }
                return bullets;
            }
        }
        if (rotation == 180) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) -0.5, 0);
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed+20, damage, (float) (Math.random() * (0.25 + 0.25)) - (float) 0.5, (float) (Math.random() * (-0.20 + 0.25)) - 0);
                    Bullet b2 = new Bullet((int) x, (int) y, 10, 10, bulletSpeed+20, damage, (float) (Math.random() * (0.25 + 0.25)) - (float) 0.5, (float) (Math.random() * (0.20 - 0.25)) - 0);
                    bullets.add(b2);
                    bullets.add(b);
                }
                return bullets;
            }
        }
        if (rotation == 225) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) -0.5, (float) -0.5);
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (-1.25 + 0.75)) +(float) 0.5, (float) (Math.random() * (0.35 - 0.15)) - 1);
                    Bullet b2 = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (1 - 0.5)) + (float) 0.5, (float) (Math.random() * (-0.35 + 0.15)) -1);
                    bullets.add(b2);
                    bullets.add(b);
                }
                return bullets;
            }
        }
        if (rotation == 270) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, 0, (float) -0.5);
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (0.25 - 0)) + 0, -1);
                    bullets.add(b);
                }
                return bullets;
            }
        }
        if (rotation == 315) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) 0.5, (float) -0.5);
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (1.25 - 0.75)) +(float) 0.5, (float) (Math.random() * (0.35 - 0.15)) - 1);
                    Bullet b2 = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (1 - 0.5)) + (float) 0.5, (float) (Math.random() * (-0.35 + 0.15)) -1);
                    bullets.add(b2);
                    bullets.add(b);
                }
                return bullets;
            }
        }

        System.out.println("No gun was found");

        return null;
    }

}

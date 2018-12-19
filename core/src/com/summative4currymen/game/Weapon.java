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

    public String getName() {
        return this.name;
    }

    public int numBullets() {
        return this.numBullets;
    }

    public int bulletSpeed() {
        return this.bulletSpeed;
    }

    public int fireRate() {
        return this.fireRate;
    }

    public int damage() {
        return this.damage;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void drawWeapon(SpriteBatch batch) {
        batch.draw(gun1Texture, this.x, this.y, 55, 55);
    }

    public ArrayList<Bullet> shootWeapon(String name, int rotation, float x, float y, int bulletSpeed, int damage, int fireRate, int numBullets) {

        if (rotation == 0) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float)0.5, 0);
                System.out.println("Made bullet");       
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                Bullet[] bArray = new Bullet[numBullets - 1];
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (1 - 0.5)) + (float) 0.5, (float) (Math.random() * (0.25 - 0)) + 0);
                    System.out.println("Bullet" + i);
                    bullets.add(b);
                }
                return bullets;
            }
        }
        if (rotation == 45) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float)0.5, (float)0.5);
                System.out.println("Made bullet");
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (1 - 0.25)) + (float)0.5, (float) (Math.random() * (1 - 0.25)) + (float)0.5);
                    System.out.println("Bullet" + i);
                    bullets.add(b);
                }
                return bullets;
            }
        }
        if (rotation == 90) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, 0, (float)0.5);
                System.out.println("Made bullet");
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (0.25 - 0)) + 0, (float) (Math.random() * (1.15 - 0.85)) + (float) 1);
                    System.out.println("Bullet" + i);
                    bullets.add(b);                    
                }
                return bullets;
            }
        }
        if (rotation == 135) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float)-0.5, (float)0.5);
                System.out.println("Made bullet");
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (-1.25 + 0.75)) - 1, (float) (Math.random() * (1.25 - 0.75)) + 1);
                    System.out.println("Bullet" + i);
                    bullets.add(b);                    
                }
                return bullets;
            }
        }
        if (rotation == 180) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float)-0.5, 0);
                System.out.println("Made bullet");
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, -1, (float) (Math.random() * (0.25 - 0)) + 0);
                    System.out.println("Bullet" + i);
                    bullets.add(b);                    
                }
                return bullets;
            }
        }
        if (rotation == 225) {
            System.out.println("225 HERE BOIS");
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float)-0.5, (float)-0.5);
                System.out.println("Made bullet");
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (-1.25 + 0.75)) - 1, (float) (Math.random() * (-1.25 + 0.75)) - 1);
                    System.out.println("Bullet" + i);
                    bullets.add(b);                    
                }
                return bullets;
            }
        }
        if (rotation == 270) {
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, 0, (float)-0.5);
                System.out.println("Made bullet");
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (0.25 - 0)) + 0, -1);
                    System.out.println("Bullet" + i);
                    bullets.add(b);                    
                }
                return bullets;
            }
        }
        if (rotation == 315) {
            System.out.println("315 HERE BOIS");
            if (numBullets == 1) {
                Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float)0.5, (float)-0.5);
                System.out.println("Made bullet");
                bullets.clear();
                bullets.add(b);
                return bullets;
            } else {
                bullets.clear();
                for (int i = 0; i < numBullets; i++) {
                    Bullet b = new Bullet((int) x, (int) y, 10, 10, bulletSpeed, damage, (float) (Math.random() * (1.25 - 0.75)) + 1, (float) (Math.random() * (-1.25 + 0.75)) - 1);
                    System.out.println("Bullet" + i);
                    bullets.add(b);                    
                }
                return bullets;
            }
        }
        
        System.out.println("No gun was found");

        return null;
    }

}

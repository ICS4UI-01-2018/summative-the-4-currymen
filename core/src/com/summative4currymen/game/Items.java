package com.summative4currymen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 * this class manages all pickups drop-rates are variables for easy adjustment,
 * but can't be input when the class is called.
 *
 * @author Matthew Goodman
 */
public class Items {

    private ArrayList<Pickup> pickups;
    private final float pickupDistance = 10;
    private final int maxAmmo = 6;
    private final int minAmmo = 2;
    private final int maxCoin = 10;
    private final int minCoin = 5;
    private final int healthpack = 15;
    private final int ammoPercent = 12;
    private final int coinPercent = 32;
    private final int healthPercent = 6;

    private Texture ammoIMG;
    private Texture coinIMG;
    private Texture healthIMG;

    public Items() {
        ammoIMG = new Texture("Ammo_box_icon.png");
        coinIMG = new Texture("coin.png");
        healthIMG = new Texture("HealthPack-transparent.png");
        pickups = new ArrayList<Pickup>();
    }

    /**
     * method for removing all pickups
     */
    public void clear() {
        pickups.clear();
    }

    /**
     * generates a pickup randomly given x and y of killed zombie
     *
     * @param x the x component of the position
     * @param y the y component of the position
     */
    public void create(float x, float y) {
        int roll = (int) (Math.floor(Math.random() * 101.0));
        int type = 1;
        boolean drop = true;
        //decide type using variables for odds/100
        if ((roll - this.coinPercent) < 0) {
            type = 0; //coin type
        } else if ((roll - (this.coinPercent + this.ammoPercent)) < 0) {
            type = 1; //ammo type
        } else if ((roll - (this.coinPercent + this.ammoPercent + healthPercent)) < 0) {
            type = 2; //health type
        } else {
            drop = false;
        }
        if (drop == true) { //if the the roll was within the valid range
            Pickup a = new Pickup(x, y, type);
            pickups.add(a);
        }
    }

    /**
     * method to check collision of ammo based on distance to the player
     *
     * @param player the position of the player
     * @return the amount of ammo the player collected
     */
    public int updateAmmo(Vector2 player) {
        int collected = 0;
        for (int i = 0; i < pickups.size(); i++) {
            if (pickups.get(i).getType() == 0 && pickups.get(i).getActive() == true) {
                if (collect(player, pickups.get(i).getVector()) == true) {
                    pickups.get(i).deactivate();
                    collected += (int) Math.floor((Math.random() * (maxAmmo - minAmmo + 1)) + minAmmo);
                }
            }
        }
        return collected;
    }

    /**
     * method to check collision of coins based on distance to the player
     *
     * @param player the position of the player
     * @return the amount of coins the player collected
     */
    public int updateCoin(Vector2 player) {
        int collected = 0;
        for (int i = 0; i < pickups.size(); i++) {
            if (pickups.get(i).getType() == 1 && pickups.get(i).getActive() == true) {
                if (collect(player, pickups.get(i).getVector()) == true) {
                    pickups.get(i).deactivate();
                    collected += (int) Math.floor((Math.random() * (maxCoin - minCoin + 1)) + minCoin);
                }
            }
        }
        return collected;
    }

    /**
     * method to check collision of health packs based on distance to the player
     *
     * @param player the position of the player
     * @return the amount of health the player regains
     */
    public int updateHealth(Vector2 player) {
        int collected = 0;
        for (int i = 0; i < pickups.size(); i++) {
            if (pickups.get(i).getType() == 2 && pickups.get(i).getActive() == true) {
                if (collect(player, pickups.get(i).getVector()) == true) {
                    pickups.get(i).deactivate();
                    collected += this.healthpack;
                }
            }
        }
        return collected;
    }

    /**
     * method to remove any pickups that are deactivated from being collected
     */
    public void dispose() {
        for (int i = 0; i < pickups.size(); i++) {
            if (pickups.get(i).getActive() == false) {
                pickups.remove(i); // Removes the element at the specified position in this ArrayList.
            }
        }
    }

    /**
     * calculates the distance between two points and returns if it is within
     * collection range
     *
     * @param player the vector position of one side of the line segment
     * @param pickup the vector position of the other side of the line segment
     * @return is the pickup within reach of the player;
     */
    public boolean collect(Vector2 player, Vector2 pickup) {
        if (player.dst2(pickup) <= pickupDistance) {
            System.out.println("collected");
            return true;
        }
        return false;
    }

    public void draw(SpriteBatch batch) {
        //batch.begin();
        for (Pickup p : this.pickups) {
            switch (p.getType()) {
                case 0:
                    batch.draw(ammoIMG, p.getX(), p.getY(), 30, 30);
                    break;
                case 1:
                    batch.draw(coinIMG, p.getX(), p.getY(), 30, 30);
                    break;
                case 2:
                    batch.draw(healthIMG, p.getX(), p.getY(), 40, 40);
                    break;
                default:
            }
        }
        //batch.end();
    }
}

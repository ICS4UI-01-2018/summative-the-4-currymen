package com.summative4currymen.game;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 * this class manages all pickups and possibly special items
 *
 * @author goodm9679
 */
public class Items {

    private ArrayList<Pickup> pickups;
    private final float pickupDistance = 5;
    private static int maxAmmo;
    private static int minAmmo;
    private static int maxCoin;
    private static int minCoin;
    private final int ammoPercent = 25;
    private final int coinPercent = 75;

    public Items(int maxAmmo, int minAmmo, int maxCoin, int minCoin) {
        
    }

    public void clear() {
        pickups.clear();
    }
    
    public void create(float x, float y){
        int roll = (int)(Math.round(Math.random() * 100.0));
        int type = 1;
        if((roll - this.coinPercent) > 0){
            type = 0;
        }
        Pickup a = new Pickup(x,y,type);
        pickups.add(a);
    }

    public int updateAmmo(Vector2 player) {
        int collected = 0;
        for (int i = 0; i < pickups.size(); i++) {
            if (pickups.get(i).getType() == 0 && pickups.get(i).getActive() == true) {
                if (collect(player, pickups.get(i).getVector()) == true) {
                    pickups.get(i).deactivate();
                    collected += (int) ((Math.random() * (maxAmmo - minAmmo)) + minAmmo);
                }
            }
        }
        return collected;
    }

    public void dispose() {
        for (int i = 0; i < pickups.size(); i++) {
            if (pickups.get(i).getActive() == false) {
                pickups.remove(i); // Removes the element at the specified position in this ArrayList.
            }
        }
    }

    public boolean collect(Vector2 player, Vector2 pickup) {
        if (player.dst2(pickup) <= pickupDistance) {
            return true;
        }
        return false;
    }
}

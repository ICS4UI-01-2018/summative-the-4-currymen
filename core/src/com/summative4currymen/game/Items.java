package com.summative4currymen.game;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 *this class manages all pickups and possibly special items
 * 
 * @author goodm9679
 */
public class Items {
    private ArrayList<Pickup> pickups;
    private static float pickupDistance = 5;
    private static int maxAmmo;
    private static int minAmmo;
    private static int maxCoin;
    private static int minCoin;
    public Items(int maxAmmo,int minAmmo,int maxCoin,int minCoin){
        
    }
    
    public void clear(){
        
    }
    
    
    public int updatePickups(Vector2 player){
        int collected = 0;
        for(int i=0; i < pickups.size(); i++){
            if(collect(player, pickups.get(i).getVector()) == true){
                
            }
        }
    }
    
    public 
    
    
    public boolean collect(Vector2 player, Vector2 pickup){
        if(player.dst2(pickup) <= pickupDistance){
            return true;
        }
        return false;
    }
}

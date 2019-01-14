package com.summative4currymen.game;

import com.badlogic.gdx.math.Vector2;

/**
 * This class defines all pickups.
 * 
 * @author Matthew Goodman
 */
public class Pickup {
    
    private Vector2 position;
    private final int type;
    private boolean active;
     /**
      * initializes pickup
      * 
      * @param x the x position of the pickup
      * @param y the y position of the pickup
      * @param type the pickup type identifier
      */
    public Pickup(float x, float y, int type) {
        this.position.set(x, y);
        this.type = type; //0 ammo, 1 coin, 2 health
        this.active = true;
    }
    /**
     * method to get x component of vector coordinates
     * @return the x position of the pickup
     */
    public float getX() {
        return this.position.x;
    }
    /**
     * method to get y component of vector coordinates
     * @return the y position of the pickup
     */
    public float getY() {
        return this.position.y;
    }
    /**
     * method to get variant identifier
     * @return the type identifier of the pickup
     */
    public int getType(){
        return this.type;
    }
    /**
     * method to get vector coordinates
     * @return the vector position of the pickup
     */
    public Vector2 getVector() {
        return this.position;
    }
    /**
     * method to check if pickup is collected
     * @return if the pickup is still waiting to be collected
     */
    public boolean getActive() {
        return this.active;
    }
    /**
     * gives the pickup a flag to be removed en mass later.
     */
    public void deactivate() {
        this.active = false;
    }
}

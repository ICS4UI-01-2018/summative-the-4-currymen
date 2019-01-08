/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author goodm9679
 */
public class Pickup {

    private Vector2 position;
    private int type;
    private boolean active;

    public Pickup(float x, float y, int type) {
        this.position.set(x, y);
        this.type = type; //0 ammo, 1 coin
        this.active = true;
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }
    
    public int getType(){
        return this.type;
    }
    
    public Vector2 getVector() {
        return this.position;
    }
    
    public boolean getActive() {
        return this.active;
    }
    
    public void deactivate() {
        this.active = false;
    }
}

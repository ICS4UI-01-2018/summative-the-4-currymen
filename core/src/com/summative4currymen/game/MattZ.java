/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Subclass of Player class, this class represents the zombies
 *
 * @author goodm9679
 */
public class MattZ {

    private float speed;
    private Vector2 position;
    private Vector2 target;
    private Boolean alive;
    private int rotation;

    /**
     * Initializes Zombie data
     *
     * @param x The x position of the zombie
     * @param y The y position of the zombie
     * @param speed The speed the zombie will move at
     * @param damage The amount of damage the zombie can attack with
     */
    public MattZ(float x, float y, float speed) {
        this.position.set(x, y);
        this.speed = speed;
        alive = true;
    }

    public void nextTarget(Player p1, Player p2) {
        if (p1.getAlive() == true) {
            if (p2.getAlive() == true) {
                Vector2 vp1 = new Vector2(p1.getX(), p1.getY());
                Vector2 vp2 = new Vector2(p2.getX(), p2.getY());
                if(position.dst(vp1)>position.dst(vp2)){                     //if p2 closest
                    this.target.set(p2.getX()- position.x, p2.getY()-position.y); //set P2 target
                }else if(position.dst(vp1)<position.dst(vp2)){ //if p1 closest
                    this.target.set(p1.getX()- position.x, p1.getY()-position.y); //set P1 target
                }
            } else {
                this.target.set(p1.getX()- position.x, p1.getY()-position.y); //set P1 target
            }
        } else {
             this.target.set(p2.getX()- position.x, p2.getY()-position.y); //set P2 target
        }
    }
    
    public void move(){
        target.setLength (speed);
        position.add(target);
    }
    
    public float getX(){
        return position.x;
    }
    
    public float getY(){
        return position.y;
    }

    /**
     * This method is used to locate the zombie
     *
     * @return The position of the zombie
     */
    public Vector2 getPos() {
        return position;
    }

}

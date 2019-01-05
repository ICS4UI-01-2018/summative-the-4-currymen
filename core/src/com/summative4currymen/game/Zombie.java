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
    private int speed;
    
   // private Rectangle zombie;
    private String name;
    //private Boolean alive;
    private int rotation;
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
    public Zombie(float x, float y, int width, int height, int health, int speed, String name, int damage, int rotation) {
        super(x, y, width, height, health, speed, name);
        //this.speed = speed;
        this.damage = damage;
        this.rotation = rotation;
        //alive = true;
        //zombie = new Rectangle(x, y, width, height);
    }
    
    
    public int getHealth(){
        return super.getHealth();
    }   
    public int getRotation(){
        return this.rotation;
    }
    public void setRotation(int num){
        this.rotation = num; 
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
    public boolean collidesWith(Zombie zombie2){
        return super.collides(zombie2.getBounds());
    }
    
}

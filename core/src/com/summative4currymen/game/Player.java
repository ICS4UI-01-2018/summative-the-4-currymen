    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

/**
 * This class represents the player that will be used by the user
 *
 * @author nagra2700
 */
public class Player {
    private int speed;
    private int health;
    private Rectangle player;
    private String name;
    private Boolean alive;
    private int direction;
    private int width;
    private int height;
    private String equipped;
    private ArrayList<Weapon> weapons;
    
    
    /**
     * Initializes Player data
     * 
     * @param x The x position of the player
     * @param y The y position of the player
     * @param width The width of the player
     * @param height The height of the player
     * @param health The health the of the player
     * @param speed The speed the player will move at
     * @param name The name of the player
     */
    public Player(float x, float y, int width, int height, int health, int speed, String name) {
        this.speed = speed;
        this.health = health;
        alive = true;
        this.name = name;
        player = new Rectangle(x, y, width, height);
        this.width = width;
        this.height = height;
    }
    
    /**
     * 
     * 
     * @return current gun name
     */
    public String getEquipped(){
        return equipped;
    }
    
    /** 
     * 
     * setter for players gun
     * @param name 
     */
    public void setEquipped(String name){
        equipped = name;
    }
    
    /**
     * Using the speed integer to increase the player's y value
     */
    public void moveUp() {
        player.y = player.y + speed;
    }
    
    /**
     * Using the speed integer to decrease the player's x value
     */
    public void moveLeft() {
        player.x = player.x - speed;
    }
    
    /**
     * Using the speed integer to decrease the player's y value
     */
    public void moveDown() {
        player.y = player.y - speed;
    }
    
    /**
     * Using the speed integer to increase the player's x value
     */
    public void moveRight() {
        player.x = player.x + speed;
    }
    
    /**
     * 
     * 
     * @return 
     */
    public ArrayList getWeapons(){
        return weapons;
    }
    
    /**
     * The method when the player is being hit
     * 
     * @param damage the amount of damage the zombie is taking
     */
    public void hit(int damage){
        health = health - damage;
        if(health <= 0){
            alive = false;
            name = "dead";
        }
    }
    
    /**
     * A method to return the player's health
     * 
     * @return the integer health
     */
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int damage){
        this.health = this.health - damage;
    }
    
    /**
     * A method to check whether boolean alive is true or false
     * 
     * @return the boolean alive
     */
    public Boolean getAlive() {
        return alive;
    }
    
    /**
     * A method to find out what the x value is
     * 
     * @return the x value of the player
     */
    public float getX() {
        return player.x;
    }
    
    /**
     * A method to find out what the y value is
     * 
     * @return the x value of the player
     */
    public float getY() {
        return player.y;
    }
    
    /**
     * A method to find out what the player's speed is
     * 
     * @return the player's speed
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * A method to find out what the width of the player 
     * 
     * @return the width of the player
     */
    public int getWidth(){
        return this.width;
    }
    
    /**
     * A method to find out what the height of the player 
     * 
     * @return the height of the player
     */
    public int getHeight(){
        return this.height;
    }
}

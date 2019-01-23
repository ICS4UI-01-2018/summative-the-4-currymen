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

    private double speed;
    private int health;
    private Rectangle player;
    private String name;
    private boolean alive;
    private int direction;
    private int width;
    private int height;
    private String equipped;
    private int coins;
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
    public Player(float x, float y, int width, int height, int health, double speed, String name) {
        this.speed = speed;
        this.health = health;
        alive = true;
        this.name = name;
        player = new Rectangle(x, y, width, height);
        this.width = width;
        this.height = height;
        this.coins = 0;
    }
    
    /**
     * Checking to see whether the player is colliding with a rectangle
     * 
     * @param r A rectangle that is being compared to the player
     * @return True if the player is colliding with the rectangle otherwise return false
     */
    public boolean collides(Rectangle r) {
        return player.overlaps(r);
    }
    
    /**
     * Getter for the players x, y, width, and height
     * 
     * @return the x, y, width, and height
     */
    public Rectangle getBounds() {
        return this.player;
    }

    /**
     * Getter for which weapon the player has equipped
     *
     * @return current gun name
     */
    public String getEquipped() {
        return equipped;
    }

    /**
     * Setter for the Player's weapon
     *
     * @param name the name of the weapon the player holds
     */
    public void setEquipped(String name) {
        equipped = name;
    }

    /**
     * Using the speed integer to increase the player's y value
     */
    public void moveUp() {
        player.y = (float) (player.y + speed);
    }

    /**
     * Using the speed integer to decrease the player's x value
     */
    public void moveLeft() {
        player.x = (float) (player.x - speed);
    }

    /**
     * Using the speed integer to decrease the player's y value
     */
    public void moveDown() {
        player.y = (float) (player.y - speed);
    }

    /**
     * Using the speed integer to increase the player's x value
     */
    public void moveRight() {
        player.x = (float) (player.x + speed);
    }

    /**
     * The method when the player is being hit
     *
     * @param damage the amount of damage the player is taking
     * @return if the player is dead or not
     */
    public int hit(int damage) {
        health = health - damage;
        if (health <= 0) {
            this.die();
        }
        return this.health;
    }
    
    /**
     * Setter for that sets the player to not alive or dead
     */
    public void die() {
        this.alive = false;
    }
    
    /**
     * Getter for the players name
     * 
     * @return the players name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Setting the player to full health, alive, and randomizing their x and y positions
     */
    public void revive(){
        this.health = 100;
        this.alive = true;
        player.x = (int) Math.floor(Math.random() * 801);
        player.y = (int) Math.floor(Math.random() * 601);
    }
    
    /**
     * Getter for the amount of coins the player has
     * 
     * @return the amount of coins the player has
     */
    public int getCoins(){
        return this.coins;
    }
    
    /**
     * If the player can afford the item they are trying to buy, 
     * return true and subtract cost from coins.
     * otherwise it will return false
     * added by matt
     * 
     * @param cost the cost of the item
     * @return was the payment successful
     */
    public boolean pay(int cost){
        if((this.coins - cost) < 0){
            return false;
        }else{
            this.coins -= cost;
            return true;
        }
    }
    
    /**
     * Method to add coins from pickups
     * added by matt
     * 
     * @param n the number of coins to add
     */
    public void addCoins(int n){
        this.coins += n;
    }
    /**
     * removes all coins for a new game
     */
    public void removeCoins(){
        this.coins = 0;
    }
    /**
     * Getter that gets players health
     *
     * @return the integer health
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Setter for the players health
     * 
     * @param health the amount of the health that the player will have
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * A method to add health from health pickups
     * added by matt
     * 
     * @param health how much health to add
     */
    public void addHealth(int health){
        this.health += health;
        if(this.health > 100){
            this.health = 100;
        }
    }
    
    /**
     * A method to check whether boolean alive is true or false
     *
     * @return the boolean alive
     */
    public Boolean getAlive() {
        return this.alive;
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
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Setter for the players speed
     * 
     * @param s the speed the player will have
     */
    public void setSpeed(double s) {
        this.speed = s;
    }

    /**
     * A method to find out what the width of the player
     *
     * @return the width of the player
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * A method to find out what the height of the player
     *
     * @return the height of the player
     */
    public int getHeight() {
        return this.height;
    }
}

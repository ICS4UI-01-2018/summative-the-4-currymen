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
    }

    public boolean collides(Rectangle r) {
        return player.overlaps(r);

    }

    public Rectangle getBounds() {
        return this.player;
    }

    /**
     *
     *
     * @return current gun name
     */
    public String getEquipped() {
        return equipped;
    }

    /**
     * Setter for the Player's weapon
     *
     * @param name
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
     *
     *
     * @return
     */
    public ArrayList getWeapons() {
        return weapons;
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

    public void die() {
        this.alive = false;
    }
    
    public void revive(){
        this.health = this.health + 1;
        this.alive = true;
    }

    /**
     * A method to return the player's health
     *
     * @return the integer health
     */
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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
     * A method to set the players speed
     *
     *
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

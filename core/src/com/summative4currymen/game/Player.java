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
 *
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

    public Player(float x, float y, int width, int height, int health, int speed, String name) {
        this.speed = speed;
        this.health = health;
        alive = true;
        this.name = name;
        player = new Rectangle(x, y, width, height);
        this.width = width;
        this.height = height;
        
    }
    public String getEquipped(){
        return equipped;
    }
    
    public void setEquipped(String name){
        equipped = name;
    }

    public void moveUp() {
        player.y = player.y + speed;
    }

    public void moveLeft() {
        player.x = player.x - speed;
    }

    public void moveDown() {
        player.y = player.y - speed;
    }

    public void moveRight() {
        player.x = player.x + speed;
    }
    
    public ArrayList getWeapons(){
        return weapons;
    }
    

    public void die() {
        health = 0;
        alive = false;
        name = "dead";
    }

    public int getHealth() {
        return health;
    }
    
    public void setHealth(int damage){
        this.health = this.health - damage;        
    }

    public Boolean getAlive() {
        return alive;
    }

    public float getX() {
        return player.x;
    }

    public float getY() {
        return player.y;
    }

    public int getSpeed() {
        return speed;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public int getHeight(){
        return this.height;
    }

    public void draw(ShapeRenderer shapeBatch) {
        shapeBatch.rect(player.x, player.y, player.width, player.height);
    }
}

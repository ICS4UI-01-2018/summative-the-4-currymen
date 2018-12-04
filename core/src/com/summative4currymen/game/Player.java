/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

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
    
    
    public Player(int x, int y, int width, int height, int speed, int health, String name){
        this.speed = speed;
        this.health = health;
        alive = true;
        this.name = name;
        player = new Rectangle(x, y, width, height);
    }
    
    public void moveUp(){
        player.y = player.y + speed;
    }
    
    public void moveLeft(){
        player.x = player.x - speed;
    }
    
    public void moveDown(){
        player.y = player.y - speed;
    }
    
    public void moveRight(){
        player.x = player.x + speed;
    }
    
    public void die(){
        health = 0;
        alive = false;
        name = "dead";
    }
    
    public int getHealth(){
        return health;
    }
    
    public Boolean getAlive(){
        return alive;
    }
    
    public Float getX(){
        return player.x;
    }
    
    public Float getY(){
        return player.y;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public void draw(ShapeRenderer shapeBatch){
        shapeBatch.rect(player.x, player.y, player.width, player.height);
    }
}

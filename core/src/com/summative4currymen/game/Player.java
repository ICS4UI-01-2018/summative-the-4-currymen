/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author nagra2700
 */
public class Player {
    private int speed;
    private int health;
    private Rectangle player;
    private String name;
    
    
    public Player(int x, int y, int width, int height, int speed){
        this.speed = speed;
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
        name = "dead";
    }
    
    
    
}

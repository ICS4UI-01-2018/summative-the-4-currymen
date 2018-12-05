/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrlamont.mygame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author lamon
 */
public class Paddle {
    private Rectangle player;
    private int speed;
    
    public Paddle(int x, int y, int width, int height, int speed){
        this.speed = speed;
        player = new Rectangle(x,y,width,height);
    }
    
    public Rectangle getBounds(){
        return player;
    }
    
    public void moveUp(){
        player.y = player.y + speed;
    }
    
    public void moveDown(){
        player.y = player.y - speed;
    }
    
    public float getLeft(){
        return player.x;
    }
    
    public float getBottom(){
        return player.y;
    }
    
    public float getRight(){
        return player.x + player.width;
    }
    
    public float getTop(){
        return player.y + player.height;
    }
    
    public void draw(ShapeRenderer shapeBatch){
        shapeBatch.rect(player.x, player.y, player.width, player.height);
    }
    
}

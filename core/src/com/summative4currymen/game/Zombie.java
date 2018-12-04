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
 * @author riepj9547
 */
public class Zombie extends Player{
    private int speed;
    private int health;
    private Rectangle zombie;
    private String name;
    private Boolean alive;
    private int direction;
    private int damage;
    
    public Zombie(int x, int y, int width, int height, int health, int speed, String name, int damage){
        super(x, y, width, height, health, speed, name);
        this.damage = damage;
    }
    
    public void move(Player player) {
        // checking to see if the zombie is moveable
        if(alive == false){
            // if the zombies x value is bigger than the players x value
            if(zombie.x > player.getX()){
                // the zombies x value decreases using the speed integer
                zombie.x = zombie.x - speed;
            // if the zombies x value is less than the players x value  
            }else if(zombie.x < player.getX()){
                // the zombies x value increases using the speed integer
                zombie.x = zombie.x + speed;
            // if the zombies x value is equal the players x value than nothing changes
            }else if(zombie.x == player.getX()){
            }
            // if the zombies y value is bigger than the players y value
            if(zombie.y > player.getY()){
                // the zombies y value decreases using the speed integer
                zombie.y = zombie.y - speed;
            // if the zombies y value is less than the players y value
            }else if(zombie.y < player.getY()){
                // the zombies y value increases using the speed integer
                zombie.y = zombie.y + speed;
            // if the zombies y value is equal to the players y value than nothing changes      
            }else if(zombie.y == player.getY()){
            }
        }else{
        }
    }
    
    public Rectangle getBounds(){
        return zombie;
    }
    
    @Override
    public void spawn(ShapeRenderer shapeBatch){
        shapeBatch.rect(zombie.x, zombie.y, zombie.width, zombie.height);
    }
    
    public int attack(){
        return this.damage;
    }
}


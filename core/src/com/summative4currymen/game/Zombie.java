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
public class Zombie extends Player {

    private float speed;
    private int health;
    private Rectangle zombie;
    private String name;
    private Boolean alive;
    private int direction;
    private int damage;

    public Zombie(float x, float y, int width, int height, int health, float speed, String name, int damage) {
        super(x, y, width, height, health,speed, name);
        
        this.damage = damage;
        alive = true;
        zombie = new Rectangle(x, y, width, height);
    }
    
    public Rectangle getBounds() {
        return zombie;
    }
    
    public void hit(int damage){
        this.health =- damage;
        if(this.health <= 0){
            alive = false;
        }
    }
    
    @Override
    public void draw(ShapeRenderer shapeBatch){
        shapeBatch.rect(zombie.x, zombie.y, zombie.width, zombie.height);
    }
    

    public int attack() {
        return damage;
    }
    
    
    
    
}

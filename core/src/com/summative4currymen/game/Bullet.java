/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * This class represents the bullet that will be shot by the user and the weapon in hand
 *
 * @author riepj9547
 */
public class Bullet {

    private Rectangle bullet;
    private int speed;
    private float dx;
    private float dy;
    private int damage;    
    private boolean spawned;
    
    /**
     * Initializes Bullet data
     * 
     * @param x The x position of the bullet
     * @param y The y position of the bullet
     * @param width The width of the bullet
     * @param height The height of the bullet
     * @param speed The speed the bullet will move at
     * @param the x direction the bullet is going in
     * @param the y direction the bullet is going in
     */
    public Bullet(float x, float y, int width, int height, int speed, int damage, float dx, float dy) {
        this.speed = speed;
        this.damage = damage;
        bullet = new Rectangle(x, y, width, height);
        bullet.x = x;
        bullet.y = y;
        this.dx = dx;
        this.dy = dy;
        spawned = false;
    }

    /**
    * Moving the Bullet's x and y position using the speed integer and dx and dy integer
    */
    public void bulletMovement() {
        bullet.x = bullet.x + speed *dx;
        bullet.y = bullet.y + speed *dy;
    }
    
    /**
    * Setter for the Bullet setting it so the Bullet has spawned in
    */
    public void spawnedIn(){
        this.spawned = true;
    }  
    
    /**
    * Getter for whether or not the Bullet spawned in
    *
    * @return whether or not the Bullet spawned
    */
    public boolean getSpawned(){
        return this.spawned;
    }
    
    /**
    * Getter for the x position of the Bullet or the left side of the bullet
    *
    * @return the x position of the Bullet
    */
    public float getLeft() {
        return bullet.x;
    }
    
    /**
    * Getter for the y position of the Bullet or the bottom of the Bullet
    *
    * @return the y position of the Bullet
    */
    public float getBottom() {
        return bullet.y;
    }
    
    // there is genuinely no need for this
    public float getX(){
        return bullet.x;
    }
    
    // genuinely no need other than the fact it has a different name 
    public float getY(){
        return bullet.y;
    }
    
    /**
    * Getter for the right side of the bullet
    *
    * @return the Bullet's x position plus the Bullet's width
    */ 
    public float getRight() {
        return bullet.x + bullet.width;
    }
    
    /**
    * Getter for the top of the bullet
    *
    * @return the Bullet's y position plus the height
    */
    public float getTop() {
        return bullet.y + bullet.height;
    }
    
    /**
    * A method for drawing the bullet a specific color
    *
    * @param shapeBatch  
    */
    public void drawBullet(ShapeRenderer shapeBatch) {
        shapeBatch.setColor(Color.YELLOW);
        shapeBatch.circle(bullet.x + (45/2), bullet.y+(45/2), bullet.width/2 /*bullet.height*/);
    }
    
    /**
    * Getter that checks whether or not a zombie and the Bullet is colliding
    *
    * @param b a zombie whom is being checked to see whether or not the Bullet is colliding with it
    * @return whether or not the Bullet is colliding with the zombie
    */
    public boolean collidesWith(Zombie b){
        return bullet.overlaps(b.getBounds());
    }

}

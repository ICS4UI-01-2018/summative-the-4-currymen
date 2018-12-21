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

    public void bulletMovement() {
        bullet.x = bullet.x + speed *dx;
        bullet.y = bullet.y + speed *dy;
    }
    
    
    
    public void spawnedIn(){
        this.spawned = true;
    }  
    public boolean getSpawned(){
        return this.spawned;
    }
    
    public float getLeft() {
        return bullet.x;
    }

    public float getBottom() {
        return bullet.y;
    }
    
    public float getX(){
        return bullet.x;
    }
    
    public float getY(){
        return bullet.y;
    }

    public float getRight() {
        return bullet.x + bullet.width;
    }

    public float getTop() {
        return bullet.y + bullet.height;
    }

    public void drawBullet(ShapeRenderer shapeBatch) {
        shapeBatch.setColor(Color.YELLOW);
        shapeBatch.circle(bullet.x + (45/2), bullet.y+(45/2), bullet.width/2 /*bullet.height*/);
    }
    
    public boolean collidesWith(Zombie b){
        return bullet.overlaps(b.getBounds());
    }

}

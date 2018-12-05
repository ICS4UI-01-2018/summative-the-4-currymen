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
 * @author riepj9547
 */
public class Bullet {

    private Rectangle bullet;
    private int speed;
    private int dx;
    private int dy;
    private int damage;
    private int direction;

    public Bullet(int x, int y, int width, int height, int speed, int damage) {
        this.speed = speed;
        this.damage = damage;
        bullet = new Rectangle(x, y, width, height);
        bullet.x = 1;
        bullet.y = 1;
    }

    public void bulletMovement() {
        bullet.x = bullet.x + speed * dx;
        bullet.y = bullet.y + speed * dy;
    }

    public float getLeft() {
        return bullet.x;
    }

    public float getBottom() {
        return bullet.y;
    }

    public float getRight() {
        return bullet.x + bullet.width;
    }

    public float getTop() {
        return bullet.y + bullet.height;
    }

    private void drawBullet(ShapeRenderer shapeBatch) {
        shapeBatch.rect(bullet.x, bullet.y, bullet.width, bullet.height);
    }
    
    public boolean collidesWith(Zombie b){
        return bullet.overlaps(b.getBounds());
    }

}

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

    private int speed;
    private int health;
    private Rectangle zombie;
    private String name;
    private Boolean alive;
    private int direction;
    private int damage;
    Vector position;
    Vector velocity;

    public Zombie(Vector position, Vector velocity, float x, float y, int width, int height, int health, int speed, String name, int damage) {
        super(x, y, width, height, health, speed, name);
        this.speed = speed;
        this.damage = damage;
        alive = true;
        zombie = new Rectangle(x, y, width, height);
        this.position = position;
        this.velocity = velocity;
    }
    
    public Rectangle getBounds() {
        return zombie;
    }
    
    public void hit(int damage){
        this.health =- damage;
        if(this.health<= 0){
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
    
    public void updateVelocity(Zombie[] zombie, int xMax, int yMax, double cohesionCoefficient, int alignmentCoefficient, double separationCoefficient) {
        velocity = velocity.plus(cohesion(zombie,  cohesionCoefficient))
                           .plus(alignment(zombie, alignmentCoefficient))
                           .plus(separation(zombie, separationCoefficient))
                           .plus(boundPosition(xMax, yMax));
        limitVelocity();
    }
    
    public void updatePosition() {
        position = position.plus(velocity);
    } 
    
    //alignment - steer towards the average heading of local flockmates
    public Vector alignment(Zombie[] zombie, int alignmentCoefficient) {
        Vector pvJ = new Vector(0,0);  
        int length = zombie.length;
        for (int i = 0; i < length; i++)  
            pvJ = pvJ.plus(zombie[i].velocity);
        pvJ = pvJ.div(length);
        return pvJ.minus(velocity).div(alignmentCoefficient);
    }  
    //rules that determine flock's behaviour
    //are all to apply on bird's velocity
    
    //cohesion - steer towards the center of mass of local flockmates
    public Vector cohesion(Zombie[] zombie, double cohesionCoefficient) {    
        Vector pcJ = new Vector(0,0);
        int length = zombie.length;
        for (int i = 0; i < length; i++)   
            pcJ = pcJ.plus(zombie[i].position);
        pcJ = pcJ.div(length);
        return pcJ.minus(position).div(cohesionCoefficient);
    }  
    
    //separation - steer to avoid crowding local flockmates
    public Vector separation(Zombie[] zombie, double separationCoefficient) {
        Vector c = new Vector(0,0);
        int length = zombie.length;
        for (int i = 0; i < length; i++)  
            if ((zombie[i].position.minus(position).magnitude()) < separationCoefficient)
            c = c.minus(zombie[i].position.minus(position));
        return c;
    }  
    
    //keep birds within a certain area
    public Vector boundPosition(int xMax, int yMax) {
        int x = 0;
        int y = 0;
        if (this.position.data[0] < 0)                x = 10;
        else if (this.position.data[0]  > xMax)       x = -10;
        if (this.position.data[1]  < 0)               y = 10;
        else if (this.position.data[1]  > yMax)       y = -10;
        return new Vector(x,y);
    }
    
    //limit the magnitude of the boids' velocities
    public void limitVelocity() {
        int vlim = 100;
        if (this.velocity.magnitude() > vlim) {
            this.velocity = this.velocity.div(this.velocity.magnitude());
            this.velocity = this.velocity.times(vlim);
        }
    }
 
    public String toString() {
        return new String("Position: " + this.position + " Velocity: " + this.velocity);
    }
    
    
}

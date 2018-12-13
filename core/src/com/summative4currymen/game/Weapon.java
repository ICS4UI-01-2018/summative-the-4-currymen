/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;

/**
 *
 * @author riepj9547
 */
public class Weapon {
    private int damage;
    private float x;
    private float y;
    private int bulletSpeed;
    private int fireRate;
    private String name;
    private Texture gun1Texture;
    
    
    public Weapon(String name, int bulletSpeed, int fireRate, int damage, float x, float y){
        this.name = name;
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.fireRate = fireRate;
        this.bulletSpeed = bulletSpeed;
        gun1Texture = new Texture("gun1.png");
        
    }
    public String getName(){
        return this.name;
    }
    
    public int bulletSpeed(){
        return this.bulletSpeed;
    }
    
    public int fireRate(){
        return this.fireRate;
    }
    
    public int damage(){
        return this.damage;
    }
    
    public float getX(){
        return this.x;
    }
    
    public float getY(){
        return this.y;
    }   
    
    public void drawWeapon(SpriteBatch batch) {        
        batch.draw(gun1Texture,this.x,this.y, 55,55);
    }
    
    public Bullet shootWeapon(String name, int roation, float x, float y, int bulletSpeed, int damage, int fireRate){
          
            if(roation == 0){ 
            Bullet b = new Bullet((int)x, (int)y, 10, 10, bulletSpeed, damage, 1, 0);            
                System.out.println("Made bullet");                
            return b;                
            }
            if(roation == 45){ 
            Bullet b = new Bullet((int)x, (int)y, 10, 10, bulletSpeed, damage, 1, 1);           
                System.out.println("Made bullet");                
            return b;                
            }
            if(roation == 90){ 
            Bullet b = new Bullet((int)x, (int)y, 10, 10, bulletSpeed, damage, 0, 1);            
                System.out.println("Made bullet");                
            return b;                
            }
            if(roation == 135){ 
            Bullet b = new Bullet((int)x, (int)y, 10, 10, bulletSpeed, damage, -1, 1);           
                System.out.println("Made bullet");                
            return b;                
            }
            if(roation == 180){ 
            Bullet b = new Bullet((int)x, (int)y, 10, 10, bulletSpeed, damage, -1, 0);           
                System.out.println("Made bullet");                
            return b;                
            }
            if(roation == 225){ 
            Bullet b = new Bullet((int)x, (int)y, 10, 10, bulletSpeed, damage, -1, -1);           
                System.out.println("Made bullet");                
            return b;                
            }
            if(roation == 270){ 
            Bullet b = new Bullet((int)x, (int)y, 10, 10, bulletSpeed, damage, 0, -1);           
                System.out.println("Made bullet");                
            return b;                
            }
            if(roation == 315){ 
            Bullet b = new Bullet((int)x, (int)y, 10, 10, bulletSpeed, damage, 1, -1);           
                System.out.println("Made bullet");                
            return b;                
            }        
        
        System.out.println("No gun was found");
        
        return null;        
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

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
    
    public Weapon(String name, int bulletSpeed, int fireRate, int damage, float x, float y){
        this.name = name;
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.fireRate = fireRate;
        this.bulletSpeed = bulletSpeed;
    }
    
    
}

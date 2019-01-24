/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;


import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Jared
 */
public class Furniture {
    public Rectangle f;  
    
    private String name;
    /**
     * create a furniture object
     * @param name of object
     * @param x position
     * @param y position
     * @param width 
     * @param height 
     */
    public Furniture(String name ,float x, float y, float width, float height){
        this.name = name;
        f = new Rectangle(x, y, 0, 0);
        f.x = x;
        f.y = y;      
        f.width = width;
        f.height = height;
    }
    /**
     * getter for the furniture
     * @return the name
     */
    public String getName(){
        return this.name;
    }
    
}

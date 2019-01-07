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
    public Furniture(String name ,float x, float y, float width, float height){
        this.name = name;
        f = new Rectangle(x, y, 0, 0);
        f.x = x;
        f.y = y;        
        if(name.equals("chair")){
            f.width = 30;
            f.height = 30;
        }else if(name.equals("couch")){
            f.width = 100;
            f.height = 45;
        }else if(name.equals("small table")){
            f.width = 60;
            f.height = 60;
        }else if(name.equals("big table")){
            f.width = 70;
            f.height = 150;
        }else if(name.equals("bed")){
            f.width = 70;
            f.height = 140;
        }else if(name.equals("tv")){
            f.width = 55;
            f.height = 10;
        }else if(name.equals("carpet")){
            f.width = 90;
            f.height = 125;
        }else if(name.equals("cactus")){
            f.width = width;
            f.height = height;
        }else if(name.equals("block")){
            f.width = width;
            f.height = height;
        }
    }
    public String getName(){
        return this.name;
    }
    
}

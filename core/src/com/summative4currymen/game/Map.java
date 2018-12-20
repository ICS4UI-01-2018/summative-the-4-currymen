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
public class Map {
    private Rectangle shape;
    
    public Map(){
        
    }
    
    public void draw(ShapeRenderer shapeBatch){
        shapeBatch.rect(shape.x, shape.y, shape.width, shape.height);
    }
}

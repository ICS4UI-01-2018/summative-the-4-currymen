package com.summative4currymen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author goodm9679
 */
public class HUD {
    private Player p1;
    private Player p2;
    private float width;
    
    public HUD(Player p1, Player p2, float width){
        this.p1 = p1;
        this.p2 = p2;
        this.width = width;
    }
    public void draw(ShapeRenderer shapeBatch){ //draw the HUD at 0,0
        shapeBatch.setColor(Color.DARK_GRAY);
        
        if(p1.getHealth()>= 50){ //deciding the color of the box for p1
            shapeBatch.setColor((50-(p1.getHealth()-50))/50f, 1f, 0f,1);
        }else{
            shapeBatch.setColor(1f, p1.getHealth()/50f, 0f,1);
        }
        shapeBatch.rect(0, 0, this.width, 90);
        shapeBatch.setColor(150/255f, 42/255f, 212/255f,1);
        
    }
    public void update(Player p1, Player p2){ //update the on screen graphic with current variables
        this.p1 = p1;
        this.p2 = p2;
    }
}

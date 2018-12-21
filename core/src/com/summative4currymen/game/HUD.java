package com.summative4currymen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author goodm9679
 */
public class HUD {
    private Player p1;
    private Player p2;
    private float width;
    private BitmapFont font;
    
    public HUD(Player p1, Player p2, float width){
        this.p1 = p1;
        this.p2 = p2;
        this.width = width;
    }
    public void draw(ShapeRenderer shapeBatch, SpriteBatch batch){ //draw the HUD at 0,0
        //font setup
        FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("Xcelsion Italic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size = 17;
        font = g.generateFont(p);
        g.dispose();
        //end of font setup
        font.setColor(Color.BLACK);
        shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
        shapeBatch.setColor(Color.DARK_GRAY);
        shapeBatch.rect(0, 0, this.width, 90);
        if(p1.getHealth()>= 50){ //deciding the color of the box for p1
            shapeBatch.setColor((50-(p1.getHealth()-50))/50f, 1f, 0f,1);
        }else{
            shapeBatch.setColor(1f, p1.getHealth()/50f, 0f,1);
        }
        shapeBatch.rect(8, 8, (this.width/2)-16, 74);
        
        if(p2.getHealth()>= 50){ //deciding the color of the box for p1
            shapeBatch.setColor((50-(p2.getHealth()-50))/50f, 1f, 0f,1);
        }else{
            shapeBatch.setColor(1f, p2.getHealth()/50f, 0f,1);
        }
        shapeBatch.rect((this.width/2)+8, 8, (this.width/2)-16, 74);
        shapeBatch.end();
        
        //batch.begin();
        //font.draw(batch, "John", 50, 95);
        //batch.end();
    }
    public void update(Player p1, Player p2){ //update the on screen graphic with current variables
        this.p1 = p1;
        this.p2 = p2;
    } 
}

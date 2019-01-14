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
    private Player player;
    private float width;
    private BitmapFont font;
    
    public HUD(float width){
        this.width = width;
        
                //font setup
        FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("Xcelsion Italic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size = 17;
        font = g.generateFont(p);
        g.dispose();
    }
    public void draw(ShapeRenderer shapeBatch, SpriteBatch batch, Player player){ //draw the HUD at 0,0

        font.setColor(Color.BLACK);
        shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
        shapeBatch.setColor(Color.DARK_GRAY);
        shapeBatch.rect(0, 0, this.width, 90);
        if(player.getHealth()>= 50){ //deciding the color of the box for player
            shapeBatch.setColor((50-(player.getHealth()-50))/50f, 1f, 0f,1);
        }else{
            shapeBatch.setColor(1f, player.getHealth()/50f, 0f,1);
        }
        shapeBatch.rect(8, 8, ((this.width)-16)*(player.getHealth()/100), 30);
        shapeBatch.rect(8, 8, (this.width)-8, 30);
        shapeBatch.end();
        
        //batch.begin();
        //font.draw(batch, "John", 50, 95);
        //batch.end();
    }
}

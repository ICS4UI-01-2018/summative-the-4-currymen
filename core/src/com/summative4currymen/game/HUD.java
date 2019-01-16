package com.summative4currymen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author goodm9679
 */
public class HUD {

    
    private float width;
    private BitmapFont font;

    public HUD(float width) {
        this.width = width;

        //font setup
        FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("Xcelsion Italic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size = 17;
        font = g.generateFont(p);
        g.dispose();
    }

    public void draw(ShapeRenderer shapeBatch, SpriteBatch batch, Player p1,OrthographicCamera cam) { //draw the HUD at 0,0
        
        shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
        shapeBatch.setColor(Color.DARK_GRAY);
        shapeBatch.rect(cam.position.x-(cam.viewportWidth/2), cam.position.y-(cam.viewportHeight/2), this.width, 90);
        if (p1.getHealth() >= 50) { //deciding the color of the box for player
            shapeBatch.setColor((50 - (p1.getHealth() - 50)) / 50f, 1f, 0f, 1);
        } else {
            shapeBatch.setColor(1f, p1.getHealth() / 50f, 0f, 1);
        }
        shapeBatch.rect(cam.position.x-(cam.viewportWidth/2)+8, cam.position.y-(cam.viewportHeight/2)+8, ((this.width) - 16)*(p1.getHealth()/100.0f), 30);
        if(p1.getEquipped() == "ShotGun"){
            //draw shotgun
        }else if(p1.getEquipped() == "Barret50"){
            //draw barret
        }
        shapeBatch.end();

        batch.begin();
        font.setColor(Color.GOLD);
        font.draw(batch, "$" + p1.getCoins(), cam.position.x-(cam.viewportWidth/2) + 310, cam.position.y-(cam.viewportHeight/2) + 70);
        
        batch.end();
    }
    
}


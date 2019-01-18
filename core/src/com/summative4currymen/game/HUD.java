package com.summative4currymen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    
    private Texture shotgunIMG;
    private Texture barretIMG;
    private Texture akIMG;

    public HUD(float width) {
        this.width = width;

        //font setup
        FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("Xcelsion Italic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size = 17;
        font = g.generateFont(p);
        g.dispose();
        shotgunIMG = new Texture("shotgun.png");
        barretIMG = new Texture("RSASS_Sideview.png");
        akIMG = new Texture("ak.png");
    }
    /**
     * 
     * @param shapeBatch used for drawing shapes like health bar
     * @param batch used for drawing images and font
     * @param p1 the player referenced for drawing
     * @param cam the position of the camera in the map
     */
    public void draw(ShapeRenderer shapeBatch, SpriteBatch batch, Player p1,OrthographicCamera cam) { //draw the HUD at 0,0
        
        shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
        shapeBatch.setColor(Color.DARK_GRAY);
        shapeBatch.rect(cam.position.x-(cam.viewportWidth/2), cam.position.y-(cam.viewportHeight/2), this.width, 90);
        if (p1.getHealth() >= 50) { //deciding the color of the healthbar
            shapeBatch.setColor((50 - (p1.getHealth() - 50)) / 50f, 1f, 0f, 1);
        } else {
            shapeBatch.setColor(1f, p1.getHealth() / 50f, 0f, 1);
        }
        shapeBatch.rect(cam.position.x-(cam.viewportWidth/2)+8, cam.position.y-(cam.viewportHeight/2)+8, ((this.width) - 16)*(p1.getHealth()/100.0f), 30);
        
        shapeBatch.end();

        batch.begin();
        //which gun to draw 
        if(p1.getEquipped() == "ShotGun"){
            batch.draw(shotgunIMG, cam.position.x-(cam.viewportWidth/2) + 12, cam.position.y-(cam.viewportHeight/2) + 50, 100, 40);
        }else if(p1.getEquipped() == "Barret50"){
            batch.draw(barretIMG, cam.position.x-(cam.viewportWidth/2) + 12, cam.position.y-(cam.viewportHeight/2) + 50, 100, 40);
        }else if(p1.getEquipped() == "AK-47"){
            batch.draw(akIMG, cam.position.x-(cam.viewportWidth/2) + 12, cam.position.y-(cam.viewportHeight/2) + 50, 100, 50);
        }
        font.setColor(Color.GOLD);
        font.draw(batch, "$" + p1.getCoins(), cam.position.x-(cam.viewportWidth/2) + 310, cam.position.y-(cam.viewportHeight/2) + 70);
        batch.end();
    }
    
    public void pressE(SpriteBatch batch, Player p1,OrthographicCamera cam){
        batch.begin();
        font.setColor(Color.GOLD);
        font.draw(batch, "press E to continue", cam.position.x-(cam.viewportWidth/2) + 40, cam.position.y-(cam.viewportHeight/2) + 500);
        batch.end();
    }
    
    
}


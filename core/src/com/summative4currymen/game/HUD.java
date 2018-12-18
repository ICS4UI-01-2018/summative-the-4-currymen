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
    private int width;
    public HUD(Player p1, Player p2, int width){
        this.p1 = p1;
        this.p2 = p2;
        this.width = width;
    }
    public void draw(ShapeRenderer shapeBatch){
        shapeBatch.setColor(Color.DARK_GRAY);
        shapeBatch.rect(0, 20, this.width, 50);
    }
}


package com.summative4currymen.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Scene{
	private SpriteBatch batch;
        private ShapeRenderer shapeBatch;
        private Texture obstacle1;
        private Texture obstacle2;
        private Texture obstacle3;
        private Texture obstacle4;
        
	

	
	public void draw (SpriteBatch batch) {
            obstacle1 = new Texture("Conrete_Roof.jpg");
                obstacle2 = new Texture("Conrete_Roof.jpg");
                obstacle3 = new Texture("Conrete_Roof.jpg");
                obstacle4 = new Texture("Conrete_Roof.jpg");
                batch.draw(obstacle1, 20,20,20,20);
                batch.draw(obstacle2, 600, 400, 20,20);
                batch.draw(obstacle3, 20, 400, 20,20);
                batch.draw(obstacle4, 600, 20, 20,20);    
	}
	
	
	
        
       
        
        /*public FitViewport getBounds() {
        return this.viewport;
        }*/
}

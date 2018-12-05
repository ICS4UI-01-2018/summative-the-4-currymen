
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
                
                
                
                cam = new OrthographicCamera();
                viewport = new FitViewport(800,600,cam);
                viewport.apply();
                
                cam.position.x = 400;
                cam.position.y = 300;
                cam.update();
                
	}

	
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
                
                
                shapeBatch.setProjectionMatrix(cam.combined);
                shapeBatch.begin(ShapeType.Filled);
                shapeBatch.setColor(Color.MAROON);
                shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
                
                shapeBatch.setColor(Color.WHITE);
                obstacle1.draw(shapeBatch);
                player2.draw(shapeBatch);
               
                
                shapeBatch.end();
                
                batch.setProjectionMatrix(cam.combined);
                batch.begin();
                batch.draw(obstacle1, 20,20,20,20);
                batch.draw(obstacle2, 600, 400, 20,20);
                batch.draw(obstacle3, 20, 400, 20,20);
                batch.draw(obstacle4, 600, 20, 20,20);    
	}
	
	
	
        
       
        
        /*public FitViewport getBounds() {
        return this.viewport;
        }*/
}

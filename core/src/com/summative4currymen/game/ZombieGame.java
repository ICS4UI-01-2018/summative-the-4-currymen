package com.summative4currymen.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class ZombieGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
        private Texture menuPic;
        private Texture startButton;
        private Player player1;
        private Player player2;
        private OrthographicCamera cam;
        private ShapeRenderer shapeBatch;
        private FitViewport viewport;
        
        private boolean startGame;
	
	@Override
	public void create () {
                startGame = true;
            
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
                menuPic = new Texture("MenuPic.jpg");
                startButton = new Texture("StartButton.png");

                cam = new OrthographicCamera();
                viewport = new FitViewport(800,600,cam);
                viewport.apply();
                
                cam.position.x = 400;
                cam.position.y = 300;
                cam.update();
                
                player1 = new Player();
                player2 = new Player();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                //if the game has not started yet, draw in the main menu
                if(startGame == false){
		shapeBatch.setProjectionMatrix(cam.combined);
                shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
                batch.begin();
                //the menu picture
		batch.draw(menuPic,0,0,viewport.getWorldWidth(),viewport.getWorldHeight());                
		batch.end();                
                //if the gmae has begon draw in the game
                }else if(startGame == true){
                shapeBatch.setProjectionMatrix(cam.combined);
                shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
                batch.begin();
                shapeBatch.rect(0, 0, viewport.getWorldWidth(),viewport.getWorldHeight());
                batch.end();
                }
                
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}

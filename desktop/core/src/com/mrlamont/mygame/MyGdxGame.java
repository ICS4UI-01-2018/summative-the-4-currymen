package com.mrlamont.mygame;

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

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
        private ShapeRenderer shapeBatch;
        private Texture ballPic;
        private Paddle player1;
        private Paddle player2;
        private Ball ball;
        private OrthographicCamera cam;
        private FitViewport viewport;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();
                
                ballPic = new Texture("badlogic.jpg");
                
                cam = new OrthographicCamera();
                viewport = new FitViewport(800,600,cam);
                viewport.apply();
                
                cam.position.x = 400;
                cam.position.y = 300;
                cam.update();
                
                player1 = new Paddle(10, 250, 25, 100,2);
                player2 = new Paddle(765, 250, 25, 100, 2);
                ball = new Ball(390,290,20,20,2);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
                if(Gdx.input.isKeyPressed(Input.Keys.W)){
                   player1.moveUp();
                }
                if(Gdx.input.isKeyPressed(Input.Keys.S)){
                   player1.moveDown();
                }
                
                if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                   player2.moveUp();
                }
                if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                   player2.moveDown();
                }
                
                ball.move();
                
                if(ball.collidesWith(player1)){
                    ball.bounceX();
                }else if(ball.collidesWith(player2)){
                    ball.bounceX();
                }
                
                
                if(ball.getTop() > 600 || ball.getBottom() < 0){
                    ball.bounceY();
                }
                if(ball.getLeft() < 0 || ball.getRight() > 800){
                    ball.bounceX();
                }
                
                shapeBatch.setProjectionMatrix(cam.combined);
                shapeBatch.begin(ShapeType.Filled);
                shapeBatch.setColor(Color.FOREST);
                shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
                
                shapeBatch.setColor(Color.WHITE);
                player1.draw(shapeBatch);
                player2.draw(shapeBatch);
               
                
                shapeBatch.end();
                
                batch.setProjectionMatrix(cam.combined);
                batch.begin();
                batch.draw(ballPic, ball.getLeft(), ball.getBottom(),20,20);
                batch.end();
                
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		
	}
        
        @Override
        public void resize(int width, int height){
            viewport.update(width, height);
        }
}

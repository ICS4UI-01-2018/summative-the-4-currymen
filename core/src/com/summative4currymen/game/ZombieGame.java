package com.summative4currymen.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class ZombieGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private ShapeRenderer shapeBatch;
    private OrthographicCamera cam;
    private FitViewport viewport;
    private Player player1;
    private Player player2;
    private Texture img;
    private Texture menuPic;
    private Texture startButton;
    private Texture chr1IMG;
    private Scene wordlMap;

    private Texture obstacle1;
    private Texture obstacle2;
    private Texture obstacle3;
    private Texture obstacle4;

    private boolean startGame;

    private Vector3 touch = new Vector3(0, 0, 0);

    @Override
    public void create() {
        startGame = false;
        shapeBatch = new ShapeRenderer();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        menuPic = new Texture("MenuPic.jpg");
        startButton = new Texture("StartButton.png");
        chr1IMG = new Texture("character1.png");

        obstacle1 = new Texture("Concrete_Roof.jpg");
        obstacle2 = new Texture("Concrete_Roof.jpg");
        obstacle3 = new Texture("Concrete_Roof.jpg");
        obstacle4 = new Texture("Concrete_Roof.jpg");

        cam = new OrthographicCamera();
        viewport = new FitViewport(800, 600, cam);
        viewport.apply();

        cam.position.x = 400;
        cam.position.y = 300;
        cam.update();
        player1 = new Player(400, 300, 45, 45, 2, 100, "Rick");
        player2 = new Player(450, 350, 45, 20, 2, 100, "Carl");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //if the game has not started yet, draw in the main menu
        if (startGame == false) {
            shapeBatch.setProjectionMatrix(cam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            //the menu picture
            shapeBatch.setColor(Color.GOLD);
            shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            shapeBatch.end();
            batch.setProjectionMatrix(cam.combined);
            batch.begin();
            batch.draw(menuPic, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(startButton, 350, 250, 100, 50);
            batch.end();

            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touch);
            if (Gdx.input.justTouched()) {
                if (touch.x > 350 && touch.x < 450 && touch.y > 250 && touch.y < 300) {
                    startGame = true;
                }
            }
            //if the gmae has begon draw in the game             
        } else if (startGame == true) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                player1.moveUp();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                player1.moveDown();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                player1.moveLeft();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                player1.moveRight();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                player2.moveUp();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                player2.moveDown();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                player2.moveLeft();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                player2.moveRight();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                System.exit(0);
            }
            shapeBatch.setProjectionMatrix(cam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            //the menu picture
            shapeBatch.setColor(Color.WHITE);
            shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            shapeBatch.end();
            batch.setProjectionMatrix(cam.combined);
            batch.begin();
            batch.draw(obstacle1, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(chr1IMG, player1.getX(), player1.getY(), 45, 45);
            batch.draw(chr1IMG, player2.getX(), player2.getY(), 45, 45);

            batch.end();
            
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}

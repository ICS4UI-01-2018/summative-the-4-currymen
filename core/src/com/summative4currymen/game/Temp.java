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
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.ArrayList;

public class Temp extends ApplicationAdapter {

    private SpriteBatch batch;
    private ShapeRenderer shapeBatch;
    private OrthographicCamera cam;
    private FitViewport viewport;
    private Player player1;
    private Player player2;
    private ArrayList<Player> player;
    private Zombie zombie;
    private ArrayList<Zombie> zombies;
    private Texture img;
    private Texture menuPic;
    private Texture startButton;
    private Texture chr1IMG;
    private Texture zomIMG;

    private Texture obstacle1;
    private Texture obstacle2;
    private Texture obstacle3;
    private Texture obstacle4;

    private boolean startGame;

    private Vector3 touch = new Vector3(0, 0, 0);

    private BitmapFont font;

    FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    
    double cohesionCoefficient = 100.0;
    int alignmentCoefficient = 8;
    double separationCoefficient = 10.0;
    int N = 500;                                 //number of boids to simulate
    int distance = 50;                           //amount of neighbours to search for in kd-tree
    
    
    @Override
    public void create() {
        startGame = false;
        shapeBatch = new ShapeRenderer();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        menuPic = new Texture("MenuPic.jpg");
        startButton = new Texture("StartButton.png");
        chr1IMG = new Texture("character1.png");
        zomIMG = new Texture("thriller-zombie.png");

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
        
        player = new ArrayList<Player>();
        player1 = new Player(400, 300, 45, 45, 2, 5, "Rick");
        player.add(player1);
        player2 = new Player(450, 350, 45, 20, 2, 5, "Carl");
        player.add(player2);

        zombies = new ArrayList<Zombie>();
        for(int i = 0; i < 100; i++){
        //zombies.add(new Zombie((int) Math.floor(Math.random() * 801), (int) Math.floor(Math.random() * 601), 45, 45, 2, 100, "Zambie", 100));
        }
        
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Xcelsion Italic.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 26;
        font = generator.generateFont(parameter);
        generator.dispose();
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
            //if the game has begon draw in the game             
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
            
            for(int i = 0; i < zombies.size(); i++){
                double distance1 = Math.sqrt((Math.pow(zombies.get(i).getX() - player1.getX(), 2)) + (Math.pow(zombies.get(i).getX() - player1.getY(), 2)));
                double distance2 = Math.sqrt((Math.pow(zombies.get(i).getY() - player2.getX(), 2)) + (Math.pow(zombies.get(i).getY() - player2.getY(), 2)));
                
                if(distance1 < distance2){
                // if the zombies x value is bigger than the players x value
            if (zombies.get(i).getX() > player1.getX()) {
                // the zombies x value decreases using the speed integer
                zombies.get(i).moveLeft();
                // if the zombies x value is less than the players x value  
            } else if (zombies.get(i).getX() < player1.getX()) {
                // the zombies x value increases using the speed integer
                zombies.get(i).moveRight();
                // if the zombies x value is equal the players x value than nothing changes
            } else if (zombies.get(i).getX() == player1.getX()) {
            }// if the zombies y value is bigger than the players y value
            if (zombies.get(i).getY() > player1.getY()) {
                // the zombies y value decreases using the speed integer
                zombies.get(i).moveDown();
                // if the zombies y value is less than the players y value
            } else if (zombies.get(i).getY() < player1.getY()) {
                // the zombies y value increases using the speed integer
                zombies.get(i).moveUp();
                // if the zombies y value is equal to the players y value than nothing changes      
            } else if (zombies.get(i).getY() == player1.getY()) {

            }
            }else if(distance1 > distance2){
                // if the zombies x value is bigger than the players x value
            if (zombies.get(i).getX() > player2.getX()) {
                // the zombies x value decreases using the speed integer
                zombies.get(i).moveLeft();
                // if the zombies x value is less than the players x value  
            } else if (zombies.get(i).getX() < player2.getX()) {
                // the zombies x value increases using the speed integer
                zombies.get(i).moveRight();
                // if the zombies x value is equal the players x value than nothing changes
            } else if (zombies.get(i).getX() == player2.getX()) {
            }// if the zombies y value is bigger than the players y value
            if (zombies.get(i).getY() > player2.getY()) {
                // the zombies y value decreases using the speed integer
                zombies.get(i).moveDown();
                // if the zombies y value is less than the players y value
            } else if (zombies.get(i).getY() < player2.getY()) {
                // the zombies y value increases using the speed integer
                zombies.get(i).moveUp();
                // if the zombies y value is equal to the players y value than nothing changes      
            } else if (zombies.get(i).getY() == player2.getY()) {

            }
            }else if(distance1 == distance2){
            }
            }
            
            Boids boids = null;
//            boids.move(distance, cohesionCoefficient, alignmentCoefficient, separationCoefficient);
            
            

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
            batch.draw(zomIMG, zombie.getX(), zombie.getY(), 45, 45);
            //for(int i = 0; i < zombies.size(); i++){
            //batch.draw(zomIMG, zombies.get(i).getX(), zombies.get(i).getY(), 45, 45);
            //}   

            font.setColor(Color.FIREBRICK);
            font.draw(batch, "Kill the Zombies or be Killed", 50, 100);
            batch.end();

        }

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}

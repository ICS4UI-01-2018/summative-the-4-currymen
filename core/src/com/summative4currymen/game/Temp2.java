/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class Temp2 extends ApplicationAdapter {

    private SpriteBatch batch;
    private ShapeRenderer shapeBatch;
    private ShapeRenderer shapeBatch2;
    private OrthographicCamera cam;
    private FitViewport viewport;
    private Player player1;
    private Player player2;
    private Zombie zombie;
    private ArrayList<Zombie> zombies;
    private Texture img;
    private Texture menuPic;
    private Texture startButton;
    private Texture chr1IMG;
    private Texture zomIMG;
    private Texture arcadeLogo;
    private Texture obstacle1;
    private Texture obstacle2;
    private Texture obstacle3;
    private Texture obstacle4;
    private int rotation1;
    private int rotation2;
    private ArrayList<Bullet> bullets;
    private BitmapFont font;
    private BitmapFont titleFont;
    private Texture instructionPic;
    private Texture nextButton;
    private Texture storeButton;
    private boolean startGame;
    private boolean goStore;
    private boolean nextScreen;

    private Vector3 touch = new Vector3(0, 0, 0);

    @Override
    public void create() {
        this.rotation1 = 270;
        this.rotation2 = 270;
        shapeBatch2 = new ShapeRenderer();
        shapeBatch = new ShapeRenderer();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        menuPic = new Texture("MenuPic.jpg");
        arcadeLogo = new Texture("Arcade_logo.png");
        startButton = new Texture("StartButton.png");
        storeButton = new Texture("StoreButton.png");
        instructionPic = new Texture("instruct.jpg");
        nextButton = new Texture("next.png");
        chr1IMG = new Texture("character1.png");
        zomIMG = new Texture("thriller-zombie.png");

        bullets = new ArrayList<Bullet>();

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
        player2 = new Player(450, 350, 45, 45, 2, 100, "Carl");

        zombies = new ArrayList<Zombie>();

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("Xcelsion Italic.ttf"));
        FreeTypeFontParameter param = new FreeTypeFontParameter();
        param.size = 35;
        titleFont = gen.generateFont(param);
        gen.dispose();

        FreeTypeFontGenerator g = new FreeTypeFontGenerator(Gdx.files.internal("Xcelsion Italic.ttf"));
        FreeTypeFontParameter p = new FreeTypeFontParameter();
        p.size = 17;
        font = g.generateFont(p);
        g.dispose();

        for (int i = 0; i < 100; i++) {
            zombies.add(new Zombie((int) Math.floor(Math.random() * 801), (int) Math.floor(Math.random() * 601), 45, 45, 2, 100, "Zambie", 100));
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //if the game has not started yet, draw in the main menu   
        if (nextScreen == false) {
            shapeBatch.setProjectionMatrix(cam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            //the menu picture
            shapeBatch.setColor(Color.GOLD);
            shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            shapeBatch.end();
            batch.setProjectionMatrix(cam.combined);
            batch.begin();
            batch.draw(menuPic, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(startButton, 275, 210, 100, 50);
            batch.draw(arcadeLogo, 335, 330, 125, 75);
            batch.draw(storeButton, 425, 215, 100, 50);
            titleFont.setColor(Color.WHITE);
            titleFont.draw(batch, "ARCADE APOCALYPSE", 125, 310);
            batch.end();

            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touch);
            if (Gdx.input.justTouched()) {
                if (touch.x > 275 && touch.x < 375 && touch.y > 210 && touch.y < 260) {
                    nextScreen = true;
                }
            }
                    
                    
            //} else if (touch.x > 275 && touch.x < 375 && touch.y > 210 && touch.y < 260) {
              //  goStore = true;
            //}

        } else if (startGame == false) {
            shapeBatch.setProjectionMatrix(cam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            //the instruction picture
            shapeBatch.setColor(Color.GOLD);
            shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            shapeBatch.end();
            batch.setProjectionMatrix(cam.combined);
            batch.begin();
            batch.draw(instructionPic, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(nextButton, 680, 20, 100, 100);
            font.setColor(Color.WHITE);
            font.draw(batch, "Instructions", 300, 550);
            font.setColor(Color.WHITE);
            font.draw(batch, "Player One Controls", 25, 450);
            font.setColor(Color.WHITE);
            font.draw(batch, "W = Move Upwards", 25, 400);
            font.setColor(Color.WHITE);
            font.draw(batch, "A = Move Left", 25, 350);
            font.setColor(Color.WHITE);
            font.draw(batch, "S = Move Downwards", 25, 300);
            font.setColor(Color.WHITE);
            font.draw(batch, "D = Move Right", 25, 250);
            font.setColor(Color.WHITE);
            font.draw(batch, "Space = Shoot", 25, 200);
            font.setColor(Color.WHITE);
            font.draw(batch, "Player Two Controls", 360, 450);
            font.setColor(Color.WHITE);
            font.draw(batch, "Up Arrow = Move Upwards", 360, 400);
            font.setColor(Color.WHITE);
            font.draw(batch, "Left Arrow = Move Left", 360, 350);
            font.setColor(Color.WHITE);
            font.draw(batch, "Right Arrow = Move Right", 360, 300);
            font.setColor(Color.WHITE);
            font.draw(batch, "Down Arrow = Move Downwards", 360, 250);
            font.setColor(Color.WHITE);
            font.draw(batch, "Enter = Shoot", 360, 200);
            font.setColor(Color.WHITE);
            font.draw(batch, "Start", 685, 23);
            batch.end();

            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touch);
            if (Gdx.input.justTouched()) {
                if (touch.x > 680 && touch.x < 780 && touch.y > 20 && touch.y < 120) {
                    startGame = true;
                }
            }

            //if the game has begn draw in the game             
        } else if (startGame == true) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                player1.moveUp();
                rotation1 = 90;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                player1.moveDown();
                rotation1 = 270;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                player1.moveLeft();
                rotation1 = 180;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                player1.moveRight();
                rotation1 = 0;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                player2.moveUp();
                rotation2 = 90;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                player2.moveDown();
                rotation2 = 270;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                player2.moveLeft();
                rotation2 = 180;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                player2.moveRight();
                rotation2 = 0;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.A)) {
                rotation1 = 135;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.D)) {
                rotation1 = 45;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.A)) {
                rotation1 = 225;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D)) {
                rotation1 = 315;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                rotation2 = 135;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                rotation2 = 45;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                rotation2 = 225;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                rotation2 = 315;
            }
            if (player1.getX() < 0) {
                player1.moveRight();
            }
            if (player1.getX() > 755) {
                player1.moveLeft();
            }
            if (player1.getY() < 0) {
                player1.moveUp();
            }
            if (player1.getY() > 555) {
                player1.moveDown();
            }
            if (player2.getX() < 0) {
                player2.moveRight();
            }
            if (player2.getX() > 755) {
                player2.moveLeft();
            }
            if (player2.getY() < 0) {
                player2.moveUp();
            }
            if (player2.getY() > 555) {
                player2.moveDown();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                if (rotation1 == 0) {
                    System.out.println("0");
                    Bullet b = new Bullet((int) player1.getX(), (int) player1.getY(), 10, 10, 5, 50, 1, 0);
                    bullets.add(b);
                    System.out.println("" + player1.getX() + " " + player1.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation1 == 45) {
                    System.out.println("45");
                    Bullet b = new Bullet((int) player1.getX(), (int) player1.getY(), 10, 10, 5, 50, 1, 1);
                    bullets.add(b);
                    System.out.println("" + player1.getX() + " " + player1.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation1 == 90) {
                    System.out.println("90");
                    Bullet b = new Bullet((int) player1.getX(), (int) player1.getY(), 10, 10, 5, 50, 0, 1);
                    bullets.add(b);
                    System.out.println("" + player1.getX() + " " + player1.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation1 == 135) {
                    System.out.println("135");
                    Bullet b = new Bullet((int) player1.getX(), (int) player1.getY(), 10, 10, 5, 50, -1, 1);
                    bullets.add(b);
                    System.out.println("" + player1.getX() + " " + player1.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation1 == 180) {
                    System.out.println("180");
                    Bullet b = new Bullet((int) player1.getX(), (int) player1.getY(), 10, 10, 5, 50, -1, 0);
                    bullets.add(b);
                    System.out.println("" + player1.getX() + " " + player1.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation1 == 225) {
                    System.out.println("270");
                    Bullet b = new Bullet((int) player1.getX(), (int) player1.getY(), 10, 10, 5, 50, -1, -1);
                    bullets.add(b);
                    System.out.println("" + player1.getX() + " " + player1.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation1 == 270) {
                    System.out.println("270");
                    Bullet b = new Bullet((int) player1.getX(), (int) player1.getY(), 10, 10, 5, 50, 0, -1);
                    bullets.add(b);
                    System.out.println("" + player1.getX() + " " + player1.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation1 == 315) {
                    System.out.println("315");
                    Bullet b = new Bullet((int) player1.getX(), (int) player1.getY(), 10, 10, 5, 50, 1, -1);
                    bullets.add(b);
                    System.out.println("" + player1.getX() + " " + player1.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                if (rotation2 == 0) {
                    System.out.println("0");
                    Bullet b = new Bullet((int) player2.getX(), (int) player2.getY(), 10, 10, 5, 50, 1, 0);
                    bullets.add(b);
                    System.out.println("" + player2.getX() + " " + player2.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation2 == 45) {
                    System.out.println("45");
                    Bullet b = new Bullet((int) player2.getX(), (int) player2.getY(), 10, 10, 5, 50, 1, 1);
                    bullets.add(b);
                    System.out.println("" + player2.getX() + " " + player2.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation2 == 90) {
                    System.out.println("90");
                    Bullet b = new Bullet((int) player2.getX(), (int) player2.getY(), 10, 10, 5, 50, 0, 1);
                    bullets.add(b);
                    System.out.println("" + player2.getX() + " " + player2.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation2 == 135) {
                    System.out.println("135");
                    Bullet b = new Bullet((int) player2.getX(), (int) player2.getY(), 10, 10, 5, 50, -1, 1);
                    bullets.add(b);
                    System.out.println("" + player2.getX() + " " + player2.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation2 == 180) {
                    System.out.println("180");
                    Bullet b = new Bullet((int) player2.getX(), (int) player2.getY(), 10, 10, 5, 50, -1, 0);
                    bullets.add(b);
                    System.out.println("" + player2.getX() + " " + player2.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation2 == 225) {
                    System.out.println("270");
                    Bullet b = new Bullet((int) player2.getX(), (int) player2.getY(), 10, 10, 5, 50, -1, -1);
                    bullets.add(b);
                    System.out.println("" + player2.getX() + " " + player2.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation2 == 270) {
                    System.out.println("270");
                    Bullet b = new Bullet((int) player2.getX(), (int) player2.getY(), 10, 10, 5, 50, 0, -1);
                    bullets.add(b);
                    System.out.println("" + player2.getX() + " " + player2.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
                if (rotation2 == 315) {
                    System.out.println("315");
                    Bullet b = new Bullet((int) player2.getX(), (int) player2.getY(), 10, 10, 5, 50, 1, -1);
                    bullets.add(b);
                    System.out.println("" + player2.getX() + " " + player2.getY());
                    System.out.println("" + b.getX() + " " + b.getY());
                }
            }

            for (Bullet b : this.bullets) {
                b.bulletMovement();
            }
            shapeBatch.setProjectionMatrix(cam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            //the menu picture
            /* shapeBatch.setColor(Color.WHITE);
            shapeBatch.rect(0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());*/
            shapeBatch.setColor(Color.WHITE);
            shapeBatch.rect(player1.getX(), player1.getY(), 45, 45);
            //player1.draw(shapeBatch);
            shapeBatch.end();
            batch.setProjectionMatrix(cam.combined);
            batch.begin();
            batch.draw(obstacle1, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

            batch.draw(chr1IMG, player2.getX(), player2.getY(), player2.getWidth() / 2, player2.getHeight() / 2, player2.getWidth(), player2.getHeight(), 1, 1, rotation2, 0, 0, chr1IMG.getWidth(), chr1IMG.getHeight(), false, false);
            batch.draw(chr1IMG, player1.getX(), player1.getY(), player1.getWidth() / 2, player1.getHeight() / 2, player1.getWidth(), player1.getHeight(), 1, 1, rotation1, 0, 0, chr1IMG.getWidth(), chr1IMG.getHeight(), false, false);

            for (int i = 0; i < zombies.size(); i++) {
                batch.draw(zomIMG, zombies.get(i).getX(), zombies.get(i).getY(), 45, 45);
            }

            font.draw(batch, "Kill the Zombies or be Killed", 50, 100);
            batch.end();
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            shapeBatch.setColor(Color.WHITE);
            for (Bullet b : this.bullets) {
                b.drawBullet(shapeBatch);
            }
            shapeBatch.end();
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}

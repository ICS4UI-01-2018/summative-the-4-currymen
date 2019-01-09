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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Temp extends ApplicationAdapter {

    private SpriteBatch batch;
    private ShapeRenderer shapeBatch;
    private ShapeRenderer shapeBatch2;
    private OrthographicCamera cam;
    private FitViewport viewport;
    private Player player1;
    private Player player2;
    private Map map;
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
    private int[] rotation3;
    private ArrayList<Bullet> bullets;
    private ArrayList<Weapon> worldWeapons;
    private BitmapFont font;
    private BitmapFont titleFont;
    private BitmapFont desc;
    private Texture instructionPic;
    private Texture nextButton;
    private Texture storeButton;
    private Texture whiteRect;
    private Texture shotgun;
    private Texture ak47;
    private Texture barrett;
    private Texture buyNow;
    private Texture coin;
    private Texture treePic;
    private boolean startGame;
    private boolean nextGame;
    private boolean goStore;
    private boolean nextScreen;
    private boolean instructNum2;
    private int totalZombies;
    private int zombiesKilled; 
    private int peopleLeft = 1002;
    private double zombieSpeed;
    
    private long previousTime;
    private long previousTime2;

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
        whiteRect = new Texture("whitebox copy.png");
        ak47 = new Texture("ak.png");
        shotgun = new Texture("shotgun.png");
        barrett = new Texture("RSASS_Sideview.png");
        buyNow = new Texture("BuyNow_1.png");
        coin = new Texture("coin.png");
        chr1IMG = new Texture("character1.png");
        zomIMG = new Texture("zombietopview.png");
        treePic = new Texture("treetop.png");

        bullets = new ArrayList<Bullet>();
        worldWeapons = new ArrayList<Weapon>();

        //load in guns from file        
        Scanner in = null;
        try {
            in = new Scanner(Gdx.files.internal("GunsFile").file());
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (in.hasNext()) {
            String gunLine = in.nextLine();
            String gunInfo[] = gunLine.split(" ");
            String gunName = gunInfo[0];
            int bulletSpeed = Integer.parseInt(gunInfo[1]);
            int fireRate = Integer.parseInt(gunInfo[2]);
            int damage = Integer.parseInt(gunInfo[3]);
            int numBullets = Integer.parseInt(gunInfo[4]);
            Weapon gun = new Weapon(gunName, bulletSpeed, fireRate, damage, numBullets, (int) (Math.random() * (750 - 50)) + 50, (int) (Math.random() * (550 - 50)) + 50);
            worldWeapons.add(gun);
            System.out.println(gunName + " " + bulletSpeed + " " + fireRate + " " + damage);
        }

        long previousTime = TimeUtils.millis();
        long previousTime2 = TimeUtils.millis();

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
        player1 = new Player(400, 300, 45, 45, 2, 2, "Rick");
        player2 = new Player(450, 350, 45, 45, 2, 2, "Carl");
        peopleLeft = peopleLeft - 2;

        player1.setEquipped("AK-47");
        player2.setEquipped("ShotGun");

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

        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("BEBAS___.ttf"));
        FreeTypeFontParameter parameter2 = new FreeTypeFontParameter();
        parameter2.size = 17;
        desc = generator2.generateFont(parameter2);
        generator2.dispose();

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
        } else if (instructNum2 == false) {
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
                    instructNum2 = true;
                }
            }

        } else if (goStore == false) {            
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
            font.draw(batch, "Go to Store", 630, 23);
            font.setColor(Color.WHITE);
            font.draw(batch, "               Welcome to Arcade Apocalypse!\n \n \n"
                    + "This game is based in a world rampant with zombies.\n \n \n"
                    + "  But, there is hope for the remaining " + peopleLeft + " people.\n \n \n"
                    + "                               That hope is … \n \n \n"
                    + "                                    YOU!\n \n \n"
                    + "                                Go for it!", 35, 415);
            batch.end();

            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touch);
            if (Gdx.input.justTouched()) {
                if (touch.x > 680 && touch.x < 780 && touch.y > 20 && touch.y < 120) {
                    goStore = true;
                }
            }

        } else if (startGame == false) {
            cam.zoom = 1;
            cam.position.x = 400;
            cam.position.y = 300;
            cam.update();
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
            batch.draw(whiteRect, 20, 375, 150, 150);
            batch.draw(whiteRect, 20, 225, 150, 150);
            batch.draw(whiteRect, 20, 75, 150, 150);
            font.setColor(Color.WHITE);
            font.draw(batch, "AK47", 160, 510);
            batch.draw(coin, 310, 493, 35, 25);
            desc.draw(batch, "100", 345, 513);
            desc.draw(batch, "A reliable weapon with a high rate of fire and \n consistant damage.", 160, 490);
            desc.draw(batch, "STATS: \n Bullet Speed: Average      Reload Speed: Fast \n Damage: 50", 160, 445);
            batch.draw(buyNow, 615, 420, 150, 50);
            batch.draw(ak47, 45, 420, 100, 50);
            font.draw(batch, "Barrett", 160, 360);
            batch.draw(coin, 310, 493, 35, 25);
            desc.draw(batch, "250", 345, 513);
            desc.draw(batch, "Packs a punch. Take on the hoard with a low magazine, but \n high damage sniper rifle.", 160, 340);
            desc.draw(batch, "STATS: \n Bullet Speed: Fast      Reload Speed: Slow \n Damage: 150", 160, 295);
            batch.draw(buyNow, 615, 270, 150, 50);
            batch.draw(barrett, 45, 280, 100, 40);
            font.draw(batch, "Shotgun", 160, 210);
            desc.draw(batch, "Is one bullet not enough? Eliminate the zombies \n with a spread shot shotgun.", 160, 190);
            desc.draw(batch, "STATS: \n Bullet Speed: Slow      Reload Speed: Average \n Damage: 70", 160, 145);
            batch.draw(shotgun, 45, 130, 100, 40);
            batch.draw(buyNow, 615, 120, 150, 50);
            batch.end();

            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touch);
        
            if (Gdx.input.justTouched()) {
                if (touch.x > 680 && touch.x < 780 && touch.y > 20 && touch.y < 120) {
                    zombiesKilled = 0;
                    totalZombies = 1;
                    zombieSpeed = 1;
                    for (int i = 0; i < totalZombies; i++) {
                        
                        zombies.add(new Zombie((int) Math.floor(Math.random() * 801), (int) Math.floor(Math.random() * 601), 45, 45, 1000, zombieSpeed, "Zambie", 100, 0));
                        zombieSpeed = zombieSpeed + 0.1;
                    }
                    this.rotation3 = new int[zombies.size()];
                    map = new Map();
                    nextGame = true;
                    
                    for (Zombie z : zombies) {
                        if(z.collides(player1.getBounds())){
                            player1.hit(z.attack());
                        }
                    }
                }
            }

            //if the game has begn draw in the game             
        } else if (startGame == true) {
            if (zombiesKilled == totalZombies) {                
                nextGame = false;
            }
            
            if(nextGame = false){
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
            font.draw(batch, "Go to Store", 630, 23);
            font.setColor(Color.WHITE);
            font.draw(batch, "               You have completed the level!\n \n \n"
                    + "The next level will not be as easy so be sure to suit up in the shop.\n \n \n"
                    + "  There are " + peopleLeft + "amount of people left.\n \n \n" 
                    + "                                Onward SOLDIER!", 35, 415);
            batch.end();

            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touch);
            if (Gdx.input.justTouched()) {
                if (touch.x > 680 && touch.x < 780 && touch.y > 20 && touch.y < 120) {
                    goStore = true;
                }
            }
            }
            //update camera to players positions and zoom in or out accordingly
            //viewport.setScreenSize(800 -(int)(1.3*(int)((Math.sqrt((Math.pow((double)(player1.getX()) - (double)(player2.getX()),2)) + (Math.pow((double)(player1.getY()) - (double)(player2.getY()),2))))/2)), (int)(viewport.getScreenWidth() / 1.333));
            //viewport.apply();

            if (Math.sqrt((Math.pow((double) (player1.getX()) - (double) (player2.getX()), 2)) + (Math.pow((double) (player1.getY()) - (double) (player2.getY()), 2))) > 400) {
                cam.zoom = (float) (Math.sqrt((Math.pow((double) (player1.getX()) - (double) (player2.getX()), 2)) + (Math.pow((double) (player1.getY()) - (double) (player2.getY()), 2)))) / 400;
            }
            if (cam.position.x - cam.viewportWidth / 2 >= 0 && cam.position.x + cam.viewportWidth / 2 <= map.getWorldWidth()) {
                cam.position.x = (player1.getX() + player2.getX()) / 2;
            }
            if (cam.position.y - cam.viewportHeight / 2 >= 0 && cam.position.y + cam.viewportHeight / 2 <= map.getWorldHeight()) {
                cam.position.y = (player1.getY() + player2.getY()) / 2;
            }
            if (cam.position.x - cam.viewportWidth / 2 < 0) {
                cam.position.x = cam.viewportWidth / 2;
            }
            if (cam.position.x + cam.viewportWidth / 2 > map.getWorldWidth()) {
                cam.position.x = map.getWorldWidth() - cam.viewportWidth / 2;
            }
            if (cam.position.y - cam.viewportHeight / 2 < 0) {
                cam.position.y = 0 + cam.viewportHeight / 2;
            }
            if (cam.position.y + cam.viewportHeight / 2 > map.getWorldHeight()) {
                cam.position.y = map.getWorldHeight() - cam.viewportHeight / 2;
            }
            cam.update();

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
            //player collition with furniture
            for (Furniture f : map.getObjects()) {
                if (player1.collides(f.f)) {
                    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                        player1.moveDown();
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                        player1.moveUp();
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                        player1.moveRight();
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                        player1.moveLeft();
                    }
                }
                if (player2.collides(f.f)) {
                    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                        player2.moveDown();
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                        player2.moveUp();
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                        player2.moveRight();
                    }
                    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                        player2.moveLeft();
                    }
                }
            }

            if (player1.getX() < 0) {
                player1.moveRight();
            }
            if (player1.getX() > map.getWorldWidth() - player1.getWidth()) {
                player1.moveLeft();
            }
            if (player1.getY() < 0) {
                player1.moveUp();
            }
            if (player1.getY() > map.getWorldHeight() - player1.getHeight()) {
                player1.moveDown();
            }
            if (player2.getX() < 0) {
                player2.moveRight();
            }
            if (player2.getX() > map.getWorldWidth() - player2.getWidth()) {
                player2.moveLeft();
            }
            if (player2.getY() < 0) {
                player2.moveUp();
            }
            if (player2.getY() > map.getWorldHeight() - player2.getHeight()) {
                player2.moveDown();
            }

            //shooting for player 1
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                for (Weapon w : this.worldWeapons) {
                    if (w.getName().equals(player1.getEquipped())) {
                        if (TimeUtils.millis() - previousTime > w.fireRate()) {
                            bullets.addAll(w.shootWeapon(w.getName(), rotation1, player1.getX(), player1.getY(), w.bulletSpeed(), w.damage(), w.fireRate(), w.numBullets()));
                            previousTime = TimeUtils.millis();

                        }
                    }
                }
            }
            //shooting for player 2
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                for (Weapon w : this.worldWeapons) {
                    if (w.getName().equals(player2.getEquipped())) {
                        if (TimeUtils.millis() - previousTime2 > w.fireRate()) {
                            bullets.addAll(w.shootWeapon(w.getName(), rotation2, player2.getX(), player2.getY(), w.bulletSpeed(), w.damage(), w.fireRate(), w.numBullets()));
                            previousTime2 = TimeUtils.millis();
                        }
                    }
                }
            }

            for (Zombie z : zombies) {
                double distance1 = Math.sqrt((Math.pow(z.getX() - player1.getX(), 2)) + (Math.pow(z.getY() - player1.getY(), 2)));
                double distance2 = Math.sqrt((Math.pow(z.getX() - player2.getX(), 2)) + (Math.pow(z.getY() - player2.getY(), 2)));

                if (distance1 < distance2) {
                    if (z.getX() < player1.getX() && z.getY() == player1.getY()) {
                        z.setRotation(0);
                        z.moveRight();
                    }
                    if (z.getX() < player1.getX() && z.getY() < player1.getY()) {
                        z.setRotation(45);
                        z.moveRight();
                        z.moveUp();

                    }
                    if (z.getX() == player1.getX() && z.getY() < player1.getY()) {
                        z.setRotation(90);
                        z.moveUp();

                    }
                    if (z.getX() > player1.getX() && z.getY() < player1.getY()) {
                        z.setRotation(135);
                        z.moveLeft();
                        z.moveUp();

                    }
                    if (z.getX() > player1.getX() && z.getY() == player1.getY()) {
                        z.setRotation(180);
                        z.moveLeft();
                        z.moveDown();
                    }
                    if (z.getX() > player1.getX() && z.getY() > player1.getY()) {
                        z.setRotation(225);
                        z.moveLeft();
                        z.moveDown();
                    }
                    if (z.getX() == player1.getX() && z.getY() > player1.getY()) {
                        z.setRotation(270);
                        z.moveDown();
                    }

                    if (z.getX() < player1.getX() && z.getY() > player1.getY()) {
                        z.setRotation(315);
                        z.moveRight();
                        z.moveDown();

                    }

                } else if (distance1 > distance2) {
                    if (z.getX() < player2.getX() && z.getY() == player2.getY()) {
                        z.setRotation(0);
                        z.moveRight();
                    }
                    if (z.getX() < player2.getX() && z.getY() < player2.getY()) {
                        z.setRotation(45);
                        z.moveRight();
                        z.moveUp();
                    }
                    if (z.getX() == player2.getX() && z.getY() < player2.getY()) {
                        z.setRotation(90);
                        z.moveUp();

                    }
                    if (z.getX() > player2.getX() && z.getY() < player2.getY()) {
                        z.setRotation(135);
                        z.moveLeft();
                        z.moveUp();

                    }
                    if (z.getX() > player2.getX() && z.getY() == player2.getY()) {
                        z.setRotation(180);
                        z.moveLeft();
                        z.moveDown();
                    }
                    if (z.getX() > player2.getX() && z.getY() > player2.getY()) {
                        z.setRotation(225);
                        z.moveLeft();
                        z.moveDown();

                    }
                    if (z.getX() == player2.getX() && z.getY() > player2.getY()) {
                        z.setRotation(270);
                        z.moveDown();
                    }
                    if (z.getX() < player2.getX() && z.getY() > player2.getY()) {
                        z.setRotation(315);
                        z.moveRight();
                        z.moveDown();

                    }
                }
                for (Furniture f : map.getObjects()) {
                    if (z.collides(f.f)) {
                        z.setSpeed(0.4);
                        break;
                    } else {
                        z.setSpeed(1);
                    }
                }
                //This can detect if a zombie collides with another zombie
                /*for (Zombie z2 : zombies) {
                    if (z.collidesWith(z2)){
                        
                    }
                }*/
            }

            Iterator<Bullet> it = this.bullets.iterator();
            Iterator<Zombie> zom = this.zombies.iterator();
            while (it.hasNext()) {
                Bullet b = it.next();
                b.bulletMovement();
                if (b.getX() > map.getWorldWidth() || b.getX() < 0 || b.getY() > map.getWorldHeight() || b.getY() < 0) {
                    it.remove();
                } else {
                    for (Zombie z : this.zombies) {
                        if (z.getAlive() == true) {
                            if (colidesWithZombie(b.getX(), b.getY(), z) == true) {
                                if (z.hit(b.getDamage())) {
                                    zombiesKilled++;
                                    System.out.println(zombiesKilled);
                                }
                                it.remove();
                                break;
                            }
                        }
                    }
                }
            }
            shapeBatch.setProjectionMatrix(cam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);

            shapeBatch.end();
            batch.setProjectionMatrix(cam.combined);
            batch.begin();
            map.draw(batch);
            batch.draw(chr1IMG, player2.getX(), player2.getY(), player2.getWidth() / 2, player2.getHeight() / 2, player2.getWidth(), player2.getHeight(), 1, 1, rotation2, 0, 0, chr1IMG.getWidth(), chr1IMG.getHeight(), false, false);
            batch.draw(chr1IMG, player1.getX(), player1.getY(), player1.getWidth() / 2, player1.getHeight() / 2, player1.getWidth(), player1.getHeight(), 1, 1, rotation1, 0, 0, chr1IMG.getWidth(), chr1IMG.getHeight(), false, false);
            for (Zombie z : zombies) {
                if (z.getAlive() == true) {
                    batch.draw(zomIMG, z.getX(), z.getY(), z.getWidth() / 2, z.getHeight() / 2, z.getWidth(), z.getHeight(), 1, 1, z.getRotation(), 0, 0, zomIMG.getWidth(), zomIMG.getHeight(), false, false);
                }
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

    public void dispose() {
        batch.dispose();
    }

    public boolean colidesWithZombie(float bX, float bY, Zombie z) {
        if (bX + 10 >= z.getX() && bX <= z.getX() + z.getWidth() / 2 && bY + 10 >= z.getY() && bY <= z.getY() + z.getHeight() / 2) {
            return true;
        }
        return false;
    }

}


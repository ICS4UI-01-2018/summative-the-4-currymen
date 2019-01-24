package com.summative4currymen.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ZombieGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private ShapeRenderer shapeBatch;
    private ShapeRenderer shapeBatch2;
    private OrthographicCamera menuCam;
    private OrthographicCamera playerOneCam;
    private OrthographicCamera playerTwoCam;
    private FitViewport menuViewPort;
    private FitViewport playerOneViewPort;
    private FitViewport playerTwoViewPort;
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
    private int rotation1;
    private int rotation2;
    private int rotation3[];
    private ArrayList<Bullet> bullets;
    private ArrayList<Weapon> worldWeapons;
    private BitmapFont font;
    private BitmapFont titleFont;
    private BitmapFont desc;
    private Texture instructionPic;
    private Texture nextButton;
    private Texture storeButton;
    private Texture whiteRect;
    private Texture healthIMG;
    private Texture ammoIMG;
    private Texture shotgun;
    private Texture ak47;
    private Texture barrett;
    private Texture buyNow;
    private Texture coin;
    private Texture treePic;
    private Texture shopTitle;
    private Texture graveStone;
    private boolean startGame;
    private boolean goStore;
    private boolean nextScreen;
    private boolean background;
    private boolean instructNum2;
    private boolean isShopTrue;
    private boolean nextGame;
    private boolean endGame;
    private boolean endWave;
    private int totalZombies;
    private int zombiesKilled;
    private int wave;
    private int peopleAlive;
    private String amount;
    private int waveIncrease;
    private int random;

    private long previousTime;
    private long previousTime2;
    private long previousTime3;
    private long previousTime4;

    private Vector3 touch = new Vector3(0, 0, 0);
    private Items pickups; //pickups class by matt
    private HUD hud1; //HUD ADDED BY MATT
    private HUD hud2; //HUD ADDED BY MATT

    private Music music;

    @Override
    /**
     * This is where all pre game set ups are done
     *
     */
    public void create() {
        //Start playing the game theme song, and let it play forever
        music = Gdx.audio.newMusic(Gdx.files.internal("arcademusic.wav"));
        music.setLooping(true);
        music.setVolume(0.25f);
        music.play();
        //set the players rotation to default 270 degrees
        this.rotation1 = 270;
        this.rotation2 = 270;
        //create the Renderers that will draw in everything
        shapeBatch2 = new ShapeRenderer();
        shapeBatch = new ShapeRenderer();
        batch = new SpriteBatch();
        //load in all the textures being used in the game here        
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
        shopTitle = new Texture("title.png");
        healthIMG = new Texture("HealthPack-transparent.png");
        ammoIMG = new Texture("Ammo_box_icon.png");
        graveStone = new Texture("GraveStone.png");
        //create the array lists for the bullets and guns
        bullets = new ArrayList<Bullet>();
        worldWeapons = new ArrayList<Weapon>();

        //load in guns from file        
        Scanner in = null;
        try {
            in = new Scanner(Gdx.files.internal("GunsFile").file());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //while there are guns to load in
        while (in.hasNext()) {
            //take in the line and break it up
            String gunLine = in.nextLine();
            String gunInfo[] = gunLine.split(" ");
            String gunName = gunInfo[0];
            //assign the different values to the guns variables
            int bulletSpeed = Integer.parseInt(gunInfo[1]);
            int fireRate = Integer.parseInt(gunInfo[2]);
            int damage = Integer.parseInt(gunInfo[3]);
            int numBullets = Integer.parseInt(gunInfo[4]);
            int ammoReserves = Integer.parseInt(gunInfo[5]);
            //create the weapon with the new variables
            Weapon gun = new Weapon(gunName, bulletSpeed, fireRate, damage, numBullets, ammoReserves, (int) (Math.random() * (750 - 50)) + 50, (int) (Math.random() * (550 - 50)) + 50);
            worldWeapons.add(gun);
            System.out.println(gunName + " " + bulletSpeed + " " + fireRate + " " + damage + " " + ammoReserves);
        }
        //load in the people
        Scanner in2 = null;
        try {
            in2 = new Scanner(Gdx.files.internal("PeopleFile").file());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ZombieGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //while there are people to load in
        while (in2.hasNext()) {
            //load in however many people are still alive
            String peopleLine = in2.nextLine();
            String peopleInfo[] = peopleLine.split(" ");
            String people = peopleInfo[0];
            amount = peopleInfo[1];
            peopleAlive = Integer.parseInt(amount);
        }
        //create timers that will be used for timing things
        long previousTime = TimeUtils.millis();
        long previousTime2 = TimeUtils.millis();
        long previousTime3 = TimeUtils.millis();
        long previousTime4 = TimeUtils.millis();
        //create the camera that will be used for the menu screens
        menuCam = new OrthographicCamera();
        menuViewPort = new FitViewport(800, 600, menuCam);
        menuViewPort.apply();
        //set the postion of the camera
        menuCam.position.x = 400;
        menuCam.position.y = 300;
        menuCam.update();
        //create the cameras that will be used for the players
        playerOneCam = new OrthographicCamera();
        playerTwoCam = new OrthographicCamera();
        playerOneViewPort = new FitViewport(400, 600, playerOneCam);
        playerTwoViewPort = new FitViewport(400, 600, playerTwoCam);
        playerTwoViewPort.setScreenX(400);
        playerTwoViewPort.apply();
        //create the two players
        player1 = new Player(400, 300, 45, 45, 100, 2, "Rick");
        player2 = new Player(450, 350, 45, 45, 100, 2, "Carl");
        //create the two players HUDS
        hud1 = new HUD(playerOneViewPort.getWorldWidth()); //HUD ADDED BY MATT
        hud2 = new HUD(playerTwoViewPort.getWorldWidth()); //HUD ADDED BY MATT
        //asign the players a gun
        player1.setEquipped("AK-47");
        player2.setEquipped("AK-47");
        //create the array list that will hold the zombies
        zombies = new ArrayList<Zombie>();
        //various fonts being used are created here
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

    /**
     * This is where the game logic is done and then finally drawn in
     */
    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //if the game has not started yet, draw in the main menu
        if (background == false) {
            shapeBatch2.begin(ShapeRenderer.ShapeType.Filled);
            //random disco lights that sorta never get seen
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                shapeBatch2.setColor(Color.CORAL);
            } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                shapeBatch2.setColor(Color.MAGENTA);
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                shapeBatch2.setColor(Color.ROYAL);
            } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                shapeBatch2.setColor(Color.LIME);
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                shapeBatch2.setColor(Color.MAROON);
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                shapeBatch2.setColor(Color.BLUE);
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                shapeBatch2.setColor(Color.FOREST);
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                shapeBatch2.setColor(Color.SALMON);
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                shapeBatch2.setColor(Color.CHARTREUSE);
            } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                shapeBatch2.setColor(Color.SKY);
            }
            shapeBatch2.rect(-2000, -2000, 4000, 4000);
            shapeBatch2.end();
        }
        //the menu screen and first screen you see when launching the game
        if (nextScreen == false) {
            shapeBatch.setProjectionMatrix(menuCam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            //the menu picture
            shapeBatch.setColor(Color.GOLD);
            shapeBatch.rect(0, 0, menuViewPort.getWorldWidth(), menuViewPort.getWorldHeight());
            shapeBatch.end();
            batch.setProjectionMatrix(menuCam.combined);
            batch.begin();
            batch.setColor(Color.CHARTREUSE);
            batch.draw(menuPic, 0, 0, menuViewPort.getWorldWidth(), menuViewPort.getWorldHeight());
            batch.setColor(Color.TAN);
            batch.draw(arcadeLogo, 335, 330, 125, 75);
            batch.setColor(Color.LIGHT_GRAY);
            batch.draw(startButton, 345, 210, 100, 50);
            titleFont.setColor(Color.MAGENTA);
            titleFont.draw(batch, "ARCADE APOCALYPSE", 125, 310);
            batch.end();
            //check if the play button has been clicked or not
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            menuCam.unproject(touch);
            if (Gdx.input.justTouched()) {
                //if it has been clicked move to the instructions page
                if (touch.x > 345 && touch.x < 445 && touch.y > 210 && touch.y < 260) {
                    music = Gdx.audio.newMusic(Gdx.files.internal("click.wav"));
                    music.setVolume(0.50f);
                    music.play();
                    nextScreen = true;
                    instructNum2 = false;
                }
            }
            //the instructions page
        } else if (instructNum2 == false) {
            shapeBatch.setColor(Color.CHARTREUSE);
            shapeBatch.setProjectionMatrix(menuCam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            //the instruction picture
            shapeBatch.rect(0, 0, menuViewPort.getWorldWidth(), menuViewPort.getWorldHeight());
            shapeBatch.end();
            batch.setProjectionMatrix(menuCam.combined);
            batch.begin();
            batch.draw(instructionPic, 0, 0, menuViewPort.getWorldWidth(), menuViewPort.getWorldHeight());
            batch.draw(nextButton, 680, 20, 100, 100);
            font.setColor(Color.MAGENTA);
            font.draw(batch, "Instructions", 300, 550);
            font.setColor(Color.ORANGE);
            font.draw(batch, "Player One Controls", 25, 450);
            font.draw(batch, "W = Move Upwards", 25, 400);
            font.draw(batch, "A = Move Left", 25, 350);
            font.draw(batch, "S = Move Downwards", 25, 300);
            font.draw(batch, "D = Move Right", 25, 250);
            font.draw(batch, "Space = Shoot", 25, 200);
            font.draw(batch, "Player Two Controls", 360, 450);
            font.draw(batch, "Up Arrow = Move Upwards", 360, 400);
            font.draw(batch, "Left Arrow = Move Left", 360, 350);
            font.draw(batch, "Right Arrow = Move Right", 360, 300);
            font.draw(batch, "Down Arrow = Move Downwards", 360, 250);
            font.draw(batch, "Enter = Shoot", 360, 200);
            font.draw(batch, "E = Enter Shop", 300, 150);
            font.draw(batch, "Esc = Exit Shop", 297, 100);
            font.draw(batch, "F = Start New Wave", 283, 50);
            batch.draw(coin, 65, 485, 50, 50);
            batch.draw(ammoIMG, 365, 485, 50, 50);
            batch.draw(healthIMG, 665, 485, 50, 50);
            font.setColor(Color.LIME);
            font.draw(batch, "Coins", 55, 485);
            font.draw(batch, "Ammo", 355, 485);
            font.draw(batch, "Health Pack", 605, 485);
            font.setColor(Color.ROYAL);
            font.draw(batch, "Start", 685, 23);
            batch.end();
            //check to if the move on button has been clicked or not
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            menuCam.unproject(touch);
            if (Gdx.input.justTouched()) {
                //if it has been clicked go to one more screen with some info about the game
                if (touch.x > 680 && touch.x < 780 && touch.y > 20 && touch.y < 120) {
                    music = Gdx.audio.newMusic(Gdx.files.internal("click.wav"));
                    music.setVolume(0.50f);
                    music.play();
                    instructNum2 = true;
                    nextGame = true;
                    endGame = true;
                    startGame = false;
                    wave = 0;
                }
            }
            //at the ned of a round that has be won this screen will appear
        } else if (nextGame == false) {
            endWave = false;
            //rest the menu camera
            menuCam.zoom = 1;
            menuCam.position.x = 400;
            menuCam.position.y = 300;
            menuCam.update();
            menuViewPort.setScreenX(0);
            menuViewPort.setScreenY(0);
            menuViewPort.setScreenWidth(Gdx.graphics.getWidth());
            menuViewPort.setScreenHeight(Gdx.graphics.getHeight());
            menuViewPort.apply();
            shapeBatch.setProjectionMatrix(menuCam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            shapeBatch.setColor(Color.GOLD);
            shapeBatch.rect(0, 0, menuViewPort.getWorldWidth(), menuViewPort.getWorldHeight());
            shapeBatch.end();
            batch.setProjectionMatrix(menuCam.combined);
            batch.begin();
            batch.draw(instructionPic, 0, 0, menuViewPort.getWorldWidth(), menuViewPort.getWorldHeight());
            batch.draw(nextButton, 680, 20, 100, 100);
            font.setColor(Color.ROYAL);
            font.draw(batch, "Next Wave", 650, 23);
            font.setColor(Color.MAGENTA);
            font.draw(batch, "                                Well done! \n"
                    + "                           Wave " + wave + " completed!\n"
                    + "                                Keep it up!", 35, 415);
            batch.end();
            //check if the move on button has been clicked or not
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            menuCam.unproject(touch);
            if (Gdx.input.justTouched()) {
                //if it has been clicked then start the next round with more zombies and revive dead players
                if (touch.x > 680 && touch.x < 780 && touch.y > 20 && touch.y < 120) {
                    nextGame = true;
                    startGame = false;
                    wave = wave + 1;
                    if (player1.getAlive() == false) {
                        player1.revive();
                    }
                    //reset health
                    player1.setHealth(100);
                    if (player2.getAlive() == false) {
                        player2.revive();
                    }
                    //reset health
                    player2.setHealth(100);
                }

            }
            //if a round has been lost this is the screen that will appear
        } else if (endGame == false) {
            //reset the menu camera
            menuCam.zoom = 1;
            menuCam.position.x = 400;
            menuCam.position.y = 300;
            menuCam.update();
            menuViewPort.setScreenX(0);
            menuViewPort.setScreenY(0);
            menuViewPort.setScreenWidth(Gdx.graphics.getWidth());
            menuViewPort.setScreenHeight(Gdx.graphics.getHeight());
            menuViewPort.apply();
            shapeBatch.setProjectionMatrix(menuCam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            shapeBatch.setColor(Color.GOLD);
            shapeBatch.rect(0, 0, menuViewPort.getWorldWidth(), menuViewPort.getWorldHeight());
            shapeBatch.end();
            batch.setProjectionMatrix(menuCam.combined);
            batch.begin();
            batch.draw(instructionPic, 0, 0, menuViewPort.getWorldWidth(), menuViewPort.getWorldHeight());
            batch.draw(nextButton, 680, 20, 100, 100);
            font.setColor(Color.MAGENTA);
            font.draw(batch, "                                 Game OVER! \n"
                    + "                           You survived " + wave + " waves!\n"
                    + "                                  Come again!", 35, 415);
            font.setColor(Color.FIREBRICK);
            font.draw(batch, "Never underestimate the power of stupid people\n"
                    + "                         in large groups.", 55, 200);
            font.setColor(Color.ROYAL);
            font.draw(batch, "Restart", 670, 23);
            font.draw(batch, "Press anywhere besides the arrow to quit", 10, 23);
            batch.end();
            //check if the move on button has been clicked
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            menuCam.unproject(touch);
            if (Gdx.input.justTouched()) {
                //if it has been clicked then restart the whole game
                if (touch.x > 680 && touch.x < 780 && touch.y > 20 && touch.y < 120) {
                    //reset all the players stats and zombies
                    nextScreen = false;
                    endGame = true;
                    wave = 1;
                    player1.die();
                    player2.die();
                    player1.revive();
                    player2.revive();
                    player1.removeCoins();
                    player2.removeCoins();
                    waveIncrease = 0;
                    totalZombies = 1;
                    for (Zombie z : zombies) {
                        z.die();
                    }
                } else {
                    System.exit(0);
                }
            }
            //this is a brief screen before the round has started
        } else if (startGame == false) {
            //rest the menu camera
            menuCam.zoom = 1;
            menuCam.position.x = 400;
            menuCam.position.y = 300;
            menuCam.update();
            menuViewPort.setScreenX(0);
            menuViewPort.setScreenY(0);
            menuViewPort.setScreenWidth(Gdx.graphics.getWidth());
            menuViewPort.setScreenHeight(Gdx.graphics.getHeight());
            menuViewPort.apply();
            shapeBatch.setProjectionMatrix(menuCam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            shapeBatch.setColor(Color.GOLD);
            shapeBatch.rect(0, 0, menuViewPort.getWorldWidth(), menuViewPort.getWorldHeight());
            shapeBatch.end();
            batch.setProjectionMatrix(menuCam.combined);
            batch.begin();
            batch.draw(instructionPic, 0, 0, menuViewPort.getWorldWidth(), menuViewPort.getWorldHeight());
            batch.draw(nextButton, 680, 20, 100, 100);
            font.setColor(Color.MAGENTA);
            if (wave == 0) {
                font.setColor(Color.ROYAL);
                font.draw(batch, "Start Game", 630, 23);
                font.setColor(Color.MAGENTA);
                font.draw(batch, "               Welcome to Arcade Apocalypse!\n \n \n", 35, 415);
                font.setColor(Color.ORANGE);
                font.draw(batch, "This game is based in a world rampant with zombies.\n \n \n"
                        + "     But, there is hope for the remaining people.\n \n \n"
                        + "                               That hope is … \n \n \n"
                        + "                                    YOU!\n \n \n"
                        + "                                Go for it!", 35, 395);
            } else {
                font.setColor(Color.MAGENTA);
                font.draw(batch, "Press next to continue", 250, 415);
                font.setColor(Color.ROYAL);
                font.draw(batch, "Next Wave", 645, 23);
            }
            batch.end();
            //check to see if the next button has been clicked
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            menuCam.unproject(touch);
            if (Gdx.input.justTouched()) {
                //if it has been clicked then start the game
                if (touch.x > 680 && touch.x < 780 && touch.y > 20 && touch.y < 120) {
                    music = Gdx.audio.newMusic(Gdx.files.internal("click.wav"));
                    music.setVolume(0.50f);
                    music.play();
                    //create the zobies rotation array
                    this.rotation3 = new int[zombies.size()];
                    //reset the number of zombies killed and spwan in however many zombies are needed
                    zombiesKilled = 0;
                    totalZombies = 1 + waveIncrease;
                    for (int i = 0; i < totalZombies; i++) {
                        music = Gdx.audio.newMusic(Gdx.files.internal("levelUp.wav"));
                        music.setVolume(0.10f);
                        music.play();
                        zombies.add(new Zombie((int) Math.floor(Math.random() * 801), (int) Math.floor(Math.random() * 601), 45, 45, 100, 1, "Zambie" + i, 20, 0, 1000));
                    }
                    //every 10 waves spawn in a super zombie who is bigger and stronger
                    if (wave % 10 == 0 && wave != 0) {
                        totalZombies++;
                        zombies.add(new Zombie((int) Math.floor(Math.random() * 801), (int) Math.floor(Math.random() * 601), 60, 60, 300, 0.99, "ZambieBIG", 50, 0, 2000));
                    }
                    this.rotation3 = new int[zombies.size()];
                    //generate the world
                    //currently the world generation is turned off because of it not working very well                    
                    map = new Map();
                    //create the pickups 
                    pickups = new Items();
                    //set the player one camera to the left side of the screen and player two to the right side
                    playerOneViewPort.update(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
                    playerTwoViewPort.update(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
                    playerOneViewPort.setScreenX(0);
                    playerTwoViewPort.setScreenX(Gdx.graphics.getWidth() / 2);
                    playerTwoViewPort.apply();
                    playerOneViewPort.apply();
                    //start the game
                    startGame = true;
                }
            }
        }
        //reset the menu camera
        menuCam.zoom = 1;
        menuCam.position.x = 400;
        menuCam.position.y = 300;
        menuCam.update();
        //check to see if the shop has been opened
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            isShopTrue = true;
            startGame = false;
            //check to see if the shope has been closed
        } else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            isShopTrue = false;
            startGame = true;
        }
        //if the shop has been opened
        if (isShopTrue == true) {
            shapeBatch.setProjectionMatrix(menuCam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            shapeBatch.setColor(Color.GOLD);
            shapeBatch.rect(0, 0, menuViewPort.getWorldWidth(), menuViewPort.getWorldHeight());
            shapeBatch.end();
            batch.setProjectionMatrix(menuCam.combined);
            //draw in the shop
            batch.begin();
            batch.draw(instructionPic, 0, 0, menuViewPort.getWorldWidth(), menuViewPort.getWorldHeight());
            batch.draw(whiteRect, 20, 375, 150, 150);
            batch.draw(whiteRect, 20, 225, 150, 150);
            batch.draw(whiteRect, 20, 75, 150, 150);
            font.draw(batch, "PRESS 'ESCAPE' TO CLOSE SHOP", 200, 50);
            batch.draw(shopTitle, 310, 510, 200, 100);
            font.setColor(Color.MAGENTA);
            desc.setColor(Color.ORANGE);
            font.draw(batch, "AK47", 160, 510);
            batch.draw(coin, 310, 493, 35, 25);
            desc.draw(batch, "100", 345, 513);
            desc.draw(batch, "A reliable weapon with a high rate of fire and \n consistant damage.", 160, 490);
            desc.draw(batch, "STATS: \n Bullet Speed: Average      Reload Speed: Fast \n Damage: 50", 160, 445);
            batch.draw(buyNow, 615, 420, 150, 50);
            batch.draw(ak47, 45, 420, 100, 50);
            font.draw(batch, "Barrett", 160, 360);
            batch.draw(coin, 340, 340, 35, 25);
            desc.draw(batch, "250", 375, 360);
            desc.draw(batch, "Packs a punch. Take on the hoard with a low magazine, but \n high damage sniper rifle.", 160, 340);
            desc.draw(batch, "STATS: \n Bullet Speed: Fast      Reload Speed: Slow \n Damage: 150", 160, 295);
            batch.draw(buyNow, 615, 270, 150, 50);
            batch.draw(barrett, 45, 280, 100, 40);
            font.draw(batch, "Shotgun", 160, 210);
            batch.draw(coin, 340, 195, 35, 25);
            desc.draw(batch, "150", 375, 215);
            desc.draw(batch, "Is one bullet not enough? Eliminate the zombies \n with a spread shot shotgun.", 160, 190);
            desc.draw(batch, "STATS: \n Bullet Speed: Slow      Reload Speed: Average \n Damage: 70", 160, 145);
            batch.draw(shotgun, 45, 130, 100, 40);
            batch.draw(buyNow, 615, 120, 150, 50);
            batch.end();
            //check to see if the ak-47 has been purchised
            if (Gdx.input.justTouched()) {
                if (touch.x > 615 && touch.x < 765 && touch.y > 420 && touch.y < 470) {
                    music = Gdx.audio.newMusic(Gdx.files.internal("Click.wav"));
                    music.setVolume(0.75f);
                    music.play();
                    player1.addCoins(player2.getCoins());
                    int tempCoin = player2.getCoins();
                    player2.pay(player2.getCoins());
                    if (player1.pay(100) == true) {
                        player1.setEquipped("AK-47");
                        player2.setEquipped("AK-47");
                        player1.pay(player1.getCoins() / 2);
                        player2.addCoins(player1.getCoins() / 2);
                    } else {
                        player2.addCoins(tempCoin);
                        player1.pay(tempCoin);
                    }
                    player1.setEquipped("AK-47");
                    player2.setEquipped("AK-47");
                    startGame = true;
                    isShopTrue = false;
                }
            }
            //check to see if the barret50 has been purchised
            if (Gdx.input.justTouched()) {
                if (touch.x > 615 && touch.x < 765 && touch.y > 270 && touch.y < 320) {
                    music = Gdx.audio.newMusic(Gdx.files.internal("Click.wav"));
                    music.setVolume(0.75f);
                    music.play();
                    player1.addCoins(player2.getCoins());
                    int tempCoin = player2.getCoins();
                    player2.pay(player2.getCoins());
                    if (player1.pay(250) == true) {
                        player1.setEquipped("Barret50");
                        player2.setEquipped("Barret50");
                        player1.pay(player1.getCoins() / 2);
                        player2.addCoins(player1.getCoins() / 2);
                    } else {
                        player2.addCoins(tempCoin);
                        player1.pay(tempCoin);
                    }
                    startGame = true;
                    isShopTrue = false;
                }
            }
            //check to see if the shotgun has been purchised
            if (Gdx.input.justTouched()) {
                if (touch.x > 615 && touch.x < 765 && touch.y > 120 && touch.y < 170) {
                    music = Gdx.audio.newMusic(Gdx.files.internal("Click.wav"));
                    music.setVolume(0.75f);
                    music.play();
                    player1.addCoins(player2.getCoins());
                    int tempCoin = player2.getCoins();
                    player2.pay(player2.getCoins());
                    if (player1.pay(150) == true) {
                        player1.setEquipped("ShotGun");
                        player2.setEquipped("ShotGun");
                        player1.pay(player1.getCoins() / 2);
                        player2.addCoins(player1.getCoins() / 2);
                    } else {
                        player2.addCoins(tempCoin);
                        player1.pay(tempCoin);
                    }
                    startGame = true;
                    isShopTrue = false;
                }
            }
        }
        //if the game has begn draw in the game             
        if (startGame == true) {
            //if all the zombies have been killed then allow the round to be ended
            if (zombiesKilled == totalZombies) {
                endWave = true;
                //if the f key is pressed end the round
                if (Gdx.input.isKeyPressed(Input.Keys.F)) {
                    waveIncrease = waveIncrease + 1;
                    wave = wave + 1;
                    nextGame = false;
                    startGame = false;
                }
            }
            //move the player one camera around player one, but dont let the camera go out side of the world boundaries
            if (playerOneCam.position.x - playerOneCam.viewportWidth / 2 >= 0 && playerOneCam.position.x + playerOneCam.viewportWidth / 2 <= map.getWorldWidth()) {
                playerOneCam.position.x = player1.getX();
            }
            if (playerOneCam.position.y - playerOneCam.viewportHeight / 2 >= -90 && playerOneCam.position.y + playerOneCam.viewportHeight / 2 <= map.getWorldHeight()) {
                playerOneCam.position.y = player1.getY();
            }
            if (playerOneCam.position.x - playerOneCam.viewportWidth / 2 < 0) {
                playerOneCam.position.x = playerOneCam.viewportWidth / 2;
            }
            if (playerOneCam.position.x + playerOneCam.viewportWidth / 2 > map.getWorldWidth()) {
                playerOneCam.position.x = map.getWorldWidth() - playerOneCam.viewportWidth / 2;
            }
            if (playerOneCam.position.y - playerOneCam.viewportHeight / 2 < -90) {
                playerOneCam.position.y = -90 + playerOneCam.viewportHeight / 2;
            }
            if (playerOneCam.position.y + playerOneCam.viewportHeight / 2 > map.getWorldHeight()) {
                playerOneCam.position.y = map.getWorldHeight() - playerOneCam.viewportHeight / 2;
            }
            playerOneCam.update();
            //move the player two camera around player two, but dont let the camera go outside of the world boundaries
            if (playerTwoCam.position.x - playerTwoCam.viewportWidth / 2 >= 0 && playerTwoCam.position.x + playerTwoCam.viewportWidth / 2 <= map.getWorldWidth()) {
                playerTwoCam.position.x = player2.getX();
            }
            if (playerTwoCam.position.y - playerTwoCam.viewportHeight / 2 >= -90 && playerTwoCam.position.y + playerTwoCam.viewportHeight / 2 <= map.getWorldHeight()) {
                playerTwoCam.position.y = player2.getY();
            }
            if (playerTwoCam.position.x - playerTwoCam.viewportWidth / 2 < 0) {
                playerTwoCam.position.x = playerTwoCam.viewportWidth / 2;
            }
            if (playerTwoCam.position.x + playerTwoCam.viewportWidth / 2 > map.getWorldWidth()) {
                playerTwoCam.position.x = map.getWorldWidth() - playerTwoCam.viewportWidth / 2;
            }
            if (playerTwoCam.position.y - playerTwoCam.viewportHeight / 2 < -90) {
                playerTwoCam.position.y = -90 + playerTwoCam.viewportHeight / 2;
            }
            if (playerTwoCam.position.y + playerTwoCam.viewportHeight / 2 > map.getWorldHeight()) {
                playerTwoCam.position.y = map.getWorldHeight() - playerTwoCam.viewportHeight / 2;
            }
            playerTwoCam.update();
            //as long as player one is alive move him around with W,A,S and D
            if (player1.getAlive()) {
                //if W is pressed move player one up
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    player1.moveUp();
                    rotation1 = 90;
                }
                //if S is pressed move player one down
                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    player1.moveDown();
                    rotation1 = 270;
                }
                //if A is pressed move player one left
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    player1.moveLeft();
                    rotation1 = 180;
                }
                //if D is pressed move player one right
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    player1.moveRight();
                    rotation1 = 0;
                }
                //if W and A is pressed move player one up and to the left
                if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.A)) {
                    rotation1 = 135;
                }
                //if W and D is pressed move player one up and to the right
                if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.D)) {
                    rotation1 = 45;
                }
                //if S and A is pressed move player one down and to the left
                if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.A)) {
                    rotation1 = 225;
                }
                //if S and D is pressed move player one down and to the right
                if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D)) {
                    rotation1 = 315;
                }
            }
            //as long as player two is alive move him around with the arrow keys
            if (player2.getAlive()) {
                //if UP is pressed move him up
                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    player2.moveUp();
                    rotation2 = 90;
                }
                //if DOWN is pressed move him down
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    player2.moveDown();
                    rotation2 = 270;
                }
                //if LEFT is pressed move him left
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    player2.moveLeft();
                    rotation2 = 180;
                }
                //if RIGHT is pressed move him right
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    player2.moveRight();
                    rotation2 = 0;
                }
                //if UP and LEFT is pressed move him up and left
                if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    rotation2 = 135;
                }
                //if UP and RIGHT is pressed move him up and right
                if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    rotation2 = 45;
                }
                //if DOWN and LEFT is pressed move him up and left
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    rotation2 = 225;
                }
                //if DOWN and RIGHT is pressed move him up and right
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    rotation2 = 315;
                }
            }
            //player collition with furniture
            for (Furniture f : map.getObjects()) {
                //if player one collides with a furniture push him back the opposite direction
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
                //if player two collides with a furniture push him back the opposite direction
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
            //if player one is walking outside of the world push him back in
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
            //if player rwo is walking outside of the world push him back in
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
            //if player one presses the space bar and is alive 
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                if (player1.getAlive()) {
                    for (Weapon w : this.worldWeapons) {
                        //depending on which gun they have shoot at the fire rate of the gun
                        if (w.getName().equals(player1.getEquipped())) {
                            if (TimeUtils.millis() - previousTime > w.fireRate()) {
                                //play the fire gun sound
                                music = Gdx.audio.newMusic(Gdx.files.internal("shot.wav"));
                                music.setVolume(0.15f);
                                music.play();
                                //create the bullet(s) by telling the gun where and with what stats
                                bullets.addAll(w.shootWeapon(w.getName(), rotation1, player1.getX(), player1.getY(), w.bulletSpeed(), w.damage(), w.fireRate(), w.numBullets()));
                                previousTime = TimeUtils.millis();

                            }
                        }
                    }
                }
            }
            //shooting for player 2
            //if player two presses the enter key and is alive
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                if (player2.getAlive()) {
                    for (Weapon w : this.worldWeapons) {
                        //depending on which gun they have shoot at the fire rate of the gun
                        if (w.getName().equals(player2.getEquipped())) {
                            if (TimeUtils.millis() - previousTime2 > w.fireRate()) {
                                //play the fire gun sound
                                music = Gdx.audio.newMusic(Gdx.files.internal("shot.wav"));
                                music.setVolume(0.15f);
                                music.play();
                                //create the bullet(s) by telling the gun where and with what stats
                                bullets.addAll(w.shootWeapon(w.getName(), rotation2, player2.getX(), player2.getY(), w.bulletSpeed(), w.damage(), w.fireRate(), w.numBullets()));
                                previousTime2 = TimeUtils.millis();
                            }
                        }
                    }
                }
            }
            //for all the zombies alive
            for (Zombie z : zombies) {
                if (z.getAlive()) {
                    //check if they have collided with player one and if so do damage to them every so often
                    if (z.collides(player1.getBounds())) {
                        if (TimeUtils.timeSinceMillis(previousTime3) > z.getHitRate()) {
                            player1.hit(z.attack());
                            previousTime3 = TimeUtils.millis();
                        }
                    }
                }
            }
            //for all the zombies alive
            for (Zombie z : zombies) {
                if (z.getAlive()) {
                    //check if they have collided with player two and if so do damage to them every so often
                    if (z.collides(player2.getBounds())) {
                        if (TimeUtils.timeSinceMillis(previousTime4) > z.getHitRate()) {
                            player2.hit(z.attack());
                            previousTime4 = TimeUtils.millis();
                        }
                    }
                }
            }
            //if both players have died then end the game
            if (player1.getAlive() == false && player2.getAlive() == false) {
                startGame = false;
                endGame = false;
            }
            //this is where the zombie movement is being done
            int j = -1;
            //for all the zombies
            for (Zombie z : zombies) {
                j++;
                //check to see which player they are closer to
                double distance1 = Math.sqrt((Math.pow(z.getX() - player1.getX(), 2)) + (Math.pow(z.getY() - player1.getY(), 2)));
                double distance2 = Math.sqrt((Math.pow(z.getX() - player2.getX(), 2)) + (Math.pow(z.getY() - player2.getY(), 2)));
                //if both the players are alive
                if (player1.getAlive() == true && player2.getAlive() == true) {
                    // they are closer to player one walk towards player one
                    if (distance1 < distance2) {
                        //depending on which side of the player they are on and below or above walk in the opposite direction to get closer to them
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
                        //if they are closer to player two then walk towards player two
                    } else if (distance1 > distance2) {
                        //depending on which side of the player they are on and below or above walk in the opposite direction to get closer to them
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
                    //if only player one is alive then only walk towards him
                } else if (player1.getAlive() == true && z.getAlive() == true) {
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
                    //if only player two is alive then only walk towards him
                } else if (player2.getAlive() == true && z.getAlive() == true) {
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
                //if the zombie selected is alive
                if (z.getAlive() == true) {
                    //go through all the other zombies that have not been selected yet
                    for (int i = j + 1; i < zombies.size(); i++) {
                        Zombie z2 = zombies.get(i);
                        //if that new zombie is alive check to see if they are to close to each other
                        if (z != z2) {
                            if (z2.getAlive() == true) {
                                double distanceZ = Math.sqrt((Math.pow(z2.getX() - z.getX(), 2)) + (Math.pow(z2.getY() - z.getY(), 2)));
                                if (distanceZ < 25) {
                                    //if they are to close check to see which player they are closer too
                                    distance1 = Math.sqrt((Math.pow(z2.getX() - player1.getX(), 2)) + (Math.pow(z2.getY() - player1.getY(), 2)));
                                    distance2 = Math.sqrt((Math.pow(z2.getX() - player2.getX(), 2)) + (Math.pow(z2.getY() - player2.getY(), 2)));
                                    if (distance1 < distance2) {
                                        //then move the second zombie in the opposite direction to bounch it off of the other zombie
                                        if (z2.getX() < player1.getX() && z2.getY() == player1.getY()) {
                                            z2.setRotation(0);
                                            z2.moveLeft();
                                        }
                                        if (z2.getX() < player1.getX() && z2.getY() < player1.getY()) {
                                            z2.setRotation(45);
                                            z2.moveLeft();
                                            z2.moveDown();
                                        }
                                        if (z2.getX() == player1.getX() && z2.getY() < player1.getY()) {
                                            z2.setRotation(90);
                                            z2.moveDown();
                                        }
                                        if (z2.getX() > player1.getX() && z2.getY() < player1.getY()) {
                                            z2.setRotation(135);
                                            z2.moveRight();
                                            z2.moveDown();
                                        }
                                        if (z2.getX() > player1.getX() && z2.getY() == player1.getY()) {
                                            z2.setRotation(180);
                                            z2.moveRight();
                                            z2.moveUp();
                                        }
                                        if (z2.getX() > player1.getX() && z2.getY() > player1.getY()) {
                                            z2.setRotation(225);
                                            z2.moveRight();
                                            z2.moveUp();
                                        }
                                        if (z2.getX() == player1.getX() && z2.getY() > player1.getY()) {
                                            z2.setRotation(270);
                                            z2.moveUp();
                                        }

                                        if (z2.getX() < player1.getX() && z2.getY() > player1.getY()) {
                                            z2.setRotation(315);
                                            z2.moveLeft();
                                            z2.moveUp();
                                        }

                                    } else if (distance1 > distance2) {
                                        if (z2.getX() < player2.getX() && z2.getY() == player2.getY()) {
                                            z2.setRotation(0);
                                            z2.moveLeft();
                                        }
                                        if (z2.getX() < player2.getX() && z2.getY() < player2.getY()) {
                                            z2.setRotation(45);
                                            z2.moveLeft();
                                            z2.moveDown();
                                        }
                                        if (z2.getX() == player2.getX() && z2.getY() < player2.getY()) {
                                            z2.setRotation(90);
                                            z2.moveDown();
                                        }
                                        if (z2.getX() > player2.getX() && z2.getY() < player2.getY()) {
                                            z2.setRotation(135);
                                            z2.moveRight();
                                            z2.moveDown();
                                        }
                                        if (z2.getX() > player2.getX() && z2.getY() == player2.getY()) {
                                            z2.setRotation(180);
                                            z2.moveRight();
                                            z2.moveUp();
                                        }
                                        if (z2.getX() > player2.getX() && z2.getY() > player2.getY()) {
                                            z2.setRotation(225);
                                            z2.moveRight();
                                            z2.moveUp();
                                        }
                                        if (z2.getX() == player2.getX() && z2.getY() > player2.getY()) {
                                            z2.setRotation(270);
                                            z2.moveUp();
                                        }

                                        if (z2.getX() < player2.getX() && z2.getY() > player2.getY()) {
                                            z2.setRotation(315);
                                            z2.moveLeft();
                                            z2.moveUp();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //check to see if any of the zombies collide with any furniture
                for (Furniture f : map.getObjects()) {
                    if (z.collides(f.f)) {
                        //if they do slow them down
                        z.setSpeed(0.4);
                        break;
                    } else {
                        //if not dont slow them down
                        z.setSpeed(1);
                    }
                }
            }
            //if a bullet is out of bounds destroy it here
            Iterator<Bullet> it = this.bullets.iterator();
            Iterator<Zombie> zom = this.zombies.iterator();
            //as long as there is a bullet to look at
            while (it.hasNext()) {
                Bullet b = it.next();
                b.bulletMovement();
                //check to see if it is out of bounds
                if (b.getX() > map.getWorldWidth() || b.getX() < 0 || b.getY() > map.getWorldHeight() || b.getY() < 0) {
                    //if it is destroy it
                    it.remove();
                } else {
                    //if it is not check to see if it has collided with a zombie
                    for (Zombie z : this.zombies) {
                        if (z.getAlive() == true) {
                            if (colidesWithZombie(b.getX(), b.getY(), z) == true) {
                                //if it has collided with a living zombie then damage the zombie
                                if (z.hit(b.getDamage()) <= 0) {
                                    //if the zombie has no more health then kill the zombie
                                    zombiesKilled++;
                                    System.out.println(zombiesKilled);
                                    z.die();
                                    this.pickups.create(z.getX(), z.getY()); //add a pickup when the zombie dies
                                }
                                //then destroy the bullet
                                it.remove();
                                break;
                            }
                        }
                    }
                }
            }
            //pickup collection happens here
            Vector2 vp1 = new Vector2(player1.getX(), player1.getY());
            Vector2 vp2 = new Vector2(player2.getX(), player2.getY());
            for (Weapon w : this.worldWeapons) {
                //if ammo is picked up then add ammo to the players guns
                if (w.getName().equals(player1.getEquipped())) {
                    w.addAmmo(pickups.updateAmmo(vp1));
                }
                if (w.getName().equals(player2.getEquipped())) {
                    w.addAmmo(pickups.updateAmmo(vp2));
                }
            }
            //if a coing is picked up add some coins to the player that grabbed it
            player1.addCoins(pickups.updateCoin(vp1));
            player2.addCoins(pickups.updateCoin(vp2));
            //if health was picked up then add some health to the player who grabbed it
            player1.addHealth(pickups.updateHealth(vp1));
            player2.addHealth(pickups.updateHealth(vp2));

            //this is where everything is drawn in
            //start off by drawing everything on player ones side of the screen (the left side)
            //set up the left side
            playerOneViewPort.setScreenX(0);
            playerOneViewPort.apply();
            shapeBatch.setProjectionMatrix(playerOneCam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            shapeBatch.end();
            batch.setProjectionMatrix(playerOneCam.combined);
            batch.begin();
            //draw the map
            map.draw(batch);
            //draw the pick up iteams
            pickups.draw(batch);
            //if player two is alive
            if (player2.getAlive()) {
                //draw him in
                batch.draw(chr1IMG, player2.getX(), player2.getY(), player2.getWidth() / 2, player2.getHeight() / 2, player2.getWidth(), player2.getHeight(), 1, 1, rotation2, 0, 0, chr1IMG.getWidth(), chr1IMG.getHeight(), false, false);
            } else {
                //if he is dead draw in a gravestone instead
                batch.draw(graveStone, player2.getX(), player2.getY(), player2.getWidth() / 2, player2.getHeight() / 2, player2.getWidth(), player2.getHeight(), 1, 1, 0, 0, 0, graveStone.getWidth(), graveStone.getHeight(), false, false);
            }
            //if player one is alive
            if (player1.getAlive()) {
                //draw him in
                batch.draw(chr1IMG, player1.getX(), player1.getY(), player1.getWidth() / 2, player1.getHeight() / 2, player1.getWidth(), player1.getHeight(), 1, 1, rotation1, 0, 0, chr1IMG.getWidth(), chr1IMG.getHeight(), false, false);
            } else {
                //if he is dead draw in a gravestone instead
                batch.draw(graveStone, player1.getX(), player1.getY(), player1.getWidth() / 2, player1.getHeight() / 2, player1.getWidth(), player1.getHeight(), 1, 1, 0, 0, 0, graveStone.getWidth(), graveStone.getHeight(), false, false);
            }
            //draw in all the zombies
            for (Zombie z : zombies) {
                if (z.getAlive() == true) {
                    batch.draw(zomIMG, z.getX(), z.getY(), z.getWidth() / 2, z.getHeight() / 2, z.getWidth(), z.getHeight(), 1, 1, z.getRotation(), 0, 0, zomIMG.getWidth(), zomIMG.getHeight(), false, false);
                }
            }
            batch.end();
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            shapeBatch.setColor(Color.WHITE);
            //draw in all the bullets
            for (Bullet b : this.bullets) {
                b.drawBullet(shapeBatch);
            }
            shapeBatch.setColor(Color.BLACK);
            //draw in screen divider
            shapeBatch.rect((playerOneCam.position.x + (playerOneCam.viewportWidth / 2)) - 5, (playerOneCam.position.y - (playerOneCam.viewportHeight / 2)), 5, playerOneCam.viewportHeight);
            shapeBatch.end();
            hud1.draw(shapeBatch, batch, player1, playerOneCam); //draw player 1 hud
            //if the round has been won then draw in the countinue text
            if (endWave == true) {
                hud1.pressF(batch, playerOneCam);
            }

            //now draw in everything on player twos side (the right side)
            //set up the right side
            playerTwoViewPort.setScreenX(Gdx.graphics.getWidth() / 2);
            playerTwoViewPort.apply();
            shapeBatch.setProjectionMatrix(playerTwoCam.combined);
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            shapeBatch.setColor(Color.BLACK);
            shapeBatch.end();
            batch.setProjectionMatrix(playerTwoCam.combined);
            batch.begin();
            //draw in the map
            map.draw(batch);
            //draw in the pickup iteams
            pickups.draw(batch);
            //if player two is alive 
            if (player2.getAlive()) {
                //draw him in
                batch.draw(chr1IMG, player2.getX(), player2.getY(), player2.getWidth() / 2, player2.getHeight() / 2, player2.getWidth(), player2.getHeight(), 1, 1, rotation2, 0, 0, chr1IMG.getWidth(), chr1IMG.getHeight(), false, false);
            } else {
                //if he is dead draw in a gravestone instead
                batch.draw(graveStone, player2.getX(), player2.getY(), player2.getWidth() / 2, player2.getHeight() / 2, player2.getWidth(), player2.getHeight(), 1, 1, 0, 0, 0, graveStone.getWidth(), graveStone.getHeight(), false, false);
            }
            //if player one is alive
            if (player1.getAlive()) {
                //draw him in
                batch.draw(chr1IMG, player1.getX(), player1.getY(), player1.getWidth() / 2, player1.getHeight() / 2, player1.getWidth(), player1.getHeight(), 1, 1, rotation1, 0, 0, chr1IMG.getWidth(), chr1IMG.getHeight(), false, false);
            } else {
                //if he is dead draw in a gravestone instead
                batch.draw(graveStone, player1.getX(), player1.getY(), player1.getWidth() / 2, player1.getHeight() / 2, player1.getWidth(), player1.getHeight(), 1, 1, 0, 0, 0, graveStone.getWidth(), graveStone.getHeight(), false, false);
            }
            //draw in all the zombies
            for (Zombie z : zombies) {
                if (z.getAlive() == true) {
                    batch.draw(zomIMG, z.getX(), z.getY(), z.getWidth() / 2, z.getHeight() / 2, 45, 45, 1, 1, z.getRotation(), 0, 0, zomIMG.getWidth(), zomIMG.getHeight(), false, false);
                }
            }
            batch.end();
            shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
            shapeBatch.setColor(Color.WHITE);
            //draw in all the bullets
            for (Bullet b : this.bullets) {
                b.drawBullet(shapeBatch);
            }
            shapeBatch.end();
            hud2.draw(shapeBatch, batch, player2, playerTwoCam);//draw player 2 hud
            pickups.dispose();//clear used pickups
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    /**
     * This is where the two split screen are resized for the window size
     *
     *
     * @param width the width of the window
     * @param height the height of the window
     */
    @Override
    public void resize(int width, int height) {
        //if a round is currently under go
        if (startGame == true) {
            //set both player cameras half the size of the screen
            playerOneViewPort.update(Gdx.graphics.getWidth() / 2, height);
            playerTwoViewPort.update(Gdx.graphics.getWidth() / 2, height);
            //put player ones camera on the left side of the screen
            playerOneViewPort.setScreenX(0);
            //put player twos camera to the right side of the screen
            playerTwoViewPort.setScreenX(Gdx.graphics.getWidth() / 2);
            playerTwoViewPort.apply();
            playerOneViewPort.apply();
            //if we are in a menu screen 
        } else {
            //set the menu camera to the entire screen szie
            menuViewPort.update(width, height);

        }
    }

    /**
     * This is where we check if a bullet has collided with a zombie
     *
     * @param bX the bullets x position
     * @param bY the bullets y position
     * @param z the zombie we are checking with
     * @return if they collide or not
     */
    public boolean colidesWithZombie(float bX, float bY, Zombie z) {
        //check if the bullet is overlaping the zombie
        if (bX + 10 >= z.getX() && bX <= z.getX() + z.getWidth() / 2 && bY + 10 >= z.getY() && bY <= z.getY() + z.getHeight() / 2) {
            //if it is then return true
            return true;
        }
        //if not return false
        return false;
    }

}

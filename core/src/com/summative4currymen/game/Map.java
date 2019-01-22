/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author riepj9547
 */
public class Map {

    private Room[] room;
    private Texture ground1;
    private Texture ground2;
    private Texture ground3;
    private Texture ground4;
    private Texture ground5;
    private Texture ground6;
    private Texture whiteGround;
    private Texture blackGround;
    private int worldHeight;
    private int worldWidth;

    /**
     * Load in all the textures and generate 1 room
     */
    public Map(){
        ground1 = new Texture("ground2.png");
        ground2 = new Texture("carpet2.png");
        ground3 = new Texture("ground3.png");
        ground4 = new Texture("ground4.png");
        ground5 = new Texture("block.png");
        ground6 = new Texture("discoFloor.png");
        whiteGround = new Texture("whiteGround.png");
        blackGround = new Texture("blackGround.png");

        worldWidth = 1000;
        worldHeight = 1000;
        room = new Room[1];
        room[0] = new Room(0,0,worldWidth, worldHeight);
        room[0].setnumSmallRooms(1);
        
    }
    /**
     * Getter for all the furniture that has been made
     * @return all the furniture that has been generated through out the map
     */
    public ArrayList<Furniture> getObjects() {
        ArrayList<Furniture> furniture = new ArrayList<Furniture>();
        //go through the rooms a add the furniture to the master list
        for (Room r : room) {
            furniture.addAll(r.getFurniture());
        }
        return furniture;
    }

    /**
     * Getter for the world height
     * @return the world height
     */
    public int getWorldHeight() {
        return this.worldHeight;
    }

    /**
     * Getter for the world width
     * @return the world width
     */
    public int getWorldWidth() {
        return this.worldWidth;
    }

    /**
     * Draw in all the rooms and boarder walls
     * @param batch the batch currently drawing in things
     */
    public void draw(SpriteBatch batch) {
        //draw the ground in
        batch.draw(ground3, 0, 0, worldWidth, worldHeight);
        //go through all the rooms and draw them in
        for (Room room1 : room) {
            room1.draw(batch);
        }
        //boarders of the map
        batch.draw(blackGround, -10, 0, 10, worldHeight);
        batch.draw(blackGround, 0, -10, worldWidth, 10);
        batch.draw(blackGround, 0, worldHeight, worldWidth, 10);
        batch.draw(blackGround, worldHeight, 0, 10, worldHeight);
    }
}

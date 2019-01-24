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
 * @author Jared
 */
public class Room {

    public float x;
    public float y;
    public float width;
    public float height;
    private int numSmallRooms;
    private Rectangle[] rect;
    private ArrayList<Furniture> furniture;

    private Texture bigTable;
    private Texture smallTable;
    private Texture bed;
    private Texture couch;
    private Texture chair;
    private Texture carpet;
    private Texture rock;

    private Texture cactus;
    private Texture blackGround;

    /**
     * Create a room with the specific values
     * @param x position
     * @param y position
     * @param width
     * @param height 
     */
    public Room(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.numSmallRooms = 0;
        furniture = new ArrayList<Furniture>();
        bigTable = new Texture("BigTable.png");
        smallTable = new Texture("smallTable.png");
        couch = new Texture("couch.png");
        bed = new Texture("bed.png");
        carpet = new Texture("carpet.png");
        chair = new Texture("chair.png");
        rock = new Texture("rock.png");

        cactus = new Texture("cactus.png");
        blackGround = new Texture("blackGround.png");

    }

    /**
     * Setter for number of small rooms
     * @param num number of small rooms
     */
    public void setnumSmallRooms(int num) {
        this.numSmallRooms = num;
    }

    /**
     * Getter for number of small rooms
     * @return number of small rooms
     */
    public int getnumSmallRooms() {
        return this.numSmallRooms;
    }

    /**
     * Build the rooms that have been asked to be built
     */
    public void buildRoom() {
        switch (getnumSmallRooms()) {
            case 1:
                //create a furniture object with a name, x, y, width, and height
                Furniture f = new Furniture("rock",10,10,50,50);    
                furniture.add(f);
                break;
            case 3:                 
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                break;
        }
    }
    /**
     * Getter for all the furniture in this room
     * @return the list of furniture in this room
     */
    public ArrayList<Furniture> getFurniture() {
        ArrayList<Furniture> objects = new ArrayList<Furniture>();
        for (Furniture f : furniture) {
            //as long as it is not a carpet add the object to the list
            if (!f.getName().equals("carpet")) {
                objects.add(f);
            }
        }
        return objects;
    }

    /**
     * Draw the furniture that is in the room
     * @param batch the current batch drawing in things
     */
    public void draw(SpriteBatch batch) {
        for(Furniture f:furniture){
            batch.draw(blackGround,f.f.x, f.f.y, f.f.width, f.f.height);
        }
    }
}

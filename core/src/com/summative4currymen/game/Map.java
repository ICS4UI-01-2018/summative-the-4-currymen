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

    public Map() {
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
        int upper = 101;
        int lower = 1;
        int num = (int) (Math.random() * (upper - lower)) + lower;
        num = 1;
        System.out.println("RANDOM NUMBER IS " + num);
        int rooms = 0;
        if (num >= 1 && num <= 33) {
            rooms = 2;
            room = new Room[2];
            room[0] = new Room(0,0,worldWidth / 2,worldHeight);
            room[1] = new Room(500, 100, 500, worldHeight);
            upper = 7;
            lower = 3;
            int smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[0].setnumSmallRooms(3);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[1].setnumSmallRooms(3);
            System.out.println("ROOM TYPE " + smallRooms);
            //build the rooms
            room[0].buildRoom();
            room[1].buildRoom();
        } else if (num > 33 && num <= 66) {
            rooms = 3;
            room = new Room[3];
            upper = 3;
            lower = 1;
            int ranNum = (int) (Math.random() * (upper - lower)) + lower;
            if (ranNum == 1) {
                room[0] = new Room(0, 0, worldWidth / 3, worldHeight / 3);
                room[1] = new Room(0, worldHeight / 3 + worldHeight / 3, worldWidth / 3, worldHeight / 3);
                room[2] = new Room(worldWidth / 3 + worldWidth / 3, 0, worldWidth / 3, worldHeight);

            } else {
                room[0] = new Room(0, 0, worldWidth / 3, worldHeight );
                room[1] = new Room(worldWidth / 3 + worldWidth / 3, worldHeight / 3 + worldHeight / 3, worldWidth / 3, worldHeight / 3);
                room[2] = new Room(worldWidth / 3 + worldWidth / 3, 0, worldWidth / 3, worldHeight/3);
            }            
            upper = 7;
            lower = 3;
            int smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[0].setnumSmallRooms(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[1].setnumSmallRooms(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[2].setnumSmallRooms(smallRooms);            
            //build the rooms
            room[0].buildRoom();
            room[1].buildRoom();
            room[2].buildRoom();            
        } else if (num > 66 && num <= 100) {
            rooms = 4;
            room = new Room[4];
            room[0] = new Room(0, 0, worldWidth / 3, worldHeight / 3);
            room[1] = new Room(0, worldHeight / 3 + worldHeight / 3, worldWidth / 3, worldHeight / 3);
            room[2] = new Room(worldWidth / 3 + worldWidth / 3, worldHeight / 3 + worldHeight / 3, worldWidth / 3, worldHeight / 3);
            room[3] = new Room(worldWidth / 3 + worldWidth / 3, 0, worldWidth / 3, worldHeight / 3);;
            upper = 7;
            lower = 3;
            int smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[0].setnumSmallRooms(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[1].setnumSmallRooms(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[2].setnumSmallRooms(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[3].setnumSmallRooms(smallRooms);
            //build the rooms
            room[0].buildRoom();
            room[1].buildRoom();
            room[2].buildRoom();
            room[3].buildRoom();
        }
        /*else if (num > 20 && num <= 60) {
            rooms = 3;
            room = new Room[3];
            upper = 3;
            lower = 1;
            int thirdRoom = (int) (Math.random() * (upper - lower)) + lower;
            if (thirdRoom == 1) {
                room[0] = new Room(0, 0, worldWidth / 2, worldHeight / 2);
                room[1] = new Room(worldWidth / 2, 0, worldWidth / 2, worldHeight);
                room[2] = new Room(0, worldHeight / 2, worldWidth / 2, worldHeight / 2);
            } else if (thirdRoom == 2) {
                room[0] = new Room(0, 0, worldWidth / 2, worldHeight);
                room[1] = new Room(worldWidth / 2, 0, worldWidth / 2, worldHeight / 2);
                room[2] = new Room(worldWidth / 2, worldHeight / 2, worldWidth / 2, worldHeight / 2);
            }
            int[] roomType = new int[3];
            upper = 6;
            lower = 1;
            int smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[0].setRoomType(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[1].setRoomType(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[2].setRoomType(smallRooms);
            //build the rooms
            room[0].buildRoom();
            room[1].buildRoom();
            room[2].buildRoom();
        } else if (num > 60 && num <= 85) {
            rooms = 4;
            room = new Room[4];
            int[] roomType = new int[4];
            upper = 6;
            room[0] = new Room(0, 0, worldWidth / 2, worldHeight / 2);
            room[1] = new Room(worldWidth / 2, 0, worldWidth / 2, worldHeight / 2);
            room[2] = new Room(0, worldHeight / 2, worldWidth / 2, worldHeight / 2);
            room[3] = new Room(worldWidth / 2, worldHeight / 2, worldWidth / 2, worldHeight / 2);
            lower = 1;
            int smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[0].setRoomType(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[1].setRoomType(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[2].setRoomType(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[3].setRoomType(smallRooms);
            //build the rooms
            room[0].buildRoom();
            room[1].buildRoom();
            room[2].buildRoom();
            room[3].buildRoom();
        } else if (num > 85 && num <= 100) {
            rooms = 5;
            room = new Room[5];
            upper = 5;
            lower = 1;
            int fithRoom = (int) (Math.random() * (upper - lower)) + lower;
            if (fithRoom == 1) {
                room[0] = new Room(0, 0, worldWidth / 4, worldHeight / 2);
                room[1] = new Room(worldWidth / 2, 0, worldWidth / 2, worldHeight / 2);
                room[2] = new Room(0, worldHeight / 2, worldWidth / 2, worldHeight / 2);
                room[3] = new Room(worldWidth / 2, worldHeight / 2, worldWidth / 2, worldHeight / 2);
                room[4] = new Room(worldWidth / 4, 0, worldWidth / 4, worldHeight / 2);
            } else if (fithRoom == 2) {
                room[0] = new Room(0, 0, worldWidth / 2, worldHeight / 2);
                room[1] = new Room(worldWidth / 2, 0, worldWidth / 2, worldHeight / 2);
                room[2] = new Room(0, worldHeight / 2, worldWidth / 4, worldHeight / 2);
                room[3] = new Room(worldWidth / 2, worldHeight / 2, worldWidth / 2, worldHeight / 2);
                room[4] = new Room(worldWidth / 4, worldHeight / 2, worldWidth / 4, worldHeight / 2);
            } else if (fithRoom == 3) {
                room[0] = new Room(0, 0, worldWidth / 2, worldHeight / 2);
                room[1] = new Room(worldHeight / 2, 0, worldWidth / 4, worldHeight / 2);
                room[2] = new Room(0, worldHeight / 2, worldWidth / 2, worldHeight / 2);
                room[3] = new Room(worldWidth / 2, worldHeight / 2, worldWidth / 2, worldHeight / 2);
                room[4] = new Room(worldWidth / 2 + worldWidth / 4, 0, worldWidth / 4, worldHeight / 2);
            } else if (fithRoom == 4) {
                room[0] = new Room(0, 0, worldWidth / 2, worldHeight / 2);
                room[1] = new Room(worldWidth / 2, 0, worldWidth / 2, worldHeight / 2);
                room[2] = new Room(0, worldHeight / 2, worldWidth / 2, worldHeight / 2);
                room[3] = new Room(worldWidth / 2, worldHeight / 2, worldWidth / 4, worldHeight / 2);
                room[4] = new Room(worldWidth / 2 + worldWidth / 4, worldHeight / 2, worldWidth / 4, worldHeight / 2);
            }
            int[] roomType = new int[5];
            upper = 6;
            lower = 1;
            int smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[0].setRoomType(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[1].setRoomType(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[2].setRoomType(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[3].setRoomType(smallRooms);
            smallRooms = (int) (Math.random() * (upper - lower)) + lower;
            room[4].setRoomType(smallRooms);
            //build the rooms
            room[0].buildRoom();
            room[1].buildRoom();
            room[2].buildRoom();
            room[3].buildRoom();
            room[4].buildRoom();
        }
        System.out.println("NUMBER OF ROOMS " + rooms);*/
    }

    public ArrayList<Furniture> getObjects() {
        ArrayList<Furniture> furniture = new ArrayList<Furniture>();
        for (Room r : room) {
            furniture.addAll(r.getFurniture());
        }
        return furniture;
    }

    public int getWorldHeight() {
        return this.worldHeight;
    }

    public int getWorldWidth() {
        return this.worldWidth;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(ground3, 0, 0, worldWidth, worldHeight);
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

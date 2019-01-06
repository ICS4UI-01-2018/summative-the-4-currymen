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
        //hard code 1 room always vv
        num = 2;
        //^^
        System.out.println("RANDOM NUMBER IS " + num);
        int rooms = 0;
        if (num >= 1 && num <= 7) {
            rooms = 1;
            room = new Room[1];
            room[0] = new Room(0, 0, worldWidth, worldHeight);
            int[] roomType = new int[1];
            upper = 5;
            lower = 1;
            int numType = (int) (Math.random() * (upper - lower)) + lower;
            room[0].setRoomType(numType);
            System.out.println("ROOM TYPE "+ numType);
            //build the rooms
            room[0].buildRoom();
        } else if (num > 7 && num <= 20) {
            rooms = 2;
            room = new Room[2];
            room[0] = new Room(0, 0, worldWidth / 2, worldHeight);
            room[1] = new Room(worldWidth / 2, 0, worldWidth / 2, worldHeight);
            int[] roomType = new int[2];
            upper = 6;
            lower = 1;
            int numType = (int) (Math.random() * (upper - lower)) + lower;
            room[0].setRoomType(numType);
            numType = (int) (Math.random() * (upper - lower)) + lower;
            room[1].setRoomType(numType);
            //build the rooms
            room[0].buildRoom();
            room[1].buildRoom();
        } else if (num > 20 && num <= 60) {
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
            int numType = (int) (Math.random() * (upper - lower)) + lower;
            room[0].setRoomType(numType);
            numType = (int) (Math.random() * (upper - lower)) + lower;
            room[1].setRoomType(numType);
            numType = (int) (Math.random() * (upper - lower)) + lower;
            room[2].setRoomType(numType);
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
            int numType = (int) (Math.random() * (upper - lower)) + lower;
            room[0].setRoomType(numType);
            numType = (int) (Math.random() * (upper - lower)) + lower;
            room[1].setRoomType(numType);
            numType = (int) (Math.random() * (upper - lower)) + lower;
            room[2].setRoomType(numType);
            numType = (int) (Math.random() * (upper - lower)) + lower;
            room[3].setRoomType(numType);
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
            int numType = (int) (Math.random() * (upper - lower)) + lower;
            room[0].setRoomType(numType);
            numType = (int) (Math.random() * (upper - lower)) + lower;
            room[1].setRoomType(numType);
            numType = (int) (Math.random() * (upper - lower)) + lower;
            room[2].setRoomType(numType);
            numType = (int) (Math.random() * (upper - lower)) + lower;
            room[3].setRoomType(numType);
            numType = (int) (Math.random() * (upper - lower)) + lower;
            room[4].setRoomType(numType);
            //build the rooms
            room[0].buildRoom();
            room[1].buildRoom();
            room[2].buildRoom();
            room[3].buildRoom();
            room[4].buildRoom();
        }
        System.out.println("NUMBER OF ROOMS " + rooms);
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
        //batch.draw(whiteGround, 0, 0, worldWidth,worldHeight);
        for (Room room1 : room) {
            switch (room1.getRoomType()) {
                case 1:
                    batch.draw(ground4, room1.x, room1.y, room1.width, room1.height);
                    break;
                case 2:
                    batch.draw(ground3, room1.x, room1.y, room1.width, room1.height);
                    break;
                case 3:
                    batch.draw(ground5, room1.x, room1.y, room1.width, room1.height);
                    break;
                case 4:
                    batch.draw(ground6, room1.x, room1.y, room1.width, room1.height);
                    break;
                case 5:
                    // batch.draw(ground1, room1.x, room1.y, room1.width, room1.height);
                    break;
                default:
                    break;
            }

            room1.draw(batch);
        }
        batch.draw(blackGround, -10, 0, 10, worldHeight);
        batch.draw(blackGround, 0, -10, worldWidth, 10);
        batch.draw(blackGround, 0, worldHeight, worldWidth, 10);
        batch.draw(blackGround, worldHeight, 0, 10, worldHeight);
    }
}

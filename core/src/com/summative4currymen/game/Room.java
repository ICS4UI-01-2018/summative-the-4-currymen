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
    private int roomType;
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

    public Room(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.roomType = 0;
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

    public void setRoomType(int num) {
        this.roomType = num;
    }

    public int getRoomType() {
        return this.roomType;
    }

    public void buildRoom() {
        switch (roomType) {
            case 1:
                int upper = 13;
                int lower = 6;
                //6 to 12 couchs
                int num = (int) (Math.random() * (upper - lower)) + lower;
                for (int i = 0; i < num; i++) {
                    float upperw = 65;
                    float lowerw = 35;
                    float fWidth = (int) (Math.random() * (upperw - lowerw)) + lowerw;
                    float fHeight = fWidth - fWidth / 8;
                    float upperx = this.x + this.width - fWidth;//the size of the room - the width of the cactus
                    float lowerx = this.x;
                    float posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
                    float uppery = this.y + this.height - fHeight;//the size of the room - the height of the cactus
                    float lowery = this.y;
                    float posY = (int) (Math.random() * (uppery - lowery)) + lowery;
                    Furniture c = new Furniture("cactus", posX, posY, fWidth, fHeight);
                    furniture.add(c);
                }
                break;
            case 2:
                upper = 8;
                lower = 4;
                //6 to 12 couchs
                num = (int) (Math.random() * (upper - lower)) + lower;
                for (int i = 0; i < num; i++) {
                    float upperx = this.x + this.width - 125;//the size of the room - the width of the cactus
                    float lowerx = this.x;
                    float posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
                    float uppery = this.y + this.height - 125;//the size of the room - the height of the cactus
                    float lowery = this.y;
                    float posY = (int) (Math.random() * (uppery - lowery)) + lowery;
                    Furniture c = new Furniture("block", posX, posY, 125, 125);
                    furniture.add(c);
                }
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    public void buildRoomOLD() {
        if (roomType == 1) {
            //living room
            float upperx = this.x + this.width - 90;//the size of the room - the width of the carpet
            float lowerx = this.x;
            float posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
            float uppery = this.y + this.height - 125;//the size of the room - the height of the carpet
            float lowery = this.y;
            float posY = (int) (Math.random() * (uppery - lowery)) + lowery;
            Furniture c = new Furniture("carpet", posX, posY, 0, 0);
            furniture.add(c);
            int upper = 3;
            int lower = 1;
            //1 to 2 couchs
            int num = (int) (Math.random() * (upper - lower)) + lower;
            for (int i = 0; i < num; i++) {
                upperx = this.x + this.width - 100; //the size of the room - the width of the couch
                lowerx = this.x;
                posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
                uppery = this.y + this.height - 45;//the size of the room - the height of the couch
                lowery = this.y;
                posY = (int) (Math.random() * (uppery - lowery)) + lowery;
                c = new Furniture("couch", posX, posY, 0, 0);
                furniture.add(c);
            }
            upper = 5;
            lower = 1;
            //1 to 4 chairs
            num = (int) (Math.random() * (upper - lower)) + lower;
            for (int i = 0; i < num; i++) {
                upperx = this.x + this.width - 30;//the size of the room - the width of the chair
                lowerx = this.x;
                posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
                uppery = this.y + this.height - 30;//the size of the room - the height of the chair
                lowery = this.y;
                posY = (int) (Math.random() * (uppery - lowery)) + lowery;
                c = new Furniture("chair", posX, posY, 0, 0);
                furniture.add(c);
            }

        } else if (roomType == 2) {
            //kitchen
            int upper = 6;
            int lower = 2;
            //2 to 5 chairs
            int num = (int) (Math.random() * (upper - lower)) + lower;
            for (int i = 0; i < num; i++) {
                float upperx = this.x + this.width - 30;//the size of the room - the width of the chair
                float lowerx = this.x;
                float posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
                float uppery = this.y + this.height - 30;//the size of the room - the height of the chair
                float lowery = this.y;
                float posY = (int) (Math.random() * (uppery - lowery)) + lowery;
                Furniture c = new Furniture("chair", posX, posY, 0, 0);
                furniture.add(c);
            }
            upper = 4;
            lower = 1;
            //1 to 3 tables
            num = (int) (Math.random() * (upper - lower)) + lower;
            for (int i = 0; i < num; i++) {
                float upperx = this.x + this.width - 60;//the size of the room - the width of the table
                float lowerx = this.x;
                float posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
                float uppery = this.y + this.height - 60;//the size of the room - the height of the table
                float lowery = this.y;
                float posY = (int) (Math.random() * (uppery - lowery)) + lowery;
                Furniture t = new Furniture("small table", posX, posY, 0, 0);
                furniture.add(t);
            }
        } else if (roomType == 3) {
            //bedroom
            float upperx = this.x + this.width - 90;//the size of the room - the width of the carpet
            float lowerx = this.x;
            float posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
            float uppery = this.y + this.height - 125;//the size of the room - the height of the carpet
            float lowery = this.y;
            float posY = (int) (Math.random() * (uppery - lowery)) + lowery;
            Furniture c = new Furniture("carpet", posX, posY, 0, 0);
            furniture.add(c);
            int upper = 3;
            int lower = 1;
            //1 to 2 beds
            int num = (int) (Math.random() * (upper - lower)) + lower;
            for (int i = 0; i < num; i++) {
                upperx = this.x + this.width - 70;//the size of the room - the width of the bed
                lowerx = this.x;
                posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
                uppery = this.y + this.height - 140;//the size of the room - the height of the bed
                lowery = this.y;
                posY = (int) (Math.random() * (uppery - lowery)) + lowery;
                Furniture b = new Furniture("bed", posX, posY, 0, 0);
                furniture.add(b);
            }
            upper = 3;
            lower = 0;
            //0 to 2 tables
            num = (int) (Math.random() * (upper - lower)) + lower;
            for (int i = 0; i < num; i++) {
                upperx = this.x + this.width - 60;//the size of the room - the width of the table
                lowerx = this.x;
                posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
                uppery = this.y + this.height - 60;//the size of the room - the height of the table
                lowery = this.y;
                posY = (int) (Math.random() * (uppery - lowery)) + lowery;
                Furniture t = new Furniture("small table", posX, posY, 0, 0);
                furniture.add(t);
            }
        } else if (roomType == 4) {
            //disco room

        } else if (roomType == 5) {
            //dinning room
            float upperx = this.x + this.width - 70;//the size of the room - the width of the carpet
            float lowerx = this.x;
            float posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
            float uppery = this.y + this.height - 150;//the size of the room - the height of the carpet
            float lowery = this.y;
            float posY = (int) (Math.random() * (uppery - lowery)) + lowery;
            Furniture t = new Furniture("big table", posX, posY, 0, 0);
            furniture.add(t);
            int upper = 8;
            int lower = 3;
            //3 to 7 chairs
            int num = (int) (Math.random() * (upper - lower)) + lower;
            for (int i = 0; i < num; i++) {
                upperx = this.x + this.width - 30;//the size of the room - the width of the chair
                lowerx = this.x;
                posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
                uppery = this.y + this.height - 30;//the size of the room - the height of the chair
                lowery = this.y;
                posY = (int) (Math.random() * (uppery - lowery)) + lowery;
                Furniture c = new Furniture("chair", posX, posY, 0, 0);
                furniture.add(c);
            }
        }
    }

    public ArrayList<Furniture> getFurniture() {
        ArrayList<Furniture> objects = new ArrayList<Furniture>();
        for (Furniture f : furniture) {
            if (!f.getName().equals("carpet")) {
                objects.add(f);
            }
        }
        return objects;
    }

    public void draw(SpriteBatch batch) {

        for (Furniture f : furniture) {
            if (f.getName().equals("carpet")) {
                batch.draw(carpet, f.f.x, f.f.y, f.f.width, f.f.height);
            } else if (f.getName().equals("big table")) {
                batch.draw(bigTable, f.f.x, f.f.y, f.f.width, f.f.height);
            } else if (f.getName().equals("small table")) {
                batch.draw(smallTable, f.f.x, f.f.y, f.f.width, f.f.height);
            } else if (f.getName().equals("chair")) {
                batch.draw(chair, f.f.x, f.f.y, f.f.width, f.f.height);
            } else if (f.getName().equals("bed")) {
                batch.draw(bed, f.f.x, f.f.y, f.f.width, f.f.height);
            } else if (f.getName().equals("couch")) {
                batch.draw(couch, f.f.x, f.f.y, f.f.width, f.f.height);
            } else if (f.getName().equals("cactus")) {
                batch.draw(cactus, f.f.x, f.f.y, f.f.width, f.f.height);
            } else if (f.getName().equals("block")) {
                batch.draw(blackGround, f.f.x, f.f.y, f.f.width, f.f.height);
            }

        }
    }
}

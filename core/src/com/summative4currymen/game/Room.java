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

    public void setnumSmallRooms(int num) {
        this.numSmallRooms = num;
    }

    public int getnumSmallRooms() {
        return this.numSmallRooms;
    }

    public void buildRoom() {
        switch (getnumSmallRooms()) {
            case 1:
                Furniture f = new Furniture("wall",this.x,this.y,this.width,5);
                Furniture f2 = new Furniture("wall",this.x,this.y,5,this.height);
                Furniture f3 = new Furniture("wall",this.x,this.y,this.width,5);
                break;
            case 3:
                 f = new Furniture("wall",-5,this.height/3,10,this.height/3 + this.height/3);
                 f2 = new Furniture("wall",(this.width/2)-5,this.height/3,10,this.height/3 + this.height/3);
                 f3 = new Furniture("wall",0,this.height/3-5,this.width,10);
                furniture.add(f);
                furniture.add(f2);
                furniture.add(f3);
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

    public void buildRoomOld() {
        if (numSmallRooms == 1) {
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

        } else if (numSmallRooms == 2) {
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
        } else if (numSmallRooms == 3) {
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
        } else if (numSmallRooms == 4) {
            //disco room

        } else if (numSmallRooms == 5) {
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
        for(Furniture f:furniture){
            batch.draw(blackGround,f.f.x, f.f.y, f.f.width, f.f.height);
        }

       /* for (Furniture f : furniture) {
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

        }*/
    }
}

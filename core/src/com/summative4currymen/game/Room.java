/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

import com.badlogic.gdx.graphics.Color;
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

    public Room(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.roomType = 0;
        furniture = new ArrayList<Furniture>();
    }

    public void setRoomType(int num) {
        this.roomType = num;
    }

    public int getRoomType() {
        return this.roomType;
    }

    public void buildRoom() {
        if (roomType == 1) {
            //living room
            float upperx = this.x + this.width - 90;//the size of the room - the width of the carpet
            float lowerx = this.x;
            float posX = (int) (Math.random() * (upperx - lowerx)) + lowerx;
            float uppery = this.y + this.height - 125;//the size of the room - the height of the carpet
            float lowery = this.y;
            float posY = (int) (Math.random() * (uppery - lowery)) + lowery;
            Furniture c = new Furniture("carpet", posX, posY);
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
                c = new Furniture("couch", posX, posY);
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
                c = new Furniture("chair", posX, posY);
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
                Furniture c = new Furniture("chair", posX, posY);
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
                Furniture t = new Furniture("small table", posX, posY);
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
            Furniture c = new Furniture("carpet", posX, posY);
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
                Furniture b = new Furniture("bed", posX, posY);
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
                Furniture t = new Furniture("small table", posX, posY);
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
            Furniture t = new Furniture("big table", posX, posY);
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
                Furniture c = new Furniture("chair", posX, posY);
                furniture.add(c);
            }
        }
    }

    public ArrayList<Furniture> getFurniture(){
        return this.furniture;
    }
    public void draw(ShapeRenderer shapeBatch) {

        for (Furniture f : furniture) {
            if (f.getName().equals("carpet")) {
                shapeBatch.setColor(Color.PINK);
            } else {
                shapeBatch.setColor(Color.BLACK);
            }
            shapeBatch.rect(f.f.x,f.f.y,f.f.width,f.f.height);
        }
    }
}

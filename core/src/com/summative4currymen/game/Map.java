/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summative4currymen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author riepj9547
 */
public class Map {

    private Rectangle[] floor;

    public Map() {
        int worldWidth = 1000;
        int worldHeight = 1000;
        int upper = 100;
        int lower = 1;
        int num = (int) (Math.random() * (upper - lower)) + lower;
        System.out.println("RANDOM NUMBER IS " + num);
        int rooms = 0;
        if (num >= 1 && num <= 7) {
            rooms = 1;
            floor = new Rectangle[1];
            floor[0] = new Rectangle(0, 0, worldWidth, worldHeight);
        } else if (num > 7 && num <= 20) {
            rooms = 2;
            floor = new Rectangle[2];
            floor[0] = new Rectangle(0, 0, worldWidth/2, worldHeight);
            floor[1] = new Rectangle(500, 0, worldWidth/2, worldHeight);
        } else if (num > 20 && num <= 60) {
            rooms = 3;
            floor = new Rectangle[3];
            upper = 3;
            lower = 1;
            int thirdRoom = (int) (Math.random() * (upper - lower)) + lower;
            if (thirdRoom == 1) {
                floor[0] = new Rectangle(0, 0, worldWidth/2, worldHeight/2);
                floor[1] = new Rectangle(worldWidth/2, 0, worldWidth/2, worldHeight);
                floor[2] = new Rectangle(0, worldHeight/2, worldWidth/2, worldHeight/2);
            } else if (thirdRoom == 2) {
                floor[0] = new Rectangle(0, 0, worldWidth/2, worldHeight);
                floor[1] = new Rectangle(worldWidth/2, 0, worldWidth/2, worldHeight/2);
                floor[2] = new Rectangle(worldWidth/2, worldHeight/2, worldWidth/2, worldHeight/2);
            }

        } else if (num > 60 && num <= 85) {
            rooms = 4;
            floor = new Rectangle[4];
            floor[0] = new Rectangle(0, 0, worldWidth/2, worldHeight/2);
            floor[1] = new Rectangle(worldWidth/2, 0, worldWidth/2, worldHeight/2);
            floor[2] = new Rectangle(0, worldHeight/2, worldWidth/2, worldHeight/2);
            floor[3] = new Rectangle(worldWidth/2, worldHeight/2, worldWidth/2, worldHeight/2);
        } else if (num > 85 && num <= 100) {
            rooms = 5;
            floor = new Rectangle[5];
            upper = 5;
            lower = 1;
            int fithRoom = (int) (Math.random() * (upper - lower)) + lower;
            if (fithRoom == 1) {
                floor[0] = new Rectangle(0, 0, worldWidth/4, worldHeight/2);
                floor[1] = new Rectangle(worldWidth/2, 0, worldWidth/2, worldHeight/2);
                floor[2] = new Rectangle(0, worldHeight/2, worldWidth/2, worldHeight/2);
                floor[3] = new Rectangle(worldWidth/2, worldHeight/2, worldWidth/2, worldHeight/2);
                floor[4] = new Rectangle(worldWidth/4, 0, worldWidth/4, worldHeight/2);
            } else if (fithRoom == 2) {
                floor[0] = new Rectangle(0, 0, worldWidth/2, worldHeight/2);
                floor[1] = new Rectangle(worldWidth/2, 0, worldWidth/2, worldHeight/2);
                floor[2] = new Rectangle(0, worldHeight/2, worldWidth/4, worldHeight/2);
                floor[3] = new Rectangle(worldWidth/2, worldHeight/2, worldWidth/2, worldHeight/2);
                floor[4] = new Rectangle(worldWidth/4, worldHeight/2, worldWidth/4, worldHeight/2);
            } else if (fithRoom == 3) {
                floor[0] = new Rectangle(0, 0, worldWidth/2, worldHeight/2);
                floor[1] = new Rectangle(worldHeight/2, 0, worldWidth/4, worldHeight/2);
                floor[2] = new Rectangle(0, worldHeight/2, worldWidth/2, worldHeight/2);
                floor[3] = new Rectangle(worldWidth/2, worldHeight/2, worldWidth/2, worldHeight/2);
                floor[4] = new Rectangle(worldWidth/2 + worldWidth/4, 0, worldWidth/4, worldHeight/2);
            } else if (fithRoom == 4) {
                floor[0] = new Rectangle(0, 0, worldWidth/2, worldHeight/2);
                floor[1] = new Rectangle(worldWidth/2, 0, worldWidth/2, worldHeight/2);
                floor[2] = new Rectangle(0, worldHeight/2, worldWidth/2, worldHeight/2);
                floor[3] = new Rectangle(worldWidth/2, worldHeight/2, worldWidth/4, worldHeight/2);
                floor[4] = new Rectangle(worldWidth/2 + worldWidth/4, worldHeight/2, worldWidth/4, worldHeight/2);
            }
        }
        System.out.println("NUMBER OF ROOMS " + rooms);
        System.out.println(floor.length);
    }
    
    public void draw(ShapeRenderer shapeBatch) {
        
        for (int i = 0; i < floor.length; i++) {
            if (i == 0) {
                shapeBatch.setColor(Color.BROWN);
            } else if (i == 1) {
                shapeBatch.setColor(Color.BLUE);
            } else if (i == 2) {
                shapeBatch.setColor(Color.CYAN);
            } else if (i == 3) {
                shapeBatch.setColor(Color.GREEN);
            } else if (i == 4) {
                shapeBatch.setColor(Color.ORANGE);
            }
            shapeBatch.rect(floor[i].x, floor[i].y, floor[i].width, floor[i].height);
        }
    }
}

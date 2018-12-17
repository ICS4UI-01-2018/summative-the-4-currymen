package com.summative4currymen.game;

import java.util.Random;
import java.awt.Graphics;

/**
 * Boids ---  class to represent a "flock" of bird-like objects and it's behaviour.
 * @author    fr05td3su
 */
class Boids 
{
//    KDTree kd;     //kd-tree structure is used to find bird's neighbours fast
//    Zombie[] zombie; 
//    int N;         //number of boids to process
//    int xRes;      //maximum x-coordinate of field
//    int yRes;      //maximum y-coordinate of field
//    
//    /**
//     * Initialize the array of bird-like objects with random coordinates within certain area,
//     * determined by width and height, so each bird will be asigned position vector (from 0 to width, from 0 to height)
//     * and zero velocity vector.
//     *
//     * @param  amount                 Number of boids to create. 
//     *         width                  Maximum value of x-coordinate of position.
//     *         height                 Maximum value of y-coordinate of position.
//     */
//    public Boids(int amount, int width, int height)
//    {
//        N = amount;
//        xRes = width;
//        yRes = height;
//        kd =  new KDTree(2);
//        zombie = new Zombie[N];
//        Random rand = new Random();
//        
//        for (int i = 0; i < N - 1; i++)   
//        {
//            zombie[i] = new Zombie(new Vector(rand.nextInt(xRes),rand.nextInt(yRes)), new Vector(0,0), (int) Math.floor(Math.random() * 801), (int) Math.floor(Math.random() * 601), 45, 45, 2, 100, "Zambie", 100); 
//            try{
//            kd.insert(zombie[i].position.data, zombie[i]);
//            } catch (Exception e) {
//                System.out.println("Exception caught: " + e);   
//            }
//        }
//    }  
//    
//    /**
//     * Updates each boid's position and velocity depending on it's neighbours.
//     *
//     * @param  distance               Number of neighbours, which positions and velocities are used to calculate 
//     *                                corresponding vectors of cohesion, alignment, separation of a bird.
//     *         cohesionCoefficient    Value affects speed at which bird moves towards the perceived centre of mass
//     *                                e.g 100 means that in each iteration bird moves 1% to the perceived centre 
//     *         alignmentCoefficient   Value affects velocity increase of bird with respect to the perceived centre 
//     *                                of mass 
//     *         separationCoefficient  If bird is within this distance from other birds, it will move away
//     * @return No return value.
//     */
//    public void move(int distance, double cohesionCoefficient, int alignmentCoefficient, double separationCoefficient) 
//    {
//        try{
//            for (int i = 0; i < N - 1; i++)  
//            {
//                double[] coords = zombie[i].position.data;
//                Zombie[] nbrs = new Zombie[distance];
//                kd.nearest(coords, distance).toArray(nbrs); 
//                kd.delete(coords);
//                zombie[i].updateVelocity(nbrs, xRes, yRes, cohesionCoefficient, alignmentCoefficient, separationCoefficient);
//                zombie[i].updatePosition();
//                kd.insert(zombie[i].position.data, zombie[i]);
//            }      
//       
//            the implementation of deletion in KdTree does not actually delete nodes, 
//            but only marks them, that affects performance, so it's necessary to rebuild the tree
//            after long sequences of insertions and deletions
//            kd = new KDTree(2);
//            for (int i = 0; i < N - 1; i++)  
//                kd.insert(zombie[i].position.data, zombie[i]);
//        } catch (Exception e) {
//            System.out.println("Exception caught: " + e);   
//        } 
//    }
//    
//    /**
//     * Draws each boid as a point on the graphics object.
//     *
//     * @param  g                 Graphics object to draw on.
//     * @return No return value.
//     */
//    public void draw(Graphics g)
//    {
//            for (int i = 0; i < N - 1; i++)   
//            {
//                int x = (int) zombie[i].position.data[0];
//                int y = (int) zombie[i].position.data[1];
//                g.drawLine(x, y, x, y); 
//            }
//    } 
}
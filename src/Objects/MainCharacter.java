/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Components.BaseObject;
import Components.Physics;
import Controllers.SpriteController;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Interfaces.Plottable;
import java.util.Date;
import javafx.scene.chart.PieChart;

/**
 *
 * @author frederik.larsen
 */
public class MainCharacter extends BaseObject implements Plottable {
    long lastJump;
    public int speed = 5;
    public MainCharacter() {
        lastJump = System.currentTimeMillis();
        String path = "stickman.png";
        File fil = new File(path);
        BufferedImage imgg = null;
        //Get Image from path
        try {
            imgg = SpriteController.loadSingleSprite();
        } catch (Exception e) {
            System.out.println("The image was not loaded.");
        }

        addPhysics();
        addHitbox(imgg.getHeight(), imgg.getWidth());
        getPhysics().gravity = false;
        img = imgg;
        
    }

    public void move(int dir) {
        Point currPoint = getCords();
        switch (dir) {
            case 37:
                //physics.applyForce(new Point(-1,0));
                setCords(currPoint.x - speed-5, currPoint.y);
                break;
            case 38:
                //physics.applyForce(new Point(0,-1));
                setCords(currPoint.x, currPoint.y-speed);
                break;
            case 39:
                //physics.applyForce(new Point(1,0));
                setCords(currPoint.x + speed+5, currPoint.y);
                break;
            case 40:
                //physics.applyForce(new Point(0,1));
                setCords(currPoint.x, currPoint.y+speed);
                break;
            case 32:
                if (!physics.gravity) {
                    if(lastJump + 1000 < System.currentTimeMillis()){
                        lastJump = System.currentTimeMillis();
                        physics.applyForce(new Point(0, -5));
                        physics.gravity = true;
                    }
                }
                break;

        }
    }

    public void stop() {

    }

}

import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

/**
 * Created by pehe on 24/01/17.
 */
public class GFXPanel extends JPanel{
    double x = 0;
    private int sizex,sizey=0;
    private Engine engine =null;

    GFXPanel(int sizex, int sizey)
    {
        this.sizex=sizex;
        this.sizey=sizey;
        engine =new Engine(sizex,sizey,1);
    }

    private void doDrawing(Graphics g) {

        Camera camera = new Camera();
        //camera.set(new Point3D(10, 5, 10), new Point3D(0, 0, 0), new Vec3d(0,1,0));
        //camera.set(new Point3D(-10, 0, 0), new Point3D(0, 0, 0), new Vec3d(0,1, 0));

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(100, 0, 0));
        g2d.drawString("Java 2D", 50, 50);
        //Cube testPlane = new Cube(0, 0, 0, 1, new Color(0, 0, 0));

        Object objects[] = {new Sphere(-1,-1,-1,1,new Color(151,8,8)), new Cube(1, 1, 1, 1, new Color(0, 0, 0)),new Sphere(-1,0,0,1,new Color(52,86,149)),new Cube(-1, 0, 1, 1, new Color(0, 0, 0))};

       // x += 0.1;
        //System.out.println(x);
        //


        x = x + 0.1;
        //System.out.println(x);


        double CameraX = 10 * Math.cos(x);
        double CameraZ = 10 * Math.sin(x);
        double CameraY = 5 * Math.sin(x);

        //System.out.println("x = " + x + " Cx " + CameraX + " Cz " + CameraZ);

        camera.set(new Point3D(CameraX, CameraY, CameraZ), new Point3D(0,0,0), new Vec3d(0,1, 0));

        double closeHit = -1;
        for(int i=0;i<sizex;i++) {
            for (int j = 0; j < sizey; j++) {

                Ray ray = camera.getRay(i,sizey - j);
                closeHit = -1;

                for (int obj = 0; obj < objects.length; obj++) {

                    RayHit hit = objects[obj].Hit(ray);

                    if(hit.getIsHit()){
                        //System.out.println("object " + obj + " t = " + hit.t + " prevT = " + closeHit);
                        if(((closeHit < 0) || (hit.t < closeHit))) {
                            closeHit = hit.t;
                            g2d.setColor(hit.getColor());
                            g2d.drawLine(i,j,i,j);
                        }
                    }
                }
            }
        }



    //}

    //}




/*
        Ray ray = camera.getRay(650,650);
        //ray.TranslateRay(testPlane.translationMatrix,testPlane.scalingMatrix);
        if(testPlane.Hit(ray).getIsHit()){
            System.out.println("Hit in gfx");
        }
*/


    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

}



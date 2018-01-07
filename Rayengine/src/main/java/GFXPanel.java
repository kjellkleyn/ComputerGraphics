import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by pehe on 24/01/17.
 */
public class GFXPanel extends JPanel{
    double x = 50;
    private int sizex,sizey=0;
    private Engine engine =null;

    GFXPanel(int sizex, int sizey)
    {
        this.sizex=sizex;
        this.sizey=sizey;
        engine =new Engine(sizex,sizey,1);
    }

    private void doDrawing(Graphics g) {

        BufferedImage image = new BufferedImage(sizex, sizey, BufferedImage.TYPE_INT_RGB);

        Camera camera = new Camera(sizex,sizey);
        camera.set(new Point3D(45, 7, 8), new Point3D(15, 10, 10), new Vec3d(0,1,0));
        //camera.set(new Point3D(-10, 0, 0), new Point3D(0, 0, 0), new Vec3d(0,1, 0));

        Graphics2D g2d =  (Graphics2D) g;
        
        g2d.setColor(new Color(100, 0, 0));
        g2d.drawString("Java 2D", 50, 50);
        //Cube testPlane = new Cube(0, 0, 0, 1, new Color(0, 0, 0));

        //Object objects[] = {new Cube(3, 1, 1, 1, new Color(0, 0, 0)),new Cube(-1, 1, 1, 1, new Color(0, 0, 0))};
        //Object objects[] = {new Sphere(10,1,1,1,Color.ORANGE),new Sphere(10,-1,1,1,Color.GREEN),new Sphere(10,-4,1,1,Color.BLUE),new Sphere(10,-1,4,4,Color.RED)};
        /*
        Object objects[] = {
                new Cube(0,0,0,60,new Material(new Color(180,180,180),0.4,0.25,0,0,1)),
                new Cube(1,1,1,2, new Material(new Color(180, 50, 30),0.4,0.25,0,0,0.5)),
                new Sphere(4,4,4,1,new Material(new Color(0, 255,0),0.4,0.25,0,0,0.5))};
*/

        Object objects[] = {
                new Cube(38,7,8,1,new Material(new Color(31, 66, 59),0.4,0.25,0,0.5,1)),
                new Cube(0,0,0,60,new Material(new Color(159, 159, 159),0.4,0.25,0.25,0,1)),
                new Sphere(15,8,8,1, new Material(new Color(50, 180, 30),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,10,8,1,new Material(new Color(180, 75, 200),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,12,8,1,new Material(new Color(180, 60, 73),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,14,8,1,new Material(new Color(180, 60, 73),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,6,8,1,new Material(new Color(180, 154, 50),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,6,10,1,new Material(new Color(36, 47, 180),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,8,10,1,new Material(new Color(180, 60, 73),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,10,10,1,new Material(new Color(180, 60, 73),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,12,10,1,new Material(new Color(180, 60, 73),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,14,10,1,new Material(new Color(180, 60, 73),0.4,0.25,0.7,0,0.5)),

                new Sphere(15,8,12,1, new Material(new Color(180, 60, 73),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,10,12,1,new Material(new Color(30, 90, 10),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,12,12,1,new Material(new Color(180, 60, 73),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,14,12,1,new Material(new Color(200, 200, 10),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,6,12,1,new Material(new Color(180, 60, 73),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,6,14,1,new Material(new Color(59, 15, 37),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,8,14,1,new Material(new Color(180, 60, 73),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,10,14,1,new Material(new Color(60, 5, 5),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,12,14,1,new Material(new Color(180, 60, 73),0.4,0.25,0.7,0,0.5)),
                new Sphere(15,14,14,1,new Material(new Color(180, 60, 73),0.4,0.25,0.7,0,0.5)),

                new Sphere(51,10,10,1,new Material(new Color(238, 16, 42),0.4,0.25,0.7,0,0.5)),
                new Sphere(51,6,6,1,new Material(new Color(16, 185, 201),0.4,0.25,0.7,0,0.5)),
                new Sphere(51,8,8,1,new Material(new Color(180, 2, 172),0.4,0.25,0.7,0,0.5))

                };


        //Object objects[] = {new Sphere(0,5,0,1,Color.cyan),new Floor(0,0,0,-1,Color.RED)};
        //Object objects[] = {new Cube(0,0,0,20,Color.GREEN)};

        Material standardMaterial = new Material(Color.BLUE,0.5,0.5,0,0,1);

        Light light = new Light(40,40,40);

        double closeHit = -1;
        for(int i=0;i<sizex;i++) {
            for (int j = 0; j < sizey; j++) {

                Ray ray = camera.getRay(i,sizey - j);
                closeHit = -1;
                RayHit hitPoint = new RayHit();
                int hitobject = -1;

                for (int obj = 0; obj < objects.length; obj++) {

                    RayHit hit = objects[obj].Hit(ray);

                    if(hit.getIsHit()){
                        if(((closeHit < 0) || (hit.t1 < closeHit))) {
                            closeHit = hit.t1;
                            hitPoint = hit;

                            hitobject = obj;
                        }
                    }
                }

                if(hitPoint.getIsHit()){
                    boolean shadow = light.checkShadow(hitPoint.getHitPos(),objects,hitobject);

                    g2d.setColor(light.getColor(hitPoint,camera.getEye(),shadow,objects,ray,0,standardMaterial));
                    g2d.drawLine(i,j,i,j);
                }


            }
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

}



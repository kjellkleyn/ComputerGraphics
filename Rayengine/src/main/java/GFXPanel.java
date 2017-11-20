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

        Camera camera = new Camera();
        camera.set(new Point3D(-10, 10, 20), new Point3D(0, 0, 0), new Vec3d(0,1,0));
        //camera.set(new Point3D(-10, 0, 0), new Point3D(0, 0, 0), new Vec3d(0,1, 0));

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(100, 0, 0));
        g2d.drawString("Java 2D", 50, 50);
        //Cube testPlane = new Cube(0, 0, 0, 1, new Color(0, 0, 0));

        //Object objects[] = {new Cube(3, 1, 1, 1, new Color(0, 0, 0)),new Cube(-1, 1, 1, 1, new Color(0, 0, 0))};
        //Object objects[] = {new Sphere(10,1,1,1,Color.ORANGE),new Sphere(10,-1,1,1,Color.GREEN),new Sphere(10,-4,1,1,Color.BLUE),new Sphere(10,-1,4,4,Color.RED)};
        Object objects[] = {new Sphere(3,3,3,1,Color.cyan),new Sphere(3,3,-3,1,Color.ORANGE),new Floor(-3,0,0,-1,Color.RED)};
        //Object objects[] = {new Cube(0,0,0,1,Color.GREEN),new Floor(0,0,0,-1,Color.RED)};
        //Object objects[] = {new Sphere(0,5,0,1,Color.cyan),new Floor(0,0,0,-1,Color.RED)};
        //Object objects[] = {new Cube(0,0,0,20,Color.GREEN)};

        Light light = new Light(20,10,3);
       // x += 0.1;
        //System.out.println(x);
        //

/*
        double lightX = 100 * Math.cos(x);
        double lightZ = 100 * Math.sin(x);
        double ligthY = 0 * Math.sin(x);




        x = x - 1;
        System.out.println(x);
*/
/*

*/
        //System.out.println("x = " + x + " Cx " + CameraX + " Cz " + CameraZ);

        //camera.set(new Point3D(x, -10, -10), new Point3D(10,10,10), new Vec3d(0,1, 0));

        double closeHit = -1;
        for(int i=0;i<sizex;i++) {
            for (int j = 0; j < sizey; j++) {

                Ray ray = camera.getRay(i,sizey - j);
                closeHit = -1;
                RayHit hitPoint = new RayHit();
                int hitobject = 0;

                for (int obj = 0; obj < objects.length; obj++) {

                    RayHit hit = objects[obj].Hit(ray);

                    if(hit.getIsHit()){
                        //System.out.println("object " + obj + " t = " + hit.t + " prevT = " + closeHit);
                        if(((closeHit < 0) || (hit.t < closeHit))) {
                            closeHit = hit.t;
                            hitPoint = hit;
                            hitobject = obj;
                        }
                    }
                }


                if(hitPoint.getIsHit()){

                    hitPoint.InverTranslateRay(objects[hitobject].scalingMatrix,objects[hitobject].translationMatrix);
                    boolean shadow = light.checkShadow(hitPoint.getHitPos(),objects,hitobject);

                    g2d.setColor(light.getColor(hitPoint.getColor(),hitPoint.getNormVec(),hitPoint.getHitPos(),camera.getEye(),shadow));
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



import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by pehe on 24/01/17.
 */
public class GFXPanel extends JPanel{
    double x = 50;
    private int sizex,sizey=0;
    private Engine engine =null;
    ArrayList<Object> objects;

    GFXPanel(int sizex, int sizey)
    {
        this.sizex=sizex;
        this.sizey=sizey;
        engine =new Engine(sizex,sizey,1);
        objects = new ArrayList<Object>();
    }

    private void doDrawing(Graphics g) {

        BufferedImage img = null;

        try
        {
            img = ImageIO.read(new File("D:\\Afbeeldingen\\eye_0058_c.jpg")); // eventually C:\\ImageTest\\pic2.jpg
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



        Camera camera = new Camera(sizex,sizey);
        camera.set( new Point3D( -13,5, 10 ),
                new Point3D( -12,3, -5),
                new Vec3d( 0 , 1, 0 ) );
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
/*
        Object objects[] = {
                new Sphere(38,7,8,1,new Material(new Color(31, 66, 59),0.4,0.25,0.5,0.5,0.5)),
                new Cube(0,0,0,60,new Material(new Color(159, 159, 159),0.4,0.25,0,0,1)),
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
*/

        //Object objects[] = {new Sphere(0,5,0,1,Color.cyan),new Floor(0,0,0,-1,Color.RED)};
        //Object objects[] = {new Cube(0,0,0,20,Color.GREEN)};


        objects.add( new Cube(new Point3D(-20, 0, -30), 60, new Material( new Color(146, 147, 143), 0.4, 0.25,0.0,0.0,0.0 ))); // ROOM
        //bjects.add()
        //objects.add(new Sphere(new Point3D(-12,3, -5),2,2,2,new Material(new Color(14, 41, 192),0.4,0.25,0.05,0.0,0.0),img));
        //objects.add(new Cube(new Point3D(-12,3, -7),1,1,1,new Material(new Color(192, 45, 75),0.4,0.25,0.0,0.0,0.0)));
        /*
        BooleanObject die = new BooleanObject(new Cube(-0, 0,0, 5, new Material( new Color( 255, 255, 255 ), 0.4, 0.25, 0.5,0.0,0.0 )),
                new Sphere(2.5, 2.5,2.5, 3.4, new Material( new Color( 255, 255, 255 ), 0.4, 0.25, 0.5,0.0,0.0 ) ), BooleanObject.INTERSECTION);
        die = new BooleanObject( die,
                new Sphere(2.5, 5,2.5, 0.4, new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )), BooleanObject.DIFFERENCE);

        BooleanObject front = new BooleanObject( new Sphere(0, 2.5,2.5, 0.4,new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )),
                new Sphere(0, 1.5,1.5, 0.4, new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )), BooleanObject.UNION  );
        front = new BooleanObject( front, new Sphere(0, 1.5,3.5, 0.4, new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )), BooleanObject.UNION  );
        front = new BooleanObject( front, new Sphere(0, 3.5,3.5, 0.4, new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )), BooleanObject.UNION );
        front = new BooleanObject( front, new Sphere(0, 3.5,1.5, 0.4, new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )), BooleanObject.UNION );

        die = new BooleanObject( die, front, BooleanObject.DIFFERENCE);

        BooleanObject rightSide = new BooleanObject( new Sphere(1.5, 3.5,5, 0.4, new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )),
                new Sphere(1.5, 1.5,5, 0.4, new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )), BooleanObject.UNION  );
        rightSide = new BooleanObject( rightSide, new Sphere(3.5, 1.5,5, 0.4, new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )), BooleanObject.UNION  );
        rightSide = new BooleanObject( rightSide, new Sphere(3.5, 3.5,5, 0.4, new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )), BooleanObject.UNION );

        die = new BooleanObject( die,rightSide, BooleanObject.DIFFERENCE);

        BooleanObject leftSide = new BooleanObject(new Sphere(2.5, 2.5,0, 0.4, new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )),
                new Sphere(1.5, 1.5,0, 0.4, new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )), BooleanObject.UNION );
        leftSide = new BooleanObject( leftSide, new Sphere(3.5, 3.5,0, 0.4, new Material( new Color(99, 99, 99), 0.4, 0.25,0.0,0.0,0.0 )), BooleanObject.UNION );

        die = new BooleanObject( die,leftSide, BooleanObject.DIFFERENCE);



        BooleanObject ballInBox = new BooleanObject( new Cube(-0-10, 0,0-10, 5, new Material( new Color(177, 21, 30), 0.4, 0.25,0.0,0.0,0.0 )),
                new Cube(-1-10, 0.3,0.3-10, 4.4, new Material( new Color(30, 35, 177), 0.4, 0.25,0.0,0.0,0.0 )),BooleanObject.DIFFERENCE);
        ballInBox = new BooleanObject( ballInBox, new Cube(1-10, 0.3,0.3-10, 4.4, new Material( new Color(46, 44, 45), 0.4, 0.25,0.0,0.0,0.0 )), BooleanObject.DIFFERENCE );
        ballInBox = new BooleanObject( ballInBox, new Cube(0.3-10, 0.3,0.3-10, 4.4, new Material( new Color(225, 209, 25), 0.4, 0.25,0.0,0.0,0.0 )) , BooleanObject.DIFFERENCE );
        ballInBox = new BooleanObject( ballInBox, new Cube(0.3-10, 1,0.3-10, 4.4,new Material( new Color(228, 33, 199), 0.4, 0.25,0.0,0.0,0.0 )) , BooleanObject.DIFFERENCE );
        ballInBox = new BooleanObject( ballInBox, new Cube(0.3-10, -1,0.3-10, 4.4, new Material( new Color(31, 203, 204), 0.4, 0.25,0.0,0.0,0.0 )) , BooleanObject.DIFFERENCE );
        ballInBox = new BooleanObject( ballInBox, new Cube(0.3-10, 0.3,1-10, 4.4, new Material( new Color(0, 160, 105), 0.4, 0.25,0.0,0.0,0.0 )) , BooleanObject.DIFFERENCE );
        ballInBox = new BooleanObject( ballInBox, new Cube(0.3-10, 0.3,-1-10, 4.4,new Material( new Color(47, 177, 22), 0.4, 0.25,0.0,0.0,0.0 )) , BooleanObject.DIFFERENCE );
        ballInBox = new BooleanObject( ballInBox, new Sphere(2.5-10, 2.5,2.5-10, 2,new Material( new Color(145, 11, 19), 0.4, 0.25,0.0,0.0,0.0 )) , BooleanObject.UNION );

        objects.add( ballInBox );
        objects.add( die );
        */

        //objects.add( new Cube(new Point3D(-20, 0, -30), 60, new Material( new Color(47, 177, 22), 0.4, 0.25,0.0,0.0,0.0 )) ); // ROOM
        //
        //objects.add( new Sphere(new Point3D(0, 2,0), 1, materials.get("matDeepOrange") ) ); // SPHERE 3
        /*
        objects.add( new BooleanObject( new Sphere(new Point3D(-2, 4,-3.5), 1, new Material( new Color(177, 80, 28), 0.4, 0.25,0.0,0.0,0.0 ) ),
                new Sphere(new Point3D(-2.5, 4,-3), 1,new Material( new Color(177, 36, 125), 0.4, 0.25,0.0,0.0,0.0 ) ), BooleanObject.INTERSECTION));
        objects.add( new BooleanObject( new Cube(new Point3D(0, 0,-1.5), 3,new Material( new Color(177, 92, 41), 0.4, 0.25,0.0,0.0,0.0 ) ),
                new Cube(new Point3D(1.5, 1.5,-1.5), 3, new Material( new Color(177, 37, 144), 0.4, 0.25,0.0,0.0,0.0 ) ), BooleanObject.UNION));
        */
        /*
        objects.add( new BooleanObject(new Sphere(new Point3D(0, 2,6), 2, new Material( new Color(177, 74, 35), 0.4, 0.25,0.0,0.0,0.0 ) ),
                new Cube(new Point3D(-2, 2,5), 3, new Material( new Color(177, 59, 153), 0.4, 0.25,0.0,0.0,0.0 )), BooleanObject.DIFFERENCE));
        */
        Material standardMaterial = new Material(Color.BLUE,0.5,0.5,0,0,1);

        Light light = new Light(-14,3, -25);

        double closeHit = -1;
        for(int i=0;i<sizex;i++) {
            for (int j = 0; j < sizey; j++) {

                Ray ray = camera.getRay(i,sizey - j);
                closeHit = -1;
                RayHit hitPoint = new RayHit();
                int hitobject = -1;

                for (int obj = 0; obj < objects.size(); obj++) {

                    RayHit hit = objects.get(obj).Hit(ray);

                    if(hit.getIsHit()){
                        if(((closeHit < 0) || (hit.getT() < closeHit))) {
                            closeHit = hit.getT();
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



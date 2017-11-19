import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by kjell on 17/11/2017.
 */
public class Light {

    Point3D pos;
    double brightness = 1;
    double diffuseReflect = 0.5;
    double specularReflect = 0.5;
    double Ia = 0.4;


    Light(double x,double y,double z){
        pos = new Point3D(x,y,z);

    }

    Color getColor(Color color, Vec3d m, Point3D hitPoint, Point3D viewPoint, boolean shadow){

        Vec3d s = new Vec3d(pos.getX()-hitPoint.getX(),pos.getY()-hitPoint.getY(),pos.getZ()-hitPoint.getZ());
        s.normalize();
        Vec3d v = new Vec3d(viewPoint.getX()-hitPoint.getX(),viewPoint.getY()-hitPoint.getY(),viewPoint.getZ()-hitPoint.getZ());
        v.normalize();
        Vec3d r = new Vec3d((-s.x + 2 * ((s.dot(m))/ (m.length() * m.length())) * m.x),(-s.y + 2 * ((s.dot(m))/ (m.length() * m.length())) * m.y),(-s.z + 2 * ((s.dot(m))/ (m.length() * m.length())) * m.z));
        r.normalize();

        double Id = brightness * diffuseReflect * (s.dot(m)) / (s.length()*m.length());

        if(Id < 0){
            Id = 0;
        }


        double Isp = brightness * specularReflect * (r.dot(v));

        if(Isp <0){
            Isp = 0;
        }

        double Ir;
        double Ig;
        double Ib;

        if(shadow){
            Ir = Ia;
            Ig = Ia;
            Ib = Ia;
        }else{
            Ir = Ia + Id + Isp;
            Ig = Ia + Id + Isp;
            Ib = Ia + Id + Isp;
        }


        double Red = color.getRed();
        Red = Red*Ir;
        int intRed = (int)Red;

        double Green = color.getGreen();
        Green = Green*Ig;
        int intGreen = (int)Green;

        double Blue = color.getBlue();
        Blue = Blue*Ib;
        int intBlue = (int)Blue;

        if(intRed > 255){
            intRed = 255;
        }

        if(intGreen > 255){
            intGreen = 255;
        }

        if(intBlue > 255){
            intBlue = 255;
        }

        Color newColor = new Color(intRed,intGreen,intBlue);


    return newColor;
    }

    boolean checkShadow(Point3D hitPoint,Object[] objects, int hitObj){



        Vec3d hitPointDir = new Vec3d(pos.getX()-hitPoint.getX(),pos.getY()-hitPoint.getY(),pos.getZ()-hitPoint.getZ());
        double distance = hitPointDir.length();
        hitPointDir.normalize();

        Ray ray = new Ray();
        ray.setStart(hitPoint);
        ray.setDir(hitPointDir);
        RayHit hit;




        for (int obj = 0; obj < objects.length; obj++) {

            if(hitObj != obj){
                hit = objects[obj].Hit(ray);
            }else{
                hit = new RayHit();
            }
/*
            if(hitObj == 0){
                System.out.println("hitpoint = " + hitPoint);
                System.out.println("distance " + distance + " hit " + hit.t);
            }
*/


            if(hit.getIsHit() && (hit.t < distance) && (hit.t > 0)){
                System.out.println("distance " + distance + " hit " + hit.t);
                return true;
            }
        }

        return false;
    }

}

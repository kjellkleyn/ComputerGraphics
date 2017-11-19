import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by kjell on 03/11/2017.
 */
public class Plane {

    double A;
    double B;
    double C;
    Point3D D;
    int number;
    Color color;
    double size;

    Plane(int number,Color c, double size){
        this.size = size;
        this.A = (number == 2)? 1.0 : 0.0; // YZ
        this.B = (number == 0)? 1.0 : 0.0; // XZ
        this.C = (number == 1)? 1.0 : 0.0; // XY
        this.D = D;
        this.number = number;
        this.color = c;

        switch(number){
            case 0: this.A = 0;
                    this.B = 0;
                    this.C = 1;
                    this.D = new Point3D(0,0,0);
                    break;
            case 1: this.A = 0;
                    this.B = 1;
                    this.C = 0;
                    this.D = new Point3D(0,0,0);
                    break;
            case 2: this.A = 1;
                    this.B = 0;
                    this.C = 0;
                    this.D = new Point3D(0,0,0);
                    break;
            case 3: this.A = 0;
                    this.B = 0;
                    this.C = 1;
                    this.D = new Point3D(0,0,-this.size);
                    break;
            case 4: this.A = 0;
                    this.B = 1;
                    this.C = 0;
                    this.D = new Point3D(0,-this.size,0);
                    break;
            case 5: this.A = 1;
                    this.B = 0;
                    this.C = 0;
                    this.D = new Point3D(-this.size,0,0);
                    break;

        }
    }


    RayHit Hit(Ray ray) {
        //System.out.println("_______________________________________");
        //System.out.println("Plane number " + number);
        //System.out.println("A = " + A  + " B = " + B + " C = " + C + "D = " + D.getX() + "," + D.getY() + "," + D.getZ());

        double teller = -(A * ray.tempStart.getX() + this.D.getX()) - (B * ray.tempStart.getY() + this.D.getY()) - (C * ray.tempStart.getZ() + this.D.getZ());
        double noemer = (A * ray.tempDir.x) + (B * ray.tempDir.y) + (C * ray.tempDir.z);
        double t = teller / noemer;

        //System.out.println("t = " + t);
        //System.out.println("_______________________________________");
        double hitPoint_x = ray.tempStart.getX() + (t * ray.tempDir.x);
        double hitPoint_y = ray.tempStart.getY() + (t * ray.tempDir.y);
        double hitPoint_z = ray.tempStart.getZ() + (t * ray.tempDir.z);

        //System.out.println("Plane hit point________________-");
        //System.out.println("X = " + hitPoint_x + " Y = " + hitPoint_y + " Z = " + hitPoint_z);


        if (size > 0) {
            if (number == 0 || number == 3) {
                if ((hitPoint_x <= this.size && hitPoint_x >= 0) && (hitPoint_y <= this.size && hitPoint_y >= 0))
                    return new RayHit(t, color, new Point3D(hitPoint_x, hitPoint_y, hitPoint_z), new Vec3d(A, B, C));
                else
                    return new RayHit();
            } else if (number == 1 || number == 4) {
                if ((hitPoint_x <= this.size && hitPoint_x >= 0) && (hitPoint_z <= this.size && hitPoint_z >= 0))
                    return new RayHit(t, color, new Point3D(hitPoint_x, hitPoint_y, hitPoint_z), new Vec3d(A, B, C));
                else
                    return new RayHit();
            } else {
                if (((hitPoint_y <= this.size) && (hitPoint_y >= 0)) && ((hitPoint_z <= this.size) && (hitPoint_z >= 0))) {

                    return new RayHit(t, color, new Point3D(hitPoint_x, hitPoint_y, hitPoint_z), new Vec3d(A, B, C));
                } else {
                    return new RayHit();
                }

            }
        }else{
            if(t > 0) {

                return new RayHit(t, color, new Point3D(hitPoint_x, hitPoint_y, hitPoint_z), new Vec3d(A, B, C));
            }else{
                return new RayHit();
            }
        }
    }

/*
    int checkPlaneXZwithOffset(Point3D rayStart, Point3D rayDir) {
        this.B = 1; // XZ

        double teller =  1 - ( B * rayStart.getY() );
        double noemer =  B * rayDir.getY();
        double t = teller / noemer;

        double hitPoint_x = rayStart.getX() + (t * rayDir.getX());
        double hitPoint_y = rayStart.getY() + (t * rayDir.getY());
        double hitPoint_z = rayStart.getZ() + (t * rayDir.getZ());

        if ((hitPoint_x <= 1 && hitPoint_x >= 0) && (hitPoint_z <= 1 && hitPoint_z >= 0))
            return new Hit(hitPoint_x, hitPoint_y, hitPoint_z, t);
        else
            return new Hit(false);
    }

    int checkPlaneXYwithOffset(Point3D rayStart, Point3D rayDir) {
        this.C = 1; // XY

        double teller =  1 - ( C * rayStart.getZ() );
        double noemer =  C * rayDir.getZ();
        double t = teller / noemer;

        double hitPoint_x = rayStart.getX() + (t * rayDir.getX());
        double hitPoint_y = rayStart.getY() + (t * rayDir.getY());
        double hitPoint_z = rayStart.getZ() + (t * rayDir.getZ());

        if ((hitPoint_x <= 1 && hitPoint_x >= 0) && (hitPoint_z <= 1 && hitPoint_z >= 0))
            return new Hit(hitPoint_x, hitPoint_y, hitPoint_z, t);
        else
            return new Hit(false);
    }

    int checkPlaneYZwithOffset(Point3D rayStart, Point3D rayDir) {
        this.A = 1; // YZ

        double teller =  1 - ( A * rayStart.getX() );
        double noemer =  A * rayDir.getX();
        double t = teller / noemer;

        double hitPoint_x = rayStart.getX() + (t * rayDir.getX());
        double hitPoint_y = rayStart.getY() + (t * rayDir.getY());
        double hitPoint_z = rayStart.getZ() + (t * rayDir.getZ());

        if ((hitPoint_x <= 1 && hitPoint_x >= 0) && (hitPoint_z <= 1 && hitPoint_z >= 0))
            return new Hit(hitPoint_x, hitPoint_y, hitPoint_z, t);
        else
            return new Hit(false);
    }
    */

}

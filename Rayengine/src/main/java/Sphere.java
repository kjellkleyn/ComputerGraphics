import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by kjell on 6-10-2017.
 */
public class Sphere extends Object {
    Point3D center = new Point3D(1, 1, 1);
    double radius = 1;

    Sphere(double x,double y,double z,double size , Color c){
        super(x,y,z,size,c);
        this.center = new Point3D(x, y, z);
        this.radius = size;

    }

    RayHit Hit(Ray ray) {

        double t;
        Vec3d temp = new Vec3d(ray.start.getX() - center.getX(), ray.start.getY() - center.getY(), ray.start.getZ() - center.getZ()); //ray.start.subtract(center);
        double a = ray.dir.dot(ray.dir);
        double b = 2.0 * temp.dot(ray.dir);
        double c = temp.dot(temp) - (1 * 1);
        double disc = b * b - 4.0 * a * c;

        if (disc < 0.0) {
            return new RayHit();  // no hit
        } else {
            double e = Math.sqrt(disc);
            double denom = 2.0 * a;
            t = (-b - e) / denom;    // smaller root

            if (t > 0.00001) {
                //Ray tempRay = ray;
                //tempRay.dir.mul(t);
                //temp.add(new Vec3d(tempRay.dir.x / radius, tempRay.dir.y / radius, tempRay.dir.z / radius));
                //Point3D localHitPoint = new Point3D(ray.start.getX() + ray.dir.x, ray.start.getY() + ray.dir.y, ray.start.getZ() + ray.dir.z);
                return new RayHit(t,color);
            }

            t = (-b + e) / denom;    // larger root

            if (t > 0.00001) {
                //Ray tempRay = ray;
                //tempRay.dir.mul(t);
                //temp.add(new Vec3d(tempRay.dir.x / radius, tempRay.dir.y / radius, tempRay.dir.z / radius));
                //Point3D localHitPoint = new Point3D(ray.start.getX() + ray.dir.x, ray.start.getY() + ray.dir.y, ray.start.getZ() + ray.dir.z);
                return new RayHit(t,color);
            }
        }

        return new RayHit(); // no hit

    }

}

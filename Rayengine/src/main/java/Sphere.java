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
        this.radius = 1;

    }

    RayHit Hit(Ray ray) {

        ray.TranslateRay(this.scalingMatrix,this.translationMatrix);

        double t;
        //Vec3d temp = new Vec3d(ray.start.getX() - center.getX(), ray.start.getY() - center.getY(), ray.start.getZ() - center.getZ()); //ray.start.subtract(center);
        Vec3d temp = new Vec3d(ray.tempStart.getX(), ray.tempStart.getY(), ray.tempStart.getZ()); //ray.start.subtract(center);
        double a = ray.tempDir.dot(ray.tempDir);
        double b = 2.0 * temp.dot(ray.tempDir);
        double c = temp.dot(temp) - (1 * 1);
        double disc = b * b - 4.0 * a * c;

        if (disc < 0.0) {
            return new RayHit();  // no hit
        } else {
            double e = Math.sqrt(disc);
            double denom = 2.0 * a;
            t = (-b - e) / denom;    // smaller root

            if (t > 0.00001) {
                Point3D hitPos = new Point3D(ray.tempStart.getX() + t * ray.tempDir.x,ray.tempStart.getY() + t * ray.tempDir.y,ray.tempStart.getZ() + t * ray.tempDir.z);
                //Vec3d normVec = new Vec3d(hitPos.getX()-center.getX(),hitPos.getY()-center.getY(),hitPos.getZ()-center.getZ());
                Vec3d normVec = new Vec3d(hitPos.getX(),hitPos.getY(),hitPos.getZ());
                normVec.normalize();
                return new RayHit(t,color,hitPos,normVec);
            }

            t = (-b + e) / denom;    // larger root

            if (t > 0.00001) {
                Point3D hitPos = new Point3D(ray.tempStart.getX() + t * ray.tempDir.x,ray.tempStart.getY() + t * ray.tempDir.y,ray.tempStart.getZ() + t * ray.tempDir.z);
                //Vec3d normVec = new Vec3d(hitPos.getX()-center.getX(),hitPos.getY()-center.getY(),hitPos.getZ()-center.getZ());
                Vec3d normVec = new Vec3d(hitPos.getX(),hitPos.getY(),hitPos.getZ());
                normVec.normalize();
                return new RayHit(t,color,hitPos,normVec);
            }
        }

        return new RayHit(); // no hit

    }

}

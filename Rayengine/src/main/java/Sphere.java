import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by kjell on 6-10-2017.
 */
public class Sphere extends Object {
    Point3D center = new Point3D(1, 1, 1);
    double radius = 1;

    Sphere(double x,double y,double z,double size , Material material){
        super(x,y,z,size,material);
        this.center = new Point3D(x, y, z);
        this.radius = size;

    }

    RayHit Hit(Ray ray){

        //ray.TranslateRay(scalingMatrix,translationMatrix);
        ray.TranslateRay();
        RayHit rayHit = calcHit(ray);

/*
        if(rayHit.getIsHit()) {
            rayHit.invertHitpoint(scalingMatrix, translationMatrix);
            rayHit.calculateT(ray);
            rayHit.setNormVec(calculateM(rayHit, ray, false));
            rayHit.setMaterial(material);
        }
*/

        return rayHit;
    }

    RayHit calcHit(Ray ray) {

        //double t;
        Vec3d temp = new Vec3d(ray.start.getX() - center.getX(), ray.start.getY() - center.getY(), ray.start.getZ() - center.getZ()); //ray.start.subtract(center);
        //Vec3d temp = new Vec3d(ray.tempStart.getX() - 0, ray.tempStart.getY() - 0, ray.tempStart.getZ() - 0); //ray.start.subtract(center);
        double a = ray.tempDir.dot(ray.tempDir);
        double b = 2.0 * temp.dot(ray.tempDir);
        double c = temp.dot(temp) - (radius * radius);
        //double c = temp.dot(temp) - (1 * 1);
        double disc = b * b - 4.0 * a * c;

        if (disc < 0.0) {
            return new RayHit();  // no hit
        } else {
            double e = Math.sqrt(disc);
            double denom = 2.0 * a;
            double t1 = (-b - e) / denom;    // smaller root

            //RayHit hit;

            //if (t > 0.000001) {
                double hitPos1X = ray.tempStart.getX() + t1 * ray.tempDir.x;
                double hitPos1Y = ray.tempStart.getY() + t1 * ray.tempDir.y;
                double hitPos1Z = ray.tempStart.getZ() + t1 * ray.tempDir.z;
                Point3D hitPos1 = new Point3D(hitPos1X,hitPos1Y,hitPos1Z);
                //Vec3d normVec = new Vec3d(hitPos.getX()-center.getX(),hitPos.getY()-center.getY(),hitPos.getZ()-center.getZ());

                RayHit rayHit1 = new RayHit(hitPos1X,hitPos1Y,hitPos1Z,t1);
                rayHit1.setNormVec(calculateM( rayHit1,ray,false));
                rayHit1.setMaterial(material);
            //}

            double t2 = (-b + e) / denom;    // larger root

            //if (t > 0.000001) {
                double hitPos2X = ray.tempStart.getX() + t2 * ray.tempDir.x;
                double hitPos2Y = ray.tempStart.getY() + t2 * ray.tempDir.y;
                double hitPos2Z = ray.tempStart.getZ() + t2 * ray.tempDir.z;
                Point3D hitPos2 = new Point3D(hitPos2X,hitPos2Y,hitPos2Z);

                RayHit rayHit2 = new RayHit(hitPos2X,hitPos2Y,hitPos2Z,t2);
                rayHit2.setNormVec(calculateM(rayHit2,ray,true));
                rayHit2.setMaterial(material);
            //}

            if(t1 > 0.000000001){
                RayHit rayHit = new RayHit(hitPos1X,hitPos1Y,hitPos1Z,t1);
                rayHit.setMaterial(material);
                rayHit.setNormVec(rayHit1.getNormVec());

                rayHit.setHitPos1(rayHit1.getHitPos());
                rayHit.setT1(t1);
                rayHit.setM1(rayHit1.getNormVec());

                rayHit.setHitPos2(rayHit2.hitPos2);
                rayHit.setT2(t2);
                rayHit.setM2(rayHit2.getNormVec());

                return rayHit;
            }else if(t2 > 0.000000001){

                RayHit rayHit = new RayHit(hitPos2X,hitPos2Y,hitPos2Z,t2);
                rayHit.setMaterial(material);
                rayHit.setNormVec(rayHit2.getNormVec());

                rayHit.setHitPos1(rayHit1.getHitPos());
                rayHit.setT1(t1);
                rayHit.setM1(rayHit1.getNormVec());

                rayHit.setHitPos2(rayHit2.hitPos2);
                rayHit.setT2(t2);
                rayHit.setM2(rayHit2.getNormVec());

                return rayHit;
            }else{
                return new RayHit();
            }
        }
    }

    private Vec3d calculateM( RayHit rayHit, Ray ray, boolean inside) {
        Vec3d m = new Vec3d(rayHit.hitPoint.getX() - center.getX(),
                rayHit.hitPoint.getY() - center.getY(),
                rayHit.hitPoint.getZ() - center.getZ() );

        m.normalize();

        if(inside){
            m.mul(-1);
        }

        return m;
    }

}

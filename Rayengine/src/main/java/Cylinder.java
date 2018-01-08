import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by kjell on 08/01/2018.
 */
public class Cylinder extends Object{



    Point3D center = new Point3D(1, 1, 1);
    double radius = 1;
    BufferedImage image;

    Cylinder(double x,double y,double z,double size , Material material){
        super(x,y,z,size,size,size,material);
        this.center = new Point3D(x, y, z);
        this.radius = size;

    }

    Cylinder(Point3D pos,double size , Material material){
        super(pos.getX(),pos.getY(),pos.getZ(),size,size,size,material);
        this.center = pos;
        this.radius = size;

    }

    Cylinder(Point3D pos,double sizeX,double sizeY,double sizeZ , Material material){
        super(pos.getX(),pos.getY(),pos.getZ(),sizeX,sizeY,sizeZ,material);
        this.center = pos;
        //this.radius = size;

    }

    Cylinder(Point3D pos, double sizeX, double sizeY, double sizeZ , Material material, BufferedImage img){
        super(pos.getX(),pos.getY(),pos.getZ(),sizeX,sizeY,sizeZ,material);
        this.center = pos;
        this.image = img;
        //this.radius = size;

    }

    RayHit Hit(Ray ray) {
        ray.TranslateRay(scalingMatrix, translationMatrix);

        double a = (ray.tempDir.x * ray.tempDir.x) + (ray.tempDir.z * ray.tempDir.z);
        double b = (2 * ray.tempDir.x * ray.tempStart.getX()) + (2 * ray.tempDir.z * ray.tempStart.getZ());
        double c = (ray.tempStart.getX() * ray.tempStart.getX()) + (ray.tempStart.getZ() * ray.tempStart.getZ()) - 1;
/*
        double t1 = (-b - Math.sqrt(b*b - 4 * a * c)) / 2 * a;
        double t2 = (-b - Math.sqrt(b*b - 4 * a * c)) / 2 * a;
*/
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

            RayHit rayHit1 = new RayHit(hitPos1X, hitPos1Y, hitPos1Z, t1);
            rayHit1.setNormVec(calculateM(rayHit1, ray, false));
            rayHit1.setMaterial(material);
            //}

            double t2 = (-b + e) / denom;    // larger root

            //if (t > 0.000001) {
            double hitPos2X = ray.tempStart.getX() + t2 * ray.tempDir.x;
            double hitPos2Y = ray.tempStart.getY() + t2 * ray.tempDir.y;
            double hitPos2Z = ray.tempStart.getZ() + t2 * ray.tempDir.z;
            Point3D hitPos2 = new Point3D(hitPos2X, hitPos2Y, hitPos2Z);

            RayHit rayHit2 = new RayHit(hitPos2X, hitPos2Y, hitPos2Z, t2);
            rayHit2.setNormVec(calculateM(rayHit2, ray, true));
            rayHit2.setMaterial(material);
            //}


            if((hitPos1Y > 0 && hitPos1Y < 1) || (hitPos2Y > 0 && hitPos2Y < 1)){
                if(t1 > 0.000000001){

                    if(image!=null){
                        int imageX = (int)(0.5 - Math.atan(hitPos1Z/ hitPos1X) * Math.PI);
                        int imageY= (int)(0.5 - Math.asin(-hitPos1Y) * Math.PI);

                        if(imageX >= image.getWidth()){
                            imageX = image.getWidth()-1;
                        }
                        if(imageY >= image.getHeight()){
                            imageY = image.getHeight()-1;
                        }

                        if(imageX <= 0){
                            imageX = 1;
                        }
                        if(imageY <= 0){
                            imageY = 1;
                        }

                        material.color = new Color(image.getRGB( imageX,imageY));

                    }else{
                        material.color = material.originalColor;
                    }

                    RayHit rayHit = new RayHit(hitPos1X,hitPos1Y,hitPos1Z,t1);
                    rayHit.setNormVec(rayHit1.getNormVec());
                    rayHit.invertHitpoint(scalingMatrix,translationMatrix);
                    rayHit.calculateT(ray);
                    rayHit.setMaterial(material);


                    rayHit.setHitPos1(rayHit1.getHitPos(),scalingMatrix,translationMatrix);
                    rayHit.calculateT1(ray);
                    rayHit.setM1(rayHit1.getNormVec());

                    rayHit.setHitPos2(rayHit2.getHitPos(),scalingMatrix,translationMatrix);
                    rayHit.calculateT2(ray);
                    rayHit.setM2(rayHit2.getNormVec());

                    return rayHit;
                }else if(t2 > 0.000000001){

                    if(image!=null){
                        int imageX = (int)(0.5 - Math.atan(hitPos2Z/ hitPos2X) * Math.PI);
                        int imageY= (int)(0.5 - Math.asin(-hitPos2Y) * Math.PI);

                        if(imageX >= image.getWidth()){
                            imageX = image.getWidth()-1;
                        }
                        if(imageY >= image.getHeight()){
                            imageY = image.getHeight()-1;
                        }

                        if(imageX <= 0){
                            imageX = 1;
                        }
                        if(imageY <= 0){
                            imageY = 1;
                        }

                        material.color = new Color(image.getRGB( imageX,imageY));

                    }else{
                        material.color = material.originalColor;
                    }

                    RayHit rayHit = new RayHit(hitPos2X,hitPos2Y,hitPos2Z,t2);
                    rayHit.setNormVec(rayHit2.getNormVec());
                    rayHit.invertHitpoint(scalingMatrix,translationMatrix);
                    rayHit.calculateT(ray);
                    rayHit.setMaterial(material);


                    rayHit.setHitPos1(rayHit1.getHitPos(),scalingMatrix,translationMatrix);
                    rayHit.calculateT1(ray);
                    rayHit.setM1(rayHit1.getNormVec());

                    rayHit.setHitPos2(rayHit2.getHitPos(),scalingMatrix,translationMatrix);
                    rayHit.calculateT2(ray);
                    rayHit.setM2(rayHit2.getNormVec());

                    return rayHit;
                }else{
                    return new RayHit();
                }
            }else{
                return new RayHit();
            }


        }
    }
    private Vec3d calculateM( RayHit rayHit, Ray ray, boolean inside) {
        Vec3d m = new Vec3d(rayHit.hitPoint.getX() - 0,
                rayHit.hitPoint.getY() - 0,
                rayHit.hitPoint.getZ() - 0 );

        m.normalize();

        if(inside){
            m.mul(-1);
        }

        return m;
    }
}

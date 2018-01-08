import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by kjell on 03/11/2017.
 */
public class Plane {

    double A;
    double B;
    double C;
    Point3D D;
    int number;
    Material material;
    double size;

    BufferedImage image;

    Plane(int number, Material material, BufferedImage img){
        image = img;
        this.size = size;
        this.number = number;
        this.material = material;

        switch(number){
            case 0: this.A = 0;this.B = 0;this.C = 1;
                    this.D = new Point3D(0,0,0);
                    break;
            case 1: this.A = 0;this.B = 1;this.C = 0;
                    this.D = new Point3D(0,0,0);
                    break;
            case 2: this.A = 1;this.B = 0;this.C = 0;
                    this.D = new Point3D(0,0,0);
                    break;
            case 3: this.A = 0;this.B = 0;this.C = 1;
                    this.D = new Point3D(0,0,-1);
                    break;
            case 4: this.A = 0;this.B = 1;this.C = 0;
                    this.D = new Point3D(0,-1,0);
                    break;
            case 5: this.A = 1;this.B = 0;this.C = 0;
                    this.D = new Point3D(-1,0,0);
                    break;
        }
    }


    RayHit Hit(Ray ray) {

        double teller = -(A * ray.tempStart.getX() + this.D.getX()) - (B * ray.tempStart.getY() + this.D.getY()) - (C * ray.tempStart.getZ() + this.D.getZ());
        double noemer = (A * ray.tempDir.x) + (B * ray.tempDir.y) + (C * ray.tempDir.z);
        double t = teller / noemer;

        double hitPoint_x = ray.tempStart.getX() + (t * ray.tempDir.x);
        double hitPoint_y = ray.tempStart.getY() + (t * ray.tempDir.y);
        double hitPoint_z = ray.tempStart.getZ() + (t * ray.tempDir.z);
/*
        Vec3d eyeVector = new Vec3d(ray.start.getX()-hitPoint_x,ray.start.getY()-hitPoint_y,ray.start.getZ()-hitPoint_z);
        Vec3d m = new Vec3d(A,B,C);

        eyeVector.normalize();
        m.normalize();

        double cos = eyeVector.dot(m);
        int angle = (int) Math.round(Math.toDegrees(Math.acos(cos)));

        if(angle >= 0 && angle<=0){

        }else{
            m = new Vec3d(-A,-B,-C);
        }
*/
 //       if(t> 0.0000000000001){
 //           if (size > 0) {
        if (number == 0 || number == 3) {
            if ((hitPoint_x <= 1 && hitPoint_x >= 0) && (hitPoint_y <= 1 && hitPoint_y >= 0)) {

                RayHit rayHit = new RayHit(hitPoint_x, hitPoint_y, hitPoint_z, t);

                if(image != null){
                    int imageX = (int)(hitPoint_x * image.getWidth());
                    int imageY = (int)(hitPoint_y * image.getHeight());

                    imageX = image.getWidth() - imageX;
                    imageY = image.getHeight() - imageY;

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

                    try{
                        material.color = new Color(image.getRGB( imageX,imageY));
                    }catch (Exception e){
                        System.out.println("X = " + imageX + " " + " Y= " + imageY);
                        e.printStackTrace();
                    }

                    rayHit.setMaterial(material);
                }else{
                    rayHit.setMaterial(material);
                }

                return rayHit;
            }else{
                return new RayHit();
            }
        } else if (number == 1 || number == 4) {
            if ((hitPoint_x <= 1 && hitPoint_x >= 0) && (hitPoint_z <= 1 && hitPoint_z >= 0)) {
                RayHit rayHit = new RayHit(hitPoint_x, hitPoint_y, hitPoint_z, t);

                material.color = material.originalColor;
                rayHit.setMaterial(material);
                return rayHit;
            }else{
                return new RayHit();
            }
        }else{
            if (((hitPoint_y <= 1) && (hitPoint_y >= 0)) && ((hitPoint_z <= 1) && (hitPoint_z >= 0))) {

                RayHit rayHit = new RayHit(hitPoint_x,hitPoint_y,hitPoint_z,t);
                material.color = material.originalColor;
                rayHit.setMaterial(material);
                return rayHit;
            }else{
                return new RayHit();
            }

        }
    }
}

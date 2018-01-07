import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by kjell on 17/11/2017.
 */
public class Light {

    Point3D pos;
    double brightness = 1;
    double Ia = 0.8;
    int MaxDepth = 3;


    Light(double x,double y,double z){
        pos = new Point3D(x,y,z);

    }

    Color getColor(RayHit hitPoint, Point3D viewPoint, boolean shadow,Object objects[], Ray ray,int depth, Material prevMaterial){

        Color color = new Color(hitPoint.getMaterial().color.getRed(),hitPoint.getMaterial().color.getGreen(),hitPoint.getMaterial().color.getBlue());

        //Reflection
        if((depth < MaxDepth) && (hitPoint.getMaterial().reflection != 0)){
            double rX = ray.dir.x - (2 * (ray.dir.dot(hitPoint.getNormVec())) * hitPoint.getNormVec().x);
            double rY = ray.dir.y - (2 * (ray.dir.dot(hitPoint.getNormVec())) * hitPoint.getNormVec().y);
            double rZ = ray.dir.z - (2 * (ray.dir.dot(hitPoint.getNormVec())) * hitPoint.getNormVec().z);

            Vec3d reflectDir = new Vec3d(rX,rY,rZ);
            reflectDir.normalize();
            double closeHit = -1;

            Ray reflectRay = new Ray();
            reflectRay.setStart(hitPoint.getHitPos());
            reflectRay.setDir(reflectDir);

            RayHit hit = new RayHit();

            int hitObject = -1;
                for (int obj = 0; obj < objects.length; obj++) {

                    RayHit temphit = objects[obj].Hit(reflectRay);
                    if(temphit.getIsHit()){
                        if(((closeHit < 0) || (temphit.getT() < closeHit))) {
                            closeHit = temphit.getT();
                            hit = temphit;
                            hitObject = obj;
                        }
                    }

                }

                if(hit.getIsHit()){
                    boolean newShadow = this.checkShadow(hit.getHitPos(),objects,hitObject);
                    //Color reflectColor = this.getColor(hit,hit.hitPos,newShadow,objects,reflectRay,depth+1,objects[hitObject].material,hitObject);
                    Color reflectColor = this.getColor(hit,reflectRay.start,newShadow,objects,reflectRay,depth+1,hitPoint.getMaterial());

                    int red = (int)((color.getRed() * (1-hit.getMaterial().reflection)) + (reflectColor.getRed() * hit.getMaterial().reflection));
                    int green = (int)((color.getGreen() * (1-hit.getMaterial().reflection)) + (reflectColor.getGreen() * hit.getMaterial().reflection));
                    int blue = (int)((color.getBlue() * (1-hit.getMaterial().reflection)) + (reflectColor.getBlue() * hit.getMaterial().reflection));

                    color = new Color(red,green,blue);

                }
        }

        //Refraction
        if((depth < MaxDepth) && (hitPoint.getMaterial().refraction!= 0)){

            double cosTeta = Math.sqrt(1 - (Math.pow((hitPoint.getMaterial().n/prevMaterial.n),2) * (1 - Math.pow((hitPoint.getNormVec().dot(ray.dir)),2))));
            double rX = ((hitPoint.getMaterial().n/prevMaterial.n) * ray.dir.x) + ((((hitPoint.getMaterial().n/prevMaterial.n) * (hitPoint.getNormVec().dot(ray.dir))) - cosTeta) * hitPoint.getNormVec().x);
            double rY = ((hitPoint.getMaterial().n/prevMaterial.n) * ray.dir.y) + ((((hitPoint.getMaterial().n/prevMaterial.n) * (hitPoint.getNormVec().dot(ray.dir))) - cosTeta) * hitPoint.getNormVec().y);
            double rZ = ((hitPoint.getMaterial().n/prevMaterial.n) * ray.dir.z) + ((((hitPoint.getMaterial().n/prevMaterial.n) * (hitPoint.getNormVec().dot(ray.dir))) - cosTeta) * hitPoint.getNormVec().z);


            /*
            double cosTeta = Math.sqrt(1 - (Math.pow((0.8),2) * (1 - Math.pow((hitPoint.normVec.dot(ray.dir)),2))));
            double rX = ((0.8) * ray.dir.x) + ((((0.8) * (hitPoint.normVec.dot(ray.dir))) - cosTeta) * hitPoint.normVec.x);
            double rY = ((0.8) * ray.dir.y) + ((((0.8) * (hitPoint.normVec.dot(ray.dir))) - cosTeta) * hitPoint.normVec.y);
            double rZ = ((0.8) * ray.dir.z) + ((((0.8) * (hitPoint.normVec.dot(ray.dir))) - cosTeta) * hitPoint.normVec.z);
*/
            Vec3d reflectDir = new Vec3d(rX,rY,rZ);
            reflectDir.normalize();
            double closeHit = -1;

            Ray reflectRay = new Ray();
            reflectRay.setStart(hitPoint.getHitPos());
            reflectRay.setDir(reflectDir);

            RayHit hit = new RayHit();

            int hitObject = -1;
            for (int obj = 0; obj < objects.length; obj++) {

                RayHit temphit = objects[obj].Hit(reflectRay);
                if(temphit.getIsHit()){
                    if(((closeHit < 0) || (temphit.getT() < closeHit))) {
                        closeHit = temphit.getT();
                        hit = temphit;
                        hitObject = obj;
                    }
                }

            }

            if(hit.getIsHit()){
                boolean newShadow = this.checkShadow(hit.getHitPos(),objects,hitObject);
                Color reflectColor = this.getColor(hit,reflectRay.start,newShadow,objects,reflectRay,depth+1,hitPoint.getMaterial());
                //System.out.println("Color = " + color + " refract = " + hitPoint.material.refraction);
                int red = (int)((color.getRed() * (1-hitPoint.getMaterial().refraction)) + (reflectColor.getRed() * hitPoint.getMaterial().refraction));
                int green = (int)((color.getGreen() * (1-hitPoint.getMaterial().refraction)) + (reflectColor.getGreen() * hitPoint.getMaterial().refraction));
                int blue = (int)((color.getBlue() * (1-hitPoint.getMaterial().refraction)) + (reflectColor.getBlue() * hitPoint.getMaterial().refraction));

                color = new Color(red,green,blue);

            }
        }



        Vec3d s = new Vec3d(pos.getX()-hitPoint.getHitPos().getX(),pos.getY()-hitPoint.getHitPos().getY(),pos.getZ()-hitPoint.getHitPos().getZ());
        s.normalize();
        Vec3d v = new Vec3d(viewPoint.getX()-hitPoint.getHitPos().getX(),viewPoint.getY()-hitPoint.getHitPos().getY(),viewPoint.getZ()-hitPoint.getHitPos().getZ());
        /*
        if((depth == 1)&&(exludeObj == 0)){
            System.out.println("viewPoint = " + viewPoint + " hitPoint = " + hitPoint.getHitPos());
            System.out.println("v = " + v);
        }
        */
        v.normalize();



        Vec3d r = new Vec3d((-s.x + 2 * ((s.dot(hitPoint.getNormVec()))/ (hitPoint.getNormVec().length() *
                hitPoint.getNormVec().length())) * hitPoint.getNormVec().x),(-s.y + 2 * ((s.dot(hitPoint.getNormVec()))/ (hitPoint.getNormVec().length() *
                hitPoint.getNormVec().length())) * hitPoint.getNormVec().y),(-s.z + 2 * ((s.dot(hitPoint.getNormVec()))/ (hitPoint.getNormVec().length() *
                hitPoint.getNormVec().length())) * hitPoint.getNormVec().z));
        r.normalize();

        double Id = brightness * hitPoint.getMaterial().diffuseReflect * (s.dot(hitPoint.getNormVec())) / (s.length()*hitPoint.getNormVec().length());

        if(Id < 0){
            Id = 0;
        }


        double Isp = brightness * hitPoint.getMaterial().specularReflect * (r.dot(v));

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
        int intRed = (int)(Red*Ir);

        double Green = color.getGreen();
        int intGreen = (int)(Green*Ig);

        double Blue = color.getBlue();
        int intBlue = (int)(Blue*Ib);

        if(intRed > 255){
            intRed = 255;
        }

        if(intGreen > 255){
            intGreen = 255;
        }

        if(intBlue > 255){
            intBlue = 255;
        }


        if(hitPoint.getMaterial().refraction != 1){
            Color newColor = new Color(intRed,intGreen,intBlue);
            return newColor;
        }else{
            Color newColor = color;
            return newColor;
        }




    }

    boolean checkShadow(Point3D hitPoint,Object[] objects, int hitObj){

        Vec3d hitPointDir = new Vec3d(pos.getX()-hitPoint.getX(),pos.getY()-hitPoint.getY(),pos.getZ()-hitPoint.getZ());
        double distance = hitPointDir.length();
        hitPointDir.normalize();

        Ray ray = new Ray();
        ray.setStart(hitPoint);
        ray.setDir(hitPointDir);
        RayHit hit = new RayHit();


        for (int obj = 0; obj < objects.length; obj++) {

            if(hitObj != obj){
                hit = objects[obj].Hit(ray);
            }else{

            }
            if(hit.getIsHit() && (hit.t1 < distance) && (hit.t1 > 0)){
                //System.out.println("distance " + distance + " hit " + hit.t);
                return true;
            }
        }

        return false;
    }

}

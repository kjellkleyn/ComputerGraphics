import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kjell on 6-10-2017.
 */
public class Cube extends Object {
    Plane planes[] = new Plane[6];

    Cube(double x,double y,double z,double size , Material material){
        super(x,y,z,size,material);



        for(int i = 0;i<6;i++){
            planes[i] = new Plane(i,material);
        }


    }

    public RayHit Hit(Ray ray){

        //RayHit tempHit;

        ray.TranslateRay(scalingMatrix, translationMatrix);
        int number = 0;

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        ArrayList<RayHit>  hits = new ArrayList<RayHit>();

        RayHit rayHit = planes[0].Hit(ray);

        if(rayHit.getIsHit()){
            hits.add(new RayHit(rayHit.getHitPos().getX(),rayHit.getHitPos().getY(),rayHit.getHitPos().getZ(),rayHit.getT()));
            numbers.add(0);

            if(!(rayHit.getT() > 0.0000000000001)){
                rayHit = new RayHit();
            }
        }

        for(int i = 1;i<6;i++){
            RayHit tempHit = planes[i].Hit(ray);

            if(((tempHit.isHit && tempHit.getT() < rayHit.getT()) || (tempHit.isHit && !rayHit.isHit)) && tempHit.getT() > 0){
                rayHit = tempHit;
                number = i;
            }

            if(tempHit.getIsHit()){
                hits.add(new RayHit(tempHit.getHitPos().getX(),tempHit.getHitPos().getY(),tempHit.getHitPos().getZ(),tempHit.getT()));
                numbers.add(i);
            }
        }

        if(rayHit.getIsHit()){
            rayHit.invertHitpoint(scalingMatrix,translationMatrix);
            rayHit.calculateT(ray);
            rayHit.setNormVec(calculateM(rayHit,ray,number));
            rayHit.setMaterial(material);

            if(hits.size() == 2) {
                if (hits.get(0).t < hits.get(1).t) {
                    rayHit.setHitPos1(hits.get(0).hitPoint, scalingMatrix, translationMatrix);
                    rayHit.calculateT1(ray);
                    rayHit.setM1( calculateM(rayHit, ray, numbers.get(0)) );
                    rayHit.setHitPos2(hits.get(1).hitPoint, scalingMatrix, translationMatrix);
                    rayHit.calculateT2(ray);
                    rayHit.setM2( calculateM(rayHit, ray, numbers.get(1)) );
                } else {
                    rayHit.setHitPos1(hits.get(1).hitPoint, scalingMatrix, translationMatrix);
                    rayHit.calculateT1(ray);
                    rayHit.setM1( calculateM(rayHit, ray, numbers.get(1)) );
                    rayHit.setHitPos2(hits.get(0).hitPoint, scalingMatrix, translationMatrix);
                    rayHit.calculateT2(ray);
                    rayHit.setM2( calculateM(rayHit, ray, numbers.get(0)) );
                }
            } else {
                rayHit.setHitPos1(hits.get(0).hitPoint, scalingMatrix, translationMatrix);
                rayHit.calculateT1(ray);
                rayHit.setM1( calculateM(rayHit, ray, numbers.get(0)) );
                rayHit.setHitPos2(hits.get(0).hitPoint, scalingMatrix, translationMatrix);
                rayHit.calculateT2(ray);
                rayHit.setM2( calculateM(rayHit, ray, numbers.get(0)) );
            }
        }

        return rayHit;
    }

    private Vec3d calculateM(RayHit hit, Ray ray, int number ) {

        double A = 0.0, B = 0.0, C = 0.0;
        if( number == 2 || number == 5 ) A = 1.0;
        if( number == 1 || number == 4 ) B = 1.0;
        if( number == 0 || number == 3 ) C = 1.0;

        Vec3d eyeVector = new Vec3d( ray.start.getX() - hit.hitPoint.getX(),
                ray.start.getY() - hit.hitPoint.getY(),
                ray.start.getZ() - hit.hitPoint.getZ() );

        Vec3d m = new Vec3d( A, B, C );

        eyeVector.normalize();
        m.normalize();

        double cos = eyeVector.dot( m );
        int angle =(int) Math.round( Math.toDegrees(Math.acos(cos)) );

        if( angle >= 0  && angle <= 90)
            return m;
        else {
            return new Vec3d(-A, -B, -C);
        }
    }
}

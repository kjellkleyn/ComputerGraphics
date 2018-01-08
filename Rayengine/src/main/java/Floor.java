/**
 * Created by kjell on 17/11/2017.
 */
import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by kjell on 6-10-2017.
 */
public class Floor extends Object {
    Plane plane;
    Color colors[] = {Color.RED,Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW,Color.black};
    Color color;

    Floor(double x, double y, double z, double size , Material material, BufferedImage image){
        super(x,y,z,size,size,size,material);

        plane= new Plane(2,material,image);



    }

    public RayHit Hit(Ray ray){

        ray.TranslateRay(scalingMatrix,translationMatrix);
        RayHit tempHit = plane.Hit(ray);

        if(tempHit.getIsHit()){
            tempHit.invertHitpoint(scalingMatrix, translationMatrix);
            tempHit.calculateT(ray);
            tempHit.setNormVec(calculateM(tempHit,ray,0));
            tempHit.setMaterial(material);
        }

        return tempHit;
    }

    private Vec3d calculateM(RayHit hit, Ray ray, int number ) {

        double A = 0.0, B = 0.0, C = 0.0;
        if( number == 2 || number == 5 ) A = 1.0;
        if( number == 1 || number == 4 ) B = 1.0;
        if( number == 0 || number == 3 ) C = 1.0;

        Vec3d eyeVector = new Vec3d( ray.start.getX() - hit.transHitPos.getX(),
                ray.start.getY() - hit.transHitPos.getY(),
                ray.start.getZ() - hit.transHitPos.getZ() );

        Vec3d m = new Vec3d( A, B, C );

        eyeVector.normalize();
        m.normalize();

        double cos = eyeVector.dot( m );
        int angle =(int) Math.round( Math.toDegrees(Math.acos(cos)) );

        //System.out.println( "cos theta: " + angle );

        if( angle >= 0  && angle <= 90)
            return m;
        else {
            //System.out.println("Viewing from the bottom");
            return new Vec3d(-A, -B, -C);
        }
    }
}


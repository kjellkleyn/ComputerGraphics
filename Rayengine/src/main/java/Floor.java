/**
 * Created by kjell on 17/11/2017.
 */
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by kjell on 6-10-2017.
 */
public class Floor extends Object {
    Square vlak;
    Plane plane;
    Color colors[] = {Color.RED,Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW,Color.black};
    Color color;

    Floor(double x,double y,double z,double size , Color c){
        super(x,y,z,size,c);

        color = c;

        plane= new Plane(1,color,size);



    }

    public RayHit Hit(Ray ray){

        //RayHit tempHit;
        RayHit hit = new RayHit();
        //boolean firstHit = true;

        ray.TranslateRay(scalingMatrix,translationMatrix);

        //for(int i = 0;i<6;i++){
            RayHit tempHit = plane.Hit(ray);
/*
            tempHit.setColor(this.color);
            if(!firstHit){
                //System.out.println("another hit = " + tempHit.getIsHit());
                if((tempHit.getIsHit() && tempHit.getT() < hit.getT())){
                    hit = tempHit;
                }
            }
            /*
            if(tempHit.getIsHit() && firstHit){
                //System.out.println("first hit = " + tempHit.getIsHit());
                hit = tempHit;
                firstHit = false;
            }

        }
*/
        return tempHit;
    }
}


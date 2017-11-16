import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by kjell on 13-10-2017.
 */
public abstract class Object {
    private Point3D position;
    Point3D vector;
    Color color;
    TransformationMatrix3D translationMatrix, scalingMatrix;
    private TransformationFactory transFact = new TransformationFactory();
    private double size;
    public Object(){
        this.vector = new Point3D(0,0,0);
        this.color = new Color(255, 134, 255);
        this.size = 1;
    }

    public Object(double x,double y,double z,double size , Color c){
        this.vector = new Point3D(x,y,z);
        this.color = c;
        this.size = size;

        translationMatrix = TransformationFactory.getTranslationMatrix(vector.getX(),vector.getY(),vector.getZ());
        scalingMatrix = TransformationFactory.getScalingMatrix(size,size,size);

    }
    abstract RayHit Hit(Ray ray);

    public Color getColor(){
        return this.color;
    }

}

import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by kjell on 13-10-2017.
 */
public abstract class Object {
    private Point3D position;
    Point3D vector;
    TransformationMatrix3D translationMatrix, scalingMatrix;
    private TransformationFactory transFact = new TransformationFactory();
    private double sizeX,sizeY,sizeZ;
    Material material;

    public Object(){
        this.vector = new Point3D(0,0,0);
        this.sizeX = 1;
        this.sizeY = 1;
        this.sizeZ = 1;
    }

    public Object(double x,double y,double z,double sizex,double sizey,double sizez, Material material){
        this.vector = new Point3D(x,y,z);
        this.material = material;
        this.sizeX = sizex;
        this.sizeY = sizey;
        this.sizeZ = sizez;

        translationMatrix = TransformationFactory.getTranslationMatrix(vector.getX(),vector.getY(),vector.getZ());
        scalingMatrix = TransformationFactory.getScalingMatrix(sizeX,sizeY,sizeZ);

    }
    abstract RayHit Hit(Ray ray);

    public Color getColor(){
        return this.material.color;
    }

}

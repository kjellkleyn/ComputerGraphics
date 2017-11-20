import Jama.Matrix;
import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by kjell on 03/11/2017.
 */
public class RayHit {

    Point3D hitPos;
    Point3D transHitPos;
    Vec3d normVec;
    double t;
    boolean isHit = false;
    Color color = Color.cyan;

    RayHit(double t, Color c, Point3D hitPos, Vec3d normVec) {
        this.t = t;
        isHit = true;

        setColor(c);

        this.hitPos = hitPos;

        this.normVec = normVec;
    }

    RayHit() {
        isHit = false;
        this.t = -1;
    }

    void InverTranslateRay(TransformationMatrix3D scalingMatrix, TransformationMatrix3D translationMatrix){

        double [][] startArray = {{hitPos.getX()},{hitPos.getY()},{hitPos.getZ()},{1}};
        Matrix startMatrix = new Matrix(startArray);

        Matrix newStart = translationMatrix.matrix.times(startMatrix);
        newStart = scalingMatrix.matrix.times(newStart);

        transHitPos = new Point3D(newStart.get(0,0),newStart.get(1,0),newStart.get(2,0));

    }

    public boolean getIsHit() {
        return isHit;
    }

    public double getT() {
        return this.t;
    }

    public Color getColor(){return this.color;}
    public void setColor(Color c){
        this.color = c;
    }

    public Point3D getHitPos(){
        return this.transHitPos;
    }

    void calcHitDist(Ray ray){

        double tx = (ray.dir.x != 0)?(transHitPos.getX() - ray.start.getX()) / ray.dir.x : 0;
        double ty = (ray.dir.y != 0)?(transHitPos.getY() - ray.start.getY()) / ray.dir.y : 0;
        double tz = (ray.dir.z != 0)?(transHitPos.getZ() - ray.start.getZ()) / ray.dir.z : 0;
        if( ray.dir.x != 0 ) this.t = tx;
        else if( ray.dir.y != 0 ) this.t = ty;
        else this.t = tz;


    }

    public Vec3d getNormVec(){
        return this.normVec;
    }
}
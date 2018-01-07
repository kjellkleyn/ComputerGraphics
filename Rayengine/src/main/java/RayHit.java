import Jama.Matrix;
import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by kjell on 03/11/2017.
 */
public class RayHit {

    double t;
    Point3D hitPoint;
    Material material;
    Vec3d normVec;


    Point3D hitPos1,hitPos2;
    Point3D transHitPos;
    Vec3d normVec1,normVec2;
    double t1,t2,transT1,transT2;
    Vec3d m1,m2;
    boolean isHit = false;
    Material material1, material2;


    //Deze was voor cube
    RayHit(double x, double y, double z, double t) {
        this.t = t;

        hitPoint = new Point3D(x,y,z);
        isHit = true;

        hitPos1 = null;
        m1 = null;
        hitPos2 = null;
        m2 = null;

    }

    RayHit(boolean hit){
        isHit = hit;
        t = -1;
    }

    RayHit() {
        isHit = false;
        this.t = -1;
    }

    public void invertHitpoint( TransformationMatrix3D scaling, TransformationMatrix3D translation ) {
        double temp[][] = { {hitPoint.getX()}, {hitPoint.getY()}, {hitPoint.getZ()}, {1.0} };
        Matrix hitpointMatrix = new Matrix( temp );
        Matrix result = scaling.getMatrix().times( hitpointMatrix );
        result = translation.getMatrix().times( result );

        hitPoint = new Point3D( result.get(0,0 ),
                result.get(1,0 ),
                result.get(2,0 ) );
    }

    public void calculateT( Ray ray ) {
        // Calculate new 't'
        double tx = (ray.getDir().x != 0)?(hitPoint.getX() - ray.start.getX()) / ray.getDir().x : 0;
        double ty = (ray.getDir().y != 0)?(hitPoint.getY() - ray.start.getY()) / ray.getDir().y : 0;
        double tz = (ray.getDir().z != 0)?(hitPoint.getZ() - ray.start.getZ()) / ray.getDir().z : 0;

        if( ray.getDir().x != 0 ) t = tx;
        else if( ray.getDir().y != 0 ) t = ty;
        else t = tz;
    }


    public boolean getIsHit() {
        return isHit;
    }

    public Color getColor(){return this.material.color;}


    public Point3D getHitPos(){
        return this.hitPoint;
    }

    public double getT(){
        return t;
    }

    public Vec3d getNormVec(){
        return this.normVec;
    }

    public void setNormVec(Vec3d m){
        normVec = m;
    }

    public void setMaterial(Material material){
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setT1(double t1){
        this.t1 = t1;
    }

    public void setT2(double t2){

    }

    public void setHitPos1(Point3D hitPos1){
        this.hitPos1 = hitPos1;
    }

    public void setHitPos2(Point3D hitPos2) {
        this.hitPos2 = hitPos2;
    }

    public void setM1(Vec3d m1) {
        this.m1 = m1;
    }

    public void setM2(Vec3d m2) {
        this.m2 = m2;
    }

    public void calculateT1(Ray ray){
        double tx = (ray.getDir().x != 0)?(hitPos1.getX() - ray.start.getX()) / ray.getDir().x : 0;
        double ty = (ray.getDir().y != 0)?(hitPos1.getY() - ray.start.getY()) / ray.getDir().y : 0;
        double tz = (ray.getDir().z != 0)?(hitPos1.getZ() - ray.start.getZ()) / ray.getDir().z : 0;

        if( ray.getDir().x != 0 ) t1 = tx;
        else if( ray.getDir().y != 0 ) t1 = ty;
        else t1 = tz;
    }

    public void calculateT2(Ray ray){
        double tx = (ray.getDir().x != 0)?(hitPos2.getX() - ray.start.getX()) / ray.getDir().x : 0;
        double ty = (ray.getDir().y != 0)?(hitPos2.getY() - ray.start.getY()) / ray.getDir().y : 0;
        double tz = (ray.getDir().z != 0)?(hitPos2.getZ() - ray.start.getZ()) / ray.getDir().z : 0;

        if( ray.getDir().x != 0 ) t1 = tx;
        else if( ray.getDir().y != 0 ) t1 = ty;
        else t2 = tz;
    }

    public void setHitPos1(Point3D hitP1, TransformationMatrix3D scaling, TransformationMatrix3D translate){
        double temp[][] = {{hitP1.getX()},{hitP1.getY()},{hitP1.getZ()},{1.0}};

        Matrix hitP1Matrix = new Matrix(temp);

        Matrix newHitP = scaling.getMatrix().times(hitP1Matrix);
        newHitP = translate.getMatrix().times(newHitP);

        hitPos1 = new Point3D(newHitP.get(0,0),newHitP.get(1,0),newHitP.get(2,0));
    }

    public void setHitPos2(Point3D hitP2, TransformationMatrix3D scaling, TransformationMatrix3D translate){

        double temp[][] = {{hitP2.getX()},{hitP2.getY()},{hitP2.getZ()},{1.0}};

        Matrix hitP2Matrix = new Matrix(temp);

        Matrix newHitP = scaling.getMatrix().times(hitP2Matrix);
        newHitP = translate.getMatrix().times(newHitP);

        hitPos2 = new Point3D(newHitP.get(0,0),newHitP.get(1,0),newHitP.get(2,0));
    }

}
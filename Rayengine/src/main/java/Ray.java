import Jama.Matrix;
import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;


/**
 * Created by kjell on 13-10-2017.
 */
public class Ray {
    Point3D start;
    Vec3d dir;
    Point3D tempStart;
    Vec3d tempDir;
    Matrix startMatrix;
    Matrix dirMatrix;

    void setStart(Point3D p){
        start = new Point3D(p.getX(),p.getY(),p.getZ());
        double [][] startArray = {{start.getX()},{start.getY()},{start.getZ()},{1.0}};
        this.startMatrix = new Matrix(startArray);
    }
    void setStart(double x, double y, double z){
        start = new Point3D(x,y,z);

        double [][] startArray = {{start.getX()},{start.getY()},{start.getZ()},{1}};
        this.startMatrix = new Matrix(startArray);
    }

    void setDir(Vec3d v){
        dir = v;
        double [][] dirArray = {{dir.x},{dir.y},{dir.z},{1}};
        this.dirMatrix = new Matrix(dirArray);
    }
    void setDir(double x, double y, double z){
        dir = new Vec3d(x,y,z);

        dir.normalize();



        double [][] dirArray = {{dir.x},{dir.y},{dir.z},{1}};
        this.dirMatrix = new Matrix(dirArray);
    }

    void TranslateRay(TransformationMatrix3D scalingMatrix, TransformationMatrix3D translationMatrix){

        Matrix newStart = translationMatrix.imatrix.times(startMatrix);

        //newStart = scalingMatrix.imatrix.times(newStart);
        //translationMatrix.printInverseMatrix();
        //System.out.println("x " + newStart.get(0,0) + " y " + newStart.get(1,0) + " z " + newStart.get(2,0));

        tempStart = new Point3D(newStart.get(0,0),newStart.get(1,0),newStart.get(2,0));
        tempDir = new Vec3d(dir.x,dir.y,dir.z);
 /*
        tempStart = start;
        tempDir = dir;
*/
    }

    void InverTranslateRay(TransformationMatrix3D scalingMatrix, TransformationMatrix3D translationMatrix){
        Matrix newStart = translationMatrix.imatrix.times(startMatrix);

        tempStart = new Point3D(newStart.get(0,0),newStart.get(1,0),newStart.get(2,0));
        tempDir = new Vec3d(dir.x,dir.y,dir.z);
    }

    void PrintRay(){
        System.out.println("Ray start = " + start + " Ray dir = " + dir);
    }
}

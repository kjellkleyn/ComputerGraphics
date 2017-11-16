/**
 * Created by kjell on 13-10-2017.
 */
public class TransformationFactory
{
    public static TransformationMatrix3D getScalingMatrix(double x, double y, double z){
        double[][] matrix = {{x,0.0,0.0,0.0},{0.0,y,0.0,0.0},{0.0,0.0,z,0.0},{0.0,0.0,0.0,1.0}};
        double[][] inverseMatrix = {{1/x,0.0,0.0,0.0},{0.0,1/y,0.0,0.0},{0.0,0.0,1/z,0.0},{0.0,0.0,0.0,1.0}};
        return new TransformationMatrix3D(matrix,inverseMatrix);
    }

    public static TransformationMatrix3D getTranslationMatrix(double x, double y, double z){
        double[][] matrix = {{1.0,0.0,0.0,x},{0.0,1.0,0.0,y},{0.0,0.0,1.0,z},{0.0,0.0,0.0,1.0}};
        double[][] inverseMatrix = {{1.0,0.0,0.0,-x},{0.0,1.0,0.0,-y},{0.0,0.0,1.0,-z},{0.0,0.0,0.0,1.0}};
        return new TransformationMatrix3D(matrix,inverseMatrix);
    }
}

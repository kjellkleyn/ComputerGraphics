import Jama.Matrix;

/**
 * Created by kjell on 13-10-2017.
 */

public class TransformationMatrix3D {

    public Matrix matrix;
    public Matrix imatrix;

    public TransformationMatrix3D( double[][] newMatrix, double[][] inverseMatrix ) {
        matrix = new Matrix( newMatrix );
        imatrix = new Matrix( inverseMatrix );
    }

    private TransformationMatrix3D( Matrix newMatrix, Matrix inverseMatrix ) {
        matrix = newMatrix ;
        imatrix = inverseMatrix ;
    }

    public Matrix getMatrix() { return matrix; }
    public Matrix getInverseMatrix() { return imatrix; }


    //TODO: mul voor extra translatie te kunnen doen

    public TransformationMatrix3D mult(TransformationMatrix3D m)
    {
        return new TransformationMatrix3D(matrix.times(m.getMatrix()),m.getInverseMatrix().times(imatrix));
    }

    public void printMatrix() {
        for( short i = 0; i < 4; i++) {
            for( short j = 0; j < 4; j++) {
                System.out.print(matrix.get(i, j)+" ");
            }
            System.out.println();
        }
    }

    public void printInverseMatrix() {
        for( short i = 0; i < 4; i++) {
            for( short j = 0; j < 4; j++) {
                System.out.print(imatrix.get(i, j)+" ");
            }
            System.out.println();
        }
    }

}



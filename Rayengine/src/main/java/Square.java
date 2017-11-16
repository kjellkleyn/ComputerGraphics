import javafx.geometry.Point3D;

/**
 * Created by kjell on 20-10-2017.
 */
public class Square {
    Point3D Normaal;
    Point3D D;

    Square(double x, double y, double z, Point3D D){
        Normaal = new Point3D(x,y,z);
        this.D = D;
    }
}

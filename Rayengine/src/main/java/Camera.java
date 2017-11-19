import com.sun.javafx.geom.Vec3d;
import com.sun.javafx.geom.Vec3f;
import javafx.geometry.Point3D;

/**
 * Created by kjell on 6-10-2017.
 */
public class Camera {
    private Point3D eye, look;
    private Vec3d up;
    private Vec3d u, v, n;
    double viewAngle, aspect, nearDist, farDist;
    double H,W;
    double nCols = 800;
    double nRows = 800;

    private void setModelViewMatrix(){ //tell where the camera is (was voor opengl mss moet da ni mr)

    }

    public Camera(){
        this.nearDist = 1;
        this.viewAngle = Math.toRadians(30.00);

        H = (nearDist * Math.tan(this.viewAngle)/2);
        W = H;
    }

    void set(Point3D eye, Point3D look, Vec3d up){
        this.eye = eye;
        this.look = look;
        this.up = up;



        n = new Vec3d(eye.getX()-look.getX(),eye.getY()-look.getY(),eye.getZ()-look.getZ());
        n.normalize();

        u = new Vec3d(0,0,0);
        u.cross(up,n);
        u.normalize();

        v = new Vec3d(0,0,0);
        v.cross(n,u);
        v.normalize();

        //System.out.println("Camera: n " + n);
        //System.out.println("Camera: u " + u);
        //System.out.println("Camera: v " + v);


    }
    void roll(float angle){}
    void pitch(float angle){}
    void yaw(float angle){}
    void slide(float delU, float delV, float delN){}
    void setShape(float vAng, float asp, float nearD, float farD){}
    void getShape(float vAng, float asp, float nearD, float farD){}

    Ray getRay(double c, double r){

        /* manier van den boek */



        double rayX = (-nearDist * n.x) + (W * (((2 * c)/(nCols)) - 1) *u.x) + (H * (((2 * r)/(nRows)) - 1)*v.x);
        double rayY = (-nearDist * n.y) + (W * (((2 * c)/(nCols)) - 1) *u.y) + (H * (((2 * r)/(nRows)) - 1)*v.y);
        double rayZ = (-nearDist * n.z) + (W * (((2 * c)/(nCols)) - 1) *u.z) + (H * (((2 * r)/(nRows)) - 1)*v.z);


        Ray ray = new Ray();
        ray.setStart(eye.getX(),eye.getY(),eye.getZ());


        ray.setDir(rayX,rayY,rayZ);

        //System.out.println("dirrr = " + ray.dir);

        /* Dees werkt voor u rays te schiete */
        /*
        Ray ray = new Ray();
        ray.setStart(eye.getX(),eye.getY(),eye.getZ());

        double pixelX = eye.getX() + look.getX();
        double pixelY = ((((2 * W)/nCols) * c) - W) + eye.getY();
        double pixelZ = ((((2 * H)/nRows) * r) - H) + eye.getZ();

        double dirX = pixelX - eye.getX();
        double dirY = pixelY - eye.getY();
        double dirZ = pixelZ - eye.getZ();

        //System.out.println("Get RAY_____________________");
        //System.out.println("dirX = " + dirX + " dirY = " + dirY + " dirZ = " + dirZ);


        ray.setDir(dirX,dirY,dirZ);
        */


        return ray;
    }

    public Point3D getEye(){
        return this.eye;
    }

}

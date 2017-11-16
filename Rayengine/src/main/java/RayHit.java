import java.awt.*;

/**
 * Created by kjell on 03/11/2017.
 */
public class RayHit {

    double t;
    boolean isHit = false;
    Color color = Color.cyan;

    RayHit(double t, Color c) {
        this.t = t;
        isHit = true;

        setColor(c);
    }

    RayHit() {
        isHit = false;
        this.t = -1;
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
}
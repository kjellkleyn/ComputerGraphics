import java.awt.*;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by kjell on 6-10-2017.
 */
public class Engine {
    private Color color = new Color(0, 255, 0);
    int sizeX,sizeY,sizeZ;
    int R,G,B;

    Engine(int x, int y,int z){
        sizeX = x;
        sizeY = y;
        sizeZ = z;
    }

    public Color getColor(int i, int j){

        Random rn = new Random();
        int randColorR = abs(rn.nextInt()%255);
        int randColorG = abs(rn.nextInt()%255);
        int randColorB = abs(rn.nextInt()%255);

        color = new Color(randColorR,randColorG,randColorB);
        return color;
    }
}

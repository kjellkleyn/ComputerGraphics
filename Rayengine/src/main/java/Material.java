import java.awt.*;

/**
 * Created by kjell on 23/11/2017.
 */
public class Material {

    double diffuseReflect = 0.5;
    double specularReflect = 0.5;
    double reflection = 0.01;
    double refraction = 0.5;
    double n = 0.5;
    Color color;
    Color originalColor;

    Material(){
        color = Color.red;
        diffuseReflect = 0.5;
        specularReflect = 0.5;
    }

    Material(Color c, double diff, double spec, double refl, double refr, double n){
        this.color = c;
        this.originalColor = c;
        this.diffuseReflect = diff;
        this.specularReflect = spec;
        this.reflection = refl;
        this.refraction = refr;
        this.n = n;
    }

}

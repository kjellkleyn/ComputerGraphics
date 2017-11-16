import javax.swing.*;
import java.awt.*;

/**
 * Created by pehe on 24/01/17.
 */
public class Canvas extends JFrame
{

    public Canvas(){
        initUI();
    }

    private void initUI(){
        add(new GFXPanel(800,800));
        setTitle("3D Engine");
        setSize(800,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Canvas c = new Canvas();
        c.setVisible(true);

        while(true){
            c.repaint();
            c.setVisible(true);
        }



    }


}

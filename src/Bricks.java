import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bricks {
    public List<Brick> bricks = new ArrayList<>();

    public Bricks () {
        for (int i = 1; i < 10; i++) {
            bricks.add(new Brick(i*70, 110,i*70+70, 130, 1, new Color(203,83,129), false));
            bricks.add(new Brick(i*70, 130,i*70+70, 150, 2, new Color(214,103,50), false));
            bricks.add(new Brick(i*70, 150,i*70+70, 170, 1, new Color(203,83,129),  false));
            bricks.add(new Brick(i*70, 170,i*70+70, 190, 2, new Color(214,103,50),  false));
            bricks.add(new Brick(i*70, 190,i*70+70, 210, 1, new Color(203,83,129),  false));
        }
    }
}

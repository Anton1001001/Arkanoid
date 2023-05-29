import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bricks {
    public static List<Brick> bricks;

    public Bricks () {
        int Width = Game.WIDTH;
        int Height = Game.HEIGHT;
        bricks = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            bricks.add(new Brick(i * (Width / 11), (int) (Height * 0.15), (i + 1) * (Width / 11), (int) (Height * 0.18), 1, new Color(203,83,129), false));
            bricks.add(new Brick(i * (Width / 11), (int) (Height * 0.18), (i + 1) * (Width / 11), (int) (Height * 0.21), 2, new Color(214,103,50), false));
            bricks.add(new Brick(i * (Width / 11), (int) (Height * 0.21), (i + 1) * (Width / 11), (int) (Height * 0.24), 1, new Color(203,83,129), false));
            bricks.add(new Brick(i * (Width / 11), (int) (Height * 0.24), (i + 1) * (Width / 11), (int) (Height * 0.27), 2, new Color(214,103,50), false));
            bricks.add(new Brick(i * (Width / 11), (int) (Height * 0.27), (i + 1) * (Width / 11), (int) (Height * 0.3), 1, new Color(203,83,129), false));
       }
    }

    public static void repaintBricks(){
        int Width = Game.WIDTH;
        int Height = Game.HEIGHT;
        int index = 0;
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                bricks.get(index).x1 = i * (Width / 11);
                bricks.get(index).y1 = (int) (Height * (0.15 + (j * 0.03)));
                bricks.get(index).x2 = (i + 1) * (Width / 11);
                bricks.get(index).y2 = (int) (Height * (0.18 + (j * 0.03)));
                index++;
            }
        }
    }
}
//new Color(203,83,129) - розовый
//new Color(214,103,50)

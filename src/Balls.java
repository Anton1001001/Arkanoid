import java.awt.*;
import java.util.ArrayList;

public class Balls {
   public static ArrayList<Ball> balls;
   public Balls() {
      balls = new ArrayList<>();
      int Width = Game.WIDTH;
      int Height = Game.HEIGHT;
      balls.add(new Ball((int) (Width * 0.5), (int) (Height * 0.5), (int) (Height * 0.012), 2, (float) (Math.PI / 2 * 0.5), Color.WHITE, true));

   }

   public static void repaintBall(){
      int previousX = balls.get(0).x1;
      int previousY = balls.get(0).y1;
      int Height = Game.HEIGHT;
      int Width = Game.WIDTH;
      int radius = (int) (Height * 0.012);
      int x = (int)Math.round((double)previousX * Width / Settings.previousWidth);
      int y = (int)Math.round((double)previousY * Height / Settings.previousHeight);

      balls.get(0).radius = radius;
      balls.get(0).x1 = x;
      balls.get(0).y1 = y;
      balls.get(0).x2 = x + 2 * radius;
      balls.get(0).y2 = y + 2 * radius;
      Settings.previousHeight = Game.HEIGHT;
      Settings.previousWidth = Game.WIDTH;

   }
}
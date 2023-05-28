import java.awt.*;

public class Ball extends DisplayObject {
    private int radius;
    private int speed;
    private float direction;

    public Ball (int x, int y, int radius, int speed, float direction, Color color, boolean isMoving) {
        this.type = Type.BALL;
        this.radius = radius;
        this.color = color;
        this.speed = speed;
        this.direction = direction;
        this.isMoving = isMoving;
        this.isVisible = true;
        this.x1 = (x - radius);
        this.x2 = (x + radius);
        this.y1 = (y - radius);
        this.y2 = (y + radius);
    }

    @Override
    public void move() throws InterruptedException {
        if (x1 <= 0)
        {
            direction = (float)(Math.PI) - direction;
        } else {
            if (x2 >= Game.WIDTH)
            {
                direction = (float)(Math.PI) - direction;
            }
            else {
                if (y1 <= 35)
                {
                    direction = -direction;
                }
            }
        }

        float dx = (float) Math.cos(direction) * speed;
        float dy = (float) Math.sin(direction) * speed;
        x1 = x1 + (int)dx;
        x2 = x2 + (int)dx;
        y1 = y1 + (int)dy;
        y2 = y2 + (int)dy;

        boolean flag = false;
        if (x1 >= Game.WIDTH - 2 * radius) {
            x1 = Game.WIDTH - 2 * radius;
            x2 = Game.WIDTH;
            flag = true;
        }
        if (x1 <= 0) {
            x1 = 0;
            x2 = 2 * radius;
            flag = true;
        }
        if (y1 <= 35) {
            y1 = 35;
            y2 = 35 + 2 * radius;
            flag = true;
        }
        if (y1 >= 800) {
            Game.player.fail();
        }
        if (flag) {
            Audio.playSoundThread(Audio.WALL_SOUND);
        }
        /*System.out.println("dx = " + dx + " " + "dy = " + dy);
        System.out.println("x1 = " + x1 + " " + "y1 = " + y1 + " " + "x2 = " + x2 + " " + "y2 = " + y2);
        System.out.println();*/
    }

    public void changeDirection(DisplayObject object) {
        if (y1 >= object.y2 || y2 <= object.y1) {
            direction = -direction;
        } else {
            direction = (float) Math.PI - direction;
        }
        if (object.type == Type.BRICK) {
            ((Brick)object).decreaseStrength();
            Player.statistics.score += 10;
            TableRecords.update();
            Audio.playSoundThread(Audio.BRICK_SOUND);
        } else if (object.type == Type.PLATFORM) {
            Audio.playSoundThread(Audio.PLATFORM_SOUND);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x1, y1, 2 * radius, 2 * radius);
        g.setColor(Color.BLACK);
        g.drawOval(x1, y1, 2 * radius, 2 * radius);
    }

}

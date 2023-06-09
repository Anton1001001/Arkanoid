import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;
import java.io.*;
import java.util.Timer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ball extends DisplayObject implements Serializable {
    public int radius;
    public int speed;
    public float direction;
    public float dx;
    public float dy;
    public static int destroyedBrick;
    public boolean fromWall;
    public Ball (int x, int y, int radius, int speed, float direction, int R, int G, int B, boolean isMoving) {
        this.classType = 1;
        this.type = Type.BALL;
        this.radius = radius;
        this.R = R;
        this.G = G;
        this.B = B;
        this.speed = speed;
        this.direction = direction;
        this.isMoving = isMoving;
        this.isVisible = true;
        this.x1 = x;
        this.x2 = x + 2 * radius;
        this.y1 = y;
        this.y2 = y + 2 * radius;
    }

    public Ball() {

    }

    @Override
    public void move() {
        if (x1 <= 0) {
            direction = (float)(Math.PI) - direction;
        } else {
            if (x2 >= Game.WIDTH) {
                direction = (float)(Math.PI) - direction;
            }
            else {
                if (y1 <= 35) {
                    direction = -direction;
                }
            }
        }

        dx = (float) Math.cos(direction) * speed;
        dy = (float) Math.sin(direction) * speed;
        x1 = x1 + (int)dx;
        x2 = x2 + (int)dx;
        y1 = y1 + (int)dy;
        y2 = y2 + (int)dy;

        fromWall = false;
        if (x1 >= Game.WIDTH - 2 * radius) {
            x1 = Game.WIDTH - 2 * radius;
            x2 = Game.WIDTH;
            fromWall = true;
        }
        if (x1 <= 0) {
            x1 = 0;
            x2 = 2 * radius;
            fromWall = true;
        }
        if (y1 <= 35) {
            y1 = 35;
            y2 = 35 + 2 * radius;
            fromWall = true;
        }
        if (y1 >= Game.HEIGHT) {
            Game.player.fail();
        }
        if (fromWall) {
            Audio.playSoundThread(Audio.WALL_SOUND);
        }
    }

    @Override
    public void saveComponentData(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(getClass().getName());
            writer.println(x1 + "," + y1 + "," + x2 + "," + y2 + "," + R + "," + G + "," + B + "," + speed + "," + radius + "," + direction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readComponentData(String dataComponent) {
        String[] dataArray = dataComponent.split(",");
        this.x1 = Integer.parseInt(dataArray[0]);
        this.y1 = Integer.parseInt(dataArray[1]);
        this.x2 = Integer.parseInt(dataArray[2]);
        this.y2 = Integer.parseInt(dataArray[3]);
        this.R = Integer.parseInt(dataArray[4]);
        this.G = Integer.parseInt(dataArray[5]);
        this.B = Integer.parseInt(dataArray[6]);
        this.speed = Integer.parseInt(dataArray[7]);
        this.radius = Integer.parseInt(dataArray[8]);
        this.direction = Float.parseFloat(dataArray[9]);
    }


    public void changeDirection(DisplayObject object) {
        if (object.type != Type.BONUS) {
            if ((y2 >= object.y1) && (y1 < object.y1) && (y2 < object.y2) && ((x2 >= object.x1) && (x1 <= object.x2)) && (dy > 0)) {
                y2 = object.y1;
                y1 = y2 - 2 * radius;
                direction = -direction;
            } else if ((y1 <= object.y2) && (y2 > object.y2) && (((x2 >= object.x1) && (x1 <= object.x2)) && (dy < 0))) {
                y1 = object.y2;
                y2 = y1 + 2 * radius;
                direction = -direction;
            } else if ((x1 <= object.x2) && (x1 > object.x1) && ((y2 >= object.y1) && (y1 <= object.y2)) && (dx < 0)) {
                x1 = object.x2;
                x2 = x1 + 2 * radius;
                direction = (float) Math.PI - direction;
            } else if ((x2 >= object.x1) && (y2 < object.y2) && ((y2 >= object.y1) || (y1 <= object.y2)) && (dx > 0)) {
                System.out.flush();
                x2 = object.x1;
                x1 = x2 - 2 * radius;
                direction = (float) Math.PI - direction;
            }

            if (object.type == Type.BRICK) {
                destroyedBrick = Bricks.bricks.indexOf(object);
                ((Brick) object).decreaseStrength();
                Player.statistics.score += 10;
                TableRecords.update();
                Audio.playSoundThread(Audio.BRICK_SOUND);
            } else if (object.type == Type.PLATFORM) {
                Audio.playSoundThread(Audio.PLATFORM_SOUND);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(R, G, B));
        g.fillOval(x1, y1, 2 * radius, 2 * radius);
        g.setColor(Color.BLACK);
        g.drawOval(x1, y1, 2 * radius, 2 * radius);
    }


}



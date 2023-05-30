import java.awt.*;
import java.util.Random;

public class Bonus extends DisplayObject{
    public int speed;
    public int num;
    public Bonus (int x1, int y1, int x2, int y2, int speed, boolean isMoving) {
        this.type = Type.BONUS;
        this.speed = speed;
        this.isMoving = isMoving;
        this.isVisible = false;
        this.num = generateRandomNumber();
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.R = 255;
        this.G = 0;
        this.B = 0;
    }

    public static int generateRandomNumber() {
        Random random = new Random();
        int randomNumber;
        while (true) {
            int probability = random.nextInt(5);
            if (probability == 0) {
                randomNumber = random.nextInt(101) - 100;
            } else {
                randomNumber = random.nextInt(100) + 1;
            }
            if (randomNumber != 0)
                break;
        }

        return randomNumber;
    }

    @Override
    public void move() {
        y1 += speed;
        y2 += speed;
    }

    @Override
    public void saveComponentData(String filename) {

    }

    @Override
    public void readComponentData(String dataComponent) {

    }



    @Override
    public void draw(Graphics g) {
        Color color;
        String text = num > 0 ? "+" + num : "" + num;
        int rectHeight = y2 - y1;
        int rectWidth = x2 - x1;
        Font font = new Font("Verdana", Font.BOLD, (int)(rectHeight * 0.9));
        g.setFont(font);
        color = num > 0 ? Color.WHITE : Color.RED;
        g.setColor(color);
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int textX = x1 + (rectWidth - textWidth) / 2;
        int textY = y1 + rectHeight - (int)(0.5 * fontMetrics.getDescent());
        g.drawString(text, textX, textY);
    }
}
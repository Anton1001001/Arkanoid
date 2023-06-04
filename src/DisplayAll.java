import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.*;
import java.util.List;

public class DisplayAll {
    public static List<DisplayObject> displayObjects;
    private static boolean isX2 = false;
    private static boolean isW;
    private static boolean isX2Started = false;
    private static boolean isWStarted = false;
    private Timer timer1;
    private Timer timer2;
    public DisplayAll(Balls balls, Platforms platforms, Bricks bricks, Bonuses bonuses) {
        displayObjects = new ArrayList<>();
        displayObjects.addAll(balls.balls);
        displayObjects.addAll(platforms.platforms);
        displayObjects.addAll(bricks.bricks);
        displayObjects.addAll(bonuses.bonuses);
    }

    public void moveObjects() {
        for (DisplayObject object : displayObjects) {
            if (object.isMoving && object.isVisible) {
                object.move();
            }
        }
    }

    public void drawObjects(Graphics g) {
        for (DisplayObject object : displayObjects) {
            if (object.isVisible) {
                object.draw(g);
            }
        }
    }

    public void checkCollisions() throws InterruptedException {
        for (DisplayObject object1 : displayObjects) {
            if (object1.isMoving && object1.isVisible) {
                for (DisplayObject object2 : displayObjects) {
                    if (object1.equals(object2) || !object2.isVisible) {
                        continue;
                    }
                    if (object1.checkCollisions(object2)) {
                        if (object1.type == Type.BALL) {
                            ((Ball) object1).changeDirection(object2);
                        }
                        if (object1.type == Type.PLATFORM && object2.type == Type.BONUS) {
                            object2.isVisible = false;
                            object2.isMoving = false;
                            int width = (int) (Game.WIDTH / 11 + Game.WIDTH * 0.56 + Game.WIDTH / 22) - (int) (Game.WIDTH / 11 + Game.WIDTH * 0.5 - Game.WIDTH / 22);
                            int num = ((Bonus)object2).num;
                            if (((Bonus) object2).bonusType != 3 || ((Bonus) object2).bonusType == 3 && ((Bonus) object2).num > 0)
                                Audio.playSoundThread(Audio.BONUS_SOUND);
                            else
                                Audio.playSoundThread(Audio.DAMAGE_SOUND);
                            if (((Bonus) object2).bonusType == 1 && !isWStarted) {
                                if (!isX2Started) {
                                    isX2Started = true;
                                    object1.x2 += width / 2;
                                    object1.x1 -= width / 2;
                                    timer1 = new Timer(4000, new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            object1.x2 -= width / 2;
                                            object1.x1 += width / 2;
                                            if (object1.x1 < 0) {
                                                object1.x1 = 0;
                                                object1.x2 = width;
                                            }
                                            isX2Started = false;
                                            timer1.stop();
                                        }

                                    });
                                    timer1.setRepeats(false);
                                    timer1.start();
                                } else {
                                    timer1.restart();
                                }

                            } else if (((Bonus) object2).bonusType == 2) {
                                if (!isWStarted) {

                                    isWStarted = true;
                                    object1.x1 = 0;
                                    object1.x2 = Game.WIDTH;
                                    float speedRatio = Settings.speedRatio;
                                   // Balls.balls.get(0).speed = (int) (Game.HEIGHT * 0.03f);
                                    timer2 = new Timer(4000, new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (Balls.balls.get(0).dx < 0) {
                                                object1.x1 = Balls.balls.get(0).x1 - width;
                                                object1.x2 = object1.x1 + width;
                                                if (object1.x1 < 0) {
                                                    object1.x1 = 0;
                                                    object1.x2 = width;
                                                }
                                            } else if (Balls.balls.get(0).dx > 0) {
                                                object1.x1 = Balls.balls.get(0).x1 + width;
                                                object1.x2 = object1.x1 + width;
                                                if (object1.x2 > Game.WIDTH) {
                                                    object1.x2 = Game.WIDTH;
                                                    object1.x1 = object1.x2 - width;
                                                }
                                            }
                                            Balls.balls.get(0).speed = (int) (Game.HEIGHT * speedRatio);
                                            isWStarted = false;
                                            timer2.stop();

                                        }
                                    });
                                    timer2.setRepeats(false);
                                    timer2.start();
                                } else
                                    timer2.restart();
                            }
                            Player.statistics.score += num;
                            TableRecords.update();
                        }
                        break;
                    }
                }
            }
        }
    }

    public void readDataComponentFromJSON(JsonNode rootNode) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        displayObjects = new ArrayList<>();
        JsonNode objectsNode = rootNode.get("displayObjects");
       // int i = 0;
        Balls.balls = new ArrayList<>();
        Platforms.platforms = new ArrayList<>();
        Bricks.bricks = new ArrayList<>();
        Bonuses.bonuses = new ArrayList<>();

        for (JsonNode objectNode : objectsNode) {
            int classType = objectNode.get("classType").asInt();
            switch (classType) {
                case 1 :
                    displayObjects.add(mapper.readValue(objectNode.toString(), Ball.class));
                    displayObjects.get(displayObjects.size() - 1).type = Type.BALL;
                    Balls.balls.add((Ball) displayObjects.get(displayObjects.size() - 1));
                    break;
                case 2 :
                    displayObjects.add(mapper.readValue(objectNode.toString(), Platform.class));
                    displayObjects.get(displayObjects.size() - 1).type = Type.PLATFORM;
                    Platforms.platforms.add((Platform) displayObjects.get(displayObjects.size() - 1));
                    break;
                case 3 :
                    displayObjects.add(mapper.readValue(objectNode.toString(), Brick.class));
                    displayObjects.get(displayObjects.size() - 1).type = Type.BRICK;
                    Bricks.bricks.add((Brick)displayObjects.get(displayObjects.size() - 1));
                    break;
                case 4 :
                    displayObjects.add(mapper.readValue(objectNode.toString(), Bonus.class));
                    displayObjects.get(displayObjects.size() - 1).type = Type.BONUS;
                    Bonuses.bonuses.add((Bonus)displayObjects.get(displayObjects.size() - 1));
            }
        }
        Game.gameField.platforms.platforms.set(0, (Platform)displayObjects.get(1));
        Game.gameField.balls.balls.set(0, (Ball)displayObjects.get(0));
    }
}

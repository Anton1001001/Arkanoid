public class GameField {

    public DisplayAll allObjects;
    public Balls balls;
    public Platforms platforms;
    public Bricks bricks;
    public Menu menu;
    public Settings settings;
    public GameField() {
        balls = new Balls();
        platforms = new Platforms();
        bricks = new Bricks();
        menu = new Menu();
        settings = new Settings();
        allObjects = new DisplayAll(balls, platforms, bricks);
    }
}

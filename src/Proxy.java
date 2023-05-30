import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Proxy {
    public void serializeToTextFile(String filename, List<DisplayObject> displayObjects, Settings settings, Player player) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (DisplayObject displayObject: displayObjects)
        {
            displayObject.saveComponentData(filename);
        }
        //settings.toString(filename);
        //players.players[0].toString(filename);
    }

    public void deserializeFromTextFile(String filename, DisplayAll allObjects, Settings settings,Player player) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int i = 0;
            String className = reader.readLine();
            Set<String> components = new HashSet<>();
            components.add("Brick");
            components.add("Ball");
            components.add("Platform");
            components.add("Settings");
            components.add("Player");
            while(className != null) {
                if (components.contains(className)) {
                    allObjects.displayObjects.get(i).readComponentData(reader.readLine());
                }
                className = reader.readLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

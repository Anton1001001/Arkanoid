import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableRecords extends JPanel {
    public static JLabel statistics;
    public static JButton menuButton;

    public TableRecords(){
        statistics = new JLabel(Player.getPlayerStatistic());
        menuButton = new JButton("Меню");
        add(statistics,BorderLayout.WEST);
        add(menuButton, BorderLayout.EAST);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Audio.playSoundThread(Audio.CLICK_SOUND);
                if (!Game.isPaused) {
                    Game.pause();
                    Game.game.requestFocus();
                } else {
                    Game.game.requestFocus();
                    Game.resume();
                }
            }
        });
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Audio.playSoundThread(Audio.CHOICE_SOUND);
            }
        });
    }
    public static void update(){
        statistics.setText(Game.player.getPlayerStatistic());
    }

}

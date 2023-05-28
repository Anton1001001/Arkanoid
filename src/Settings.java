import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Settings extends JFrame{
    public JPanel settingsPanel;
    public static final String[] COMPLEXITY_LABELS = {"Легко", "Легко+", "Средне", "Средне+", "Сложно"};
    public static final String[] SCREEN_RESOLUTION_LABELS = {"800x600", "1200x800", "1366x768", "1200x600", "FullScreen"};
    private static int currentIndexComplexityLabels = 0;
    private static int currentIndexScreenResolutionLabels = 0;
    public Settings() {
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        settingsPanel.setOpaque(false);

        JButton complexityButton = new JButton(COMPLEXITY_LABELS[currentIndexComplexityLabels]);
        JButton backToMenuButton = new JButton("Назад в меню");
        JButton screenResolutionButton =  new JButton(SCREEN_RESOLUTION_LABELS[currentIndexScreenResolutionLabels]);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        complexityButton.setPreferredSize(new Dimension(200, 40));
        complexityButton.setHorizontalAlignment(SwingConstants.CENTER);
        backToMenuButton.setPreferredSize(new Dimension(200, 40));
        backToMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
        screenResolutionButton.setPreferredSize(new Dimension(200, 40));
        screenResolutionButton.setHorizontalAlignment(SwingConstants.CENTER);

        settingsPanel.add(complexityButton, gbc);
        gbc.gridy = 1;
        settingsPanel.add(screenResolutionButton, gbc);
        gbc.gridy = 2;
        settingsPanel.add(backToMenuButton, gbc);

        settingsPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    Game.game.remove(Game.gameField.settings.settingsPanel);
                    Game.game.add(Game.gameField.menu.menuPanel, BorderLayout.CENTER);
                    Game.gameField.menu.menuPanel.requestFocus();
                    Game.gameField.menu.menuPanel.setVisible(true);
                    Game.game.revalidate();
                    Game.game.repaint();
                    Game.frame.revalidate();
                    Game.frame.repaint();
                }
            }
        });

        complexityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Audio.playSoundThread(Audio.CLICK_SOUND);
                currentIndexComplexityLabels = (currentIndexComplexityLabels + 1) % COMPLEXITY_LABELS.length;
                complexityButton.setText(COMPLEXITY_LABELS[currentIndexComplexityLabels]);
                switch (complexityButton.getText()) {
                    case "Легко" :
                        Game.delay = 6;
                        break;
                    case "Легко+" :
                        Game.delay = 4;
                        break;
                    case "Средне" :
                        Game.delay = 3;
                        break;
                    case "Средне+" :
                        Game.delay = 2;
                        break;
                    case "Сложно" :
                        Game.delay = 1;
                        break;
                }
                settingsPanel.requestFocus();

            }
        });
        complexityButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Audio.playSoundThread(Audio.CHOICE_SOUND);
            }
        });

        screenResolutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Audio.playSoundThread(Audio.CLICK_SOUND);
                currentIndexScreenResolutionLabels = (currentIndexScreenResolutionLabels + 1) % SCREEN_RESOLUTION_LABELS.length;
                screenResolutionButton.setText(SCREEN_RESOLUTION_LABELS[currentIndexScreenResolutionLabels]);
                switch (screenResolutionButton.getText()) {
                    case "800x600" :
                        //Iliya pidor
                        break;
                    case "1200x800":
                        //Pososi
                        break;
                    case "1366x768" :
                        //Iliya
                        break;
                    case "1200x600" :
                        //Iliya kotik
                        break;
                    case "FullScreen" :
                        // Ygr
                        break;
                }
                settingsPanel.requestFocus();
            }
        });
        screenResolutionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Audio.playSoundThread(Audio.CHOICE_SOUND);
            }
        });

        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Audio.playSoundThread(Audio.CLICK_SOUND);
                Game.game.remove(Game.gameField.settings.settingsPanel);
                Game.game.add(Game.gameField.menu.menuPanel, BorderLayout.CENTER);
                Game.gameField.menu.menuPanel.setVisible(true);
                Game.game.revalidate();
                Game.game.repaint();
                Game.frame.revalidate();
                Game.frame.repaint();
            }
        });
        backToMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Audio.playSoundThread(Audio.CHOICE_SOUND);
            }
        });
    }

}
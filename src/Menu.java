import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Menu extends JFrame{

    public JPanel menuPanel;
    public Menu() {
        ArrayList<JButton> menuItems = new ArrayList<>();
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setOpaque(false);
        //menuPanel.setBackground(Color.RED);

        JButton resumeGameButton = new JButton("Продолжить игру");
        JButton newGameButton = new JButton("Новая игра");
        JButton saveGameButton = new JButton("Сохранить игру");
        JButton loadGameButtonJSON = new JButton("Загрузить игру JSON");
        JButton loadGameButtonTXT = new JButton("Загрузить игру TXT");
        JButton settingsButton = new JButton("Настройки");
        JButton exitButton = new JButton("Выход");

        menuItems.add(resumeGameButton);
        menuItems.add(newGameButton);
        menuItems.add(saveGameButton);
        menuItems.add(loadGameButtonJSON);
        menuItems.add(loadGameButtonTXT);
        menuItems.add(settingsButton);
        menuItems.add(exitButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Отступы между кнопками

        for (int i = 0; i < menuItems.size(); i++) {
            JButton menuItem = menuItems.get(i);
            menuItem.setPreferredSize(new Dimension(300, 40)); // Задать размер кнопки
            menuItem.setHorizontalAlignment(SwingConstants.CENTER); // Выравнивание текста по центру
            menuPanel.add(menuItem, gbc);
            gbc.gridy++;
        }

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.newGame();
                Game.resume();
                dispose();
            }
        });
        newGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Audio.playSoundThread(Audio.CLICK_SOUND);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Audio.playSoundThread(Audio.CHOICE_SOUND);
            }
        });

        resumeGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.player.statistics.lives > 0) {
                    Game.resume();
                    dispose();
                }
            }
        });
        resumeGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Audio.playSoundThread(Audio.CHOICE_SOUND);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Audio.playSoundThread(Audio.CLICK_SOUND);
            }
        });

        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.save();
                dispose();
            }
        });
        saveGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Audio.playSoundThread(Audio.CHOICE_SOUND);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Audio.playSoundThread(Audio.CLICK_SOUND);
            }
        });

        loadGameButtonJSON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.loadJSON();
            }
        });
        loadGameButtonJSON.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Audio.playSoundThread(Audio.CHOICE_SOUND);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Audio.playSoundThread(Audio.CLICK_SOUND);
            }
        });

        loadGameButtonTXT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.loadTXT();
            }
        });
        loadGameButtonTXT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Audio.playSoundThread(Audio.CHOICE_SOUND);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Audio.playSoundThread(Audio.CLICK_SOUND);
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Audio.playSoundThread(Audio.CLICK_SOUND);
                Game.gameField.menu.menuPanel.setVisible(false);
                Game.game.remove(Game.gameField.menu.menuPanel);
                Game.game.add(Game.gameField.settings.settingsPanel, BorderLayout.CENTER);
                Game.gameField.settings.settingsPanel.requestFocus();
                Game.game.revalidate();
                Game.game.repaint();
                Game.frame.revalidate();
                Game.frame.repaint();
                dispose();
            }
        });
        settingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Audio.playSoundThread(Audio.CHOICE_SOUND);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Audio.playSoundThread(Audio.CHOICE_SOUND);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Audio.playSoundThread(Audio.CLICK_SOUND);
            }
        });
    }
}

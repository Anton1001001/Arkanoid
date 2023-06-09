import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageBox {
    public static void showMessageBox(String message) {
        JFrame parentFrame = new JFrame();
        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentFrame.setVisible(false);

        JDialog dialog = new JDialog(parentFrame, "MessageBox", true);
        dialog.setPreferredSize(new Dimension(400, 300));
        dialog.setUndecorated(true); // Убираем верхнюю панель с крестиком потому что это очеь важно!!!

        Font font = new Font("Arial", Font.BOLD, 40);
        JPanel messagePanel = new JPanel(new GridBagLayout());
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(font);

        messagePanel.add(messageLabel);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.pause();
                parentFrame.setVisible(false);
                parentFrame.requestFocus();
            }
        });
        buttonPanel.add(okButton);
        dialog.getContentPane().add(messagePanel, BorderLayout.CENTER);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Отображаем диалоговое окно по центру экрана
        dialog.setVisible(true);
    }
}
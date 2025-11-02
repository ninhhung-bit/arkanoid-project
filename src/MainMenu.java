import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu extends JPanel {
    private JButton playButton;
    private JButton optionsButton;
    private JButton exitButton;
    private JButton leaderboardButton; // thêm nút leaderboard

    public MainMenu(ActionListener listener) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension((int) Game.WIDTH, (int) Game.HEIGHT));

        playButton = new JButton("Play");
        optionsButton = new JButton("Options");
        exitButton = new JButton("Exit");
        leaderboardButton = new JButton("Leaderboard"); // khởi tạo

        // Kích thước nút
        Dimension buttonSize = new Dimension(200, 40);
        playButton.setPreferredSize(buttonSize);
        optionsButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);
        leaderboardButton.setPreferredSize(buttonSize);

        // Layout GridBag
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        add(playButton, gbc);
        gbc.gridy = 1;
        add(optionsButton, gbc);
        gbc.gridy = 2;
        add(leaderboardButton, gbc); // thêm ở hàng 2
        gbc.gridy = 3;
        add(exitButton, gbc);

        // Action commands
        playButton.setActionCommand("play");
        optionsButton.setActionCommand("options");
        exitButton.setActionCommand("exit");
        leaderboardButton.setActionCommand("leaderboard"); // đặt command

        // Gán listener
        playButton.addActionListener(listener);
        optionsButton.addActionListener(listener);
        exitButton.addActionListener(listener);
        leaderboardButton.addActionListener(listener); // gán listener

        SoundManager.playBackground("menu.wav");
    }
}

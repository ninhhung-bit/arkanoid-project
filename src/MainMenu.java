import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private JButton playButton;
    private JButton optionsButton;
    private JButton exitButton;

    public MainMenu(ActionListener listener) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        // ensure menu uses same size as the Game panel
        setPreferredSize(new Dimension((int) Game.WIDTH, (int) Game.HEIGHT));

        playButton = new JButton("Play");
        optionsButton = new JButton("Options");
        exitButton = new JButton("Exit");

        playButton.setPreferredSize(new Dimension(200, 40));
        optionsButton.setPreferredSize(new Dimension(200, 40));
        exitButton.setPreferredSize(new Dimension(200, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        add(playButton, gbc);
        gbc.gridy = 1;
        add(optionsButton, gbc);
        gbc.gridy = 2;
        add(exitButton, gbc);

        playButton.setActionCommand("play");
        optionsButton.setActionCommand("options");
        exitButton.setActionCommand("exit");

        playButton.addActionListener(listener);
        optionsButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }
}
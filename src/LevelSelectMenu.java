import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LevelSelectMenu extends JPanel {
    private JButton level1Button, level2Button, level3Button, backButton;

    public LevelSelectMenu(ActionListener listener) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension((int) Game.WIDTH, (int) Game.HEIGHT));

        level1Button = new JButton("Level 1");
        level2Button = new JButton("Level 2");
        level3Button = new JButton("Level 3");
        backButton = new JButton("Back");

        Dimension btnSize = new Dimension(200, 40);
        level1Button.setPreferredSize(btnSize);
        level2Button.setPreferredSize(btnSize);
        level3Button.setPreferredSize(btnSize);
        backButton.setPreferredSize(btnSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0; add(level1Button, gbc);
        gbc.gridy = 1; add(level2Button, gbc);
        gbc.gridy = 2; add(level3Button, gbc);
        gbc.gridy = 3; add(backButton, gbc);

        level1Button.setActionCommand("level1");
        level2Button.setActionCommand("level2");
        level3Button.setActionCommand("level3");
        backButton.setActionCommand("back");

        level1Button.addActionListener(listener);
        level2Button.addActionListener(listener);
        level3Button.addActionListener(listener);
        backButton.addActionListener(listener);
    }
}

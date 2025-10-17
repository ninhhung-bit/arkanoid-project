import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Arkanoid");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Menu first
            MainMenu menu = new MainMenu(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    if ("play".equals(cmd)) {
                        Game game = new Game(() -> {
                            // back to main menu on game over
                            SwingUtilities.invokeLater(() -> {
                                MainMenu newMenu = new MainMenu(this);
                                frame.setContentPane(newMenu);
                                frame.pack();
                                frame.revalidate();
                            });
                        });
                        frame.setContentPane(game);
                        frame.pack();
                        frame.revalidate();
                        game.requestFocusInWindow();
                    } else if ("options".equals(cmd)) {
                        OptionsMenu optionsMenu = new OptionsMenu(frame);
                        optionsMenu.setVisible(true);
                    } else if ("exit".equals(cmd)) {
                        System.exit(0);
                    }
                }
            });

            frame.setContentPane(menu);
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Arkanoid");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MainMenu mainMenu = new MainMenu(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();

                    if ("play".equals(cmd)) {
                        // Nhấn Play → sang menu chọn level
                        LevelSelectMenu levelMenu = new LevelSelectMenu(this);
                        frame.setContentPane(levelMenu);
                        frame.pack();
                        frame.revalidate();
                    }
                    else if ("options".equals(cmd)) {
                        OptionsMenu optionsMenu = new OptionsMenu(frame);
                        optionsMenu.setVisible(true);
                    }
                    else if ("exit".equals(cmd)) {
                        System.exit(0);
                    }
                    else if ("back".equals(cmd)) {
                        // Quay lại menu chính
                        MainMenu newMenu = new MainMenu(this);
                        frame.setContentPane(newMenu);
                        frame.pack();
                        frame.revalidate();
                    }
                    else if ("level1".equals(cmd)) {
                        startGame(frame, 1, this);
                    }
                    else if ("level2".equals(cmd)) {
                        startGame(frame, 2, this);
                    }
                    else if ("level3".equals(cmd)) {
                        startGame(frame, 3, this);
                    }
                }

                private void startGame(JFrame frame, int level, ActionListener listener) {
                    Game game = new Game(level, () -> {
                        // Khi thua → quay về menu chính
                        SwingUtilities.invokeLater(() -> {
                            MainMenu newMenu = new MainMenu(listener);
                            frame.setContentPane(newMenu);
                            frame.pack();
                            frame.revalidate();
                        });
                    });

                    frame.setContentPane(game);
                    frame.pack();
                    frame.revalidate();
                    game.requestFocusInWindow();
                }
            });

            frame.setContentPane(mainMenu);
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

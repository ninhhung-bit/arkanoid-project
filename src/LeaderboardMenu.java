import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LeaderboardMenu extends JPanel {
    private Leaderboard leaderboard;
    private Runnable onBack;

    public LeaderboardMenu(Leaderboard leaderboard, Runnable onBack) {
        this.leaderboard = leaderboard;
        this.onBack = onBack;
        setBackground(Color.BLACK);
        setLayout(null);

        // Nút Back
        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 500, 120, 40);
        backButton.addActionListener(e -> onBack.run());
        add(backButton);

        // Nút Clear
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(230, 500, 150, 40);
        clearButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                leaderboard.clear();  // xóa dữ liệu
                repaint();            // làm mới hiển thị
            }
        });
        add(clearButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString("Leaderboard", 80, 50);

        g.setFont(new Font("Arial", Font.PLAIN, 24));
        int y = 120;
        int rank = 1;
        for (Leaderboard.PlayerScore ps : leaderboard.getScores()) {
            g.drawString(rank + ". " + ps.name + " - " + ps.score, 50, y);
            y += 40;
            rank++;
        }
    }

    public static void showLeaderboard(JFrame frame, ActionListener listener) {
        Leaderboard leaderboard = new Leaderboard();

        JFrame lbFrame = new JFrame("Leaderboard");
        lbFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lbFrame.setSize(400, 600);
        lbFrame.setLayout(new BorderLayout());

        LeaderboardMenu lbMenu = new LeaderboardMenu(leaderboard, () -> lbFrame.dispose());
        lbFrame.add(lbMenu);

        lbFrame.setLocationRelativeTo(frame);
        lbFrame.setVisible(true);
    }
}

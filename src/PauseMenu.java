import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseMenu {
    private boolean visible = false;
    private int selectedIndex = 0;
    private final String[] options = {"Resume", "Restart", "Main Menu"};

    public boolean isVisible() {
        return visible;
    }

    public void show() {
        visible = true;
        selectedIndex = 0;
    }

    public void hide() {
        visible = false;
    }

    // Xử lý điều hướng menu
    public void handleKeyPressed(KeyEvent e) {
        if (!visible) return;

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            selectedIndex = (selectedIndex - 1 + options.length) % options.length;
        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            selectedIndex = (selectedIndex + 1) % options.length;
        }
    }

    // Vẽ menu overlay
    public void render(Graphics2D g, int screenWidth, int screenHeight) {
        if (!visible) return;

        // Lớp phủ mờ nền
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, screenWidth, screenHeight);

        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.setColor(Color.WHITE);
        String title = "PAUSED";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (screenWidth - titleWidth) / 2, screenHeight / 3);

        // Các lựa chọn
        g.setFont(new Font("Arial", Font.PLAIN, 28));
        for (int i = 0; i < options.length; i++) {
            String text = options[i];
            int textWidth = g.getFontMetrics().stringWidth(text);
            int x = (screenWidth - textWidth) / 2;
            int y = screenHeight / 2 + i * 50;

            if (i == selectedIndex) {
                g.setColor(Color.YELLOW);
                g.drawString("> " + text + " <", x - 40, y);
            } else {
                g.setColor(Color.LIGHT_GRAY);
                g.drawString(text, x, y);
            }
        }

        g.setFont(new Font("Arial", Font.ITALIC, 18));
        g.setColor(Color.GRAY);
        String hint = "(Nhấn ESC để tiếp tục)";
        int hintW = g.getFontMetrics().stringWidth(hint);
        g.drawString(hint, (screenWidth - hintW) / 2, screenHeight - 60);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public String getSelectedOption() {
        return options[selectedIndex];
    }
}

import java.awt.*;

public class Renderer {
    private Graphics2D g;

    public Renderer(Graphics g) {
        this.g = (Graphics2D) g;
        // Bật khử răng cưa cho hình ảnh mượt hơn
        this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    // ----- Thiết lập màu -----
    public void setColor(Color c) {
        g.setColor(c);
    }

    public void setColor(int red, int green, int blue) {
        g.setColor(new Color(red, green, blue));
    }

    // ----- Vẽ hình chữ nhật -----
    public void drawRect(double x, double y, double w, double h) {
        g.drawRect((int) x, (int) y, (int) w, (int) h);
    }

    public void fillRect(double x, double y, double w, double h) {
        g.fillRect((int) x, (int) y, (int) w, (int) h);
    }

    // ----- Vẽ hình tròn -----
    public void drawCircle(double x, double y, double r) {
        g.drawOval((int) x, (int) y, (int) (r * 2), (int) (r * 2));
    }

    public void fillCircle(double x, double y, double r) {
        g.fillOval((int) x, (int) y, (int) (r * 2), (int) (r * 2));
    }

    // ----- Vẽ văn bản -----
    public void drawText(String text, double x, double y) {
        g.drawString(text, (int) x, (int) y);
    }

    // ----- Phương thức tự động vẽ một GameObject -----
    public void draw(GameObject obj) {
        if (obj == null) return;

        // Ví dụ: vẽ theo loại đối tượng
        if (obj instanceof Paddle) {
            setColor(Color.CYAN);
            fillRect(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
        }
        else if (obj instanceof Ball) {
            setColor(Color.WHITE);
            fillCircle(obj.getX(), obj.getY(), obj.getWidth() / 2);
        }
        else if (obj instanceof Brick) {
            Brick b = (Brick) obj;
            if (!b.isDestroyed()) {
                setColor(Color.ORANGE);
                fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                setColor(Color.BLACK);
                drawRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
        }
        else if (obj instanceof PowerUp) {
            setColor(Color.GREEN);
            fillRect(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
            setColor(Color.BLACK);
            drawRect(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
        }
    }
}

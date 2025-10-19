import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.ImageIO;

public class Renderer {
    private Graphics2D g;
    private static final String IMAGE_DIR = "src/images/";
    private final Map<String, BufferedImage> imageCache = new ConcurrentHashMap<>();

    public Renderer(Graphics g) {
        this.g = (Graphics2D) g;
        this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void setColor(Color c) { g.setColor(c); }
    public void setColor(int r, int g, int b) { this.g.setColor(new Color(r, g, b)); }

    public void drawRect(double x, double y, double w, double h) { g.drawRect((int)x, (int)y, (int)w, (int)h); }
    public void fillRect(double x, double y, double w, double h) { g.fillRect((int)x, (int)y, (int)w, (int)h); }
    public void drawCircle(double x, double y, double r) { g.drawOval((int)x, (int)y, (int)(r*2), (int)(r*2)); }
    public void fillCircle(double x, double y, double r) { g.fillOval((int)x, (int)y, (int)(r*2), (int)(r*2)); }
    public void drawText(String text, double x, double y) { g.drawString(text, (int)x, (int)y); }

    public void drawImage(String imageName, double x, double y, double w, double h) {
        if (imageName == null) return;
        try {
            BufferedImage img = imageCache.get(imageName);
            if (img == null) {
                File f = new File(IMAGE_DIR + imageName);
                if (f.exists()) {
                    img = ImageIO.read(f);
                    imageCache.put(imageName, img);
                }
            }
            if (img != null) {
                g.drawImage(img, (int)x, (int)y, (int)w, (int)h, null);
            } else {
                Color old = g.getColor();
                g.setColor(Color.MAGENTA);
                fillRect(x, y, w, h);
                g.setColor(old);
            }
        } catch (IOException e) {
            Color old = g.getColor();
            g.setColor(Color.MAGENTA);
            fillRect(x, y, w, h);
            g.setColor(old);
        }
    }

    public void draw(GameObject obj) {
        if (obj == null) return;
        if (obj instanceof Paddle) {
            drawImage("Paddle.png", obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
        } else if (obj instanceof Ball) {
            drawImage("Ball.png", obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
        } else if (obj instanceof Brick) {
            Brick b = (Brick) obj;
            if (!b.isDestroyed()) {
                String img = "NormalBrick.png";
                if ("strong".equalsIgnoreCase(b.getType())) img = "StrongBrick.png";
                drawImage(img, b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
        } else if (obj instanceof PowerUp) {
            PowerUp p = (PowerUp) obj;
            String img = p.getType() + "PowerUp.png";
            drawImage(img, p.getX(), p.getY(), p.getWidth(), p.getHeight());
        } else {
            setColor(Color.GRAY);
            fillRect(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
        }
    }
}
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.ImageIO;

public class Renderer {
    private Graphics2D g;
    private static final String IMAGE_DIR = "src/images/";
    private final Map<String, BufferedImage> imageCache = new ConcurrentHashMap<>();

    public Renderer(Graphics g) {
        this.g = (Graphics2D) g;
        this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void setColor(Color c) { g.setColor(c); }
    public void setColor(int r, int g, int b) { this.g.setColor(new Color(r, g, b)); }

    public void drawRect(double x, double y, double w, double h) { g.drawRect((int)x, (int)y, (int)w, (int)h); }
    public void fillRect(double x, double y, double w, double h) { g.fillRect((int)x, (int)y, (int)w, (int)h); }
    public void drawCircle(double x, double y, double r) { g.drawOval((int)x, (int)y, (int)(r*2), (int)(r*2)); }
    public void fillCircle(double x, double y, double r) { g.fillOval((int)x, (int)y, (int)(r*2), (int)(r*2)); }

    public void drawText(String text, double x, double y) { g.drawString(text, (int)x, (int)y); }

    public void drawImage(String imageName, double x, double y, double w, double h) {
        if (imageName == null) return;
        try {
            BufferedImage img = imageCache.get(imageName);
            if (img == null) {
                File f = new File(IMAGE_DIR + imageName);
                if (f.exists()) {
                    img = ImageIO.read(f);
                    imageCache.put(imageName, img);
                }
            }
            if (img != null) {
                g.drawImage(img, (int)x, (int)y, (int)w, (int)h, null);
            } else {
                Color old = g.getColor();
                g.setColor(Color.MAGENTA);
                fillRect(x, y, w, h);
                g.setColor(old);
            }
        } catch (IOException e) {
            Color old = g.getColor();
            g.setColor(Color.MAGENTA);
            fillRect(x, y, w, h);
            g.setColor(old);
        }
    }

    public void draw(GameObject obj) {
        if (obj == null) return;
        if (obj instanceof Paddle) {
            drawImage("Paddle.png", obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
        } else if (obj instanceof Ball) {
            drawImage("Ball.png", obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
        } else if (obj instanceof Brick) {
            Brick b = (Brick) obj;
            if (!b.isDestroyed()) {
                String img = "NormalBrick.png";
                if ("strong".equalsIgnoreCase(b.getType())) img = "StrongBrick.png";
                drawImage(img, b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
        } else if (obj instanceof PowerUp) {
            PowerUp p = (PowerUp) obj;
            String img = p.getType() + "PowerUp.png";
            drawImage(img, p.getX(), p.getY(), p.getWidth(), p.getHeight());
        } else {
            setColor(Color.GRAY);
            fillRect(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
        }
    }
}


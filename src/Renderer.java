import java.awt.*;
<<<<<<< HEAD
=======
<<<<<<< HEAD

public class Renderer {
    private Graphics2D g;
=======
>>>>>>> backup-changes
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Renderer {
    private Graphics2D g;
    private static HashMap<String, BufferedImage> imageCache = new HashMap<>();
    private static final String IMAGE_DIR = "src/images/";
<<<<<<< HEAD
=======
>>>>>>> cc4cdf6 (Initial project files: add source and README)
>>>>>>> backup-changes

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

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> backup-changes
    // ----- Draw image helper -----
    public void drawImage(String imageName, double x, double y, double w, double h) {
        if (imageName == null) return;
        try {
            BufferedImage img = imageCache.get(imageName);
            if (img == null) {
                File f = new File(IMAGE_DIR + imageName);
                System.out.println("Loading image: " + f.getAbsolutePath() + " exists: " + f.exists());
                if (f.exists()) {
                    img = ImageIO.read(f);
                    imageCache.put(imageName, img);
                    System.out.println("Image loaded successfully: " + imageName);
                }
            }
            if (img != null) {
                g.drawImage(img, (int) x, (int) y, (int) w, (int) h, null);
                System.out.println("Drawing image: " + imageName + " at " + x + "," + y);
            } else {
                // fallback box
                setColor(Color.MAGENTA);
                fillRect(x, y, w, h);
                System.out.println("Fallback: drawing magenta box for " + imageName);
            }
        } catch (IOException ex) {
            // fallback box on error
            System.out.println("Error loading image " + imageName + ": " + ex.getMessage());
            setColor(Color.MAGENTA);
            fillRect(x, y, w, h);
        }
    }

<<<<<<< HEAD
=======
>>>>>>> cc4cdf6 (Initial project files: add source and README)
>>>>>>> backup-changes
    // ----- Phương thức tự động vẽ một GameObject -----
    public void draw(GameObject obj) {
        if (obj == null) return;

        // Ví dụ: vẽ theo loại đối tượng
        if (obj instanceof Paddle) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
            setColor(Color.CYAN);
            fillRect(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
        }
        else if (obj instanceof Ball) {
            setColor(Color.WHITE);
            fillCircle(obj.getX(), obj.getY(), obj.getWidth() / 2);
=======
>>>>>>> backup-changes
            // try image first
            drawImage("Paddle.png", obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
        }
        else if (obj instanceof Ball) {
            drawImage("Ball.png", obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
<<<<<<< HEAD
=======
>>>>>>> cc4cdf6 (Initial project files: add source and README)
>>>>>>> backup-changes
        }
        else if (obj instanceof Brick) {
            Brick b = (Brick) obj;
            if (!b.isDestroyed()) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
                setColor(Color.ORANGE);
                fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
=======
>>>>>>> backup-changes
                // choose brick image according to type if available
                String img = null;
                if ("strong".equalsIgnoreCase(b.getType())) img = "StrongBrick.png";
                else img = "NormalBrick.png";
                drawImage(img, b.getX(), b.getY(), b.getWidth(), b.getHeight());
                // draw border
<<<<<<< HEAD
=======
>>>>>>> cc4cdf6 (Initial project files: add source and README)
>>>>>>> backup-changes
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

import java.awt.*;

public class Ball extends MovableObject {
    protected double speed;
    protected double directionX;
    protected double directionY;

    public static final double screenWidth = 800;
    public static final double screenHeight = 600;

    private boolean piercing = false;
    private double piercingDuration = 0.0;

    public Ball(double x, double y, double radius, double speed, double directionX, double directionY) {
        super(x, y, radius * 2, radius * 2, directionX, directionY);
        this.speed = speed;
        this.directionX = directionX;
        this.directionY = directionY;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        if (speed > 0) this.speed = speed;
    }

    public double getDirectionX() {
        return directionX;
    }

    public void setDirectionX(double directionX) {
        this.directionX = directionX;
    }

    public double getDirectionY() {
        return directionY;
    }

    public void setDirectionY(double directionY) {
        this.directionY = directionY;
    }

    public double getRadius() {
        return width / 2;
    }

    public void setRadius(double radius) {
        if (radius > 0) {
            this.width = radius * 2;
            this.height = radius * 2;
        }
    }

    public void activatePiercing(double duration) {
        this.piercing = true;
        this.piercingDuration = duration;
    }

    public void deactivatePiercing() {
        this.piercing = false;
        this.piercingDuration = 0.0;
    }

    public void setPiercing(boolean value) {
        this.piercing = value;
    }

    public boolean isPiercing() {
        return piercing;
    }

    public boolean isOutOfScreen() {
        return getY() > screenHeight;
    }

    @Override
    public void move(double dt) {
        setX(getX() + directionX * speed * dt);
        setY(getY() + directionY * speed * dt);
    }

    @Override
    public void update(double dt) {
        move(dt);

        if (piercing) {
            piercingDuration -= dt;
            if (piercingDuration <= 0) {
                deactivatePiercing();
            }
        }

        // Va chạm tường trái/phải
        if (getX() <= 0) {
            setX(0);
            directionX *= -1;
            SoundManager.playSound("hitpaddle.wav");
        } else if (getX() >= screenWidth - getWidth()) {
            setX(screenWidth - getWidth());
            directionX *= -1;
            SoundManager.playSound("hitpaddle.wav");
        }

        // Va chạm trần
        if (getY() <= 0) {
            setY(0);
            directionY *= -1;
        }
    }

    @Override
    public void render(Renderer rd) {
        if (piercing) {
            Graphics2D g2d = rd.getGraphics2D();

            // Vẽ bóng gốc (dùng hình PNG bình thường)
            rd.drawImage("Ball.png", getX(), getY(), getWidth(), getHeight());

            // Vẽ viền đỏ mảnh quanh bóng
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
        } else {
            // Bình thường
            rd.drawImage("Ball.png", getX(), getY(), getWidth(), getHeight());
        }
    }

    public void resetPosition(Paddle paddle) {
        this.setX(paddle.getX() + paddle.getWidth() / 2 - this.getWidth() / 2);
        this.setY(paddle.getY() - this.getHeight());
        this.directionX = Math.random() > 0.5 ? 1 : -1; // random hướng
        this.directionY = -1;
    }

    public void reset(double newX, double newY) {
        setX(newX);
        setY(newY);
        directionX = Math.random() > 0.5 ? 1 : -1;
        directionY = -1;
    }

    public void bounceOff(GameObject other) {
        if (piercing && other instanceof Brick) return;

        directionY *= -1;

        double hitPoint = (getX() + getWidth() / 2) - (other.getX() + other.getWidth() / 2);
        directionX = hitPoint / (other.getWidth() / 2);

        //chuẩn hoá hướng cho vector
        double length = Math.sqrt(directionX * directionX + directionY * directionY);
        if (length != 0) {
            directionX /= length;
            directionY /= length;
        }
    }

    public boolean checkCollision(GameObject other) {
        return getX() < other.getX() + other.getWidth() &&
                getX() + getWidth() > other.getX() &&
                getY() < other.getY() + other.getHeight() &&
                getY() + getHeight() > other.getY();
    }
}

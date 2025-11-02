import java.awt.*;

public class Ball extends MovableObject {
    protected double speed;
    protected double directionX;
    protected double directionY;
    private double prevX;
    private double prevY;

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
        prevX = getX();
        prevY = getY();

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
            SoundManager.playSound("hitpaddle.wav");
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
        // Compute overlap depths
        double overlapX = Math.min(getX() + getWidth(), other.getX() + other.getWidth()) - Math.max(getX(), other.getX());
        double overlapY = Math.min(getY() + getHeight(), other.getY() + other.getHeight()) - Math.max(getY(), other.getY());

        // Ball center and other center
        double cx = getX() + getWidth() / 2.0;
        double cy = getY() + getHeight() / 2.0;
        double ocx = other.getX() + other.getWidth() / 2.0;
        double ocy = other.getY() + other.getHeight() / 2.0;

        boolean handled = false;

        // Decide collision side by comparing penetration depths
        if (overlapX <= 0 || overlapY <= 0) {
            // No overlap (safety), fallback to invert vertical
            directionY *= -1;
            handled = true;
        } else if (overlapX < overlapY) {
            // Horizontal collision (hit left or right side)
            directionX *= -1;
            handled = true;

            // Push ball outside to avoid sticking
            if (prevX + getWidth() <= other.getX()) {
                setX(other.getX() - getWidth() - 0.1);
            } else if (prevX >= other.getX() + other.getWidth()) {
                setX(other.getX() + other.getWidth() + 0.1);
            }
        } else if (overlapY < overlapX) {
            // Vertical collision (hit top or bottom)
            // For paddle, we want to change X direction based on hit point
            if (other instanceof Paddle) {
                double hitPoint = cx - (other.getX() + other.getWidth() / 2.0);
                directionX = hitPoint / (other.getWidth() / 2.0);
            }
            directionY *= -1;
            handled = true;

            // Push ball outside vertically
            if (prevY + getHeight() <= other.getY()) {
                setY(other.getY() - getHeight() - 0.1);
            } else if (prevY >= other.getY() + other.getHeight()) {
                setY(other.getY() + other.getHeight() + 0.1);
            }
        } else {
            // Corner collision: reflect across normal from corner to ball center
            double nx = cx - ocx;
            double ny = cy - ocy;
            double nlen = Math.sqrt(nx * nx + ny * ny);
            if (nlen == 0) {
                // Degenerate -> invert both
                directionX *= -1;
                directionY *= -1;
            } else {
                nx /= nlen;
                ny /= nlen;
                // reflect d across n: d' = d - 2*(d·n)*n
                double dot = directionX * nx + directionY * ny;
                directionX = directionX - 2 * dot * nx;
                directionY = directionY - 2 * dot * ny;
            }
            handled = true;

            // Nudge out a bit
            setX(getX() + directionX * 0.5);
            setY(getY() + directionY * 0.5);
        }

        // Normalize direction vector
        double len = Math.sqrt(directionX * directionX + directionY * directionY);
        if (len != 0) {
            directionX /= len;
            directionY /= len;
        }

        // Prevent very shallow angles which can cause sticking
        double minY = 0.25; // minimum vertical component
        if (Math.abs(directionY) < minY) {
            directionY = Math.signum(directionY == 0 ? -1 : directionY) * minY;
            // re-normalize preserving horizontal sign
            double dx = directionX;
            double dy = directionY;
            double l2 = Math.sqrt(dx * dx + dy * dy);
            if (l2 != 0) {
                directionX = dx / l2;
                directionY = dy / l2;
            }
        }

        // If not specifically handled above, as a fallback invert Y
        if (!handled) directionY *= -1;
    }

    public boolean checkCollision(GameObject other) {
        return getX() < other.getX() + other.getWidth() &&
                getX() + getWidth() > other.getX() &&
                getY() < other.getY() + other.getHeight() &&
                getY() + getHeight() > other.getY();
    }
}

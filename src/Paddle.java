public class Paddle extends MovableObject {
    protected double speed;
    public static final double screenWidth = 800;
    public static final double screenHeight = 600;

    protected double minWidth;
    protected double maxWidth;;
    protected double baseWidth;

    protected double height;

    protected String currentPowerUp;

    protected int level;

    public Paddle(double x, double y,double speed) {
        super(x, y, 0, 0);

        this.minWidth = screenWidth * 0.10;
        this.maxWidth = screenWidth * 0.20;
        this.baseWidth = screenWidth * 0.15;
        this.height = screenHeight * 0.02;

        this.width = baseWidth;
        this.height = height;

        this.speed = speed;
        this.currentPowerUp = null;
        this.level = 1;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getCurrentPowerUp() {
        return currentPowerUp;
    }

    public void setCurrentPowerUp(String powerUp) {
        this.currentPowerUp = currentPowerUp;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public void move(double dt) {
        setX(getX() + getDx() * dt);
    }

    public void moveLeft(double dt) {
        setX(getX() - speed * dt);
    }

    public void moveRight(double dt) {
        setX(getX() + speed * dt);
    }

    public void applyPowerUp(String powerUpType) {
        currentPowerUp = powerUpType;

        switch (powerUpType.toLowerCase()) {
            case "expand":
                width = Math.min(width * 1.5, maxWidth);
                break;
            case "shrink":
                width = Math.max(width * 0.75, minWidth);
                break;
            case "speedup":
                speed *= 1.2;
                break;
            case "slow":
                speed *= 0.8;
                break;
            case "reset":
                resetPaddle();
                break;
        }
    }

    public void resetPaddle() {
        width = baseWidth;
        speed = 300;
        setX(screenWidth / 2 - getWidth() / 2);
        setY(screenHeight - 50);
        currentPowerUp = null;
    }

    @Override
    public void update(double dt) {
        if (getX() < 0) {
            setX(0);
        }
        if (getX() + getWidth() > screenWidth) {
            setX(screenWidth - getWidth());
        }
    }

    @Override
    public void render(Renderer rd) {
        rd.drawRect(getX(), getY(), getWidth(), getHeight());
    }
}

public class Paddle extends MovableObject {
    protected double speed;
    public static final double screenWidth = 800;
    public static final double screenHeight = 600;

    protected double minWidth;
    protected double maxWidth;
    protected double baseWidth;

    protected double height;

    protected String currentPowerUp;
    protected String activePowerUpType = null;
    protected double activePowerUpRemaining = 0.0; // seconds
    protected int level;

    public Paddle(double x, double y, double speed) {
        super(x, y, 0, 0);

        this.minWidth = screenWidth * 0.15;
        this.maxWidth = screenWidth * 0.25;
        this.baseWidth = screenWidth * 0.20;
        this.height = screenHeight * 0.04;

        this.width = baseWidth;
        this.height = height;

        this.speed = speed;
        this.currentPowerUp = null;
        this.level = 1;
    }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    public String getCurrentPowerUp() { return currentPowerUp; }
    public void setCurrentPowerUp(String powerUp) { this.currentPowerUp = powerUp; }

    public int getLevel() { return level; }

    @Override
    public void move(double dt) { setX(getX() + getDx() * dt); }

    public void moveLeft(double dt) { setX(getX() - speed * dt); }
    public void moveRight(double dt) { setX(getX() + speed * dt); }

    public void applyPowerUp(String powerUpType) {
        if (powerUpType == null) return;
        powerUpType = powerUpType.toLowerCase();

        // refresh if same type
        if (powerUpType.equals(activePowerUpType)) {
            activePowerUpRemaining = 20.0;
            return;
        }

        // remove old power-up if different type active
        if (activePowerUpType != null) {
            clearActivePowerUp();
        }

        activePowerUpType = powerUpType;
        activePowerUpRemaining = 20.0;

        switch (powerUpType) {
            case "expand":
                width = Math.min(width * 1.5, maxWidth);
                break;
            case "shrink":
                width = Math.max(width * 0.75, minWidth);
                break;
            case "speedup":
                speed *= 1.5;
                break;
            case "slow":
                speed *= 0.7;
                break;
            default:
                break;
        }
    }

    public void clearActivePowerUp() {
        if (activePowerUpType == null) return;
        switch (activePowerUpType) {
            case "expand":
            case "shrink":
                width = baseWidth;
                break;
            case "speedup":
            case "slow":
                speed = 500; // reset to default base
                break;
            default:
                break;
        }
        activePowerUpType = null;
        activePowerUpRemaining = 0.0;
    }

    public void resetPaddle() {
        width = baseWidth;
        speed = 500;
        setX(screenWidth / 2 - getWidth() / 2);
        setY(screenHeight - 50);
        currentPowerUp = null;
        activePowerUpType = null;
        activePowerUpRemaining = 0.0;
    }

    @Override
    public void update(double dt) {
        if (activePowerUpType != null) {
            activePowerUpRemaining -= dt;
            if (activePowerUpRemaining <= 0) {
                clearActivePowerUp();
            }
        }
        if (getX() < 0) setX(0);
        if (getX() + getWidth() > screenWidth) setX(screenWidth - getWidth());
    }

    @Override
    public void render(Renderer rd) {
        rd.drawImage("Paddle.png", getX(), getY(), getWidth(), getHeight());
    }
}

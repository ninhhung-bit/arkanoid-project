<<<<<<< HEAD
=======
<<<<<<< HEAD
public class PowerUp extends GameObject {
    protected String type;
    protected double duration;
    protected double fallSpeed;

    public static final double screenHeight = 600;

    public PowerUp(double x, double y, double width, double height, String type, double duration) {
        super(x, y, width, height);
        this.type = type;
        this.duration = duration;
        this.fallSpeed = 150;
    }

    public String getType() {
        return type;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void move(double dt) {
        setY(getY() + fallSpeed * dt);
    }

    public boolean isOutOfScreen() {
        return getY() > screenHeight;
    }

    @Override
    public void update(double dt) {
        move(dt);
    }

    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.applyPowerUp(type);
        }
    }

    public void removeEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.resetPaddle();
        }
    }

    @Override
    public void render(Renderer rd) {
        rd.setColor(0, 255, 0); // Màu xanh mặc định, lớp con có thể override
        rd.drawRect(getX(), getY(), getWidth(), getHeight());
    }
}
=======
>>>>>>> backup-changes
public class PowerUp extends GameObject {
    protected String type;
    protected double duration;
    protected double fallSpeed;
    protected String imageName;

    public static final double screenHeight = 600;

    public PowerUp(double x, double y, double width, double height, String type, double duration) {
        super(x, y, width, height);
        this.type = type;
        this.duration = duration;
        this.fallSpeed = 150;
        // map type -> image file if exists in src/images
        switch (type.toLowerCase()) {
            case "expand": imageName = "ExpandPaddlePowerUp.png"; break;
            case "shrink": imageName = "ShrinkPaddlePowerUp.png"; break;
            case "speedup": imageName = "SpeedUpPaddlePowerUp.png"; break;
            case "slow": imageName = "SlowPaddlePowerUp.png"; break;
            case "fastball": imageName = "FastBallPowerUp.png"; break;
            case "slowball": imageName = "SlowBallPowerUp.png"; break;
            case "multiball": imageName = "MultiBallPowerUp.png"; break;
            default: imageName = null; break;
        }
    }

    public String getType() {
        return type;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void move(double dt) {
        setY(getY() + fallSpeed * dt);
    }

    public boolean isOutOfScreen() {
        return getY() > screenHeight;
    }

    @Override
    public void update(double dt) {
        move(dt);
    }

    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.applyPowerUp(type);
        }
    }

    public void removeEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.resetPaddle();
        }
    }

    @Override
    public void render(Renderer rd) {
        // draw image if available, otherwise fallback to rectangle
        if (imageName != null) {
            rd.drawImage(imageName, getX(), getY(), getWidth(), getHeight());
        } else {
            rd.setColor(0, 255, 0); // Màu xanh mặc định
            rd.drawRect(getX(), getY(), getWidth(), getHeight());
        }
    }
}
<<<<<<< HEAD
=======
>>>>>>> cc4cdf6 (Initial project files: add source and README)
>>>>>>> backup-changes

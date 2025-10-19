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
        this.imageName = type + "PowerUp.png";
    }

    public String getType() { return type; }
    public double getDuration() { return duration; }
    public void setDuration(double duration) { this.duration = duration; }

    public void move(double dt) { setY(getY() + fallSpeed * dt); }
    public boolean isOutOfScreen() { return getY() > screenHeight; }

    @Override
    public void update(double dt) { move(dt); }

    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) paddle.applyPowerUp(type);
    }

    public void removeEffect(Paddle paddle, Ball ball) {
        if (paddle != null) paddle.resetPaddle();
    }

    @Override
    public void render(Renderer rd) {
        if (imageName != null) rd.drawImage(imageName, getX(), getY(), getWidth(), getHeight());
        else {
            rd.setColor(0, 255, 0);
            rd.drawRect(getX(), getY(), getWidth(), getHeight());
        }
    }
}

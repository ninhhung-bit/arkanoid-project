public class PiercingPowerUp extends PowerUp {
    private static final double duration = 5.0;

    public PiercingPowerUp(double x, double y) {
        super(x, y, 30, 30, "piercingball", duration);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (ball != null) ball.activatePiercing(duration);
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (ball != null) ball.deactivatePiercing();
    }

    @Override
    public void render(Renderer rd) {
        rd.drawImage("PiercingBallPowerUp.png", getX(), getY(), getWidth(), getHeight());
    }
}

public class SlowBallPowerUp extends PowerUp {
    private static final double speedDivider = 0.5;

    public SlowBallPowerUp(double x, double y) {
        super(x, y, 30, 30, "slowball", 20.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (ball != null) ball.setSpeed(ball.getSpeed() * speedDivider);
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (ball != null) ball.setSpeed(ball.getSpeed() / speedDivider);
    }

    @Override
    public void render(Renderer rd) {
        rd.drawImage("SlowBallPowerUp.png", getX(), getY(), getWidth(), getHeight());
    }
}

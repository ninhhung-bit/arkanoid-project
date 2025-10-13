public class SlowBallPowerUp extends PowerUp {
    private static final double speedDivider = 0.5;

    public SlowBallPowerUp(double x, double y) {
        super(x, y, 20, 20, "slowball", 5);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (ball != null) {
            ball.setSpeed(ball.getSpeed() * speedDivider);
        }
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (ball != null) {
            ball.setSpeed(ball.getSpeed() / speedDivider);
        }
    }

    @Override
    public void render(Renderer rd) {
        rd.setColor(0, 150, 255);
        rd.fillCircle(getX(), getY(), getWidth() / 2);
    }
}

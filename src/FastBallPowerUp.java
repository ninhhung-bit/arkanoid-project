public class FastBallPowerUp extends PowerUp {
    private static final double speedMultiplier = 1.5;

    public FastBallPowerUp(double x, double y) {
        super(x, y, 20, 20, "speedup", 5.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (ball != null) {
            ball.setSpeed(ball.getSpeed() * speedMultiplier);
        }
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (ball != null) {
            ball.setSpeed(ball.getSpeed() / speedMultiplier);
        }
    }

    @Override
    public void render(Renderer rd) {
        rd.setColor(255, 0, 0); // đỏ cho fastball
        rd.fillCircle(getX(), getY(), getWidth() / 2);
    }
}

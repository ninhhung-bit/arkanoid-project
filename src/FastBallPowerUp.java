public class FastBallPowerUp extends PowerUp {
    private static final double speedMultiplier = 2;

    public FastBallPowerUp(double x, double y) {
        super(x, y, 30, 30, "fastball", 20.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (ball != null) {
            // store original speed on ball using tag (if not present)
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
        rd.drawImage("FastBallPowerUp.png", getX(), getY(), getWidth(), getHeight());
    }
}

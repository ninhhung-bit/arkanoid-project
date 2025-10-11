public class SlowBallPowerUp extends PowerUp {
    public SlowBallPowerUp(double x, double y) {
        super(x, y, 20, 20, "slowball", 5);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (ball != null) {
            ball.setSpeed(ball.getSpeed() * 0.7);
        }
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (ball != null) {
            ball.setSpeed(ball.getSpeed() / 0.7);
        }
    }

    @Override
    public void render(Renderer rd) {
        rd.setColor(0, 150, 255);
        rd.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}

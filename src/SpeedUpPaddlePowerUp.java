public class SpeedUpPaddlePowerUp extends PowerUp {

    private double speedMultiplier = 1.3;

    public SpeedUpPaddlePowerUp(double x, double y) {
        super(x, y, 30, 30, "speedup", 20.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.applyPowerUp("speedup");
        }
    }

    @Override
    public void removeEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.resetPaddle();
        }
    }

    @Override
    public void render(Renderer rd) {
        rd.drawImage("SpeedUpPaddlePowerUp.png", getX(), getY(), getWidth(), getHeight());
    }
}

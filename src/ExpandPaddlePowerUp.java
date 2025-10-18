public class ExpandPaddlePowerUp extends PowerUp {

    public ExpandPaddlePowerUp(double x, double y) {
        super(x, y, 30, 30, "expand", 20.0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) {
            paddle.applyPowerUp("expand");
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
        rd.drawImage("ExpandPaddlePowerUp.png", getX(), getY(), getWidth(), getHeight());
    }
}

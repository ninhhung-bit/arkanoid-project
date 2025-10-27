public class ReverseControlPowerUp extends PowerUp {
    public ReverseControlPowerUp(double x, double y) {
        super(x, y, 20, 20, "reverse", 10);
        this.imageName = "ReversePaddle.png"; // 🟡 Ảnh riêng cho powerup
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        if (paddle != null) paddle.applyPowerUp("reverse");
    }
}

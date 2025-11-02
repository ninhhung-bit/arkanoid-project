public class DeathPowerUp extends PowerUp {
    public DeathPowerUp(double x, double y) {
        super(x, y, 20, 20, "Death", 0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        super.applyEffect(paddle, ball);
        // Find the Game instance - we'll need to modify Game class
        if (paddle != null && paddle.getGame() != null) {
            paddle.getGame().decreaseLives();
        }
        SoundManager.playSound("powerup.wav");
    }
}
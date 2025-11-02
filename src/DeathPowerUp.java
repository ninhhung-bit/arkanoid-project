public class DeathPowerUp extends PowerUp {
    public DeathPowerUp(double x, double y) {
        super(x, y, 20, 20, "Death", 0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        // When collected, immediately end the game and exit the program
        if (paddle != null && paddle.getGame() != null) {
            paddle.getGame().endGameAndExit();
        } else {
            // Fallback: play game over sound and exit
            SoundManager.playSound("gameover.wav");
            SoundManager.stopBackground();
            System.exit(0);
        }
    }
}
public class DeathPowerUp extends PowerUp {
    public DeathPowerUp(double x, double y) {
        super(x, y, 20, 20, "Death", 0);
    }

    @Override
    public void applyEffect(Paddle paddle, Ball ball) {
        // Khi paddle lấy được DeathPowerUp, kết thúc trò chơi ngay lập tức
        if (paddle != null && paddle.getGame() != null) {
            paddle.getGame().endGameAndExit();
        } else {
            // Âm thanh dự phòng và thoát chương trình
            SoundManager.playSound("gameover.wav");
            SoundManager.stopBackground();
            System.exit(0);
        }
    }
}
public class ExpandPaddlePowerUp extends PowerUp {
       private static final double EXPAND_FACTOR = 1.5;

       public ExpandPaddlePowerUp(double x, double y) {
              super(x, y, 30, 30, "expand", 20.0);
       }

       @Override
       public void applyEffect(Paddle paddle, Ball ball) {
              paddle.setWidth(paddle.getWidth() * EXPAND_FACTOR);
       }

       @Override
       public void removeEffect(Paddle paddle, Ball ball) {
              paddle.setWidth(paddle.getWidth() / EXPAND_FACTOR);
       }

       @Override
       public void render(Renderer rd) {
              rd.drawImage("ExpandPaddlePowerUp.png", getX(), getY(), getWidth(), getHeight());
       }
}

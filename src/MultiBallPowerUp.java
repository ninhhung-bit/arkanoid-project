import java.util.List;

public class MultiBallPowerUp extends PowerUp {
    public MultiBallPowerUp(double x, double y) {
        super(x, y, 20, 20, "multiball", 0);
    }

    public void applyEffect(List<Ball> balls, Paddle paddle) {
        if (balls.size() >= 3) return;

        Ball baseBall = balls.get(0);
        Ball b1 = new Ball(baseBall.getX(), baseBall.getY(), baseBall.getRadius(),
                baseBall.getSpeed(), 1, -1);
        Ball b2 = new Ball(baseBall.getX(), baseBall.getY(), baseBall.getRadius(),
                baseBall.getSpeed(), -1, -1);

        balls.add(b1);
        balls.add(b2);
    }

    @Override
    public void render(Renderer rd) {
        rd.setColor(255, 165, 0);
        rd.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}

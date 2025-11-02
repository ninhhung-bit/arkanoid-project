public class UnbreakableBrick extends Brick {
    public UnbreakableBrick(double x, double y, double width, double height) {
        super(x, y, width, height, Integer.MAX_VALUE, "unbreakable");
    }

    @Override
    public void takeHit() {
        // Không bị phá hủy
    }

    @Override
    public void render(Renderer rd) {
        if (!isDestroyed()) {
            rd.drawImage("UnbreakableBrick.png", getX(), getY(), getWidth(), getHeight());
        }
    }
}

public class Brick extends GameObject {
    private boolean destroyed = false;
    private int hitPoints;
    private String type;

    public Brick(double x, double y, double width, double height) {
        this(x, y, width, height, 1, "normal");
    }

    public Brick(double x, double y, double width, double height,int hitPoints, String type) {
        super(x, y, width, height);
        this.hitPoints = hitPoints;
        this.type = type;
    }

    public int getHitPoints() { return hitPoints; }
    public String getType() { return type; }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public void takeHit() {
        if (!destroyed) {
            hitPoints--;
            if (hitPoints <= 0) {
                destroyed = true;
            }
        }
    }

    @Override
    public void update(double dt) {
        // bricks are static in this simple implementation
    }

    @Override
    public void render(Renderer rd) {
        if (!destroyed) {
            rd.setColor(java.awt.Color.ORANGE);
            rd.fillRect(getX(), getY(), getWidth(), getHeight());
            rd.setColor(java.awt.Color.BLACK);
            rd.drawRect(getX(), getY(), getWidth(), getHeight());
        }
    }
}
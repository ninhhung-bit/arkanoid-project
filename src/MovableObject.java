//Tam thoi chua dung duoc, powerup loi, update sau.
public abstract class MovableObject extends GameObject {
    private double dx;
    private double dy;

    public MovableObject() {
        super();
    }

    public MovableObject(double x, double y, double width, double height, double dx, double dy) {
        super(x, y, width, height);
        this.dx = dx;
        this.dy = dy;
    }

    public MovableObject(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public abstract void move(double dt);
}

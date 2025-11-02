import javax.swing.*;

public class StrongBrick extends Brick {

    public StrongBrick(double x, double y, double width, double height) {
        super(x,y,width,height,2,"strong");
    }

    @Override
    public void render(Renderer rd) {
        if (!isDestroyed()) {
            if(getHitPoints() == 2) {
                rd.setColor(255,0,0);
            } else if (getHitPoints() == 1) {
                rd.setColor(255,165,0);
            }

            rd.drawRect(getX(), getY(), getWidth(), getHeight());
        }
    }

    @Override
    public void takeHit() {
        super.takeHit();
    }
}

import javax.swing.*;

public class NormalBrick extends Brick {

    public NormalBrick(double x, double y, double width, double height) {
        super(x,y,width,height,1,"normal");
    }

    @Override
    public void render(Renderer rd) {
        if (!isDestroyed()) {
            rd.setColor(0,0,255);//RGB (Xanh lam, se chinh lai sau)
            rd.drawRect(getX(), getY(), getWidth(), getHeight());
        }
    }
}

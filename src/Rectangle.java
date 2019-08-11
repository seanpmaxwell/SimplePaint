import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;


class Rectangle extends Shape implements Serializable {

    private static final long serialVersionUID = 1L;
    

    @Override
    public void draw(Graphics graphics) {
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setColor(super.getColor());
        if (!super.getIsFilled()) {
            g2d.drawRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        } else {
            g2d.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        }
    }

    
    @Override
    int getArea() {
        return (super.getHeight() * super.getWidth());
    }
}

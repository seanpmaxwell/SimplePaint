import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;


class Circle extends Shape implements Serializable {

    private static final long serialVersionUID = 1L;


    @Override
    public void draw(Graphics graphics) {
        Graphics2D g2d = (Graphics2D)graphics;
        // Set dimensions
        int x = super.getX();
        int y = super.getY();
        int width = super.getWidth();
        int height = super.getHeight();
        // Set color/fill
        g2d.setColor(super.getColor());
        if (!super.getIsFilled()) {
            g2d.drawOval(x, y, width, height);
        } else {
            g2d.fillOval(x, y, width, height);
        }
    }


    @Override
    int getArea() {
        return (int)((super.getHeight()/2) * (super.getWidth()/2) * Math.PI);
    }
}

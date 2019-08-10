import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;


class Circle extends Shape implements Serializable {

    private static final long serialVersionUID = 1L;
    int width, height;


    @Override
    public void draw(Graphics graphics) {
        Graphics2D g2d = (Graphics2D)graphics;
        // Set dimensions
        this.width = super.getWidth();
        this.height = super.getHeight();
        // Set color/fill
        g2d.setColor(super.getColor());
        if (!super.getIsFilled()) {
            g2d.drawOval(super.getX(), super.getY(), this.width, this.height);
        } else {
            g2d.fillOval(super.getX(), super.getY(), this.width, this.height);
        }
    }


    @Override
    double getArea() {
        return ((this.height/2) * (this.width/2) * Math.PI);
    }
}

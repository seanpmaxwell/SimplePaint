import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;


class Triangle extends Shape implements Serializable {

    private static final long serialVersionUID = 1L;
    private int[] xArr, yArr;


    void setXArr(int[] xArr) {
        this.xArr = xArr;
    }


    void setYArr(int[] yArr) {
        this.yArr = yArr;
    }


    @Override
    public void draw(Graphics graphics) {
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setColor(super.getColor());
        if (!super.getIsFilled()) {
            g2d.drawPolygon(this.xArr, this.yArr, 3);
        } else {
            g2d.fillPolygon(this.xArr, this.yArr, 3);
        } 
    }


    @Override
    public int getArea() {
        int base = (this.xArr[0] - this.xArr[1]);
        int height = (this.yArr[2] - this.yArr[1]);
        int area = ((base * height) / 2);
        return (area < 0 ? area * -1 : area);
    }
}

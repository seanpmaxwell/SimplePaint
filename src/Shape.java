import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;


abstract class Shape implements Cloneable, Comparable<Shape>, Serializable {

	private static final long serialVersionUID = 1L;
    private int xCoord, yCoord;
    private int width, height;
	private boolean isFilled;
    private Color shapeColor;


    Shape() {}


	Shape(Shape shape) {
		this.setX(shape.getX());
		this.setY(shape.getY());
		this.setColor(shape.getColor());
	}


    Color getColor() {
        return this.shapeColor;
    }


    void setColor(Color shapeColor) {
        this.shapeColor = shapeColor;
    }


    int getWidth() {
        return width;
    }


    void setWidth(int width) {
        this.width = width;
    }


    int getHeight() {
        return this.height;
    }


    void setHeight(int height) {
        this.height = height;
    }


	boolean getIsFilled() {
		return this.isFilled;
    }
    

    void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }


    int getX() {
        return this.xCoord;
    }


    void setX(int xCoord) {
		this.xCoord = xCoord;
    }


	int getY() {
		return this.yCoord;
	}


	void setY(int yCoord) {
		this.yCoord = yCoord;
    }


    abstract double getArea();


    abstract void draw(Graphics g);
    

    @Override 
    public Shape clone() {
        try {
            return (Shape)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException( "Can't Make Clone" );
        }
    }


	@Override
	public int compareTo(Shape other) {
		if (other == null || !(other instanceof Shape)) {
            throw new RuntimeException("Error Comparing Shapes");
        } else if (this.getArea() == other.getArea()) {
            return 0;
        } else if (this.getArea() > other.getArea()) {
            return 1;
        } else {
            return -1;
        }
	}
}


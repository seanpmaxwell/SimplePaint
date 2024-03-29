import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


class Canvas extends JPanel implements MouseListener, ChangeListener {

    private static final long serialVersionUID = 1L;
    private JColorChooser colorChooser;
    private ShapeList shapeList;
    private ShapeList redoList;

    private Color currColor;
    private Shape currShape;

    private BrushOptions currBrush;
    private boolean isFilled;


    Canvas(JColorChooser colorChooser) {
        this.shapeList = new ShapeList();
        this.redoList = new ShapeList();
        this.colorChooser = colorChooser;
        this.colorChooser.setPreviewPanel(new JPanel());
        this.colorChooser.getSelectionModel().addChangeListener(this);
        this.currColor = Color.BLACK;
        super.addMouseListener(this);
        super.setBackground(Color.WHITE);
    }


    boolean getIsFilled() {
        return isFilled;
    }


    ShapeList getList() {
        return new ShapeList(this.shapeList);
    }


    ShapeList getRedoList() {
        return new ShapeList(this.redoList);
    }


    void redo() {
        int size = (this.redoList.getSize() - 1);
        Shape shape = this.redoList.get(size).clone();
        this.shapeList.push(shape);
        this.redoList.pop();
        repaint();
    }


    void setCurrBrush(BrushOptions brush) {
        this.currBrush = brush;
    }


    void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }


    void setShapeList(ShapeList list) {
        this.shapeList = new ShapeList(list);
        repaint();
    }


    void sort() {
        this.shapeList.sort();
        repaint();
    }


    void undo() {
        int size = (this.shapeList.getSize() - 1);
        Shape shape = this.shapeList.get(size).clone();
        this.redoList.push(shape);
        this.shapeList.pop();
        repaint();
    }


    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (this.currBrush == null) {
            javax.swing.JOptionPane.showMessageDialog(null, "Must Select a Shape");
            return;
        }
    }


    @Override
    public void mouseEntered(MouseEvent arg0) {}


    @Override
    public void mouseExited(MouseEvent arg0) {}


    @Override
    public void mousePressed(MouseEvent arg0) {
        // Set shape
        if (this.currBrush == null) {
            javax.swing.JOptionPane.showMessageDialog(null, "Must Select a Shape");
            return;
        } else if (this.currBrush.equals(BrushOptions.Rectangle)) {
            this.currShape = new Rectangle();
        } else if (this.currBrush.equals(BrushOptions.Cirlce)) {
            this.currShape = new Circle();
        } else if (this.currBrush.equals(BrushOptions.Triangle)) {
            this.currShape = new Triangle();
        }
        // Set coordinates
        this.currShape.setX(arg0.getX());
        this.currShape.setY(arg0.getY());
        this.currShape.setColor(this.currColor);
        // Set filled
        if (this.isFilled) {
            this.currShape.setIsFilled(true);
        }
    }


    @Override
    public void mouseReleased(MouseEvent arg0) {
        if (this.currBrush.equals(BrushOptions.Cirlce) || this.currBrush.equals(BrushOptions.Rectangle)) {
            this.makeCircOrRect(arg0);
        } else if (this.currBrush.equals(BrushOptions.Triangle)) {
            this.makeATriangle(arg0);
        }
        repaint();
    }


    private void makeCircOrRect(MouseEvent arg0) {
        // Get coordinates
        int xCoor = this.currShape.getX();
        int yCoor = this.currShape.getY();
        int width = (arg0.getX() - xCoor);
        int height = (arg0.getY() - yCoor);
        // Set width and height
        if (width > 0) {
            this.currShape.setWidth(width);
        } else {
            this.currShape.setWidth(width * -1);
            this.currShape.setX(arg0.getX());
        }
        if (height > 0) {
            this.currShape.setHeight(height);
        } else {
            this.currShape.setHeight(height * -1);
            this.currShape.setY(arg0.getY());
        }
        // Create new shape
        if (this.currBrush.equals(BrushOptions.Cirlce)) {
            Circle newCirc = (Circle)this.currShape;
            this.shapeList.push(newCirc.clone());
        } else {
            Rectangle newRect = (Rectangle)this.currShape;
            this.shapeList.push(newRect.clone());
        }
    }


    private void makeATriangle(MouseEvent arg0) {
        Triangle newTri = (Triangle)currShape;
        int apex = ((newTri.getX() + arg0.getX()) / 2);
        int[] xArr = {newTri.getX(), arg0.getX(), apex};
        int[] yArr = {newTri.getY(), newTri.getY(), arg0.getY()};
        newTri.setXArr(xArr);
        newTri.setYArr(yArr);
        this.shapeList.push(newTri.clone());
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        this.currColor = this.colorChooser.getColor();
    }


    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        for (int i = 0; i < this.shapeList.getSize(); i++) {
            this.shapeList.get(i).draw(graphics);
        }
    }
}

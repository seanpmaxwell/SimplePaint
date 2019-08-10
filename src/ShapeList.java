import java.io.Serializable;
import java.util.Arrays;


class ShapeList implements Serializable {

	private static final long serialVersionUID = 1L;
    private int numElements;
    private int capacity;
	private Shape[] shapeArr;


	ShapeList() {
        this.numElements = 0;
        this.capacity = 20;
        this.shapeArr = new Shape[20];
    }


	ShapeList(ShapeList list) {
        // Copy properties
        this.numElements = list.getSize();
        this.capacity = list.getCapacity();
        // Copy shapes
        var newLength = this.shapeArr.length;
        var temp = new Shape[newLength];
		for (int i = 0; i <= newLength - 1; i++) {
            temp[i] = shapeArr[i].clone();
        }
        this.shapeArr = temp;
	}
    

    Shape[] getShapeArr() {
        return this.shapeArr;
    }


    int getCapacity() {
        return this.capacity;
    }


    Shape getShape(int i) {
        return this.shapeArr[i];
    }


    int getSize() {
        return this.numElements;
    }


    void push(Shape shape) {
        if (this.numElements >= this.capacity) {
            this.capacity *= 2;
            var tempArr = new Shape[capacity];
            for (int i = 0; i < this.numElements; i++) {
                tempArr[i] = this.shapeArr[i];
            }
            this.shapeArr = tempArr;
        }
        this.shapeArr[this.numElements++] = shape;
    }


	Shape pop() {
		return this.shapeArr[--this.numElements];
	}


    void sort() {
        this.shapeArr = Arrays.copyOf(this.shapeArr, this.numElements);
        Arrays.sort(this.shapeArr);
    }
}

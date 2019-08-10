/**
 * Custom dynamic list for holding shapes. Using ArrayList or built-in sorting was not allowed for 
 * the assignment.
 */

import java.io.Serializable;


class ShapeList implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int defaultCapacity = 20;
    private int currCapacity = ShapeList.defaultCapacity;
    private int numOfShapes;
	private Shape[] shapeArr;


	ShapeList() {
        this.numOfShapes = 0;
        this.shapeArr = new Shape[ShapeList.defaultCapacity];
    }


	ShapeList(ShapeList list) {
        this.numOfShapes = list.getSize();
        this.currCapacity = list.getCapacity();
        var newLength = this.shapeArr.length;
        var newArr = new Shape[newLength];
		for (int i = 0; i <= newLength - 1; i++) {
            newArr[i] = shapeArr[i].clone();
        }
        this.shapeArr = newArr;
	}
    

    Shape[] getShapeArr() {
        return this.shapeArr;
    }


    int getCapacity() {
        return this.currCapacity;
    }


    Shape getShape(int i) {
        return this.shapeArr[i];
    }


    int getSize() {
        return this.numOfShapes;
    }


    void push(Shape shape) {
        if (this.numOfShapes == this.currCapacity) {
            this.currCapacity *= 2;
            var newArr = new Shape[this.currCapacity];
            for (int i = 0; i < this.numOfShapes; i++) {
                newArr[i] = this.shapeArr[i];
            }
            this.shapeArr = newArr;
        }
        this.shapeArr[this.numOfShapes++] = shape;
    }


	Shape pop() {
        Shape lastShape = this.shapeArr[this.numOfShapes];
        var newArr = new Shape[this.numOfShapes - 1];
        for (int i = 0; i < this.numOfShapes; i++) {
            newArr[i] = this.shapeArr[i];
        }
        this.shapeArr = newArr;
        this.numOfShapes--;
        return lastShape;
	}


    void sort() {
        this.quickSort(this.shapeArr, 0, this.numOfShapes-1);
    }

    
    /**
     * Note: QuickSort selects a pivot, moves the higher
     * elements forward and the lesser elements backwards
     * using the pivot (or reverse in this case). Then 
     * recursively repeats the process by return the pivot.
     */
    private void quickSort(Shape[] arr, int low, int high) {
        if (low < high) {
            int pi = this.partition(arr, low, high);
            this.quickSort(arr, low, pi - 1);
            this.quickSort(arr, pi + 1, high);
        }
    }


    private int partition(Shape[] arr, int low, int high) {
        Shape pivot = arr[high];  
        int i = (low - 1);
        for (int j = low; j <= high; j++) {
            if (arr[j].compareTo(pivot) >= 0) { // Sort high to low
                this.swap(arr, ++i, j);
            }
        }
        this.swap(arr, i+1, high);
        return (i+1);
    }


    private void swap(Shape[] arr, int idx1, int idx2) {
        Shape shape = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = shape;
    }
}

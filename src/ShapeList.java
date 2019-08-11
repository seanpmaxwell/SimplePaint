/**
 * Custom dynamic list for holding shapes. Using ArrayList or built-in sorting was not allowed for 
 * the assignment.
 */

import java.io.Serializable;


class ShapeList implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int defaultCapacity = 10;
    private int numOfShapes;
    private Shape[] shapeArr;


    ShapeList() {
        this.numOfShapes = 0;
        this.shapeArr = new Shape[ShapeList.defaultCapacity];
    }


    ShapeList(ShapeList list) {
        this.numOfShapes = list.getSize();
        this.shapeArr = new Shape[list.getCapacity()];
        for (int i = 0; i <= this.numOfShapes - 1; i++) {
            this.shapeArr[i] = list.get(i).clone();
        }
    }


    Shape[] getShapeArr() {
        return this.shapeArr;
    }


    int getCapacity() {
        return this.shapeArr.length;
    }


    Shape get(int i) {
        return this.shapeArr[i];
    }


    int getSize() {
        return this.numOfShapes;
    }


    void push(Shape shape) {
        int currCapacity = this.shapeArr.length;
        if (this.numOfShapes == currCapacity) {
            var newArr = new Shape[(currCapacity*2)];
            for (int i = 0; i < this.numOfShapes; i++) {
                newArr[i] = this.shapeArr[i];
            }
            this.shapeArr = newArr;
        }
        this.shapeArr[this.numOfShapes++] = shape;
    }


    Shape pop() {
        return this.shapeArr[--this.numOfShapes];
    }


    /**
     * Note: QuickSort selects a pivot, moves the higher
     * elements forward and the lesser elements backwards
     * using the pivot (or reverse in this case). Then 
     * recursively repeats the process by return the pivot.
     */
    void sort() {
        this.quickSort(this.shapeArr, 0, this.numOfShapes-1);
    }


    private void quickSort(Shape[] arr, int low, int high) {
        if (low < high) {
            int pi = this.partition(arr, low, high);
            this.quickSort(arr, low, pi-1);
            this.quickSort(arr, pi+1, high);
        }
    }


    private int partition(Shape[] arr, int low, int high) {
        Shape pivot = arr[high];  
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) >= 0) { // Sort high to low
                i++;
                this.swap(arr, i, j);
            }
        }
        i++;
        this.swap(arr, i, high);
        return i;
    }


    private void swap(Shape[] arr, int idx1, int idx2) {
        Shape shape = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = shape;
    }
}

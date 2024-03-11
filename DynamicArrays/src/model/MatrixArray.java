package model;

public class MatrixArray<T> implements IArray<T> {

    private int size;
    private int vector;
    private IArray<IArray<T>> array;

    public MatrixArray(int vector) {
        this.vector = vector;
        array = new SingleArray<>();
        array.add(new VectorArray<>(vector));
        size = 0;
    }

    public MatrixArray() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
       add(item, size);
    }

    @Override
    public void add(T item, int index) {
        int arrayIndex = 0;
        int vectorArraySize = array.get(arrayIndex).size();
        while (vectorArraySize<index  ) {//||  array.get(arrayIndex).size() == vector
            arrayIndex++;
            vectorArraySize += array.get(arrayIndex).size();
        }

        IArray<T> leftArray = array.get(arrayIndex);
        int size_int = leftArray.size();
        if(leftArray.size()<vector) {
            leftArray.add(item,index-vectorArraySize+size_int);
        }
        else {
            VectorArray<T> midleArray = new VectorArray<>(vector);
            midleArray.add(item);
            array.add(midleArray, arrayIndex+1);
            VectorArray<T> rightArray = new VectorArray<>(vector);
            for(int i=index-vectorArraySize+size_int; i<vector; i++) {
                rightArray.add(leftArray.get(i));
            }
            for(int i=index-vectorArraySize+size_int; i<vector; i++) {
                leftArray.remove(i);
            }
            array.add(rightArray, arrayIndex+2);
        }
        size ++;
    }


    @Override
    public T remove(int index) {
        int arrayIndex = 0;
        int vectorArraySize = array.get(arrayIndex).size();
        while (vectorArraySize<=index ) {
            arrayIndex++;
            vectorArraySize += array.get(arrayIndex).size();
        }
        IArray<T> tiArray = array.get(arrayIndex);
        int size1 = tiArray.size();
        T t = tiArray.get(index - vectorArraySize+size1);
        tiArray.remove(index - vectorArraySize+size1);
        size--;
        return  t;
    }
//    @Override
//    public T get(int index) {
//        return array.get(index / vector).get(index % vector);
//    }

//        if (size == array.size() * vector)
//            array.add(new VectorArray<T>(vector));
//        array.get(size / vector).add(item);
//    size ++;
//
    @Override
    public T get(int index) {
        int arrayIndex = 0;
        int vectorArraySize = array.get(arrayIndex).size();
        while (vectorArraySize<=index ) {
            arrayIndex++;
            vectorArraySize += array.get(arrayIndex).size();
        }
        int size = array.get(arrayIndex).size();
        return array.get(arrayIndex).get(index-vectorArraySize+size);
    }

}

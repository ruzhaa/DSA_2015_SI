/**
 * Created by kakato10 on 10/7/2015.
 */
public class SequentialList implements ListInterface {
    private static final int INITIAL_SIZE = 2;
    private static final double GROWING_FACTOR = 2;
    private static final double SHRINKING_FACTOR = 4;
    private int elementsCount;
    private int[] array;

    public SequentialList() {
        this.array = new int[INITIAL_SIZE];
        this.elementsCount = 0;
    }

    private void tryGrow() {
        if (this.elementsCount == this.array.length) {
            int[] newArray = new int[(int) (this.array.length * this.GROWING_FACTOR)];
            for (int i = 0; i < this.array.length; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
    }

    public void tryShrink() {
        if (this.elementsCount == this.array.length / 4) {
            int[] newArray = new int[(int) (this.array.length / this.GROWING_FACTOR)];
            for (int i = 0; i < elementsCount; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
    }

    public void add(int newElement) {
        this.tryGrow();
        this.array[this.elementsCount] = newElement;
        this.elementsCount++;
    }

    public void deleteAt(int index) {
        if (index >= this.elementsCount) {
            System.out.println("There is no such element!");
            return;
        }
        for (int i = index + 1; i < elementsCount; i++) {
            this.array[i - 1] = this.array[i];
        }
        this.elementsCount--;
        tryShrink();
    }

    public int get(int index) {
        return this.array[index];
    }

    public void insert(int newElement, int index) {
        if (index >= this.elementsCount) {
            this.add(newElement);
            return;
        }
        this.tryGrow();
        for (int i = this.elementsCount - 1; i >= index; i--) {
            this.array[i + 1] = this.array[i];
        }
        this.array[index] = newElement;
        this.elementsCount++;
    }

    public int indexOf(int element) {
        for (int i = 0; i < elementsCount; i++) {
            if (element == this.array[i]) {
                return i;
            }
        }
        return -1;
    }

    public SequentialList copy() {
        SequentialList listCopy = new SequentialList();
        for (int i = 0; i < elementsCount; i++) {
            listCopy.add(this.array[i]);
        }
        return listCopy;
    }

    public void print() {
        for (int i = 0; i < this.array.length; i++) {
            int element = this.array[i];
            System.out.println(element);
        }
    }
}

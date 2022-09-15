//Ali Sbeih

public class ArrayBinaryHeap<E extends Comparable<E>> {
    //default size of the array
    public static final int DEFAULT_SIZE = 16;
    // array of objects
    private Object[] contents;
    //initial size
    private int size = 0;
    //initial capacity
    private int capacity = DEFAULT_SIZE;

    //method for finding the index of the left child
    public int left(int i) {
        return 2 * i + 1;
    }

    //method for finding the index of the right child
    public int right(int i) {
        return 2 * i + 2;
    }

    //method for finding the index of the parent
    public int parent(int i) {
        return (i - 1) / 2;
    }

    //initializing the array
    public ArrayBinaryHeap() {
        contents = new Object[DEFAULT_SIZE];
    }

    /**
     * Get the current size
     */
    public int size() {
        return size;
    }

    /**
     * Determine if the array is currently empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the smallest element
     */
    public E min() {
        return (E) contents[0];
    }

    /**
     * Insert the element x.
     */
    public void insert(E x) {
        // check if we need to increase the capacity
        if (size == capacity) {
            increaseCapacity();
        }
        //insert a new element at the end
        contents[size] = x;
        //increase the size
        size++;
        //index of the added element
        int c = size - 1;
        //index of its parent
        int p = parent(c);
        //bubble up switching child and parent to ensure the heap property
        for (int i = 0; i < size; i++) {
            //check if child is smaller than parent
            if (x.compareTo((E) contents[p]) < 0) {
                contents[c] = contents[p];
                contents[p] = x;
                c = p;
                p = parent(c);
            } else break;
        }
    }

    public E removeMin() {
        //value to be returned
        E value = (E) contents[0];
        //setting the root to the last element in the array
        contents[0] = contents[size - 1];
        //decreasing the size
        size--;
        //the parent is the root
        int p = 0;
        //parent's right child
        int r = right(p);
        //parent's left child
        int l = left(p);
        //trickle-down switching parent and smaller child to ensure heap property
        for (int i = 0; i < size + 1; i++) {
            //check if the parent has children
            if (l < size) {
                //get the value of the right child
                E right = (E) contents[r];
                //get the value of the left child
                E left = (E) contents[l];
                //get the value of the parent
                E parent = (E) contents[p];
                //if the value of the right child is less than the value of the left child
                //then switch the parent with its right child and move on
                if ((right).compareTo(left) < 0) {
                    contents[r] = parent;
                    contents[p] = right;
                    p = r;
                    r = right(p);
                    l = left(p);
                }
                //Otherwise, switch the parent with its left child and move on
                else {
                    contents[l] = parent;
                    contents[p] = left;
                    p = l;
                    r = right(p);
                    l = left(p);
                }

            } else {
                return value;
            }
        }
        return null;
    }

    //method for increasing the capacity
    private void increaseCapacity() {
        //create new array with twice the capacity
        Object[] bigContents = new Object[2 * capacity];
        //copy the elements to the new array
        for (int i = 0; i < capacity; ++i) {
            bigContents[i] = contents[i];
        }
//update the previous array
        contents = bigContents;
        //update the capacity
        capacity = 2 * capacity;
    }
}

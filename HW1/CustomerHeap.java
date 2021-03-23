package eurovision;

/**
 * A heap, implemnted as an array. The elemnts in the heap are instances of the
 * class CustomerElement, and the heap is ordered according to the Customer
 * instances wrapped by those objects.
 * 
 * IMPORTANT: Except in the constuctor no single function may loop/recurse
 * through all elements in the heap. You may only use loops which look at a
 * small fraction of the heap.
 * 
 *
 */
public class CustomerHeap {

    /*
     * The array in which the elements are kept according to the heap order. The
     * following must always hold true: if i < size then heap[i].heapIndex == i
     */
    CustomerElement[] heap;
    int size; // the number of elements in the heap, neccesarilty size <= heap.length

    /**
     * Creates an empty heap which can initally accomodate 10 elements.
     */
    public CustomerHeap() {
        this.size = 0;
        this.heap = new CustomerElement[10];
    }

    /**
     * Returns the size of the heap.
     *
     * @return the size of the heap
     */
    public int size() {
        return this.size;
    }

    /**
     * Inserts a given element into the heap.
     *
     * @param e
     *            - the element to be inserted.
     */
    public void insert(CustomerElement e) {
        heap[size] = e;
        e.heapIndex = size;
        percUp(size);
        size++;
        if (size == heap.length) {
            heapResize();
        }
    }

    /**
     * Returns and does not remove the element which wraps the cutomer with maximal
     * priority.
     * 
     * @return the element which wraps the cutomer with maximal priority.
     */
    public CustomerElement findMax() {
        if (size > 0) {
            return this.heap[0];
        } else
            return null;
    }

    /**
     * Returns and removes the element which wraps the cutomer with maximal
     * priority.
     * 
     * @return the element which wraps the cutomer with maximal priority.
     */
    public CustomerElement extractMax() {
        if (size == 0) {
            return null;
        } else {
            CustomerElement currentMax = findMax();
            this.heap[0] = this.heap[size - 1];
            heap[0].heapIndex = 0;
            this.heap[size - 1] = null;
            this.size--;
            if (this.size > 1)
                percDown(0);
            return currentMax;
        }
    }

    /**
     * Removes the element located at the given index.
     * 
     * Note: this function is not part of the standard heap API. Make sure you
     * understand how to implement it why it is required. There are several ways
     * this function could be implemented. No matter how you choose to implement it,
     * you need to consider the different possible edge cases.
     * 
     * @param index
     */
    public void remove(int index) {
        if (this.size - 1 == index) {
            this.heap[index] = null;
            this.size--;
            return;
        } else {
            if (index >= 0 && index < this.size && this.size > 0) {
                this.heap[index] = this.heap[this.size - 1];
                this.heap[index].heapIndex = index;
                this.heap[this.size - 1] = null;
                size--;
                if (compareCustomers(getFatherIndex(index), index) == 1) {
                    percUp(index);
                } else {
                    percDown(index);
                }
            }
        }
    }

    // Fixing the heap from the parameter index upward
    private void percUp(int index) {
        int indexFather = (index - 1) / 2;
        if (index <= 0 || this.size == 0) {
            return;
        } else {
            if (getPriority(index) > getPriority(indexFather) || (getPriority(index) == getPriority(indexFather)
                    && heap[index].c.compareTo(heap[indexFather].c) > 0))
                swap((index - 1) / 2, index);
            percUp(indexFather);
        }
    }

    // Fixing the heap from the parameter index downward
    private void percDown(int index) {
        if (isLeaf(index)) {
            return;
        } else {
            if (compareCustomers(getLeftChildIndex(index), index) == 1
                    || compareCustomers(getRightChildIndex(index), index) == 1) {
                if (compareCustomers(getLeftChildIndex(index), getRightChildIndex(index)) == 1) {
                    swap(getLeftChildIndex(index), index);
                    percDown(getLeftChildIndex(index));
                } else
                    swap(getRightChildIndex(index), index);
                percDown(getRightChildIndex(index));
            }
        }
    }

    // The function compares between two CustomerElement and return 1 if the
    // indexFirstCustomer is bigger than indexSecondCustomer or -1 if
    // indexSecondCustomer is bigger than indexFirstCustomer
    private int compareCustomers(int indexFirstCustomer, int indexSecondCustomer) {
        CustomerElement firstElement = heap[indexFirstCustomer];
        CustomerElement secondElement = heap[indexSecondCustomer];
        if (indexSecondCustomer > size - 1) {
            return 1;
        }
        if (firstElement.c.priority == secondElement.c.priority) {
            return firstElement.c.compareTo(secondElement.c);
        }
        if (firstElement.c.priority > secondElement.c.priority) {
            return 1;
        }
        if (firstElement.c.priority < secondElement.c.priority) {
            return -1;
        }
        return 0;
    }

    // The function swap between two CustomerElements in the heap.
    private void swap(int indexFirst, int indexSecond) {
        CustomerElement coustomerTmp;
        int indexTmp;
        coustomerTmp = this.heap[indexSecond];
        this.heap[indexSecond] = this.heap[indexFirst];
        this.heap[indexFirst] = coustomerTmp;
        indexTmp = this.heap[indexFirst].heapIndex;
        this.heap[indexFirst].heapIndex = this.heap[indexSecond].heapIndex;
        this.heap[indexSecond].heapIndex = indexTmp;
    }

    // The function return the priority of the Customer in the given index
    private int getPriority(int index) {
        return heap[index].c.priority;
    }

    // The function return the father of the CustomerElement in the given index
    private int getFatherIndex(int index) {
        return (index - 1) / 2;
    }

    // The function return the right child of the CustomerElement in the given index
    private int getRightChildIndex(int index) {
        return ((index * 2) + 2);
    }

    // The function return the left child of the CustomerElement in the given index
    private int getLeftChildIndex(int index) {
        return ((index * 2) + 1);
    }

    // The function return true if the given index is leaf otherwise return false
    private boolean isLeaf(int index) {
        if (index >= ((size - 1) / 2) && index <= size - 1)
            return true;
        else
            return false;
    }

    // The function increasing the heap size
    private void heapResize() {
        CustomerElement[] newHeap = new CustomerElement[size * 2];
        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    public static void main(String[] args) {
        /*
         * A basic test for the heap. You should be able to run this before implementing
         * the queue.
         * 
         * Expected outcome: customer: Netta, priority: 10 customer: Liran, priority: 20
         * customer: Liran, priority: 20 customer: Netta, priority: 10 customer: Dana,
         * priority: 3 customer: Izhar, priority: 2
         * 
         */
        CustomerHeap heap = new CustomerHeap();
        Customer a = new Customer(10, "Netta");
        Customer b = new Customer(2, "Izhar");
        Customer c = new Customer(3, "Dana");
        Customer d = new Customer(20, "Liran");

        heap.insert(new CustomerElement(a));
        System.out.println(heap.findMax());

        heap.insert(new CustomerElement(b));
        heap.insert(new CustomerElement(c));
        heap.insert(new CustomerElement(d));
        System.out.println(heap.findMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
    }
}

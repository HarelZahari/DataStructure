package eurovision;

/**
 * A queue class, implemented as a linked list. The nodes of the linked list are
 * implemnted as the CustomerElement class.
 * 
 * IMPORTANT: you may not use any loops/recursions in this class.
 */
public class CustomerQueue {

    CustomerElement first;
    CustomerElement last;

    /**
     * Constructs an empty queue
     */
    public CustomerQueue() {
        first = null;
        last = null;
    }

    /**
     * Removes and returns the first element in the queue
     * 
     * @return the first element in the queue
     */
    public CustomerElement dequeue() {
        if (!isEmptyQueue()) {
            if (first != last) {
                CustomerElement Currfirst = this.first;
                this.first = Currfirst.prev;
                this.first.next = null;
                if (this.first.prev == null) {
                    this.last = this.first;
                }
                this.first = Currfirst.prev;
                return Currfirst;
            } else {
                CustomerElement Currfirst = this.first;
                first = null;
                last = null;
                return Currfirst;
            }
        } else
            return null;
    }

    /**
     * Returns and does not remove the first element in the queue
     * 
     * @return the first element in the queue
     */
    public CustomerElement peek() {
        if (isEmptyQueue()) {
            return null;
        } else
            return this.first;
    }

    /**
     * Adds a new element to the back of the queue
     * 
     * @param node
     */
    public void enqueue(CustomerElement node) {
        if (isEmptyQueue()) {
            this.first = node;
            this.last = node;
            node.prev = null;
            node.next = null;
        } else {
            node.next = this.last;
            node.next.prev = node;
            node.prev = null;
            this.last = node;
        }
    }

    // The function return true if the queue is empty otherwise return false
    private boolean isEmptyQueue() {
        return this.first == null;
    }

}

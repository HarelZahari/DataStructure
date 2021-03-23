package eurovision;

/**
 * A eurovision queue which holds potential customer in two parllel lines. One
 * according to priority and one according to order of apperance. At any given
 * time, a customer standing at the front of either line may be removed from the
 * data structure.
 * 
 * 
 * 
 *
 */
public class EuroVisionQueue {

    /*
     * The heap and queue which compose the data structure. The most important thing
     * to observe is: A customer element exists in the queue if and only if it also
     * exists in the heap.
     */
    CustomerHeap heap;
    CustomerQueue q;

    /**
     * Creates an empty eurovision queue
     */
    public EuroVisionQueue() {
        this.heap = new CustomerHeap();
        this.q = new CustomerQueue();
    }

    /**
     * Adds a new customer to the data structure. The customer is entered (wrapped
     * by a customer element) to the back of the queue and into the heap, according
     * to its priority.
     * 
     * @param c
     */
    public void addCustomer(Customer c) {
        CustomerElement customerElement = new CustomerElement(c);
        q.enqueue(customerElement);
        heap.insert(customerElement);
    }

    /**
     * Removes the customer with the highest priority from the data structure. The
     * customer must be removed both from the heap and the queue.
     * 
     * @return the customer with the highest priority.
     */
    public Customer servePriorityCustomer() {
        CustomerElement customerElement = heap.findMax();
        if (customerElement != null) {
            remove(customerElement);
            return heap.extractMax().c;
        } else {
            return null;
        }
    }

    /**
     * Removes the customer which arrived first to the data structure. The customer
     * must be removed both from the heap and the queue.
     * 
     * @return the customer which arrived first to the data structure
     */
    public Customer serveRegularCustomer() {
        CustomerElement customerElement = q.dequeue();
        if (customerElement != null) {
            heap.remove(customerElement.heapIndex);
            return customerElement.c;
        } else
            return null;
    }

    // The function remove CustomerElement from the CustomerQueue
    private void remove(CustomerElement node) {
        if (node.next == null && node.prev == null) {
            q.first = null;
            q.last = null;
        } else if (node.next != null && node.prev == null) {
            q.last = node.next;
            node.next.prev = null;
        } else if (node.next == null && node.prev != null) {
            q.first = node.prev;
            node.prev.next = null;
        } else {
            node.prev = node.next;
        }
    }

    public static void main(String[] args) {

        /*
         * A basic test to check your class. Expected outcome: customer: Liran
         * GreyShirt, priority: 20 customer: Netta Barzilay, priority: 10 customer:
         * Izhar Ashdot, priority: 2 customer: Alan Turing, priority: 100 customer: Dana
         * International, priority: 3
         */
        EuroVisionQueue q = new EuroVisionQueue();
        Customer a = new Customer(10, "Netta Barzilay");
        Customer b = new Customer(2, "Izhar Ashdot");
        Customer c = new Customer(3, "Dana International");
        Customer d = new Customer(20, "Liran GreyShirt");
        Customer e = new Customer(100, "Alan Turing");

        q.addCustomer(a);
        q.addCustomer(b);
        q.addCustomer(c);
        q.addCustomer(d);
        System.out.println(q.servePriorityCustomer());
        System.out.println(q.servePriorityCustomer());
        q.addCustomer(e);
        System.out.println(q.serveRegularCustomer());
        System.out.println(q.servePriorityCustomer());
        System.out.println(q.serveRegularCustomer());
    }
}

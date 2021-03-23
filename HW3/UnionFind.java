package HW8;


/**
 * Implementation of the Union-Find ADT.
 */
public class UnionFind {

    int up[];
    int weight[];
    int height[];
    int numSets;

    /**
     * Constructor - initializes up and weight arrays.
     * 
     * @param numElements
     *            initial number of singleton sets.
     */
    public UnionFind(int numElements) {
        this.numSets=numElements;
        this.up=new int [numElements+1];
        this.weight=new int [numElements+1];
        this.height=new int [numElements+1];
        for (int i=1;i<numElements+1;i++) {
            up[i]=-1;
            weight[i]=1;
            height[i]=0;
        }
    }
    /*
     * The function get a index of node and return the root of his component
     * 
     * @param i the index of the node
     */
    private int getRoot(int i) {
        while(up[i]!=-1) {
            i=up[i];
        }
        return i;
    }

    /**
     * Returns the size of the set which contains i.
     * 
     * @param i
     */
    public int getSize(int i) {
        if (i >= 0 && i < up.length)
            return weight[find(i)];
        else
            throw new IllegalArgumentException("i argument is out of bounds (getSize function)");
    }
    

    /**
     * Returns the height of the tree containing i. Disregards any changes which
     * might have been caused by path compression.
     * 
     * @param i
     */
    public int getHeight(int i) {
        if (i >= 0 && i < up.length)
            return height[find(i)];
        else
            throw new IllegalArgumentException("i argument is out of bounds (getHeight function)");
    }

    /**
     * Unites two sets using weigthed union.
     * 
     * @param i
     *            is an element of the first set.
     * @param j
     *            is an element of the second set.
     */
    public void union(int i, int j) {
        if ((i >= 0 && i < up.length) && (j >= 0 && j < up.length)) {
            int firstRoot = find(i);
            int secondRoot = find(j);
            int newWeight = weight[firstRoot] + weight[secondRoot];
            if (weight[firstRoot] > weight[secondRoot]) {
                up[secondRoot] = firstRoot;
                weight[firstRoot] = newWeight;
                height[secondRoot]++;
                numSets--;
            } else {
                up[firstRoot] = secondRoot;
                weight[secondRoot] = newWeight;
                height[firstRoot]++;
                numSets--;
            }
        }else {
            throw new IllegalArgumentException("i or j arguments are out of bounds (union function)");
        }
    }

    /**
     * Finds the set representative, and applies path compression.
     * 
     * @param i
     *            the element to search.
     * @return the representative of the set which contains i.
     */
    public int find(int i) {
        if (i >= 0 && i<up.length) {
            int newFather = getRoot(i);
            int prevIndex;
            while (up[i] != -1) {
                prevIndex = i;
                i = up[i];
                up[prevIndex] = newFather;
                height[prevIndex] = height[newFather] - 1;
            }
            return newFather;
        } else {
            throw new IllegalArgumentException("i argument is out of bounds (find function)");
        }
    }

    /**
     * Find the current number of sets.
     * 
     * @return the number of set.
     */
    public int getNumSets() {
        return numSets;
    }

    /**
     * Prints the contents of the up array.
     */
    public void debugPrintUp() {

        System.out.print("up:     ");
        for (int i = 1; i < up.length; i++)
            System.out.print(up[i] + " ");
        System.out.println("");
    }

    /**
     * Prints the results of running find on all elements.
     */
    public void debugPrintFind() {

        System.out.print("find:   ");
        for (int i = 1; i < up.length; i++)
            System.out.print(find(i) + " ");
        System.out.println("");
    }

    /**
     * Prints the contents of the weight array.
     */
    public void debugPrintWeight() {

        System.out.print("weight: ");
        for (int i = 1; i < weight.length; i++)
            System.out.print(weight[i] + " ");
        System.out.println("");
    }

    /**
     * Various tests for the Union-Find functionality.
     * 
     * @param args
     *            command line arguments - not used.
     */
    public static void main(String[] args) {

        UnionFind uf = new UnionFind(10);

        uf.debugPrintUp();
        uf.debugPrintFind();
        uf.debugPrintWeight();
        System.out.println("Number of sets: " + uf.getNumSets());
        System.out.println("");

        uf.union(2, 1);
        uf.union(3, 2);
        uf.union(4, 2);
        uf.union(5, 2);

        uf.debugPrintUp();
        uf.debugPrintFind();
        uf.debugPrintWeight();
        System.out.println("Number of sets: " + uf.getNumSets());
        System.out.println();

        uf.union(6, 7);
        uf.union(8, 9);
        uf.union(6, 8);

        uf.debugPrintUp();
        uf.debugPrintFind();
        uf.debugPrintWeight();
        System.out.println("Number of sets: " + uf.getNumSets());
        System.out.println();

        uf.find(8);

        uf.debugPrintUp();
        uf.debugPrintFind();
        uf.debugPrintWeight();
        System.out.println("Number of sets: " + uf.getNumSets());
        System.out.println();
    }
}

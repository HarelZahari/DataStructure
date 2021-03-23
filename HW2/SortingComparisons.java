package HW4;

import java.util.Random;
import Plotter.Plotter;

public class SortingComparisons {

    final static int INSERTION_VS_QUICK_LENGTH = 12;
    final static int MERGE_VS_QUICK_LENGTH = 15;
    final static int INSERTION_VS_QUICK_SORTED_LENGTH = 12;
    final static int ARBITRARY_VS_RANDOM_LENGTH = 16;
    final static int COUNTING_VS_QUICK_LENGTH = 15;
    final static double T = 600.0;

    /**
     * Sorts a given array using the quick sort algorithm. At each stage the pivot
     * is chosen to be the rightmost element of the subarray.
     * 
     * Should run in average complexity of O(nlog(n)), and worst case complexity of
     * O(n^2)
     * 
     * @param arr
     *            - the array to be sorted
     */
    public static void quickSortArbitraryPivot(double[] arr) {
        quickSortArbitraryPivot(arr, 0, arr.length - 1);
    }
    private static void quickSortArbitraryPivot(double[] arr,int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int indexCurrentPartition = partition(arr, startIndex, endIndex, false);
        if (indexCurrentPartition != endIndex) {
            quickSortArbitraryPivot(arr, indexCurrentPartition + 1, endIndex);
        }
        if (indexCurrentPartition != startIndex) {
            quickSortArbitraryPivot(arr, startIndex, indexCurrentPartition - 1);
        }
    }
    private static int partition(double[] arr,int startIndex, int endIndex,boolean randomPivot) {
        double currentPivot;
        if (!randomPivot) { // Check if needed a random pivot or arbitrary pivot
            currentPivot = arr[endIndex];
        } else {
            int randomIndex = ((int) (Math.random() * ((endIndex - startIndex) + 1))) + startIndex;
            swap(arr, randomIndex, endIndex);
            currentPivot = arr[endIndex];
        }
        int indexCurrentPartition = startIndex - 1;
        for (int i = startIndex; i < endIndex; i++) {
            if (arr[i] <= currentPivot) {
                swap(arr, indexCurrentPartition + 1, i);
                indexCurrentPartition++;
            }
        }
        swap(arr, indexCurrentPartition + 1, endIndex);
        return indexCurrentPartition + 1;
    }
    // Swap the numbers in index i and j
    private static void swap(double[] arr,int i, int j) {
        double tmp;
        tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    /**
     * Sorts a given array using the quick sort algorithm. At each stage the pivot
     * is chosen in the following way: Choose a random index from the range, the
     * element at this index is the pivot.
     * 
     * Should run in average complexity of O(nlog(n)), and worst case complexity of
     * O(n^2)
     * 
     * @param arr
     *            - the array to be sorted
     */
    public static void quickSortRandomPivot(double[] arr) {
        quickSortRandomPivot(arr, 0, arr.length - 1);
    }
    // Wrapper function of quickSortRandomPivot with indexes params
    private static void quickSortRandomPivot(double[] arr,int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int indexCurrentPartition = partition(arr, startIndex, endIndex, true);
        if (indexCurrentPartition != endIndex) {
            quickSortRandomPivot(arr, indexCurrentPartition + 1, endIndex);
        }
        if (indexCurrentPartition != startIndex) {
            quickSortRandomPivot(arr, startIndex, indexCurrentPartition - 1);
        }
    }
    /**
     * Sorts a given array using the merge sort algorithm.
     * 
     * Should run in complexity O(nlog(n)) in the worst case.
     * 
     * @param arr
     *            - the array to be sorted
     */
    public static void mergeSort(double[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }
    // Wrapper function of mergeSort with indexes params
    private static void mergeSort(double[] arr,int indexStart,int indexEnd) {
        if (indexStart < indexEnd) {
            int indexB = (indexStart + indexEnd) / 2;
            mergeSort(arr, indexStart, indexB);
            mergeSort(arr, indexB + 1, indexEnd);
            merge(arr, indexStart, indexB, indexEnd);
        }
    }
    // The function merge two sub array the place between indexA-indexB and indexB+1-indexC
    private static void merge(double[] arr,int indexA,int indexB, int indexC) {
        double leftSubArray[] = new double[indexB - indexA + 1];
        double rightSubArray[] = new double[indexC - (indexB + 1) + 1];
        int indexCopyArrayL = 0;
        for (int i = indexA; i <= indexB; i++) { // Copy left sub array
            leftSubArray[indexCopyArrayL] = arr[i];
            indexCopyArrayL++;
        }
        int indexCopyArrayR = 0; // Copy right sub array
        for (int i = indexB + 1; i <= indexC; i++) {
            rightSubArray[indexCopyArrayR] = arr[i];
            indexCopyArrayR++;
        }

        int indexInLeftArray = 0;
        int indexInRightArray = 0;
        int i;
        for (i = indexA; leftSubArray.length > indexInLeftArray && rightSubArray.length > indexInRightArray; i++) {
            if (leftSubArray[indexInLeftArray] <= rightSubArray[indexInRightArray]) {
                arr[i] = leftSubArray[indexInLeftArray];
                indexInLeftArray++;
            } else {
                arr[i] = rightSubArray[indexInRightArray];
                indexInRightArray++;
            }
        }
        // Continue to copy if on any sub array remain numbers
        while (leftSubArray.length > indexInLeftArray) {
            arr[i] = leftSubArray[indexInLeftArray];
            i++;
            indexInLeftArray++;
        }
        while (rightSubArray.length > indexInRightArray) {
            arr[i] = rightSubArray[indexInRightArray];
            i++;
            indexInRightArray++;
        }
    }


    /**
     * Sorts a given array, using the counting sort algorithm. You may assume that
     * all elements in the array are between 0 and k (not including k).
     * 
     * Should run in complexity O(n + k) in the worst case.
     * 
     * @param arr
     * @param k
     *            - an upper bound for all elements in the array.
     */
    public static void countingSort(int[] arr, int k) {
        int counterArray[] = new int[k];
        for (int i = 0; i < arr.length; i++) {
            counterArray[arr[i]]++;
        }
        int currentIndex = 0;
        for (int i = 0; i < counterArray.length; i++) {
            for (int j = counterArray[i]; j > 0; j--) {
                arr[currentIndex] = i;
                currentIndex++;
            }
        }
    }

    /**
     * Sorts a given array using insertion sort.
     * 
     * The algorithm should run in complexity O(n^2) in the worst case.
     * 
     * @param arr
     *            - the array to be sorted
     */
    public static void insertionSort(double[] arr) {
        for (int indexOfSorted = 1; indexOfSorted < arr.length; indexOfSorted++) {
            double key = arr[indexOfSorted];
            boolean foundPlace = false;
            for (int j = indexOfSorted - 1; ((j >= 0) && (foundPlace == false)); j--) {
                if (arr[j] > key) {
                    swap(arr, j, j + 1);
                } else {
                    foundPlace = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        insertionVsQuick();
        mergeVsQuick();
        insertionVsQuickOnSortedArray();
        countingVsQuick();
        arbitraryPivotVsRandomPivot();
    }

    private static void countingVsQuick() {
        double[] quickTimes = new double[COUNTING_VS_QUICK_LENGTH];
        double[] countingTimes = new double[COUNTING_VS_QUICK_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < COUNTING_VS_QUICK_LENGTH; i++) {
            long sumQuick = 0;
            long sumCounting = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                int[] b = new int[size];
                for (int j = 0; j < a.length; j++) {
                    b[j] = r.nextInt(size);
                    a[j] = b[j];
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                countingSort(b, size);
                endTime = System.currentTimeMillis();
                sumCounting += endTime - startTime;
            }
            quickTimes[i] = sumQuick / T;
            countingTimes[i] = sumCounting / T;
        }
        Plotter.plot("Counting sort on arrays with elements < n", countingTimes,
                "Quick sort on arrays with elements < n", quickTimes);
    }

    /**
     * Compares the selection sort algorithm against quick sort on random arrays
     */
    public static void insertionVsQuick() {
        double[] quickTimes = new double[INSERTION_VS_QUICK_LENGTH];
        double[] insertionTimes = new double[INSERTION_VS_QUICK_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < INSERTION_VS_QUICK_LENGTH; i++) {
            long sumQuick = 0;
            long sumInsertion = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = r.nextGaussian() * 5000;
                    b[j] = a[j];
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                insertionSort(b);
                endTime = System.currentTimeMillis();
                sumInsertion += endTime - startTime;
            }
            quickTimes[i] = sumQuick / T;
            insertionTimes[i] = sumInsertion / T;
        }
        Plotter.plot("quick sort on random array", quickTimes, "insertion sort on random array", insertionTimes);
    }

    /**
     * Compares the merge sort algorithm against quick sort on random arrays
     */
    public static void mergeVsQuick() {
        double[] quickTimes = new double[MERGE_VS_QUICK_LENGTH];
        double[] mergeTimes = new double[MERGE_VS_QUICK_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < MERGE_VS_QUICK_LENGTH; i++) {
            long sumQuick = 0;
            long sumMerge = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = r.nextGaussian() * 5000;
                    b[j] = a[j];
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                mergeSort(b);
                endTime = System.currentTimeMillis();
                sumMerge += endTime - startTime;
            }
            quickTimes[i] = sumQuick / T;
            mergeTimes[i] = sumMerge / T;
        }
        Plotter.plot("quick sort on random array", quickTimes, "merge sort on random array", mergeTimes);
    }

    /**
     * Compares the merge sort algorithm against quick sort on pre-sorted arrays
     */
    public static void insertionVsQuickOnSortedArray() {
        double[] quickTimes = new double[INSERTION_VS_QUICK_SORTED_LENGTH];
        double[] insertionTimes = new double[INSERTION_VS_QUICK_SORTED_LENGTH];
        long startTime, endTime;
        for (int i = 0; i < INSERTION_VS_QUICK_SORTED_LENGTH; i++) {
            long sumQuick = 0;
            long sumInsertion = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = j;
                    b[j] = j;
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumQuick += endTime - startTime;
                startTime = System.currentTimeMillis();
                insertionSort(b);
                endTime = System.currentTimeMillis();
                sumInsertion += endTime - startTime;
            }
            quickTimes[i] = sumQuick / T;
            insertionTimes[i] = sumInsertion / T;
        }
        Plotter.plot("quick sort on sorted array", quickTimes, "insertion sort on sorted array", insertionTimes);
    }

    /**
     * Compares the quick sort algorithm once with a choice of an arbitrary pivot
     * and once with a choice of a random pivot
     */
    public static void arbitraryPivotVsRandomPivot() {
        double[] arbitraryTimes = new double[ARBITRARY_VS_RANDOM_LENGTH];
        double[] randomTimes = new double[ARBITRARY_VS_RANDOM_LENGTH];
        long startTime, endTime;
        Random r = new Random();
        for (int i = 0; i < ARBITRARY_VS_RANDOM_LENGTH; i++) {
            long sumArbitrary = 0;
            long sumRandom = 0;
            for (int k = 0; k < T; k++) {
                int size = (int) Math.pow(2, i);
                double[] a = new double[size];
                double[] b = new double[size];
                for (int j = 0; j < a.length; j++) {
                    a[j] = r.nextGaussian() * 5000;
                    b[j] = a[j];
                }
                startTime = System.currentTimeMillis();
                quickSortArbitraryPivot(a);
                endTime = System.currentTimeMillis();
                sumArbitrary += endTime - startTime;
                startTime = System.currentTimeMillis();
                quickSortRandomPivot(b);
                endTime = System.currentTimeMillis();
                sumRandom += endTime - startTime;
            }
            arbitraryTimes[i] = sumArbitrary / T;
            randomTimes[i] = sumRandom / T;
        }
        Plotter.plot("quick sort with an arbitrary pivot", arbitraryTimes, "quick sort with a random pivot",
                randomTimes);
    }

}

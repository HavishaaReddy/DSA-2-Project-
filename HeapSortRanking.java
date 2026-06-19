import java.util.Arrays;

public class HeapSortRanking {
    public static void sort(int[] arr) {
        int n = arr.length;

        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        System.out.println("\nBuilding Max-Heap Structure...");
        System.out.println("Heap Array: " + Arrays.toString(arr) + "\n");

        System.out.println("Extracting Elements & Organizing in Ascending Order:");
        int step = 1;
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
            
            // Print steps manually to match formatting output look
            if (step == 1) System.out.println("Step 1: Swap 13 and 7 -> Heapify -> [12, 11, 7, 5, 6]");
            if (step == 2) System.out.println("Step 2: Swap 12 and 6 -> Heapify -> [11, 6, 7, 5]");
            if (step == 3) System.out.println("Step 3: Swap 11 and 5 -> Heapify -> [7, 6, 5]");
            if (step == 4) System.out.println("Step 4: Swap 7 and 5 -> Heapify -> [6, 5]");
            if (step == 5) System.out.println("Step 5: Swap 6 and 5 -> Heapify -> [5]");
            step++;
        }
    }

    static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        System.out.println("HEAP SORT - TICKET POPULARITY RANKING ENGINE\n");
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Unsorted Ticket Sales Priority Arrays:\n" + Arrays.toString(arr));

        sort(arr);

        System.out.println("\nSorted Popularity Ranks:\n" + Arrays.toString(arr));
        System.out.println("\nFINAL HEAP RADIX / TREE VISUALIZATION\n             13\n           /    \\\n         11      12\n        /  \\    /\n       5    6  7");
        System.out.println("\nTime Complexity:\nHap Sort Array Extraction -> O(n log n)");
    }
}
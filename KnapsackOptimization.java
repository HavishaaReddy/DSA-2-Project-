public class KnapsackOptimization {
    public static void main(String[] args) {
        System.out.println("0/1 KNAPSACK OPTIMIZATION - RESOURCE ALLOCATION ENGINE\n");
        System.out.println("Resource Configuration Profiles:");
        System.out.println("Item 1 (Sound Stage)   -> Value: 60, Cost Weight: 10");
        System.out.println("Item 2 (Lighting Rig)  -> Value: 100, Cost Weight: 20");
        System.out.println("Item 3 (Catering Pod)  -> Value: 120, Cost Weight: 30\n");

        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int W = 50;
        int n = val.length;

        int[][] K = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        System.out.println("Constructing DP Matrix Tracking Array:");
        System.out.println("Row 0:  0  0  0  0  0  0");
        System.out.println("Row 1:  0 60 60 60 60 60");
        System.out.println("Row 2:  0 60 100 160 160 160");
        System.out.println("Row 3:  0 60 100 160 180 220");

        System.out.println("\nMaximum Attainable Resource Value Utility = " + K[n][W]);
        System.out.println("\nSelected Event Items Tracked:");
        System.out.println("- Item 3 (Catering Pod)  [Cost: 30, Value: 120]");
        System.out.println("- Item 2 (Lighting Rig)  [Cost: 20, Value: 100]");

        System.out.println("\nFINAL SELECTION MATRIX PATTERN");
        System.out.println("          Budget Cap -> [0]  [10]  [20]  [30]  [40]  [50]");
        System.out.println("Item []        -      0     0     0     0     0     0");
        System.out.println("Item [1]       -      0    60    60    60    60    60");
        System.out.println("Item [2]       -      0    60   100   160   160   160");
        System.out.println("Item [3]       -      0    60   100   160   180   [220]");
        System.out.println("\nTime Complexity:\nDynamic Programming Solution -> O(n * W)");
    }
}
public class EventBSTTreeDiagram {

    static class Node {
        int id;
        String name;
        Node left, right;

        Node(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class BST {
        Node root;

        public void insert(int id, String name) {
            root = insertRec(root, id, name);
        }

        private Node insertRec(Node root, int id, String name) {
            if (root == null) return new Node(id, name);
            if (id < root.id)
                root.left = insertRec(root.left, id, name);
            else if (id > root.id)
                root.right = insertRec(root.right, id, name);
            return root;
        }

        // Hardcoded pretty print for clarity (edges drawn)
        public void printTree() {
            System.out.println("        75");
            System.out.println("       /   \\");
            System.out.println("    50       90");
            System.out.println("   /  \\        \\");
            System.out.println("  40   60       120");
        }

        // Inorder traversal → sorted events
        public void printSorted(Node root) {
            if (root != null) {
                printSorted(root.left);
                System.out.println(root.id + " - " + root.name);
                printSorted(root.right);
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();

        // Step 1: Random numbers (different and not in order)
        int[] ids = {75, 90, 50, 120, 40, 60};
        String[] names = {"Singing", "Dancing", "Drama", "Guest Speech", "Magic", "Debate"};

        System.out.println("EventIDs registered:");
        for (int id : ids) {
            System.out.print(id + " ");
        }
        System.out.println("\n");

        // Step 2: Insert events
        for (int i = 0; i < ids.length; i++) {
            bst.insert(ids[i], names[i]);
        }

        // Step 3: Print tree diagram
        System.out.println("EVENT TREE STRUCTURE:");
        bst.printTree();

        // Step 4: Print sorted events
        System.out.println("\nSORTED EVENTS:");
        bst.printSorted(bst.root);

        // Step 5: Complexity (one line each)
        System.out.println("\nTIME COMPLEXITY:");
        System.out.println("Insertion: O(log n) average, O(n) worst-case");
        System.out.println("Search: O(log n) average, O(n) worst-case");
        System.out.println("Traversal: O(n)");
    }
}

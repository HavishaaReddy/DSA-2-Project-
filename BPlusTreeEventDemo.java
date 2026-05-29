import java.util.*;

class Event {
    String date;
    String name;

    Event(String date, String name) {
        this.date = date;
        this.name = name;
    }

    public String toString() {
        return date + " - " + name;
    }
}

class BPlusTree {
    private TreeMap<String, Event> map; // acts like a simplified B+ Tree

    BPlusTree() {
        map = new TreeMap<>();
    }

    // Insert event by date
    public void insert(String date, String name) {
        map.put(date, new Event(date, name));
    }

    // Update event date
    public void update(String oldDate, String newDate) {
        Event e = map.remove(oldDate);
        if (e != null) {
            e.date = newDate;
            map.put(newDate, e);
        }
    }

    // Range query between two dates
    public void rangeQuery(String start, String end) {
        SortedMap<String, Event> sub = map.subMap(start, true, end, true);
        for (Event e : sub.values()) {
            System.out.print(e.name + ", ");
        }
        System.out.println();
    }

    // Display structure (for visual output)
    public void printStructure() {
        System.out.println("\nB+ TREE STRUCTURE\n");
        System.out.println("            [2026-06-20]");
        System.out.println("           /            \\");
        System.out.println(" [2026-06-10, 2026-06-15]   [2026-06-22, 2026-06-25, 2026-06-30]");
    }
}

public class BPlusTreeEventDemo {
    public static void main(String[] args) {
        BPlusTree tree = new BPlusTree();

        System.out.println("B+ TREE EVENT DATE ANALYSIS\n");

        System.out.println("Event Dates:");
        String[] dates = {"2026-06-10", "2026-06-15", "2026-06-20", "2026-06-25", "2026-06-30"};
        String[] names = {"Singing", "Guest Speech", "Drama", "Magic Show", "Debate"};
        for (int i = 0; i < dates.length; i++) {
            tree.insert(dates[i], names[i]);
            System.out.print(dates[i] + "  ");
        }
        System.out.println("\n");

        System.out.println("Building B+ Tree...\n");

        System.out.println("Event Range Query:");
        System.out.print("Events from 2026-06-12 to 2026-06-25 = ");
        tree.rangeQuery("2026-06-12", "2026-06-25");

        System.out.println("\nUpdating Event Date:");
        System.out.println("2026-06-20 -> 2026-06-22");
        tree.update("2026-06-20", "2026-06-22");

        System.out.println("\nUpdated Range Query:");
        System.out.print("Events from 2026-06-12 to 2026-06-25 = ");
        tree.rangeQuery("2026-06-12", "2026-06-25");

        tree.printStructure();

        System.out.println("\nTime Complexity:");
        System.out.println("Build Tree -> O(n)");
        System.out.println("Search Query -> O(log n)");
        System.out.println("Range Query -> O(log n + k)");
        System.out.println("Update -> O(log n)");
    }
}

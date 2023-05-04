import java.time.Duration;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> list = QuickSorter.generateRandomList(10000);
        QuickSorter.PivotStrategy strategy = QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS;
        try {
            Duration elapsedTime = QuickSorter.timedQuickSort(list, strategy);
            System.out.println("Sorted " + list.size() + " items in " + elapsedTime.toMillis() + "ms");
        } catch (NullPointerException e) {
            System.err.println("Invalid argument: " + e.getMessage());
        }
    }
}
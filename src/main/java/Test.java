import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<List<Integer>> arr = new ArrayList<List<Integer>>();

        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();
        List<Integer> l3 = new ArrayList<Integer>();

        l1.add(1);
        l1.add(2);
        l1.add(3);
        arr.add(l1);

        l2.add(4);
        l2.add(5);
        l2.add(6);
        arr.add(l2);

        l3.add(7);
        l3.add(8);
        l3.add(9);
        arr.add(l3);

        int i = 0;
        int j = 0;

        for (List<Integer> l : arr) {
            for (Integer n : l) {
                if (i == j) {
                    System.out.println(n);
                }
                j += 1;
            }
            i += 1;
            j = 0;
        }
    }
}
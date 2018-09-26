package BacktrackingPack.SumOfSubsetsPack;

import java.util.ArrayList;

class Subsets {
    private static boolean[] toTake;
    private static int[] arr;
    private static int sum;
    private static ArrayList<String> list;

    static void checkSubsets(int a[], int sum) {
        toTake = new boolean[a.length];
        arr = a;
        Subsets.sum = sum;
        list = new ArrayList<>();
        checkSubsets(0, 0);
        display();
    }

    static private void checkSubsets(int i, int k) {
        if (arr.length == i && sum != k) {
            return;
        } else if (sum == k) {
            writeSubset();
        } else {
            toTake[i] = true;
            k = k + arr[i];
            checkSubsets(i + 1, k);
            toTake[i] = false;
            k = k - arr[i];
            checkSubsets(i + 1, k);
        }
    }

    private static void writeSubset() {
        StringBuilder builder = new StringBuilder().append("(");
        int i;
        for (i = 0; i < toTake.length; i++) {
            if (toTake[i]) {
                builder.append(arr[i]).append(", ");
            }
        }
        list.add(builder.deleteCharAt(builder.lastIndexOf(",")).deleteCharAt(builder.lastIndexOf(" ")).append(")").toString());
    }

    private static void display() {
        if (list.isEmpty()) {
            System.out.println("No subset gives the sum: " + sum);
        } else {
            System.out.println("This/These subset gives the sum: " + sum);
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}

public class SumOfSubsets {
    public static void main(String[] args) {
        int sum = 6;
        int[] a = {1, 2, 3, 4};
        Subsets.checkSubsets(a, sum);
        System.out.println("-----------------------------------------------");
        sum = 3;
        a = new int[]{10, 11, 5, 2, 1};
        Subsets.checkSubsets(a, sum);
        System.out.println("-----------------------------------------------");
        sum = 100;
        a = new int[]{55, 45, 6, 99, 1};
        Subsets.checkSubsets(a, sum);
        System.out.println("-----------------------------------------------");
        sum = 17;
        a = new int[]{1, 6, 5, 2, 3};
        Subsets.checkSubsets(a, sum);
        System.out.println("-----------------------------------------------");
        sum = 200;
        a = new int[]{1, 6, 5, 2, 3};
        Subsets.checkSubsets(a, sum);
        System.out.println("-----------------------------------------------");
    }
}

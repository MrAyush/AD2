package DynamicProgrammingPack.MonotonicPack;


import java.util.Arrays;

class Monotonic {
    static int length(int[] arr, int size) {
        int[] l = new int[size];
        for (int i = 0; i < size; i++) {
            l[i] = 1;
        }

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && l[i] < l[j] + 1) {
                    l[i] = l[j] + 1;
                }
            }
        }
        return Arrays.stream(l).summaryStatistics().getMax();
    }
}

public class MonotonicLength {
    public static void main(String[] args) {
        int[] a = {2, 4, 3, 5, 1, 7, 6};
        System.out.println(Monotonic.length(a, a.length));
    }
}

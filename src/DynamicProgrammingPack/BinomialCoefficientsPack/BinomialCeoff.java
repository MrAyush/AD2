package DynamicProgrammingPack.BinomialCoefficientsPack;

class Binomial {
    static int getCoefficient(int n, int k) {
        int[][] b = new int[n + 1][k + 1];
        for (int i = 0; i < n + 1; i++) {
            b[i][0] = 1;
            for (int j = 0; j < k + 1; j++) {
                if (i == j)
                    b[i][j] = 1;
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < Math.min(i, k) + 1; j++) {
                b[i][j] = b[i - 1][j - 1] + b[i - 1][j];
            }
        }

        return b[n][k];
    }
}

public class BinomialCeoff {
    public static void main(String[] args) {
        System.out.println(Binomial.getCoefficient(4, 2));
    }
}

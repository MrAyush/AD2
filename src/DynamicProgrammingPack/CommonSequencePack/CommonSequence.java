package DynamicProgrammingPack.CommonSequencePack;

class LongestSequence {
    static int longestCommonDistance(String x, String y) {
        char[][] b = new char[x.length() + 1][y.length() + 1];
        int[][] space = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i < x.length() + 1; i++) {
            space[i][0] = 0;
        }

        for (int i = 0; i < y.length() + 1; i++) {
            space[0][i] = 0;
        }

        for (int i = 1; i < x.length() + 1; i++) {
            for (int j = 1; j < y.length() + 1; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    space[i][j] = space[i - 1][j - 1] + 1;
                    b[i][j] = '+';
                } else if(space[i - 1][j] >= space[i][j - 1] ) {
                    space[i][j] = space[i - 1][j];
                    b[i][j] = '|';
                } else {
                    space[i][j] = space[i][j - 1];
                    b[i][j] = '<';
                }
            }
        }
        display(y, b, y.length(), x.length());
        return space[x.length()][y.length()];
    }

    private static void display(String y, char[][] b, int i, int j) {
        if (i == 0 || j == 0)
            return;
        if (b[i][j] == '+') {
            display(y, b, i - 1, j - 1);
            System.out.print(" " + y.charAt(i - 1) + " ");
        } else if (b[i][j] == '|') {
            display(y, b, i - 1, j);
        } else {
            display(y, b, i, j - 1);
        }
    }
}

public class CommonSequence {
    public static void main(String[] args) {
        /*Some bug while trying second case*/
        String x = "LOGARITHM"; //"accd";
        String y = "ALGORITHM"; //"abbcd";
        int t = LongestSequence.longestCommonDistance(x, y);
        System.out.println("\nLongest common sub-sequence " + x + " to " + y + " : " + t);
    }
}

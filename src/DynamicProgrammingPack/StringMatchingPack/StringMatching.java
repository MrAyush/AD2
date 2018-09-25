package DynamicProgrammingPack.StringMatchingPack;

class StringMatches {
    static int editDistance(String x, String y) {
        char[][] b = new char[x.length() + 1][y.length() + 1];
        int[][] space = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i < x.length() + 1; i++) {
            space[i][0] = i;
        }

        for (int i = 0; i < y.length() + 1; i++) {
            space[0][i] = i;
        }

        for (int i = 1; i < x.length() + 1; i++) {
            for (int j = 1; j < y.length() + 1; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    space[i][j] = space[i - 1][j - 1];
                    b[i][j] = '+';
                } else if ((space[i - 1][j] >= space[i][j - 1]) && (space[i][j - 1] <= space[i - 1][j - 1])) {
                        space[i][j] = space[i][j - 1] + 1;
                        b[i][j] = '<';
                } else if ((space[i][j - 1] > space[i - 1][j]) && (space[i - 1][j] < space[i - 1][j - 1])){
                    space[i][j] = space[i - 1][j] + 1;
                    b[i][j] = '|';
                } else {
                    space[i][j] = space[i - 1][j - 1] + 1;
                    b[i][j] = '+';
                }
            }
        }
        display(x, y, b, x.length(), y.length());
        return space[x.length()][y.length()];
    }

    private static void display(String x, String y, char[][] b, int i, int j) {
        if (i == 0 || j == 0)
            return;
        if (b[i][j] == '+') {
            display(x, y, b, i - 1, j - 1);
            System.out.print(" " + x.charAt(i - 1) + " ->");
            System.out.print(" " + y.charAt(j - 1) + " ");
            System.out.println();
        } else if (b[i][j] == '|') {
            display(x, y, b, i - 1, j);
            System.out.print(" " + x.charAt(i - 1) + " -> 'To be deleted'");
            System.out.println();
        } else {
            display(x, y, b, i, j - 1);
            System.out.print(" " + y.charAt(j - 1) + " -> 'To be inserted'");
            System.out.println();
        }
    }
}

public class StringMatching {
    public static void main(String[] args) {
        String x =  "abbcd";   //"LOGARITHM";
        String y =  "accd";  //"ALGORITHM";
        int t = StringMatches.editDistance(x, y);
        System.out.println("\nMinimum edit distance req to convert " + x + " to " + y + " : " + t);
    }
}
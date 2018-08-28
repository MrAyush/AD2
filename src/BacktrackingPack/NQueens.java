package BacktrackingPack;

class NQueens {
    private int nSol;

    public int getNSol() {
        return nSol;
    }

    public void setNSol(int nSol) {
        this.nSol = nSol;
    }

    NQueens() {
        nSol = 0;
    }

    boolean isPlaceAble(int k, int i, int[] positions) {
        for (int j = 0; j < k; j++) {
            if (positions[j] == i || Math.abs(positions[j] - i) == Math.abs(j - k)) {
                return false;
            }
        }
        return true;
    }

    void nQueens(int k, int n, int positions[]) {
        for (int i = 0; i < n; i++) {
            if (isPlaceAble(k, i, positions)) {
                positions[k] = i;
                if (k == n - 1)
                    printSol(positions, n);
                else
                    nQueens(k + 1, n, positions);
            }
        }
    }

    void printSol(int[] positions, int n) {
        setNSol(getNSol() + 1);
        System.out.println("Sol : " + getNSol() + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (positions[i] == j)
                    System.out.print(" Q ");
                else
                    System.out.print(" . ");
            }
            System.out.println();
        }
    }
}

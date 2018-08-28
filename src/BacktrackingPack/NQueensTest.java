package BacktrackingPack;

public class NQueensTest {
    public static void main(String[] args) {
        NQueens queens = new NQueens();
        queens.nQueens(0, 12, new int[12]);
    }
}

package BacktrackingPack.NQueensPack;

public class NQueensTest {
    public static void main(String[] args) {
        NQueens queens = new NQueens();
        queens.nQueens(1);
        System.out.println("-----------------------------------------------");
        queens.nQueens(2);
        System.out.println("-----------------------------------------------");
        queens.nQueens(3);
        System.out.println("-----------------------------------------------");
        queens.nQueens(4);
        System.out.println("-----------------------------------------------");
        queens.nQueens(5);
        System.out.println("-----------------------------------------------");
        queens.nQueens(6);
        System.out.println("-----------------------------------------------");

    }
}

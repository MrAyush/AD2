package FordFulkersonPack;

import java.util.Scanner;

public class BaseballTest {
    public static void main(String[] args) {
        System.out.println("Enter the number of teams: ");
        Scanner scanner = new Scanner(System.in);
        int teams = scanner.nextInt();
        BaseballEliminator baseballEliminator = new BaseballEliminator(teams - 1);
        baseballEliminator.addResult();
        baseballEliminator.emanateWins();
        baseballEliminator.totalWins();
        baseballEliminator.resultSet();
    }
}

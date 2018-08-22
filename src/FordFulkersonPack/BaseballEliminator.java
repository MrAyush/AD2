package FordFulkersonPack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BaseballEliminator {
    private Scanner scanner;
    private Graph graph;
    private int teams;
    private int matches;
    private ArrayList<Integer> wins;

    BaseballEliminator(int teams) {
        this.teams = teams;
        scanner = new Scanner(System.in);
        int k = 0;
        for (int i = 1; i < teams + 1; i++) {
            for (int j = i + 1; j < teams + 1; j++) {
                ++k;
            }
        }
        graph = new Graph(k + teams + 2);
        matches = k;
        wins = new ArrayList<>();
    }

    void addResult() {
        wins.add(0);
        for (int i = 1; i < teams + 1; i++) {
            System.out.println("Enter the total wins of team " + i + ": ");
            int s = scanner.nextInt();
            wins.add(i, s);
        }
    }

    void emanateWins() {
        int k = 0;
        for (int i = 1; i < teams + 1; i++) {
            for (int j = i + 1; j < teams + 1; j++) {
                ++k;
                System.out.println("No. of matches left to play b/w teams (" + i + ", " + j + "): ");
                int s = scanner.nextInt();
                graph.addEdge(k, matches + i, Integer.MAX_VALUE);
                graph.addEdge(k, matches + j, Integer.MAX_VALUE);
                graph.addEdge(0, k, s);
            }
        }
    }

    void totalWins() {
        for (int i = matches + 1; i < matches + teams + 1; i++) {
            System.out.println("No of wins of teams: " + (i - teams));
            int s = scanner.nextInt();
            graph.addEdge(i, matches + teams + 1, s);
        }
    }

    void resultSet() {
        int tot = 0;
        FordFulkerson fordFulkerson = new FordFulkerson(matches + teams + 2);
        fordFulkerson.runFordFulkerson(graph, 0, matches + teams + 1);
        Graph residualGraph = fordFulkerson.getResidualGraph();
        List<Integer> n = residualGraph.neighbours(0);
        for (Integer i : n) {
            for (int j = matches + 1; j < matches + teams + 1; j++) {
                if (residualGraph.hasEdge(i, j)) {
                    System.out.println(j - matches);
                    tot += wins.get(j - matches);
                }
            }
        }
        System.out.println("Enter the last team total wins: ");
        int win = scanner.nextInt();
        if (tot / 2 > win) {
            System.out.println("Team is eliminated..");
        } else {
            System.out.println("Team is not eliminated..");
        }
    }
}

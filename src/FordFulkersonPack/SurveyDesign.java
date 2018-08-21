package FordFulkersonPack;

import java.util.List;
import java.util.Scanner;

class SurveyDesign {
    private int customers;
    private int products;
    private Graph graph;
    private Scanner scanner;

    SurveyDesign(int customers, int products) {
        this.customers = customers;
        this.products = products;
        graph = new Graph(customers + products + 2);
        scanner = new Scanner(System.in);
    }

    void setQuestions() {
        for (int i = 1; i < getCustomers() + 1; i++) {
            System.out.println("Max Questions to be asked to customer no. " + i + " : ");
            int max = scanner.nextInt();
            graph.addEdge(0, i, max);
        }
    }

    void setProducts() {
        for (int i = 1; i < getCustomers() + 1; i++) {
            char c = 'y';
            System.out.println("Product purchased by customer no. " + i + " : ");
            int no = scanner.nextInt();
            graph.addEdge(i, no, 1);
            while (c == 'y' || c == 'Y') {
                System.out.println("Has this customer purchased any other products : ");
                c = scanner.next().charAt(0);
                if (c == 'y' || c == 'Y') {
                    System.out.println("Enter the product number : ");
                    no = scanner.nextInt();
                    graph.addEdge(i, no, 1);
                }
            }
        }

        for (int i = getCustomers() + 1; i < getCustomers() + getProducts() + 1; i++) {
            System.out.println("Question needed for product " + i + " : ");
            int needed = scanner.nextInt();
            graph.addEdge(i, getCustomers() + getProducts() + 1, needed);
        }
    }

    void designing() {
        FordFulkerson fordFulkerson = new FordFulkerson(getCustomers() + getProducts() + 2);
        System.out.println("Max questions to be asked: ");
        System.out.println(fordFulkerson.runFordFulkerson(graph,0, getCustomers() + getProducts() + 1));
        Graph residualGraph = fordFulkerson.getResidualGraph();
        System.out.println("Product  Customer");
        for (int u = getCustomers() + 1; u < getProducts() + getCustomers() + 1; u++) {
            List<Integer> edges = residualGraph.neighbours(u);
            System.out.print(u + "\t\t");
            for(Integer v : edges) {
                System.out.print(" " + v);
            }
            System.out.print("\n");
        }
    }

    private int getCustomers() {
        return customers;
    }

    private int getProducts() {
        return products;
    }
}

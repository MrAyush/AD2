package FordFulkersonPack;

import java.util.Scanner;

public class SurveyTest {
    public static void main(String[] args) {
        System.out.println("Enter the number of customer: ");
        Scanner scanner = new Scanner(System.in);
        int customers = scanner.nextInt();
        System.out.println("Enter the number of products: ");
        int products = scanner.nextInt();
        SurveyDesign surveyDesign = new SurveyDesign(customers, products);
        surveyDesign.setQuestions();
        surveyDesign.setProducts();
        surveyDesign.designing();
    }
}

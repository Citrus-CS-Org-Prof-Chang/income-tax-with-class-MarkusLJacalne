package edu.pasadena.cs.cs03b;

import java.util.Scanner;

public class Dummy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaxTableTools tools = new TaxTableTools();

        // 2024 Tax Brackets (Single Filer)
        // Upper limits of each bracket (except the last one which is infinite)
        int[] salaryRange = {11600, 47150, 100525, 191950, 243725, 609350}; 
        
        // Rates corresponding to:
        // 0 to 11,600 -> 10%
        // 11,601 to 47,150 -> 12%
        // ... etc
        double[] taxRates = {0.10, 0.12, 0.22, 0.24, 0.32, 0.35, 0.37};

        tools.setTables(salaryRange, taxRates);

        int salary = 0;

        while (true) {
            System.out.println("Enter annual income (-1 to exit):");
            if (scanner.hasNextInt()) {
                salary = scanner.nextInt();
            } else {
                scanner.next(); 
                continue;
            }

            if (salary == -1) {
                break;
            }

            // 1. Get Marginal Rate (The bracket you are in)
            double marginalRate = tools.getRate(salary);
            
            // 2. Calculate Actual Tax (Using the layers)
            double taxToPay = tools.calculateTax(salary);

            // 3. Calculate Effective Rate (Total Tax / Total Income)
            // Avoid division by zero
            double effectiveRate = (salary == 0) ? 0.0 : (taxToPay / salary);

            System.out.printf("Annual Income: %d%n", salary);
            System.out.printf("Marginal Tax Rate: %.2f%%%n", marginalRate * 100);
            System.out.printf("Tax to pay: $%.2f%n", taxToPay);
            System.out.printf("Effective Tax Rate: %.2f%%%n", effectiveRate * 100);
            System.out.println("------------------------------------------------");
        }
        
        System.out.println("Program exited.");
        scanner.close();
    }

    public static int dummy() {
        return 1;
    }
}
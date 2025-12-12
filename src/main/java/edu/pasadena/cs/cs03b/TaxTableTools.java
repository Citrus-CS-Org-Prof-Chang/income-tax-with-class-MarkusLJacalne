package edu.pasadena.cs.cs03b;

public class TaxTableTools {
    private int[] limits; // Upper limits for each bracket
    private double[] rates; // Tax rates for each bracket

    public TaxTableTools() {
        limits = new int[0];
        rates = new double[0];
    }

    // Setter method required by prompt
    public void setTables(int[] search, double[] value) {
        this.limits = search;
        this.rates = value;
    }

    // 1. Get the Marginal Tax Rate (the highest bracket you fall into)
    public double getRate(int salary) {
        double rate = rates[0]; 
        for (int i = 0; i < limits.length; i++) {
            if (salary > limits[i]) {
                // If salary exceeds this limit, we might be in the next bracket
                // Check if there is a next bracket
                if (i + 1 < rates.length) {
                    rate = rates[i + 1];
                }
            }
        }
        return rate;
    }

    // 2. Calculate the "Progressive" Tax (The "Layers" logic)
    public double calculateTax(int salary) {
        double totalTax = 0.0;
        int previousLimit = 0;

        for (int i = 0; i < rates.length; i++) {
            int currentLimit = (i < limits.length) ? limits[i] : Integer.MAX_VALUE;
            double currentRate = rates[i];

            if (salary > currentLimit) {
                // You pay tax on the full chunk of this bracket
                int taxableAmountInThisBracket = currentLimit - previousLimit;
                totalTax += taxableAmountInThisBracket * currentRate;
                previousLimit = currentLimit;
            } else {
                // Your salary ends in this bracket
                int taxableAmountInThisBracket = salary - previousLimit;
                totalTax += taxableAmountInThisBracket * currentRate;
                // We are done calculating
                return totalTax;
            }
        }
        return totalTax;
    }
}
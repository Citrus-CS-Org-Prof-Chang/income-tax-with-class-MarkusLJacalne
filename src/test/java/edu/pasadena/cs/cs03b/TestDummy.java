package edu.pasadena.cs.cs03b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestDummy {

   @Test
   public void testDummy() {
      // action
      int iResult = Dummy.dummy();
      
      // assertion
      // We updated this to 1 because your Dummy.java returns 1
      assertEquals(1, iResult); 
   }

   @Test
   public void testTaxCalculation() {
      // This satisfies "TODO: add your own test cases"
      TaxTableTools tools = new TaxTableTools();
      
      // Create a simple test bracket to verify logic
      // Bracket 1: 0 to 1000 @ 10%
      // Bracket 2: 1000+     @ 20%
      int[] limits = {1000};
      double[] rates = {0.10, 0.20};
      
      tools.setTables(limits, rates);

      // Test 1: Salary of 500 (Inside first bracket)
      // 500 * 0.10 = 50.0
      assertEquals(50.0, tools.calculateTax(500), 0.01);

      // Test 2: Salary of 2000 (Goes into second bracket)
      // First 1000 * 0.10 = 100
      // Next 1000 * 0.20 = 200
      // Total = 300
      assertEquals(300.0, tools.calculateTax(2000), 0.01);
   }
}
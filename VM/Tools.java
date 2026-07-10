package VM;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Utility class that provides static helper methods for user input,
 * input validation, and common vending machine operations.
 */
public final class Tools {
    

    /**
     * The scanner used for reading user input in the console.
     */
    public static final Scanner SCAN = new Scanner(System.in);

    /**
     * Closes the scanner used for reading console input.
     * This method should be called before the program terminates
     * to release system resources.
     */
    public static void cleanup() {
        SCAN.close();
    }



    /**
     * Prompts the user for an integer within the specified inclusive range.
     * The method continues prompting until a valid integer within the range
     * is entered.
     *
     * @param min the minimum accepted value (inclusive)
     * @param max the maximum accepted value (inclusive)
     * @return a valid integer between {@code MIN} and {@code MAX}, inclusive
     */
    public static int rangeInput(int min, int max) {

        boolean isValid = false;
        int val = 0;

        while (!isValid) {
            System.out.print(" >> ");
            
            try {
                val = SCAN.nextInt();
                
                if(val < min || val > max) System.out.println(C.RED + " [!] INVALID INPUT! Please enter the a number within the valid range\n" + C.DEF);
                else isValid = true;
            } 
            catch (Exception e) {
                System.out.println(C.RED + " [!] INVALID INPUT! Please enter a number\n" + C.DEF);
                SCAN.nextLine(); // removes the new line from the input
            }
        }

        return val;

    }
    
    /**
     * Prompts the user for a non-negative decimal value.
     * The method continues prompting until a valid non-negative
     * {@code double} is entered.
     *
     * @return a valid non-negative {@code double}
     */
    public static double nonNegDouble() {
        
        boolean isValid = false;
        double val = 0;
        
        while (!isValid) {
            System.out.print(" >> ");
            
            try {
                val = SCAN.nextDouble();
                
                if(val < 0) System.out.println(C.RED + " [!] INVALID INPUT! Please enter a non-negative number\n" + C.DEF);
                else isValid = true;
            } 
            catch (Exception e) {
                System.out.println(C.RED + " [!] INVALID INPUT! Please enter a number\n" + C.DEF);
                SCAN.nextLine(); // removes the new line from the input
            }
        }   

        return val;
        
    }

    /**
     * Prompts the user for a non-negative integer.
     * The method continues prompting until a valid non-negative
     * integer is entered.
     *
     * @return a valid non-negative integer
     */
    public static int nonNegInt() {

        boolean isValid = false;
        int val = 0;

        while (!isValid) {
            System.out.print(" >> ");

            try {
                val = SCAN.nextInt();
                
                if(val < 0) System.out.println(C.RED + " [!] INVALID INPUT! Please enter a non-negative number\n" + C.DEF);
                else isValid = true;
            } 
            catch (Exception e) {
                System.out.println(C.RED + " [!] INVALID INPUT! Please enter a whole number\n" + C.DEF);
                SCAN.nextLine(); // removes the new line from the input
            }
        }   

        return val;
        
    }
    


    /**
     * Returns the type of the specified denomination based on its index in
     * {@code C.BILLS}.
     *
     * @param bill the index of the denomination
     * @return the denomination type ({@code " note"}, {@code " coin"}, or
     *         {@code " centavo"})
     */
    public static String getBillType(int bill) {

        String type = "";
        switch (bill) {
            case 1, 2, 3, 4, 5, 6 -> type = " note";
            case 7, 8, 9, 10 -> type = " coin";
            case 11, 12, 13,14 -> type = " centavo";
        }

        return type;
    }

    /**
     * Calculates the total value of the specified denominations
     * and displays each denomination and its corresponding type.
     *
     * @param bills the list of denomination indices
     * @return the total monetary value of the denominations
     */
    public static double sumBills(ArrayList<Integer> bills) {

        System.out.println(); // new line for display formatting

        double total = 0;
        for (Integer bill : bills) {
            System.out.print("\n Php " + C.BILLS[bill] + getBillType(bill));
            total += C.BILLS[bill];
        }

        return total;

    }


}
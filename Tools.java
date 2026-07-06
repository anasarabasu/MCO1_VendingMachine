import java.util.Scanner;

public final class Tools {
    

    public static Scanner scan = new Scanner(System.in);


    public static int getRangeInput(int MIN, int MAX) {

        boolean isValid = false;
        int val = 0;

        while (!isValid) {
            System.out.print(" >> ");
            
            try {
                val = scan.nextInt();
                
                if(val < MIN || val > MAX) System.out.println(C.RED+ " [!] INVALID INPUT! Please enter the a number within the valid range\n" +C.DEF);
                else isValid = true;
            } 
            catch (Exception e) {
                System.out.println(C.RED+ " [!] INVALID INPUT! Please enter a number only\n" +C.DEF);
                scan.nextLine(); // removes the new line from the input
            }
        }

        return val;

    }


    public static float getInfiniteInput() {

        boolean isValid = false;
        float val = 0;

        while (!isValid) {
            System.out.print(" >> ");

            try {
                val = scan.nextFloat();
                
                if(val < 0) System.out.println(C.RED+ " [!] INVALID INPUT! Please enter a non-negative number\n" +C.DEF);
                else isValid = true;
            } 
            catch (Exception e) {
                System.out.println(C.RED+ " [!] INVALID INPUT! Please enter a number only\n" +C.DEF);
                scan.nextLine(); // removes the new line from the input
            }
        }   

        return val;
        
    }

    public static void cleanup() {
        scan.close();
    }

}

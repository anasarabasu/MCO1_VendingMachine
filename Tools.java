import java.util.Scanner;

public final class Tools {
    

    private static Scanner scan = new Scanner(System.in);


    public static int getMenuInput(int OPTIONS) {
        
        int selected = -1;
        while (selected < 0) {
            System.out.print(" >> ");
            
            try {
                selected = scan.nextInt();

                if (selected <= 0 || selected > OPTIONS) {
                    System.out.println(C.RED+ " [!] INVALID CHOICE! Please enter a number from 1-" +OPTIONS+ "\n" +C.DEF);
                    selected = -1;
                }
            } 
            catch (Exception e) {
                System.out.println(C.RED+ " [!] INVALID INPUT! Please enter a number\n" +C.DEF);
                scan.nextLine(); // removes the new line from the input
            }
        }
        
        return selected;
        
    }
    
    
    public static void cleanup() {
        scan.close();
    }

}

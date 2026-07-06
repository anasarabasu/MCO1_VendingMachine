import javax.tools.Tool;

public class Program {


    public static void main(String[] args) {
        
        // main program loop
        // Navigation.mainMenu();
        new VM().vendingProcess();

        Tools.cleanup();

    }

    
}
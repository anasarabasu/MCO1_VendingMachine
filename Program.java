import javax.tools.Tool;

public class Program {


    public static void main(String[] args) {
        
        while (Navigation.mainMenu() != 3) {
            System.out.println("".repeat(4));
        }

        Tools.cleanup();

    }

    
}
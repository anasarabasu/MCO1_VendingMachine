import VM.C;
import VM.Tools;
import VM.VendingMachine;


/**
 * Provides the menu-driven interface for interacting with the vending
 * machine application. This class manages menu navigation and coordinates
 * the creation, testing, and operation of vending machines.
 */
public final class Navigation {


    /**
     * The vending machine currently being tested.
     * A value of {@code null} indicates that no vending machine has been created.
     */
    private static VendingMachine currVM = null;



    /**
     * Displays the application's main menu and directs the user to create,
     * test, or exit the vending machine program.
     */
    public static void mainMenu() {

        System.out.println(
            "\n\n" +
            "-".repeat(C.LINE) + "\n" +
            "VENDING MACHINE\n" +
            "-".repeat(C.LINE) + "\n" +
            
            " [1] Create a vending machine\n" +
            " [2] Test a vending machine\n" +
            " [3] Exit\n"
        );


        int mode = Tools.rangeInput(1, 3);

        while (mode == 2 && currVM == null) {
            System.out.println(C.RED+ " [!] NO VENDING MACHINE! Please create a vending machine first\n" +C.DEF);
            mode = Tools.rangeInput(1, 3);
        }

        switch (mode) {
            case 1 -> createMenu();
            case 2 -> testMenu();
            case 3 -> System.out.println(C.YEL+ " PROGRAM CLOSED");
        }
            
    }



    /**
     * Displays the vending machine creation menu and creates a new vending
     * machine based on the user's selection.
     */
    private static void createMenu() {
        
        System.out.println(
            "\n\n" +
            "-".repeat(C.LINE) + "\n" +
            "CREATE A VENDING MACHINE\n" +
            "-".repeat(C.LINE)
        );
        
        currVM = new VendingMachine();

        mainMenu();

    }



    /**
     * Displays the testing menu for the current vending machine and allows the
     * user to access its vending and maintenance features.
     */
    private static void testMenu() {

            System.out.println(
                "\n\n" +
                "-".repeat(C.LINE) + "\n" +
                "TEST A VENDING MACHINE - " +currVM.getType()+ "\n" +
                "-".repeat(C.LINE) + "\n" +
                
                " [1] Vending features\n" +
                " [2] Maintenance features\n" +
                " [3] Back to main menu\n"
            );
            
            switch (Tools.rangeInput(1, 3)) {
                case 1 -> vendingMenu();
                case 2 -> maintenanceMenu();
                case 3 -> mainMenu();
            }

    }



    // ----------------------------------------------------
    // VENDING FEATURES


    /**
     * Allows the user to test individual vending operations after selecting an
     * item. Payment, dispensing, and change-return functions may be invoked
     * independently.
     */
    private static void individualVending() {
        
        if(currVM.selectItem()) {
            int mode;
            do {
                System.out.println(
                    " [1] Accept payment\n" +
                    " [2] Dispense item\n" +
                    " [3] Return change\n" +
                    " [4] Back\n"
                );
                
                mode = Tools.rangeInput(1, 4);
                switch (mode) {
                    case 1 -> currVM.acceptPayment();
                    case 2 -> currVM.dispenseItem();
                    case 3 -> {
                        if(currVM.getInsertedBills().isEmpty()) System.out.println(C.RED+ " [!] ERROR! Nothing to return" +C.DEF);
                        else currVM.returnChange(currVM.getInsertedBills());
                    }
                }
                
                System.out.println("\n");
            } 
            while (mode != 4);
        }

    }



    /**
     * Displays the vending features menu. Users may execute the complete
     * vending process or test individual vending operations.
     */
    private static void vendingMenu() {

        int mode;
        do {
            
            System.out.println(
                "\n\n" +
                "-".repeat(C.LINE) + "\n" +
                "VENDING FEATURES\n" +
                "-".repeat(C.LINE) + "\n" +
                
                " [1] Run entire vending process\n" +
                " [2] Test individual functions\n" +
                " [3] Finish testing\n"
            );
            
            mode = Tools.rangeInput(1, 3);
            switch (mode) {
                case 1 -> {if(currVM.selectItem()) if(currVM.acceptPayment()) currVM.dispenseItem();}
                case 2 -> individualVending();
                case 3 -> testMenu();
            }

        } 
        while (mode != 3);

    }



    // ----------------------------------------------------
    // MAINTENANCE FEATURES 

    
    /**
     * Displays the maintenance features menu. Users may restock items, update
     * item prices, collect money, replenish cash reserves, and view the
     * transaction summary.
     */
    private static void maintenanceMenu() {

        int mode;
        do {

            System.out.println(
                "\n\n" +
                "-".repeat(C.LINE) + "\n" +
                "MAINTENANCE FEATURES\n" +
                "-".repeat(C.LINE) + "\n" +
                
                " [1] Restock items\n" +
                " [2] Set item prices\n" +
                " [3] Collect money\n" +
                " [4] Replenish cash reserves\n" +
                " [5] View transaction summary\n" +
                " [6] Finish testing\n"
            );
            
            mode = Tools.rangeInput(1, 6);
            switch (mode) {
                case 1 -> currVM.restockItems();
                case 2 -> currVM.setItemPrices();
                case 3 -> currVM.collectMoney();
                case 4 -> currVM.replenishCashReserves();
                case 5 -> currVM.viewTransactionSummary();
                case 6 -> testMenu();
            }

        } 
        while (mode != 6);

    }
 

}
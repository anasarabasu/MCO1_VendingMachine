import java.util.ArrayList;

public final class Navigation {


    public static VM currVM = null;


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


    // ----------------------------------------------------
    

    private static void createMenu() {
        
        System.out.println(
            "\n\n" +
            "-".repeat(C.LINE) + "\n" +
            "CREATE A VENDING MACHINE\n" +
            "-".repeat(C.LINE) + "\n" +

            " [1] Regular\n" +
            " [2] Special\n"
        );

        currVM = null;

        switch (Tools.rangeInput(1, 2)) {
            case 1 -> currVM = new VM();
            // case 2 -> currVM = new VM(); 
        }

        mainMenu();

    }


    private static void testMenu() {

            System.out.println(
                "\n\n" +
                "-".repeat(C.LINE) + "\n" +
                "TEST A VENDING MACHINE - " +currVM.getName()+ "\n" +
                "-".repeat(C.LINE) + "\n" +
                
                " [1] Vending features\n" +
                " [2] Maintenance features\n" +
                " [3] Back to main menu\n"
            );
            
            switch (Tools.rangeInput(1, 3)) {
                case 1 -> vendingMenu();
                // case 2 -> maintenanceMenu();
                case 3 -> mainMenu();
            }

    }


    // ----------------------------------------------------


    private static void individualVending() {
        
        Item item = currVM.selectItem();
        ArrayList<Double> bills = new ArrayList<>();

        int mode;
        do {
            System.out.println(
                "\n\n" +
                C.YEL +item.getName()+ ", " +item.getCalories()+ " calories - " +item.getPrice()+C.DEF+ "\n" +
                " [1] Accept payment\n" +
                " [2] Dispense item\n" +
                " [3] Return change\n" +
                " [4] Back\n"
            );
            

            mode = Tools.rangeInput(1, 4);
            switch (mode) {
                case 1 -> bills.addAll(currVM.acceptPayment(item.getPrice()));
                case 2 -> currVM.dispenseItem(bills, item);
                case 3 -> {
                    if(bills.isEmpty()) System.out.println(C.RED+ " [!] ERROR! Nothing to return" +C.DEF);
                    else currVM.returnChange(bills);
                }
                case 4 -> vendingMenu();
            }
        } 
        while (mode != 4);

    }

    private static void vendingMenu() {

        System.out.println(
            "\n\n" +
            "-".repeat(C.LINE) + "\n" +
            "VENDING FEATURES\n" +
            "-".repeat(C.LINE) + "\n" +

            " [1] Run entire vending process\n" +
            " [2] Test individual functions\n" +
            " [3] Finish testing\n"
        );

        int mode = Tools.rangeInput(1, 3);
        switch (mode) {
            case 1 -> currVM.vendingProcess();
            case 2 -> individualVending();
            case 3 -> testMenu();
        }

    }

    // // ----------------------------------------------------

    // private static void maintenanceMenu() {


    //     System.out.println(
    //         "\n\n" +
    //         "-".repeat(C.LINE) + "\n" +
    //         "TESTING \"" +currVM.getName()+ "\" - MAINTENANCE FEATURES\n" +
    //         "-".repeat(C.LINE) + "\n" +

    //         " [4] Finish testing\n"
    //     );


    //     int mode = Tools.getRangeInput(1, 4);
    //     do {
    //         switch (mode) {
    //             // case 1 ->
    //             // case 2 ->
    //             // case 3 ->
    //             case 4 -> testMenu();
    //         }
    //     } 
    //     while (mode != 4);

    // }
 

}
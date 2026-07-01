public final class Navigation {


    public static RegVM currRegVM = null;
    public static SpecialVM currSpecialVM = null;


    public static void mainMenu() {

        System.out.println(
            "\n" +
            "-".repeat(C.line) + "\n" +
            "VENDING MACHINE\n" +
            "-".repeat(C.line) + "\n" +
            
            " [1] Create a vending machine\n" +
            " [2] Test a vending machine\n" +
            " [3] Exit\n"
        );


        int selection = Tools.getMenuInput(3);

        while (selection == 2 && (currRegVM == null && currSpecialVM == null)) {
            System.out.println(C.RED+ " [!] NO VENDING MACHINE! Please create a vending machine first\n" +C.DEF);
            selection = Tools.getMenuInput(3);
        }
        
        switch (selection) {
            case 1 -> createMenu();
            case 2 -> testMenu();
            case 3 -> System.out.println(C.YEL+ " PROGRAM CLOSED");
        }
    }


    private static void createMenu() {
        
        System.out.println(
            "\n" +
            "-".repeat(C.line) + "\n" +
            "CREATE A VENDING MACHINE\n" +
            "-".repeat(C.line) + "\n" +

            " [1] Regular\n" +
            " [2] Special\n"
        );

        currRegVM = null;
        currSpecialVM = null;

        switch (Tools.getMenuInput(2)) {
            case 1 -> currRegVM = new RegVM();
            case 2 -> currSpecialVM = new SpecialVM();
        }

    }


    private static void testMenu() {

            System.out.println(
                "-".repeat(C.line) + "\n" +
                "TEST A VENDING MACHINE\n" +
                "-".repeat(C.line) + "\n" +
                
                " [1] Vending features\n" +
                " [2] Maintenance features"
            );
            
            switch (Tools.getMenuInput(2)) {
                // case 1 ->
                // case 2 ->
            }

    }


}
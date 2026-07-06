public class VM {
    

    private String name;
    public String getName() {return name;}

    private Item items[];

    private int cashReserve();


    public VM() {

        System.out.print(
            "\n Enter a name for this vending machine:" +
            "\n >> " 
        );
        name = Tools.scan.next();

        if(name.equals("skip")) {items = new Item[1]; return;} 

        System.out.print("\n Enter the number of slots (8-24):\n");
        items = new Item[Tools.getRangeInput(8, 24)];

        for (int i = 0; i < items.length; i++) {
            System.out.print(
                "\n" +
                "-".repeat(C.LINE) +
                "\n Slot " + (i+1) +
                "\n Item name:" +
                "\n >> "
            );
            String name = Tools.scan.next();

            System.out.print("\n Price:\n");
            float price = Tools.getInfiniteInput();

            System.out.print("\n Calories:\n");
            int calories = (int)Tools.getInfiniteInput();

            System.out.print("\n Slot capacity: (1-16)\n");
            int capacity = Tools.getRangeInput(1, 16);
            
            items[i] = new Item(name, price, calories);
        }

    }

    // ----------------------------------------------------
    // TEST
    public void test1() {
        items = new Item[9];

        items[0] = new Item("Choco", 100, 100);
        items[1] = new Item("Soda", 50, 1000);
        items[2] = new Item("Noodles", 75.25, 1023);
        items[3] = new Item("Chips", 12.25, 676767);
        items[4] = new Item("Water", 1, 0);
        items[5] = new Item("Coffee", 99.25, 99);
        items[6] = new Item("Juice", 69.50, 1);
        items[7] = new Item("Milktea", 100, 46);
        items[8] = new Item("Samwich", 44, 44);

    }


    // ----------------------------------------------------
    // VENDING

    public void vendingProcess() {

        int itemCode = selectItem();
        if(itemCode != -1) {
            // int payment = acceptPayment();
        }

    }

    
    public int selectItem() {
        
        test1();
    
        // DISPLAY
        System.out.print(
            "\n\n" +
            "-".repeat(C.LINE*3) + "\n" +
            " ".repeat(C.LINE*3/2-name.length()) +name+ "\n" +
            "-".repeat(C.LINE*3)
        );
        
        for (int i = 0; i < items.length; i++) {
            if(i % 4 == 0) System.out.println();
            if(!items[i].isAvailable()) System.out.print(C.RED);
            System.out.print("#" +(i+1)+ " " +String.format("%-24s", (items[i].getName()+ " (" +items[i].getPrice()+ ")")) + C.DEF);
        }

        System.out.println("\n" + "-".repeat(C.LINE*3) + "\n");


        // INPUT
        System.out.println(" Enter item number:");

        int selection = Tools.getRangeInput(1, items.length);
        if(!items[selection].isAvailable()) {
            System.out.println(C.RED + " [!] This item is not available");
            return -1;
        }
        else return selection;

    } 
    
    

    public void acceptPayment(Item item) {

        System.out.println("Insert cash:");


    }


    public void dispenseItem() {}
    public void returnChange() {}
    public void printItemInfo() {}

    
    // ----------------------------------------------------
    // MAINTENANCE

}

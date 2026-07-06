public class VM {
    

    private String name;
    public String getName() {return name;}

    private Item items[];

    private int cashReserve[] = new int[14]; 
    // PAPER :  1k   500  200  100  50  20
    // COINS :  20   10   5    1
    // CENTS : .25  .10  .05  .01
    // private int coinReserve[] = new int[4]; 
    // private int centReserve[] = new int[4]; 


    public VM() {

        // NAME
        // System.out.print(
        //     "\n Enter a name for this vending machine:" +
        //     "\n >> " 
        // );
        // name = Tools.scan.next();

        // if(name.equals("skip")) {items = new Item[1]; return;} 


        // // CASH RESERVE
        // System.out.println("\n Enter the machine's initial cash reserve:");

        // System.out.println("\n\tNumber of Php1000 bills: "); billReserve[0] = Tools.nonNegInt();
        // System.out.println("\n\tNumber of Php500 bills: "); billReserve[1] = Tools.nonNegInt();
        // System.out.println("\n\tNumber of Php200 bills: "); billReserve[2] = Tools.nonNegInt();
        // System.out.println("\n\tNumber of Php100 bills: "); billReserve[3] = Tools.nonNegInt();
        // System.out.println("\n\tNumber of Php50 bills: "); billReserve[4] = Tools.nonNegInt();
        // System.out.println("\n\tNumber of Php20 bills: "); billReserve[5] = Tools.nonNegInt();

        // System.out.println("\n\tNumber of Php20 coins: "); coinReserve[0] = Tools.nonNegInt();
        // System.out.println("\n\tNumber of Php10 coins: "); coinReserve[1] = Tools.nonNegInt();
        // System.out.println("\n\tNumber of Php5 coins: "); coinReserve[2] = Tools.nonNegInt();
        // System.out.println("\n\tNumber of Php1 coins: "); coinReserve[3] = Tools.nonNegInt();

        // System.out.println("\n\tNumber of Php0.25 coins: "); centReserve[0] = Tools.nonNegInt();
        // System.out.println("\n\tNumber of Php0.10 coins: "); centReserve[1] = Tools.nonNegInt();
        // System.out.println("\n\tNumber of Php0.05 coins: "); centReserve[2] = Tools.nonNegInt();
        // System.out.println("\n\tNumber of Php0.01 coins: "); centReserve[3] = Tools.nonNegInt();

        
        // // ITEM SLOTS
        // System.out.print("\n Enter the number of slots (8-24):\n");
        // items = new Item[Tools.rangeInput(8, 24)];

        // for (int i = 0; i < items.length; i++) {
        //     System.out.print(
        //         "\n" +
        //         "-".repeat(C.LINE) +
        //         "\n Slot " + (i+1) +
        //         "\n Item name:" +
        //         "\n >> "
        //     );
        //     String name = Tools.scan.next();

        //     System.out.print("\n Price:\n");
        //     double price = Tools.nonNegDouble();

        //     System.out.print("\n Calories:\n");
        //     int calories = Tools.nonNegInt();

        //     System.out.print("\n Slot capacity: (1-16)\n");
        //     int capacity = Tools.rangeInput(1, 16);
            
        //     items[i] = new Item(name, price, calories);
        // }

    }


    // private void 

    // ----------------------------------------------------
    // TEST
    public void test1() {
        cashReserve = new int[]{1, 2, 1, 4, 1, 2, 10, 2, 1, 3, 3, 2, 1, 2};

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
            acceptPayment(items[itemCode].getPrice());
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

        int selection = Tools.rangeInput(1, items.length);
        if(!items[selection].isAvailable()) {
            System.out.println(C.RED + " [!] This item is not available");
            return -1;
        }
        else return selection;

    } 
    
    

    public boolean acceptPayment(double price) {

        cashReserve = new int[]{
            1, 2, 1, 4, 1, 2, 
            10, 2, 1, 3, 
            3, 2, 1, 2};


        double billList[] = {
            1000, 500, 200, 100, 50, 20,
            20, 10, 5, 1,
            0.25, 0.10, 0.05, 0.01
        };

        System.out.println("Insert cash:");

        double payment = Tools.nonNegDouble();
        while (payment < price) {
            System.out.println(C.RED + " [!] Please enter a valid amount" +C.DEF);
            payment = Tools.nonNegDouble();
        }
        double change = payment - price;


        String changeInfo = "";
        
        for (int i = 0; i < billList.length; i++) {
            int denom = 0;

            if(change / billList[i] > 0 && cashReserve[i] > 0) {
                int rawDenom = (int) (change / billList[i]);
                
                for (; rawDenom > 0 && cashReserve[i] > 0; denom++) {
                    change -= billList[i];

                    rawDenom--;
                    cashReserve[i]--;
                }

                if(denom > 0) changeInfo += "\nPhp " +billList[i]+ " (" +Math.abs(denom)+ ")";
            }
        }

        System.out.println(changeInfo);

        if(change > 0)
            System.out.println("oh no");
        
        
        return true;

    }


    public void dispenseItem() {}
    public void returnChange() {}
    public void printItemInfo() {}

    
    // ----------------------------------------------------
    // MAINTENANCE

}

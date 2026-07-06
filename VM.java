import java.util.ArrayList;

public class VM {
    

    private String name;
    public String getName() {return name;}

    private Item items[];

    private int cashReserve[] = new int[14]; 
    // PAPER :  1k   500  200  100  50  20
    // COINS :  20   10   5    1
    // CENTS : .25  .10  .05  .01


    public VM() {

        // NAME
        System.out.print(
            "\n Enter a name for this vending machine:" +
            "\n >> " 
        );
        name = Tools.scan.next();

        if(name.equals("skip")) {items = new Item[1]; return;} 


        // CASH RESERVE
        System.out.println("\n Enter the machine's initial cash reserve:");

        System.out.println("\n\tNumber of Php1000 bills: "); cashReserve[0] = Tools.nonNegInt();
        System.out.println("\n\tNumber of Php500 bills: "); cashReserve[1] = Tools.nonNegInt();
        System.out.println("\n\tNumber of Php200 bills: "); cashReserve[2] = Tools.nonNegInt();
        System.out.println("\n\tNumber of Php100 bills: "); cashReserve[3] = Tools.nonNegInt();
        System.out.println("\n\tNumber of Php50 bills: "); cashReserve[4] = Tools.nonNegInt();
        System.out.println("\n\tNumber of Php20 bills: "); cashReserve[5] = Tools.nonNegInt();

        System.out.println("\n\tNumber of Php20 coins: "); cashReserve[6] = Tools.nonNegInt();
        System.out.println("\n\tNumber of Php10 coins: "); cashReserve[7] = Tools.nonNegInt();
        System.out.println("\n\tNumber of Php5 coins: "); cashReserve[8] = Tools.nonNegInt();
        System.out.println("\n\tNumber of Php1 coins: "); cashReserve[9] = Tools.nonNegInt();

        System.out.println("\n\tNumber of Php0.25 coins: "); cashReserve[10] = Tools.nonNegInt();
        System.out.println("\n\tNumber of Php0.10 coins: "); cashReserve[11] = Tools.nonNegInt();
        System.out.println("\n\tNumber of Php0.05 coins: "); cashReserve[12] = Tools.nonNegInt();
        System.out.println("\n\tNumber of Php0.01 coins: "); cashReserve[13] = Tools.nonNegInt();

        
        // ITEM SLOTS
        System.out.print("\n Enter the number of slots (8-24):\n");
        items = new Item[Tools.rangeInput(8, 24)];

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
            double price = Tools.nonNegDouble();

            System.out.print("\n Calories:\n");
            int calories = Tools.nonNegInt();

            System.out.print("\n Slot capacity: (1-16)\n");
            int capacity = Tools.rangeInput(1, 16);
            
            items[i] = new Item(name, price, calories);
        }

    }


    // private void 

    // ----------------------------------------------------
    // TEST
    public void test1() {
cashReserve = new int[]{
            1, 0, 0, 3, 0, 7, 
            0, 0, 2, 5, 
            3, 2, 1, 2
        };
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

    }

    
    public Item selectItem() {

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
            System.out.print("#" +(i+1)+ " " +String.format("%-24s", (items[i].getName()+ " (" +String.format("%,.2f", items[i].getPrice())+ ")")) + C.DEF);
        }

        System.out.println("\n" + "-".repeat(C.LINE*3) + "\n");


        // INPUT
        System.out.println(" Enter item number:");

        int selection = Tools.rangeInput(1, items.length)-1;
        if(!items[selection].isAvailable()) {
            System.out.println(C.RED + " [!] This item is not available");
            return null;
        }
        else return items[selection];

    } 


    // ----
    
    private final double BILLS[] = new double[]{
        0,
        1000, 500, 200, 100, 50, 20,
        20, 10, 5, 1,
        0.25, 0.10, 0.05, 0.01
    };
    
    public ArrayList<Double> acceptPayment(double price) {

        System.out.println(
            " [00] CANCEL TRANSACTION\n" +
            " [01] 1000    [02] 500     [03] 200     [04] 100    [05] 50    [06] 20\n" +
            " [07] 20      [08] 10      [08] 5       [10] 1\n" +
            " [11] 0.25    [12] 0.10    [13] 0.05    [14] 0.01\n\n" +
            " Insert cash:"
        );
        
        int input = Tools.rangeInput(0, 13);
        if(input == 0) System.out.println(C.YEL+ " Transaction cancelled" +C.DEF);
        
        
        ArrayList<Double> bills = new ArrayList<>();
        bills.add(BILLS[input]);
        double payment = BILLS[input];

        while (payment < price && input != 0) {
            System.out.println(C.RED + " [!] INSUFFICIENT PAYMENT! Please insert more cash, or cancel transaction\n\n" +C.DEF+

            " Insert cash: (Current: " +String.format("%,.2f", payment)+ ")"
            );

            input = Tools.rangeInput(0, 13);
            payment += BILLS[input];
            
            if(input == 0) System.out.println(C.YEL +" Transaction cancelled" +C.DEF);
            else bills.add(BILLS[input]);
        }            

        if(input == 0) {
            returnChange(bills);
            return null;
        }
        else return bills;

    }


    private boolean calculatePayment(ArrayList<Double> bills, double price) {

        double payment = 0;
        for (Double bill : bills) payment += bill;
        
        double remaining = payment-price;

        ArrayList<Double> returnedBills = new ArrayList<>();
        
        for (int i = 0; i < BILLS.length-1; i++) {
            if(remaining / BILLS[i+1] > 0 && cashReserve[i] > 0) {
                int denominations = (int) (remaining / BILLS[i+1]);

                while (denominations > 0 && cashReserve[i] > 0) {
                    remaining -= BILLS[i+1];
                    returnedBills.add(BILLS[i+1]);
                    
                    denominations--;
                    cashReserve[i]--;
                }
            }
        }
        
        if(remaining > 0) {
            System.out.println(C.RED+ " [!] TRANSACTION CANCELLED! The machine has insufficient change" +C.DEF);
            returnChange(bills);
            return false;
        }
        else {
            bills.clear();
            returnChange(returnedBills);
            return true;
        }

    }


    // ----
        
        
    public void dispenseItem(ArrayList<Double> bills, Item item) {
        
        if(calculatePayment(bills, item.getPrice())) item.decreaseStock();

    }


    // ----


    public void returnChange(ArrayList<Double> bills) {

        double total = 0;
        for (Double bill : bills) {
            System.out.print("\n Php " +bill);
            total +=bill;
        }
        System.out.println(C.YEL+ "\n Change total: " +total+ "\n" +C.DEF);
        bills.clear();

    }

    
    // ----------------------------------------------------
    // MAINTENANCE

}

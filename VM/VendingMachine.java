package VM;

import java.util.ArrayList;
import Items.FoodStuff;


/**
 * Represents a vending machine that manages item slots, cash reserves, and
 * vending operations. The machine allows users to select items, accept
 * payments, calculate and dispense change, and dispense purchased items.
 */
public class VendingMachine {
    

    /**
     * The vending machine category selected during initialization.
     */
    private int type;

    /**
     * Returns the name of the vending machine's selected type.
     *
     * @return the machine type as a string, or {@code null} if no valid type
     *         has been assigned
     */
    public String getType() {
        
        switch (this.type) {
            case 1 : return "Drinks";
            case 2 : return "Sandwiches";
        }
        return null;

    }



    /**
     * The collection of item slots managed by this vending machine.
     */
    private Slot[] slots;

    /**
     * The slot containing the item currently selected for purchase.
     */
    private Slot selectedItem;



    /**
     * The quantity of each bill and coin denomination currently available for
     * dispensing change.
     */
    private int[] cashReserve = new int[14];



    /**
     * The bills and coins inserted during the current transaction.
     */
    private ArrayList<Integer> insertedBills = new ArrayList<>();

    /**
     * Returns the bills and coins inserted during the current transaction.
     *
     * @return the list of inserted payment denominations
     */
    public ArrayList<Integer> getInsertedBills() {return insertedBills;}



    /**
     * The bills and coins collected as earnings since the last collection.
     */
    private ArrayList<Integer> earnings = new ArrayList<>();



    /**
     * Constructs a vending machine by prompting the user to select a machine
     * type and initialize its cash reserves. The machine is created with a
     * default item corresponding to the selected type.
     */
    public VendingMachine() {

        // TYPE
        System.out.print(
            " Pick the vending machine type:" +
            "\n [1] Drinks" +
            "\n [2] Sandwiches\n\n"
        );
        type = Tools.rangeInput(1, 2);
        
        slots = new Slot[FoodStuff.getFoodList(type).length];
        for (int i = 0; i < FoodStuff.getFoodList(type).length; i++) slots[i] = new Slot(FoodStuff.getFoodList(type)[i], 16);


        // CASH RESERVE
        System.out.println("\n Enter the machine's initial cash reserve (all):");
        int amount = Tools.nonNegInt();
        for (int i = 0; i < cashReserve.length; i++) cashReserve[i] += amount;

    }



    /**
     * Displays the vending machine's available items. When accessed in
     * maintenance mode, each item's current stock is also displayed.
     *
     * @param isAdmin {@code true} to display stock information;
     *                {@code false} to display only item names and prices
     */
    private void displayItemSelection(boolean isAdmin) {

        System.out.print(
            "\n\n" +
            "-".repeat(C.LINE * 3) + "\n" +
            " ".repeat(C.LINE * 3 / 2 - getType().length()) + getType() + "\n" +
            "-".repeat(C.LINE * 3)
        );

        System.out.print("\n00 CANCEL");
        for (int i = 0; i < slots.length; i++) {
            String info = isAdmin ? 
                String.format("%-60s", (slots[i].getItem().getName() + " Php " + String.format("%,.2f", slots[i].getItem().getPrice()) + " (STOCK : " + slots[i].getStock() + (slots[i].isFull() ? "  MAX" : "") + ")"))
                : 
                String.format("%-60s", (slots[i].getItem().getName() + " Php " + String.format("%,.2f", slots[i].getItem().getPrice())));


            if(i % 3 == 0) System.out.println();
            if(!slots[i].itemAvailable()) System.out.print(C.RED);
            System.out.print(String.format("%02d", (i + 1)) + " " + info + C.DEF);
        }

        System.out.println("\n" + "-".repeat(C.LINE * 3) + "\n");

    }



    // ----------------------------------------------------
    // VENDING


    /**
     * Prompts the user to select an item from the machine. If the selected item
     * is unavailable, the user is repeatedly prompted until a valid item is
     * chosen. Selecting {@code 0} cancels the operation.
     *
     * @return {@code true} if an item is successfully selected;
     *         {@code false} if the operation is cancelled
     */
    public boolean selectItem() {
    

        // DISPLAY
        displayItemSelection(false);


        // INPUT
        System.out.println(" Select an item to purchase:");

        int selection = Tools.rangeInput(0, slots.length) - 1;
        if(selection >= 0) {
            System.out.println(
                "\n\n" +
                C.YEL + slots[selection].getItem().getName() + "    " + 
                slots[selection].getItem().getCalories() + " calories    Php" + 
                slots[selection].getItem().getPrice() + C.DEF
            );
        }
            
        while (selection >= 0 && !slots[selection].itemAvailable()) {
            System.out.println(C.RED + " [!] ERROR! This item is not available, please select different item\n" + C.DEF);
            selection = Tools.rangeInput(0, slots.length) - 1;
        }


        // RETURN
        if(selection >= 0) {
            selectedItem = slots[selection];
            return true;
        }
        else return false;

    } 



    /**
     * Accepts payment from the user until the selected item's price has been met
     * or the transaction is cancelled. Each inserted bill or coin is recorded.
     *
     * @return {@code true} if sufficient payment is received;
     *         {@code false} if the transaction is cancelled
     */
    public boolean acceptPayment() {

        // CASH SELECTION
        System.out.println(
            "\n\n Insert cash:\n" +
            " [00] CANCEL TRANSACTION\n" +
            " [01] 1000    [02] 500     [03] 200     [04] 100    [05] 50    [06] 20\n" +
            " [07] 20      [08] 10      [09] 5       [10] 1\n" +
            " [11] 0.25    [12] 0.10    [13] 0.05    [14] 0.01\n\n"
        );
        

        int input = Tools.rangeInput(0, 14);
        double payment = C.BILLS[input];
        System.out.println(" Inserted: Php" + C.BILLS[input] + Tools.getBillType(input));


        if(input == 0) System.out.println(C.YEL + " Transaction cancelled" + C.DEF);
        else insertedBills.add(input);

        while (payment < selectedItem.getItem().getPrice() && input != 0) {
            System.out.println(C.RED + " [!] INSUFFICIENT PAYMENT! Please insert more cash, or cancel transaction\n\n" + C.DEF +

            " Insert cash: (Current: " +String.format("%,.2f", payment)+ ")"
            );

            input = Tools.rangeInput(0, 13);
            payment += C.BILLS[input];
            System.out.println(" Inserted: Php " + C.BILLS[input] + Tools.getBillType(input));

            
            if(input == 0) System.out.println(C.YEL + " Transaction cancelled" + C.DEF);
            else insertedBills.add(input);
        }            


        // RETURN
        if(input == 0) {
            returnChange(insertedBills);
            return false;
        }
        else return true;

    }

    

    /**
     * Calculates the payment received, determines whether sufficient change can
     * be returned, and updates the machine's earnings accordingly.
     *
     * @return {@code true} if the transaction can be completed;
     *         {@code false} if insufficient change is available
     */
    private boolean calculatePayment() {

        double payment = 0;
        for (Integer bill : insertedBills) payment += C.BILLS[bill];
        double remaining = payment - selectedItem.getItem().getPrice();

        ArrayList<Integer> returnedBills = new ArrayList<>();


        // Calculate change using the largest available denominations 
        for (int i = 0; i < C.BILLS.length - 1; i++) {
            if(remaining / C.BILLS[i + 1] > 0 && cashReserve[i] > 0) {
                int denominations = (int) (remaining / C.BILLS[i + 1]);

                while (denominations > 0 && cashReserve[i] > 0) {
                    remaining -= C.BILLS[i + 1];
                    returnedBills.add(i + 1);
                    
                    denominations--;
                    cashReserve[i]--;
                }
            }
        }
        
        
        // RETURN
        if(remaining > 0) {
            System.out.println(C.RED + " [!] TRANSACTION CANCELLED! The machine has insufficient change" + C.DEF);
            returnChange(insertedBills);
            return false;
        }
        else {
            for (Integer integer : insertedBills) earnings.add(integer);
            insertedBills.clear();
            returnChange(returnedBills);
            return true;
        }

    }

    

    /**
     * Dispenses the selected item if it is available and the payment is valid.
     * If the transaction cannot be completed, the inserted payment is returned.
     */
    public void dispenseItem() {
        
        // Slot slot = Arr
        if(selectedItem.itemAvailable()) {
            if(calculatePayment()) {
                selectedItem.decreaseStock();
                System.out.println(" Item dispensed: " + selectedItem.getItem().getName());
            }
        }
        else {
            System.out.println(C.RED + " [!] This item is not available" + C.DEF);
            returnChange(insertedBills);
        } 

    }


    /**
     * Returns the specified bills and coins to the user and displays the total
     * amount returned. The provided collection is cleared after the change has
     * been returned.
     *
     * @param bills the bills and coins to return
     */
    public void returnChange(ArrayList<Integer> bills) {

        double total = Tools.sumBills(bills);
        
        System.out.println(C.YEL + "\n Amount returned: " + total + C.DEF);
        bills.clear();

    }

    

    // ----------------------------------------------------
    // MAINTENANCE


    /**
     * Allows the user to restock selected items by specifying the quantity to
     * add. The operation may be repeated until it is cancelled.
     */
    public void restockItems() {

        int selection = 0;
        while (selection >= 0) {

            // DISPLAY
            displayItemSelection(true);
        
        
            // INPUT
            System.out.println(" Select an item to restock:");
            selection = Tools.rangeInput(0, slots.length) - 1;

            while(selection >= 0 && slots[selection].isFull()) {
                System.out.println(C.RED + " [!] ERROR! This item is already at max capacity, please select a different item\n" + C.DEF);
                selection = Tools.rangeInput(0, slots.length) - 1;
            }

            if(selection >= 0) {
                System.out.println(
                    "\n\n" +
                    C.YEL + slots[selection].getItem().getName() + "    " + 
                    slots[selection].getItem().getCalories() + " calories    " + 
                    "Php " + slots[selection].getItem().getPrice() + "    " + 
                    "STOCK : " + slots[selection].getStock() + C.DEF
                );
                
                System.out.println("\n\n Enter the restock amount:");
                int amount = Tools.nonNegInt();
                while (amount < 0) {
                    System.out.println(C.RED + " [!] ERROR! Please enter a valid amount\n" + C.DEF);
                    amount = Tools.nonNegInt();
                }
                slots[selection].setStock(amount);
            }

        }

    }



    /**
     * Allows the user to update the price of selected items. The user may
     * repeatedly select items and assign new non-negative prices until the
     * operation is cancelled.
     */
    public void setItemPrices() {

        int selection = 0;
        while (selection >= 0) {
            
            // DISPLAY
            displayItemSelection(true);
         
            // INPUT
            System.out.println(" Select an item to update its price:");
            selection = Tools.rangeInput(0, slots.length) - 1;

            if(selection >= 0) {
                System.out.println(
                    "\n\n" +
                    C.YEL + slots[selection].getItem().getName() + "    " + 
                    slots[selection].getItem().getCalories() + " calories    " + 
                    "Php " + slots[selection].getItem().getPrice() + "    " + 
                    "STOCK : " + slots[selection].getStock() + C.DEF
                );
                
                System.out.println("\n\n Enter the new price :");
                double price = Tools.nonNegDouble();
                while (price < 0) {
                    System.out.println(C.RED + " [!] ERROR! Please enter a valid amount\n" + C.DEF);
                    price = Tools.nonNegDouble();
                }
                slots[selection].getItem().setPrice(price);
            }

        }

    }

    

    /**
     * Displays the total value of the bills and coins received since the last
     * collection and clears the machine's recorded payment history.
     */
    public void collectMoney() {

        double total = Tools.sumBills(earnings);

        System.out.println(C.YEL + "\n Total collected: " + total + C.DEF);
        earnings.clear();

    }



    /**
     * Replenishes the machine's cash reserves by allowing the user to specify
     * the quantity of each supported bill and coin denomination.
     */
    public void replenishCashReserves() {

        System.out.println(" Enter the amount of cash to add");

        for (int i = 0; i < cashReserve.length; i++) {
            System.out.print("\n (" + cashReserve[i] + ") Php " + C.BILLS[i+1] + Tools.getBillType(i+1) + "\n");
            cashReserve[i] += Tools.nonNegInt();
        }

        System.out.println(
            "\n" +
            "-".repeat(C.LINE) +
            "\n" +
            C.YEL + "Updated cash reserves:" + C.DEF
        );

        // display updates values
        for (int i : cashReserve) {
            System.out.println(" (" + cashReserve[i] + ") Php " + C.BILLS[i+1] + Tools.getBillType(i+1));
        }

    }



    /**
     * Displays a summary of item transactions, including each item's starting
     * inventory, quantity sold, remaining stock, and the machine's total sales.
     */
    public void viewTransactionSummary() {

        System.out.print(
            "\n\n" +
            "-".repeat(C.LINE * 3) + "\n" +
            " ".repeat(C.LINE * 3 / 2 - "TRANSACTIONS".length()) + "TRANSACTIONS" + "\n\n" +
            String.format("%-26s", "Item") + " | " + 
            String.format("%-18s", "Starting Inventory") + " | " + 
            String.format("%-18s", "Sold") + " | " +
            String.format("%-18s", "Latest Inventory") + "\n" +
            "-".repeat(C.LINE * 3) + "\n"

        );

        double sales = 0;

        for (Slot slot : slots) {
            if(slot.getInitialStock() - slot.getStock() == 0) System.out.print(C.GRY);

            System.out.println(
                String.format("%-26s", slot.getItem().getName()) + " | " + 
                String.format("%-18s", slot.getInitialStock()) + " | " + 
                String.format("%-18s", slot.getInitialStock() - slot.getStock()) + " | " +
                String.format("%-18s", slot.getStock()) + C.DEF
            );

            sales += (slot.getInitialStock() - slot.getStock()) * slot.getItem().getPrice();
        }

        System.out.print(
            "-".repeat(C.LINE * 3) + "\n" +
            "Total Sales: Php " + sales + "\n"
        );

    }


}
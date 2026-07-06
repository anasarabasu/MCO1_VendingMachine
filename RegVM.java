import Items.Item;
import VM.Slot;

public class RegVM {
    

    private String name;
    private Item slots[];



    public RegVM() {

        System.out.print(
            "\n" +
            "-".repeat(C.line) +
            "\n Enter a name for this vending machine:" +
            "\n >> " 
        );
        name = Tools.scan.next();


        System.out.print("\n Enter the number of slots (8-24):\n");
        slots = new Item[Tools.getRangeInput(8, 24)];

        for (int i = 0; i < slots.length; i++) {
            System.out.print(
                "\n" +
                "-".repeat(C.line) +
                "\n Slot " + (i+1) +
                "\n Item name:" +
                "\n >> "
            );
            String name = Tools.scan.next();

            System.out.print("\n Price:\n");
            float price = Tools.getInfiniteInput();

            System.out.print("\n Calories:\n");
            float calories = Tools.getInfiniteInput();

            System.out.print("\n Slot capacity: (1-16)\n");
            int capacity = Tools.getRangeInput(1, 16);
            
            slots[i] = new Item(name, price, calories, capacity);
        }

    }


    public void receivePayment() {}
    public void dispenseItem() {}
    public void calculateChange() {}
    public void printItemInfo() {}


}

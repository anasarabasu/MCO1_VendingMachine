package Items;


/**
 * Represents an item that can be stored and sold by the vending machine.
 * Each item has a name, price, and calorie count.
 */
public class Item {
   

    /**
     * The name of the item.
     */ 
    private String name;

    /**
     * Returns the name of the item.
     *
     * @return the item's name
     */
    public String getName() {return name;}



    /**
     * The selling price of the item.
     */
    private double price;

    /**
     * Returns the selling price of the item.
     *
     * @return the item's price
     */
    public double getPrice() {return price;}

    /**
     * Sets the selling price of the item.
     *
     * @param price the new price of the item
     */
    public void setPrice(double price) {this.price = price;}



    /**
     * The number of calories contained in the item.
     */
    private int calories;


    /**
     * Returns the calorie count of the item.
     *
     * @return the item's calories
     */
    public int getCalories() {return calories;}



    /**
     * Constructs an item with the specified name, price, and calorie count.
     *
     * @param name the name of the item
     * @param price the selling price of the item
     * @param calories the number of calories contained in the item
     */
    public Item(String name, double price, int calories) {
    
        this.name = name;
        this.price = price;
        this.calories = calories;

    }


}
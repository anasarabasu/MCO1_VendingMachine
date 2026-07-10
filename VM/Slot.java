package VM;

import Items.Item;


/**
 * Represents a slot in the vending machine that stores a single type of item.
 * Each slot has a fixed capacity and tracks its inventory and sales information.
 */
public class Slot {

    
    /**
     * The item assigned to this slot.
     */
    private Item item;
    
    /**
     * Returns the item assigned to this slot.
     *
     * @return the item stored in this slot
     */
    public Item getItem() {return item;}



    /**
     * The maximum number of items this slot can hold.
     */
    private int capacity;

    /**
     * Determines whether the slot is fully stocked.
     * 
     * @return {@code true} if the slot is at maximum capacity;
     *         {@code false} otherwise
     */
    public boolean isFull() {

        if(stock == capacity) return true;
        else return false;

    }



    /**
     * The current number of items available in this slot.
     */
    private int stock;
    
    /**
     * Returns the current stock of the item.
     *
     * @return the number of items currently available
     */
    public int getStock() {return stock;}

    /**
     * Determines whether the slot contains at least one item.
     *
     * @return {@code true} if the slot has available stock;
     *         {@code false} otherwise
     */
    public boolean itemAvailable() {

        if(stock <= 0) return false;
        else return true;

    }

    /**
     * Adds the specified quantity to the current stock. If the resulting stock
     * exceeds the slot's capacity, the stock is set to the maximum capacity.
     * The transaction record is then updated to reflect the new stock level.
     *
     * @param stock the quantity of items to add
     */
    public void setStock(int stock) {
        this.stock += stock;
        if(this.stock > capacity) this.stock = capacity;

        updateInitialStock();
    }

    /**
     * Decreases the stock by one and records the sale of the item.
     */
    public void decreaseStock() {

        stock--;
        itemsSold++;

    }



    /**
     * The stock level immediately after the most recent restocking.
     */
    private int initialStock;

    /**
     * Returns the stock level immediately after the most recent restocking.
     *
     * @return the initial stock level
     */
    public int getInitialStock() {return initialStock;}


    /**
     * Updates the recorded initial stock to match the current stock and resets
     * the sales count.
     */
    public void updateInitialStock() {
        
        initialStock = stock;
        itemsSold = 0;

    }


    
    /**
     * The number of units sold since the most recent restocking.
     */
    private int itemsSold;
    
    /**
     * Returns the number of units sold since the most recent restocking.
     *
     * @return the number of units sold
     */
    public int getItemsSold() {return itemsSold;}



    /**
     * Constructs a slot with the specified item and capacity. If the specified
     * capacity is less than {@code 10}, the slot capacity is set to {@code 10}.
     * If it exceeds {@code 60}, the slot capacity is set to {@code 60}. The slot
     * is initially filled to its maximum capacity.
     *
     * @param item the item assigned to this slot
     * @param capacity the desired maximum number of items the slot can hold
     */
    public Slot(Item item, int capacity) {
        this.item = item;

        if(capacity < 10) this.capacity = 10;
        else if(capacity > 60) this.capacity = 60;
        else this.capacity = capacity;
        

        this.stock = this.capacity;
        this.initialStock = this.stock;
        
    }


}
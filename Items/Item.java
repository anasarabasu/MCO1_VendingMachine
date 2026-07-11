package Items;
public class Item {
   
    
    private String name;
    public String getName() {return name;}
    
    private double price;
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    
    private int calories;
    public int getCalories() {return calories;}


    // Number of items this slot can hold
    private int capacity;

    public int getCapacity() {
    return capacity;
    }

    //Number of items available in the machine
    private int stock;
    public int getStock() {
    return stock;
    }

    //Record the stock level after the laast restocking.
    private int startingStock;

    public int getStartingStock(){
        return startingStock;
    }

    public void setStartingStock(int startingStock){
        this.startingStock = startingStock;
    }

    
    public Item(String name, double price, int calories, int capacity) {
    
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.capacity = capacity;
        this.stock = capacity;
        //When the machine is first created, 
        // starting inventory is the same as current stock
        this.startingStock = stock;
    
    }
    
    
    public boolean isAvailable() {

        if(stock <= 0) return false;
        else return true;

    }

    public void decreaseStock() {
        stock--; 
    }


    // Adds items during maintenance without exceeding the slot capacity.
    public void addStock(int amount) {

    if (stock + amount <= capacity) {
        stock += amount;

        //Update stock
        startingStock = stock;
        System.out.println("Stock successfully updated.");
    }
    else {
        System.out.println("Cannot exceed the slot capacity.");
    }

}

}

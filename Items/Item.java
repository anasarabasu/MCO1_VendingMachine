package Items;
public class Item {
   
    
    private String name;
    public String getName() {return name;}
    
    private double price;
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    
    private int calories;
    public int getCalories() {return calories;}


    private int capacity;
    
    private int stock = 1;
    public void decreaseStock() {stock--; System.out.println(stock);}
    
    public Item(String name, double price, int calories) {
    
        this.name = name;
        this.price = price;
        this.calories = calories;
    
    }
    
    
    public boolean isAvailable() {

        if(stock <= 0) return false;
        else return true;

    }

}

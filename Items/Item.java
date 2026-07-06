package Items;

import VM.Slot;

public class Item {
    

    private String name;
    private float price;
    private float calories;
    private int capacity;
    private int stock;


    public Item(String name, float price, float calories, int capacity) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.capacity = capacity;
        this.stock = this.capacity;
    }


    public String getName() {return name;}
    public void setName(String name) {this.name = name;}


    public float getPrice() {return price;}
    public void setPrice(float price) {
        this.price = price;
    }


    public float getCalories() {return calories;}
    public void setCalories(float calories) {this.calories = calories;}




}

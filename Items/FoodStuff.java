package Items;

public final class FoodStuff {
 
    public static Item[] getFoodList(int type) {

        if(type == 1) return DRINKS;
        else return SNACKS;

    }

    public static final Item DRINKS[] = {
        
        new Item("Bottled Water", 32, 0),

        new Item("Coke", 55.50, 140),
        new Item("Root Beer", 67.75, 140),
        
        new Item("Black Coffee", 60, 2),
        new Item("Latte", 90, 150),
        
        new Item("Apple Juice", 44, 110),
        new Item("Orange Juice", 40.50, 120),
        
        new Item("Iced Tea", 70.25, 120),
        new Item("Milk Tea", 120, 160)

    };


    public static final Item SNACKS[] = {

        new Item("Bread Loaf", 44, 80),
        new Item("Toasted Bread", 50, 90),

        new Item("Egg Sandwich", 80, 485),
        new Item("Peanut Butter Sandwich", 80, 342),

        new Item("Ham & Cheese Sandwich", 225.50, 352),
        new Item("Grilled Cheese Sandwich", 250, 291),

        new Item("Burger", 110, 344),
        new Item("Tuna Sandwich", 125.75, 350),
        new Item("Chicken Wrap", 173.64, 344)

    };


}

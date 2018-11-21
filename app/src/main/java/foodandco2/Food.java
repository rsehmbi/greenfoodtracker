package foodandco2;

/*This is a class for simple abstraction of Food store name of the food
 and its Carbon emission per kilogram */
public class Food {
    private String foodName;
    private double carbonPerKg;

    public Food(String foodName, double carbonPerKg) {
        this.foodName = foodName;
        this.carbonPerKg = carbonPerKg;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getCarbonPerKg() {
        return carbonPerKg;
    }
}

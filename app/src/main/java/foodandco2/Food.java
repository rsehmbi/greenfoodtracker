package foodandco2;


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

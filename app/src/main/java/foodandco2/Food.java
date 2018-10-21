package foodandco2;


public class Food {
    private String foodName;
    private double carbonPerKg;
    private int numberOfConsumptionPerWeek;

    public void setNumberOfConsumptionPerWeek(int consumption) {
        this.numberOfConsumptionPerWeek = consumption;
    }

    public Food(String foodName, double carbonPerKg, int numberOfConsumptionPerWeek) {
        this.foodName = foodName;
        this.carbonPerKg = carbonPerKg;
        this.numberOfConsumptionPerWeek = numberOfConsumptionPerWeek;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getCarbonPerKg() {
        return carbonPerKg;
    }

    public int getNumberOfConsumptionPerWeek() {
        return numberOfConsumptionPerWeek;
    }

    public String getFoodInFoInString() {
        String foodInformation = foodName + ", " + carbonPerKg + ", " + numberOfConsumptionPerWeek;
        return foodInformation;
    }
}

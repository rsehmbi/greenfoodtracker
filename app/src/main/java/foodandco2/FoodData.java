package foodandco2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodData {
    private final String csvFilePath = "foodtable.csv";
    private List<Food> foodList = new ArrayList<>();

    public FoodData() throws IOException {
        saveCsvToList();
    }

    public void saveCsvToList() throws IOException {
        File csvFile = new File(csvFilePath);
        Scanner scanner = new Scanner(csvFile);

        while(scanner.hasNextLine()) {
            String[] currentLine = scanner.nextLine().split(", ");
            String foodName = currentLine[0];
            double carbonPerKg = Double.valueOf(currentLine[1]);
            int numberOfConsumptionPerWeek = Integer.valueOf(currentLine[2]);

            Food food = new Food(foodName, carbonPerKg, numberOfConsumptionPerWeek);
            foodList.add(food);
        }
    }

    public void upDateCsv() throws IOException {
        FileWriter fileWriter = new FileWriter(csvFilePath, false);
        for(Food food: foodList) {
            fileWriter.write(food.getFoodInFoInString());
            fileWriter.write(System.lineSeparator());
        }
    }

    public Food getCarbonPerKgByFoodName(String foodName) {
        int indexOfFoodList = foodList.indexOf(foodName);
        Food pickedFood = foodList.get(indexOfFoodList);
        return pickedFood;
    }

    public List<Food> getFoodList() {
        return foodList;
    }
}

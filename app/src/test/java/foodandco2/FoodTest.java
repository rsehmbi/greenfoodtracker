package foodandco2;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodTest {
    private Food testFood;

    @Before
    public void testFoodInitialize() {
        String foodName = "Pasta";
        double carbonPerKg =  11.2;
        testFood = new Food(foodName, carbonPerKg);
    }

    @Test
    public void testGetFoodName() {
        String expectedFoodName = "Pasta";
        String actualFoodName = testFood.getFoodName();
        assertEquals(expectedFoodName, actualFoodName);
    }

    @Test
    public void testGetCarbonPerKg() {
        double expectedCarbonPerKg = 11.2;
        double actualCarbonPerKg = testFood.getCarbonPerKg();
        assertEquals(expectedCarbonPerKg, actualCarbonPerKg, 0.1);
    }
}
package foodandco2;

import android.content.Context;

import com.t.teamten.greenfoodtracker.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 27)
public class FoodDataTest {
    FoodData foodDataTest;

    @Before
    public void initializeFoodDataTest() throws IOException {
        Context context = RuntimeEnvironment.application;
        foodDataTest = new FoodData(context);
    }

    @Test
    public void testGetFoodList() {
        List<Food> expectedFoodList = new ArrayList<>();
        expectedFoodList = foodDataTest.getFoodList();
        Food actualFood = new Food("Lamb", 39.2);
        Food expectedFood = expectedFoodList.get(0);
        assertEquals(actualFood.getFoodName(), expectedFood.getFoodName());
        assertEquals(actualFood.getCarbonPerKg(), expectedFood.getCarbonPerKg(), 0.01);
    }

    @Test(expected = IOException.class)
    public void testThrowIoException() throws IOException {
        throw new IOException();
    }
}
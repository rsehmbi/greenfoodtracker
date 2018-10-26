package foodandco2;

import android.content.Context;
import android.content.res.Resources;

import com.t.teamten.greenfoodtracker.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*This is a class that can access the csv foodtable located the raw folder. and this class
* can only read the file and stores them into list<Food>*/

public class FoodData {
    private Context context;
    //private final String csvFileName = "foodtable.csv";
    private List<Food> foodList = new ArrayList<>();

    public FoodData(Context context) throws IOException {
        this.context = context;
        saveCsvFileToList();
    }

    public void saveCsvFileToList() throws IOException {
        Resources resources = context.getResources();
        InputStream csvFile = resources.openRawResource(R.raw.foodtable);
        BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile));

        String currentline;
        while((currentline = reader.readLine()) != null) {
            String[] currentLineSplitByComma = currentline.split(", ");
            String foodName = currentLineSplitByComma[0];
            double carbonPerKg = Double.valueOf(currentLineSplitByComma[1]);

            Food food = new Food(foodName, carbonPerKg);
            foodList.add(food);
        }

        csvFile.close();
    }

    public List<Food> getFoodList() {
        return foodList;
    }
}

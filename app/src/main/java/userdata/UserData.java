package userdata;

import android.content.Context;
import android.util.Pair;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    private final String CSVFILNAME = "userdiettable.csv";
    private Context context;
    List<Pair<String, Integer>> userList;

    public UserData(Context context) {
        this.context = context;
        userList = new ArrayList<>();
    }

    public void overWriteCsvFile() throws IOException {
        FileOutputStream csvFile = context.openFileOutput(CSVFILNAME, context.MODE_PRIVATE);
        if(!userList.isEmpty()) {
            for(Pair<String, Integer> pair: userList) {
                String newLine = pair.first + ", " + pair.second + "\n";
                csvFile.write(newLine.getBytes());
            }
        }
    }

    public void saveCsvFileToList() throws IOException {
        if(!userList.isEmpty()) {
            userList.clear();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput(CSVFILNAME)));
        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            String[] currentLineSplitByComma = currentLine.split(", ");
            String foodName = currentLineSplitByComma[0];
            int numberOfConsumptionPerWeek = Integer.valueOf(currentLineSplitByComma[1]);
            userList.add(new Pair<String, Integer>(foodName, numberOfConsumptionPerWeek));
        }
    }

    public void addUserList(String foodName, int numberOfConsumptionPerWeek) {
        Pair<String, Integer> pair = new Pair<>(foodName, numberOfConsumptionPerWeek);
        userList.add(pair);
    }

    public List<Pair<String, Integer>> getUserList() {
        return userList;
    }
}

package userdata;

import android.content.Context;
import android.os.Environment;
import android.util.Pair;

import com.t.teamten.greenfoodtracker.BuildConfig;
//--
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
public class UserDataTest {
    private Context context;
    private UserData testUserData;

    @Before
    public void setUp() {
        context = RuntimeEnvironment.application;
        testUserData = new UserData(context);
    }

    @Test
    public void TestUserCsvData() throws IOException {
        testUserData.addUserList("Beef", 5);
        testUserData.addUserList("Pork", 4);
        testUserData.addUserList("Chicken", 2);

        testUserData.overWriteCsvFile();
        testUserData.saveCsvFileToList();

        List<Pair<String, Integer>> expectedUserList = new ArrayList<>();
        expectedUserList.add(new Pair<String, Integer>("Beef", 5));
        expectedUserList.add(new Pair<String, Integer>("Pork", 4));
        expectedUserList.add(new Pair<String, Integer>("Chicken", 2));

        List<Pair<String, Integer>> actualUserList = testUserData.getUserList();
        for(int i = 0; i < actualUserList.size(); i++) {
            assertEquals(expectedUserList.get(i), actualUserList.get(i));
        }
    }
}
package firebaseuser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private String userId;
    private String email;
    private String password;
    private String gender;
    private String age;
    private String city;
    private String firstName;
    private String lastName;
    private String pledge;
    private String profileIcon;

    private User testUser;

    @Before
    public void setUp() {
        userId = "qaz123edc";
        email = "chari@hotmail.com";
        password = "chairdesktable";
        gender = "male";
        age = "25";
        city = "surrey";
        firstName = "John";
        lastName = "Woo";
        pledge = "143";
        profileIcon = "dog";

        testUser = new User(userId, email, password, gender, age, city, firstName, lastName, pledge, profileIcon);
    }

    @Test
    public void getFirstNameWithLastNameInitial() {
        String expectedName = "John.W";
        String actualName = testUser.getFirstNameWithLastNameInitial();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void setUserId() {
        testUser.setUserId("qazzxcedcqwe");

        String expectedUserId = "qazzxcedcqwe";
        String actualUserId = testUser.getUserId();

        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    public void setEmail() {
        testUser.setEmail("abc@qwe.de");
        String expectedEmail = "abc@qwe.de";
        String actualEmail = testUser.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void setPassword() {
        testUser.setPassword("123456");
        String expectedPassword = "123456";
        String actualPassword = testUser.getPassword();
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void setGender() {
        testUser.setGender("male");
        String expectedGender = "male";
        String actualGender = testUser.getGender();
        assertEquals(expectedGender, actualGender);
    }

    @Test
    public void setAge() {
        testUser.setAge("11");
        String expectedAge = "11";
        String actualAge = testUser.getAge();
        assertEquals(expectedAge, actualAge);
    }

    @Test
    public void setCity() {
        testUser.setCity("Burnaby");
        String expectedCity = "Burnaby";
        String actualCity = testUser.getCity();
        assertEquals(expectedCity, actualCity);
    }

    @Test
    public void setFirstName() {
        testUser.setFirstName("test");
        String expectedFirstName = "test";
        String actualFirstName = testUser.getFirstName();
        assertEquals(expectedFirstName, actualFirstName);
    }

    @Test
    public void setLastName() {
        testUser.setLastName("test");
        String expectedLastName = "test";
        String actualLastName = testUser.getLastName();
        assertEquals(expectedLastName, actualLastName);
    }

    @Test
    public void setPledge() {
        testUser.setPledge("100");
        String expectedPledge = "100";
        String actualPledge = testUser.getPledge();
        assertEquals(expectedPledge, actualPledge);
    }

    @Test
    public void setProfileIcon() {
        testUser.setProfileIcon("dog");
        String expectedPI = "dog";
        String actualPI = testUser.getProfileIcon();
        assertEquals(expectedPI, actualPI);
    }
}
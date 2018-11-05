package userdata;

public class FireBaseUserData
{
    public String first_name;
    public String last_name;
    public String city;
    public int age;
    public String sex;
    public double pledge_amount;

    public FireBaseUserData()
    {
        first_name = " ";
        last_name = " ";
        city = " ";
        pledge_amount = 0;
        sex = " ";
        age = 0;
    }
    public FireBaseUserData (String full_name, String city, double pledge_amount,int age, String sex)
    {
        this.first_name = full_name;
        this.city = city;
        this.pledge_amount = pledge_amount;
        this.age = age;
        this.sex = sex;
    }

    public void set_first_name(String first_name)
    {
        this.first_name = first_name;
    }

    public void set_city(String city)
    {
        this.city = city;
    }

    public void set_pledge_amount(double pledge_amount)
    {
        this.pledge_amount = pledge_amount;
    }

    public void set_sex(String sex)
    {
        this.sex = sex;
    }
    public void set_age(int age)
    {
        this.age = age;
    }
    public void set_last_name(String last_name)
    {
        this.last_name = last_name;
    }
}

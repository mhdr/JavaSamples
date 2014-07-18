package nasimeshomal;

import java.io.Serializable;

/**
 * Created by Mahmood on 7/18/2014.
 */
public class Person implements Serializable{
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Person(String firstName)
    {
        this.firstName=firstName;
    }
}

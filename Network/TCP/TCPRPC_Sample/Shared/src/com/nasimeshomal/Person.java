package com.nasimeshomal;

import java.io.Serializable;

/**
 * Created by Mahmood on 9/12/2014.
 */
public class Person implements Serializable{
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person()
    {

    }

    public Person(String firstName,String lastName)
    {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }
}

package com.nasimeshomal;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    public String toJSON()
    {
        JSONObject jsonObject=new JSONObject();

        jsonObject.put("firstName",this.firstName);
        jsonObject.put("lastName",this.lastName);

        return jsonObject.toString();
    }

    public static Person fromJSON(String jsonStr) throws ParseException {
        JSONParser parser=new JSONParser();
        JSONObject jsonObject= (JSONObject) parser.parse(jsonStr);

        String firstName= (String) jsonObject.get("firstName");
        String lastName= (String) jsonObject.get("lastName");

        Person newPerson=new Person(firstName,lastName);

        return newPerson;
    }
}

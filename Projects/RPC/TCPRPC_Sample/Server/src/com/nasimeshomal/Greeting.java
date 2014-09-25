package com.nasimeshomal;

/**
 * Created by Mahmood on 9/12/2014.
 */
public class Greeting {
    public static Object sayHello(Object obj) {
        Person person= (Person) obj;
        return String.format("Hello %s %s",person.getFirstName(),person.getLastName());
    }
}

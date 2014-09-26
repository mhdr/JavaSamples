package com.nasimeshomal;

import org.json.simple.parser.ParseException;

/**
 * Created by Mahmood on 9/12/2014.
 */
public class Greeting {
    public static Object sayHello(Object obj) throws ParseException {
        Person person= Person.fromJSON((String) obj);
        return String.format("Hello %s %s",person.getFirstName(),person.getLastName());
    }
}

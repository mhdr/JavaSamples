package com.nasimeshomal;

import java.io.UnsupportedEncodingException;

/**
 * Created by Mahmood on 9/8/2014.
 */
public class Greeting {
    public static String sayHello(Object obj) throws UnsupportedEncodingException {
        String name= (String) obj;
        return String.format("Hello %s",name);
    }


}

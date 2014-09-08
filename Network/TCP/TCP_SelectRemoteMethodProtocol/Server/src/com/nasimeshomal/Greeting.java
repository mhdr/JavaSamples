package com.nasimeshomal;

import java.io.UnsupportedEncodingException;

/**
 * Created by Mahmood on 9/8/2014.
 */
public class Greeting {
    public static String sayHello(byte[] data) throws UnsupportedEncodingException {
        String name=new String(data,"UTF-8");
        return String.format("Hello %s",name);
    }
}

package com.nasimeshomal;

import java.io.UnsupportedEncodingException;

/**
 * Created by Mahmood on 9/8/2014.
 */
public class Greeting {
    public static byte[] sayHello(byte[] data) throws UnsupportedEncodingException {
        String name=new String(data,"UTF-8");
        String returnValue= String.format("Hello %s",name);
        byte[] returnValueByte=returnValue.getBytes("UTF-8");
        return returnValueByte;
    }
}

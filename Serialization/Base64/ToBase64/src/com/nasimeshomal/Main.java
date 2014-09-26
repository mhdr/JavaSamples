package com.nasimeshomal;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
	    String msg="Hello World";
        Base64.Encoder encoder= Base64.getEncoder();

        String base64Msg=encoder.encodeToString(msg.getBytes("UTF-8"));

        System.out.println(base64Msg);
    }
}

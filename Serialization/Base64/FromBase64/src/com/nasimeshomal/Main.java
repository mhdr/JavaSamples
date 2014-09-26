package com.nasimeshomal;

import java.util.Base64;

public class Main {

    public static void main(String[] args) {
	    String base64Msg="SGVsbG8gV29ybGQ=";
        Base64.Decoder decoder=Base64.getDecoder();

        String msg=new String(decoder.decode(base64Msg));

        System.out.println(msg);
    }
}

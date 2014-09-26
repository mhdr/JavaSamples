package com.nasimeshomal;

import java.io.*;

/**
 * Created by Mahmood on 9/26/2014.
 */
public class PayloadBinary extends Payload{


    public PayloadBinary(String methodName, Object parameter) {
        super(methodName, parameter);
    }

    public PayloadBinary(Object returnValue) {
        super(returnValue);
    }

    @Override
    public byte[] Serialize() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);
        return byteArrayOutputStream.toByteArray();
    }
}

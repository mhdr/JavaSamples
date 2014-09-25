package com.nasimeshomal;

import java.io.*;

/**
 * Created by Mahmood on 9/12/2014.
 */
public class Payload implements Serializable{
    private String methodName;
    private Object parameter;
    private Object returnValue;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public Payload(String methodName, Object parameter)
    {
        this.methodName=methodName;
        this.parameter=parameter;
    }

    public Payload(Object returnValue)
    {
        this.returnValue=returnValue;
    }

    public byte[] Serialize() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);
        return byteArrayOutputStream.toByteArray();
    }

    public static Payload Deserialize(byte[] payload) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(payload);
        ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
        return (Payload) objectInputStream.readObject();
    }
}

package com.nasimeshomal;

/**
 * Created by Mahmood on 9/11/2014.
 */
public class SocketData {
    private String methodName;
    private byte[] data;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public SocketData(String methodName,byte[] data)
    {
        this.setMethodName(methodName);
        this.setData(data);
    }
}

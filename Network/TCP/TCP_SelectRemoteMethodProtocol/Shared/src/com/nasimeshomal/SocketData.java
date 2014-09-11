package com.nasimeshomal;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

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

    public SocketData()
    {
        this.methodName="";
    }

    public SocketData(String methodName,byte[] data)
    {
        this.methodName=methodName;
        this.data=data;
    }

    public String getDataString() throws UnsupportedEncodingException {
        return new String(this.data);
    }

    public String getDataString(Charset charset) throws UnsupportedEncodingException {
        return new String(this.data,charset);
    }

    public void setDataString(String dataStr)
    {
        this.setData(dataStr.getBytes());
    }

    public void setDataString(String dataStr,Charset charset) throws UnsupportedEncodingException {
        this.setData(dataStr.getBytes(charset));
    }

    public ByteBuffer getDataByteBuffer()
    {
        return ByteBuffer.wrap(this.data);
    }

    public Object getDataObject() throws ClassNotFoundException, IOException {
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(this.data);
        ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }

    public void setDataObject(Object objData) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(objData);
        this.data=byteArrayOutputStream.toByteArray();
    }
}

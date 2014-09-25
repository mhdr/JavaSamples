package com.nasimeshomal;

import java.io.*;
import java.net.InetAddress;
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

    public byte[] getData() {
        return data;
    }

    public SocketData()
    {
        this.setMethodName("");
    }

    public SocketData(String methodName,byte[] data) throws UnsupportedEncodingException {
        this.methodName=methodName;
        this.data=data;
    }

    public String getDataString() throws UnsupportedEncodingException {
        return new String(this.getData());
    }

    public String getDataString(Charset charset) throws UnsupportedEncodingException {
        return new String(this.getData(),charset);
    }

    public String getDataString(String charsetName) throws UnsupportedEncodingException {
        return new String(this.getData(),charsetName);
    }

    public void setDataString(String dataStr)
    {
        this.setData(dataStr.getBytes());
    }

    public void setDataString(String dataStr,Charset charset) throws UnsupportedEncodingException {
        this.setData(dataStr.getBytes(charset));
    }

    public void setDataString(String dataStr,String charsetName) throws UnsupportedEncodingException {
        this.setData(dataStr.getBytes(charsetName));
    }

    public ByteBuffer getDataByteBuffer()
    {
        return ByteBuffer.wrap(this.getData());
    }

    public Object getDataObject() throws ClassNotFoundException, IOException {
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(this.getData());
        ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }

    public void setDataObject(Object objData) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(objData);
        this.setData(byteArrayOutputStream.toByteArray());
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}

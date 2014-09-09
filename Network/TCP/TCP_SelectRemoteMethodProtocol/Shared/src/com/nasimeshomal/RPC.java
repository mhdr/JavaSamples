package com.nasimeshomal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by Mahmood on 9/9/2014.
 */
public class RPC {
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

    public RPC(String methodName,byte[] data)
    {
        this.setMethodName(methodName);
        this.setData(data);
    }

    public byte[] Serialize() throws IOException {
        byte[] msgByte=this.getData();
        int sizeOfMsg=msgByte.length;
        byte[] sizeOfMsgByte= ByteBuffer.allocate(4).putInt(sizeOfMsg).array();
        String methodNameToSend=this.getMethodName();
        byte[] methodNameByte=methodNameToSend.getBytes("UTF-8");
        int sizeOfMethodName=methodNameByte.length;
        byte[] sizeOfMethodNameByte=ByteBuffer.allocate(4).putInt(sizeOfMethodName).array();

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byteArrayOutputStream.write(sizeOfMethodNameByte);
        byteArrayOutputStream.write(methodNameByte);
        byteArrayOutputStream.write(sizeOfMsgByte);
        byteArrayOutputStream.write(msgByte);
        byte[] dataToSend=byteArrayOutputStream.toByteArray();

        return dataToSend;
    }

    public static RPC Deserialize(InputStream inputStream) throws IOException {
        byte[] sizeOfMethodNameByte=new byte[4];
        inputStream.read(sizeOfMethodNameByte);
        int sizeOfMethodName=ByteBuffer.wrap(sizeOfMethodNameByte).getInt();
        byte[] methodNameByte=new byte[sizeOfMethodName];
        inputStream.read(methodNameByte);
        String methodName=new String(methodNameByte,"UTF-8");

        byte[] sizeofMsgByte=new byte[4];
        inputStream.read(sizeofMsgByte);
        int sizeOfMsg= ByteBuffer.wrap(sizeofMsgByte).getInt();
        byte[] data=new byte[sizeOfMsg];

        return new RPC(methodName,data);
    }
}

package com.nasimeshomal;

import com.nasimeshomal.RPC;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) throws IOException {
        //String serverAddress="192.168.241.131";
        String serverAddress="127.0.0.1";
        Socket socket=new Socket(serverAddress,9001);

        InputStream inputStream=socket.getInputStream();
        OutputStream outputStream=socket.getOutputStream();

        String msg="Mahmood";

        byte[] msgByte=msg.getBytes("UTF-8");
        int sizeOfMsg=msgByte.length;
        byte[] sizeOfMsgByte= ByteBuffer.allocate(4).putInt(sizeOfMsg).array();
        String methodName="sayHello";
        byte[] methodNameByte=methodName.getBytes("UTF-8");
        int sizeOfMethodName=methodNameByte.length;
        byte[] sizeOfMethodNameByte=ByteBuffer.allocate(4).putInt(sizeOfMethodName).array();

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byteArrayOutputStream.write(sizeOfMethodNameByte);
        byteArrayOutputStream.write(methodNameByte);
        byteArrayOutputStream.write(sizeOfMsgByte);
        byteArrayOutputStream.write(msgByte);
        byte[] data=byteArrayOutputStream.toByteArray();

        outputStream.write(data);
        outputStream.flush();

        RPC rpc= RPC.Deserialize(inputStream);

        String returnedFromServer=new String(rpc.getData(),"UTF-8");

        outputStream.close();
        inputStream.close();
        socket.close();

        System.out.println(returnedFromServer);
    }
}

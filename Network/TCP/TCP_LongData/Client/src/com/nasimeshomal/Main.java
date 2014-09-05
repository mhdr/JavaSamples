package com.nasimeshomal;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("127.0.0.1",9001);

        InputStream inputStream=socket.getInputStream();
        OutputStream outputStream=socket.getOutputStream();


        String initialMsg="Hello World\n";
        String msg="";

        for (int i=0;i<100;i++)
        {
            msg +=initialMsg;
        }

        byte[] msgByte=msg.getBytes("UTF-8");
        int sizeOfMsg=msgByte.length;
        byte[] sizeByte= ByteBuffer.allocate(4).putInt(sizeOfMsg).array();

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byteArrayOutputStream.write(sizeByte);
        byteArrayOutputStream.write(msgByte);
        byte[] data=byteArrayOutputStream.toByteArray();

        outputStream.write(data);
        outputStream.flush();
        outputStream.close();



        socket.close();
    }
}

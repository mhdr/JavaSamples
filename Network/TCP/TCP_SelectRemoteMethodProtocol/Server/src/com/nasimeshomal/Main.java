package com.nasimeshomal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(9001);

        while (true)
        {
            Socket newSocket= serverSocket.accept();
            InputStream inputStream=newSocket.getInputStream();
            OutputStream outputStream=newSocket.getOutputStream();

            byte[] sizeByte=new byte[4];
            inputStream.read(sizeByte);
            int size= ByteBuffer.wrap(sizeByte).getInt();
            byte[] data=new byte[size];
            inputStream.read(data);

            String msg=new String(data,"UTF-8");

            System.out.println(msg);
        }
    }
}

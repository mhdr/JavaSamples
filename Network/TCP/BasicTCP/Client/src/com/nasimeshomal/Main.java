package com.nasimeshomal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws IOException {
        InetAddress address= InetAddress.getByName("127.0.0.1");
        int port=9001;
        Socket socket=new Socket(address,port);

        String name="Mahmood";
        byte[] data=name.getBytes("UTF-8");

        InputStream inputStream=socket.getInputStream();
        OutputStream outputStream=socket.getOutputStream();

        outputStream.write(data);
        outputStream.flush();

        byte[] recvData=new byte[1024];
        inputStream.read(recvData,0,recvData.length);

        String recvMsg=new String(recvData,"UTF-8");
        System.out.println(recvMsg);

        inputStream.close();
        outputStream.close();
        socket.close();
    }
}

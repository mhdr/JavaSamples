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
        String methodName="sayHello";

        RPC rpc1=new RPC(methodName,msgByte);
        byte[] data=rpc1.Serialize();
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

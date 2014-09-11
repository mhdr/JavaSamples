package com.nasimeshomal;

import com.nasimeshomal.RPC;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //String serverAddress="192.168.241.131";
        String serverAddress="127.0.0.1";
        Socket socket=new Socket(serverAddress,9001);

        String msg="Mahmood";

        byte[] msgByte=msg.getBytes("UTF-8");
        String methodName="sayHello";

        //RPC rpc=new RPC(socket,new SocketData(methodName,msgByte));
        //rpc.Send();
        //rpc.CloseSocket();

        //socket=new Socket(serverAddress,9001);
        RPC rpc=new RPC(socket);
        SocketData socketData=new SocketData();
        socketData.setDataObject(msg);
        socketData.setMethodName(methodName);
        rpc.Send(socketData);
        socketData=rpc.Receive();

        System.out.println(socketData.getDataObject());
        rpc.CloseSocket();
    }
}

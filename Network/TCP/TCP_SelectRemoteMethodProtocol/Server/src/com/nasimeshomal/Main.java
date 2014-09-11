package com.nasimeshomal;

import com.nasimeshomal.RPC;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        ServerSocket serverSocket=new ServerSocket(9001);

        while (true)
        {
            Socket newSocket= serverSocket.accept();
            RPC rpc=new RPC(newSocket);
            SocketData socketData= rpc.Receive();

            Class<Greeting> reflClass=Greeting.class;
            Method method=reflClass.getMethod(socketData.getMethodName(),Object.class);
            Object param=socketData.getDataObject();
            Object returnValue= method.invoke(null,param);

            socketData=new SocketData();
            socketData.setDataObject(returnValue);
            rpc.Send(socketData);

            System.out.println(param);
            //System.out.println(socketData.getDataString("UTF-8"));
            rpc.CloseSocket();
        }
    }
}

package com.nasimeshomal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int port=9001;
        ServerSocket serverSocket=new ServerSocket(port);

        while (true)
        {
            Socket socket=serverSocket.accept();

            TcpRPC tcpRPC=new TcpRPC(socket);
            TcpPayload payload= tcpRPC.Receive2();

            System.out.println(payload.getMethodName());
            System.out.println(payload.getParameter());

            MethodInvoker methodInvoker=new MethodInvoker(Greeting.class,payload.getMethodName());
            Object returnValue= methodInvoker.Invoke(payload.getParameter());

            payload=new TcpPayload(returnValue);
            tcpRPC.Send2(payload);
        }
    }
}

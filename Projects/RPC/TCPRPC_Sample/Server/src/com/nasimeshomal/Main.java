package com.nasimeshomal;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {
        int port=9001;
        ServerSocket serverSocket=new ServerSocket(port);

        while (true)
        {
            Socket socket=serverSocket.accept();

            TcpRPC tcpRPC=new TcpRPC(socket);
            PayloadJSON payload= tcpRPC.ReceiveJSON();

            System.out.println(payload.getMethodName());
            System.out.println(payload.getParameter());

            MethodInvoker methodInvoker=new MethodInvoker(Greeting.class,payload.getMethodName());
            Object returnValue= methodInvoker.Invoke(payload.getParameter());

            payload=new PayloadJSON(returnValue);
            tcpRPC.Send(payload);
        }
    }
}

package com.nasimeshomal;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {
        int port=9001;
        DatagramSocket socket=new DatagramSocket(port);

        while (true)
        {
            //Socket socket=serverSocket.accept();

            //TcpRPC tcpRPC=new TcpRPC(socket);
            UdpRPC udpRPC=new UdpRPC(socket);

            UdpPayloadJSON payload= udpRPC.ReceiveJSON();

            System.out.println(payload.getMethodName());
            System.out.println(payload.getParameter());

            MethodInvoker methodInvoker=new MethodInvoker(Greeting.class,payload.getMethodName());
            Object returnValue= methodInvoker.Invoke(payload.getParameter());

            InetAddress address=payload.getDatagramPacket().getAddress();
            int recvPort=payload.getDatagramPacket().getPort();

            payload=new UdpPayloadJSON(returnValue);
            udpRPC.Send(payload,address,recvPort);
        }
    }
}

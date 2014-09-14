package com.nasimeshomal;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        InetAddress address=InetAddress.getByName("127.0.0.1");
        int port=9001;
        Socket socket=new Socket(address,port);
        Person person=new Person("Mahmood","Ramzani");
	    Payload payload=new Payload("sayHello",person);
        TcpRPC tcpRPC=new TcpRPC(socket);
        tcpRPC.Send(payload);

        payload= tcpRPC.Receive();
        System.out.println(payload.getReturnValue());
    }
}

package com.nasimeshomal;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {

        InetAddress address=InetAddress.getByName("127.0.0.1");
        //InetAddress address=InetAddress.getByName("192.168.241.131");
        int port=9001;
        Socket socket=new Socket(address,port);
        Person person=new Person("Mahmood","Ramzani");
	    PayloadJSON payload=new PayloadJSON("sayHello",person.toJSON());
        TcpRPC tcpRPC=new TcpRPC(socket);
        tcpRPC.Send(payload);

        payload= tcpRPC.ReceiveJSON();
        System.out.println(payload.getReturnValue());
    }
}

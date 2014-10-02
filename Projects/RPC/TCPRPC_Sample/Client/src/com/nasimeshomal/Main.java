package com.nasimeshomal;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {

        InetAddress address=InetAddress.getByName("127.0.0.1");
        //InetAddress address=InetAddress.getByName("192.168.241.131");
        int port=9001;

        String longName="";

        for (int i=0;i<2000;i++)
        {
            longName +="Ramzani\n";
        }

        Person person=new Person("Mahmood",longName);
	    PayloadJSON payload=new PayloadJSON("sayHello",person.toJSON());

        DatagramSocket socket=new DatagramSocket();
        UdpRPC udpRPC=new UdpRPC(socket);
        udpRPC.Send(payload,address,port);
    }
}

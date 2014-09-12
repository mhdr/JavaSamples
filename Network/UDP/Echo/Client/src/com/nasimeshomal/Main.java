package com.nasimeshomal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InetAddress address= InetAddress.getByName("127.0.0.1");
        int port=9002;

        byte[] outByte=new byte[1024];
        byte[] inByte=new byte[1024];

        DatagramSocket socket=new DatagramSocket();

        String msg="Mahmood";

        outByte=msg.getBytes("UTF-8");
        DatagramPacket outPacket=new DatagramPacket(outByte,outByte.length,address,port);
        socket.send(outPacket);

        DatagramPacket inPacket=new DatagramPacket(inByte,inByte.length);
        socket.receive(inPacket);

        String data=new String(inByte,"UTF-8");

        System.out.println(data);

        socket.close();
    }
}

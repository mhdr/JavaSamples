package com.nasimeshomal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InetAddress address=InetAddress.getByName("127.0.0.1");
        int port=9002;
        byte[] msgByte=new byte[1024];

        DatagramSocket socket=new DatagramSocket();

        String msg="Mahmood";

        msgByte=msg.getBytes("UTF-8");
        DatagramPacket outPacket=new DatagramPacket(msgByte,msgByte.length,address,port);
        socket.send(outPacket);
        socket.close();
    }
}

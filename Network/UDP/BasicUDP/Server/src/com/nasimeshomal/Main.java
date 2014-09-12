package com.nasimeshomal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Main {

    public static void main(String[] args) throws IOException {

        DatagramSocket socket=new DatagramSocket(9002);

        while (true)
        {
            byte[] dataRecv=new byte[1024];
            DatagramPacket inPacket=new DatagramPacket(dataRecv,dataRecv.length);
            socket.receive(inPacket);
            String data=new String(dataRecv,"UTF-8");
            System.out.println(data);
        }
    }
}

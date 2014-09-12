package com.nasimeshomal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket=new DatagramSocket(9002);

        while (true)
        {
            byte[] inByte=new byte[1024];
            byte[] outByte=new byte[1024];

            DatagramPacket inPacket=new DatagramPacket(inByte,inByte.length);
            socket.receive(inPacket);
            String data=new String(inByte,"UTF-8");
            System.out.println(data);

            int clientPort=inPacket.getPort();
            InetAddress clientAddress=inPacket.getAddress();

            String returnData=String.format("Hello %s",data);
            outByte=returnData.getBytes("UTF-8");

            DatagramPacket outPacket=new DatagramPacket(outByte,outByte.length,clientAddress,clientPort);
            socket.send(outPacket);
        }
    }
}

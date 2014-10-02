package com.nasimeshomal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) throws IOException {
        int port=9010;
        InetAddress multicastAddress=InetAddress.getByName("224.1.0.0");
        MulticastSocket socket=new MulticastSocket(port);
        socket.joinGroup(multicastAddress);

        byte[] dataRecv=new byte[4];

        while (true)
        {
            DatagramPacket packet=new DatagramPacket(dataRecv,dataRecv.length);
            socket.receive(packet);

            int counter= ByteBuffer.wrap(packet.getData()).getInt();

            String msg=String.format("%d",counter);
            System.out.println(msg);
        }

        //socket.leaveGroup(multicastAddress);
    }
}
